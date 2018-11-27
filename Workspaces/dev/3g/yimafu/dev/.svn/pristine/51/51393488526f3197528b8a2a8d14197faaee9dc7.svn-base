package com.yeepay.g3.ymf.boss.controller.appversion;/**
 * Created by jiwei.lv on 17/1/5.
 */

import com.yeepay.g3.facade.laike.dto.AppVersionResponse;
import com.yeepay.g3.facade.laike.dto.boss.CreateAppVersionRequest;
import com.yeepay.g3.facade.laike.dto.boss.UpdateAppVersionRequest;
import com.yeepay.g3.facade.laike.enumtype.AppRoleEnum;
import com.yeepay.g3.facade.laike.enumtype.VersionPlatform;
import com.yeepay.g3.facade.laike.facade.AppVersionFacade;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.ymf.boss.controller.ValidateController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author jiwei.lv
 * @create 2017-01-17/1/5
 */
@Controller
@RequestMapping("/app")
public class AppVersionController extends ValidateController {

    private static final Logger log = LoggerFactory.getLogger(AppVersionController.class);

    @RequestMapping("/query")
    public String orderQuery() {
        return "appversion/appVersionQuery";
    }

    @RequestMapping("/toAdd")
    public String toAddCustomer() {
        return "appversion/add";
    }

    @RequestMapping("add")
    @ResponseBody
    public ResponseMessage add(String roleType,String platform,
                               String versionCode,
                               String description, HttpSession session) {
        log.info("roleType:"+roleType+" platform:"+platform+" versionCode:"+versionCode+" description:"+description);
        try {
            CreateAppVersionRequest createAppVersionRequest=new CreateAppVersionRequest();
            createAppVersionRequest.setDescription(description);
            createAppVersionRequest.setPlatform(VersionPlatform.valueOf(platform));
            createAppVersionRequest.setRoleType(AppRoleEnum.valueOf(roleType));
            createAppVersionRequest.setVersionCode(versionCode);
            String operator = getUser(session);
            createAppVersionRequest.setOperator(operator);
            AppVersionFacade appVersionFacade = RemoteServiceFactory.getService(AppVersionFacade.class);
            AppVersionResponse saveRep = appVersionFacade.save(createAppVersionRequest);
            String errorcode=saveRep.getErrCode();
            if(errorcode!=null){
                return ResponseMessage.error(saveRep.getErrMsg());
            }
            return ResponseMessage.ok();
        }catch (Exception e){
            log.error("增加版本发生异常",e);
            return ResponseMessage.error(e.toString());
        }
    }

    @RequestMapping("toModify")
    public String toModify(@RequestParam String id, Model model) {
        AppVersionFacade appVersionFacade = RemoteServiceFactory.getService(AppVersionFacade.class);
        AppVersionResponse appVersionResponse = appVersionFacade.findOne(id);
        if (null == appVersionResponse) {
            model.addAttribute("msg", "数据字典不存在");
            return "error";
        } else {

            model.addAttribute("app", appVersionResponse);
            model.addAttribute("appid", id);
            return "appversion/modify";
        }
    }

    @RequestMapping("modify")//produces = {"text/html;charset=UTF-8"}
    @ResponseBody
    public ResponseMessage modify(String appid, String forceUpdate, @RequestParam("uploadFile") MultipartFile file, String description,
                                  HttpSession session) {
        try {
            UpdateAppVersionRequest updateAppVersionRequest=new  UpdateAppVersionRequest();
            String operator = getUser(session);
            updateAppVersionRequest.setForceUpdate("1".equals(forceUpdate)?true:false);
            updateAppVersionRequest.setDescription(description);
            updateAppVersionRequest.setFile(file.getBytes());
            updateAppVersionRequest.setOperator(operator);
            updateAppVersionRequest.setId(appid);
            AppVersionFacade appVersionFacade = RemoteServiceFactory.getService(AppVersionFacade.class);
            AppVersionResponse updateRep = appVersionFacade.updateUrl(updateAppVersionRequest);
            String errorcode = updateRep.getErrCode();
            if(errorcode!=null){
                return ResponseMessage.error(updateRep.getErrMsg());
            }
            return ResponseMessage.ok();
        }catch (Exception e){
            log.error("修改失败",e);
            return ResponseMessage.error(e.toString());
        }
    }
}
