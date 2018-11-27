package com.yeepay.g3.ymf.boss.controller.sales;

import com.alibaba.fastjson.JSON;
import com.yeepay.g3.core.ymf.entity.Group;
import com.yeepay.g3.core.ymf.entity.Sales;
import com.yeepay.g3.core.ymf.service.GroupService;
import com.yeepay.g3.core.ymf.service.SalesService;
import com.yeepay.g3.core.ymf.utils.web.POIUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.query.QueryParam;
import com.yeepay.g3.utils.query.QueryService;
import com.yeepay.g3.utils.query.impl.QueryServiceImpl;
import com.yeepay.g3.ymf.boss.controller.ValidateController;
import com.yeepay.g3.ymf.boss.controller.common.DownloadController;
import com.yibao.utils.DateUtils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import javax.servlet.http.HttpServletResponse;
/**
 * 一级商户管理
 * @author liuxinghua
 *
 */
@Controller
@RequestMapping("sales")
public class SalesController extends DownloadController{
	private static final Logger LOG = LoggerFactory.getLogger(SalesController.class);
	
    @Autowired
    private SalesService salesService;
    
    @Autowired
    private GroupService groupService;
    
    @Autowired
    protected QueryService ymfQuery2Service;
    /**
     * 保险代理人查询
     * @param model
     * @return
     */
    @RequestMapping("query")
    public String query(Model model){
    	List<Group> groupList = groupService.getGroupList();
    	model.addAttribute("groupList", groupList);
        return "sales/salesQuery";
    }
    
    /**
     * 保险代理人交易统计
     * @param model
     * @return
     */
    @RequestMapping("report")
    public String report(Model model){
    	List<Group> groupList = groupService.getGroupList();
    	String startDate = DateUtils.formatDate(DateUtils.getFirstDayOfMonth(new Date()), "yyyy-MM-dd");
    	String endDate = DateUtils.formatDate(new Date(), "yyyy-MM-dd");
    	model.addAttribute("startDate", startDate);
    	model.addAttribute("endDate", endDate);
    	model.addAttribute("groupList", groupList);
    	
        return "sales/salesReport";
    }
    
	@RequestMapping("download")
    public void download(HttpServletResponse response,String qrId,String userName,String mobile,Long groupId,String customerNumber,String customerName,String from,String to){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("qrId", qrId);
		param.put("userName", userName);
		param.put("mobile", mobile);
		param.put("groupId", groupId);
		param.put("customerNumber",customerNumber);
		param.put("customerName", customerName);
		param.put("from", from);
		param.put("to", to);
    	List list = ymfQuery2Service.query("ymfSalesReportQuery", param);//.query("", null);
    	LOG.info(JSON.toJSONString(list));
    	String strTitle = "序号,一级商户名称,二级商户编号,二级商户名称,代理人姓名,二维码ID,代理人手机号,卡号,银行,笔数,金额(元)";
    	String strBody="rowid,group_name,customer_number,customer_name,user_name,qr_id,mobile,bank_no,bank_name,cnt,amt";
    	
    	POIUtils.exportExcelByObject(list, Map.class, "sals_report", strTitle, strBody, response);
    }
   
    /**
     * 查看代理人信息
     * @param id
     * @return
     */
    @RequestMapping(value="/viewSales",produces = "application/json; charset=utf-8")
   	@ResponseBody
    public String viewSales(Long id){
        Sales sales = salesService.getSalesById(id);
        return JSON.toJSONString(sales);
    }
    /**
     * 修改代理人银行信息
     * @param salesId
     * @param bankNo
     * @param bankName
     * @return
     */
    @RequestMapping(value="/updateBankInfo",produces = "application/text; charset=utf-8")
   	@ResponseBody
    public String updateBankInfo(Long salesId,String bankNo,String bankName){
    	salesService.editBankInfo(bankNo, bankName, salesId);
    	return "success";
    }
    /**
     * 删除代理人(将代理人状态置于删除状态)
     * @param salesId
     * @return
     */
    @RequestMapping(value="/deleteSales",produces = "application/text; charset=utf-8")
   	@ResponseBody
    public String deleteSales(Long id){
    	salesService.frozenSales(id);
    	return "success";
    }
}
