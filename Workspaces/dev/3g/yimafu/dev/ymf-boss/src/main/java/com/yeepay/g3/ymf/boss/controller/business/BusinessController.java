package com.yeepay.g3.ymf.boss.controller.business;

import com.yeepay.g3.core.ymf.entity.business.Business;
import com.yeepay.g3.core.ymf.entity.business.BusinessArgs;
import com.yeepay.g3.core.ymf.service.BusinessService;
import com.yeepay.g3.core.ymf.support.OperateEntity;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.enumtype.OperateType;
import com.yeepay.g3.utils.common.BeanUtils;
import com.yeepay.g3.ymf.boss.controller.ValidateController;
import com.yeepay.g3.ymf.boss.support.annotations.CommonArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * Created by fei.lu on 16/8/11.
 */
@Controller
@RequestMapping("business")
public class BusinessController extends ValidateController {

    @Autowired
    private BusinessService businessService;

    //查找所有的业务方
    @RequestMapping("loadBusinessCode")
    @ResponseBody
    public List<Business> findAllBusiness(){
        return businessService.findAllBusiness();
    }

    //业务方查询
    @RequestMapping("query")
    public String businessQuery() {
        return "business/businessQuery";
    }

    //业务方新增页面
    @RequestMapping("/toAdd")
    public String toAddBusiness(){
        return "/business/businessAdd";
    }

    //业务方新增
    @RequestMapping("/add")
    @ResponseBody
    public ResponseMessage add(@CommonArgs BusinessArgs businessArgs, HttpSession session){
        String user = this.getUser(session);
        Business business = new Business();
        BeanUtils.copyProperties(businessArgs,business);
        ResponseMessage resp = validate(businessArgs);
        if(resp.isOk()) {
            Business checkBusiness = businessService.getBusinessByCode(business.getBizCode());
            if (checkBusiness != null) {
                return ResponseMessage.error("业务方已存在!");
            } else {
                OperateEntity<Business> en = new OperateEntity<Business>(user,business, OperateType.ADD);
                int executeResult = businessService.saveBusiness(en);
                if (executeResult >= 0) {
                    return ResponseMessage.ok();
                } else {
                    return ResponseMessage.error("操作失败");
                }
            }
        }else{
            return resp;
        }

    }

    //业务方修改页面
    @RequestMapping("toModify")
    public ModelAndView toModifyBusiness(@RequestParam(value = "id") Long id){
        ModelAndView mv = new ModelAndView();
        Business business = businessService.getBusinessById(id);
        if(business==null){
            mv.addObject("msg","业务方不存在");
            mv.setViewName("error");
        }else {
            mv.addObject("business", business);
            mv.setViewName("business/businessModify");
        }
        return mv;

    }

    //业务方修改
    @RequestMapping("modify")
    @ResponseBody
    public ResponseMessage modifyBusiness(@CommonArgs BusinessArgs businessArgs,HttpSession session) {
        String user = this.getUser(session);
        Business business = new Business();
        BeanUtils.copyProperties(businessArgs,business);
        ResponseMessage resp = validate(businessArgs);
        if (resp.isOk()) {
            Business checkBusiness = businessService.getBusinessById(business.getId());
            if (checkBusiness == null) {
                return ResponseMessage.error("业务方不存在,不能修改");
            } else {
                OperateEntity<Business> en = new OperateEntity<Business>(user,business,OperateType.UPDATE);
                int executeResult= businessService.updateBusiness(en);
                if (executeResult >= 0) {
                    return ResponseMessage.ok();
                } else {
                    return ResponseMessage.error("操作失败");
                }
            }
        }else{
            return resp;
    }
    }
}
