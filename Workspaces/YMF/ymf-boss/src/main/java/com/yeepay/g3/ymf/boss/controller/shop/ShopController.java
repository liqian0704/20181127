package com.yeepay.g3.ymf.boss.controller.shop;

import com.yeepay.g3.core.ymf.biz.shop.ShopBiz;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.shop.Shop;
import com.yeepay.g3.core.ymf.ext.AreaExt;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.qrcode.QrCodeService;
import com.yeepay.g3.core.ymf.service.shop.ShopService;
import com.yeepay.g3.core.ymf.utils.web.POIHelper;
import com.yeepay.g3.core.ymf.vo.shop.BatchShopRequest;
import com.yeepay.g3.core.ymf.vo.shop.BatchShopResponse;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.enumtype.common.ShopStatus;
import com.yeepay.g3.utils.common.BeanUtils;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.ymf.boss.WebConstants;
import com.yeepay.g3.ymf.boss.controller.common.DownloadController;
import com.yeepay.g3.ymf.boss.utils.common.DownloadUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Description: 网点管理
 * @Author: xiaobin.liu
 * @Date: 17/6/20
 * @Time: 下午4:07
 */
@Controller
@RequestMapping("/shop")
public class ShopController extends DownloadController {
    private static final Logger logger = LoggerFactory.getLogger(ShopController.class);

    //三级 省 市 县
//    {"110000":"北京 ","120000":"天津 ","130000":"河北 ","140000":"山西 ","150000":"内蒙古","210000":"辽宁 ","220000":"吉林 ","230000":"黑龙江","310000":"上海 ","320000":"江苏 ","330000":"浙江 ","340000":"安徽 ","350000":"福建 ","360000":"江西 ","370000":"山东 ","410000":"河南 ","420000":"湖北 ","430000":"湖南 ","440000":"广东 ","450000":"广西 ","460000":"海南 ","500000":"重庆 ","510000":"四川 ","520000":"贵州 ","530000":"云南 ","540000":"西藏 ","610000":"陕西 ","620000":"甘肃 ","630000":"青海 ","640000":"宁夏 ","650000":"新疆"}

    @Autowired
    private QrCodeService qrCodeService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopBiz shopBiz;
    @Autowired
    private AreaExt areaExt;


    /**
     * 跳转到查询页面
     * @return
     */
    @RequestMapping("/query")
    public String toShopQuery(HttpServletRequest request) {
        queryAllProvince(request);
        return "shop/shopQuery";
    }

    /**
     * 跳转到新增页面
     * @return
     */
    @RequestMapping("/toAdd")
    public String toShopAdd(HttpServletRequest request) {
        queryAllProvince(request);
        return "shop/shopAdd";
    }


    /**
     * 新增网点
     * @return
     */
    @RequestMapping(value = "/addShop",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage addShop(Shop shop, HttpSession session) {
        logger.info("toAdd params:{}",shop);
        try {
            ResponseMessage resp = null;
            String user = this.getUser(session);
            shop.setCreateUser(user);//记录创建人员
            shopService.save(shop);

            return ResponseMessage.ok();
        } catch (Exception e) {
            logger.error("Save shop exception：", e);
            return ResponseMessage.error("保存异常。请稍后重试！");
        }
    }


    @RequestMapping("/queryCustomerName")
    @ResponseBody
    public ResponseMessage queryCustomerName(@RequestParam(value = "customerNumber") String customerNumber) {
        Customer customer = null;
        try {
            customer = customerService.findByCustomerNumber(customerNumber);
            if (customer == null) {
                return ResponseMessage.error("商户编号错误，未查询到。");
            }
        } catch (Exception e) {
            logger.error("Query customer exception：", e);
            return ResponseMessage.error("查询商户异常。请稍后重试！");
        }

        return ResponseMessage.data(customer.getCustomerName());

    }

    /**
     * 核销网点
     */
    @RequestMapping(value = "/destroyShop",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage destroyShop(@RequestParam(value = "id") String id) {
        try {
            if (id == null) {
                return ResponseMessage.error("参数id错误。");
            }
            Long aLong = Long.valueOf(id);
            Shop shop = new Shop();
            shop.setId(aLong);
            shop.setStatus(ShopStatus.ACTIVE.DELETED);
            shopService.updateSelective(shop);
            return ResponseMessage.ok();
        } catch (Exception e) {
            logger.error("Destroy shop exception：", e);
            return ResponseMessage.error("核销网点异常。请稍后重试！");
        }
    }

    /**
     * 跳转到修改页面
     * @return
     */
    @RequestMapping("/toModifyShop")
    public String toModifyShop(@RequestParam(value = "id") Long id,HttpServletRequest request) {

        try {
            Shop shop = shopService.queryShopById(id);
            if (shop == null) {
                logger.error("toModifyShop error,param id is error : {}" ,id);
                return "error_exception";
            }
            Customer customer = customerService.findByCustomerNumber(shop.getCustomerNumber());

            request.setAttribute("shop",shop);
            request.setAttribute("customer",customer);

            queryAllProvince(request);

            return "shop/shopModify";
        } catch (Exception e) {
            logger.error("toModifyShop exception：", e);
            return "error_exception";
        }
    }

    private void queryAllProvince(HttpServletRequest request) {
        Map<String, String> map = areaExt.queryAllProvince();
        request.setAttribute("provinces", JSONUtils.toJsonString(map));
    }

    /**
     * 修改网点
     * @param shop1
     * @return
     */
    @RequestMapping("/modifyShop")
    @ResponseBody
    public ResponseMessage modifyShop(Shop shop1) {
        try {
            Shop shop = shopService.queryShopById(shop1.getId());
            if (shop == null) {
                logger.error("modify Shop error,shop is null");
                return ResponseMessage.error("参数异常。请稍后重试！");
            }
            BeanUtils.copyProperties(shop1,shop);
            shopService.update(shop);
            return ResponseMessage.ok();
        } catch (Exception e) {
            logger.error("Modify shop exception：", e);
            return ResponseMessage.error("保存异常。请稍后重试！");
        }
    }

    /**
     * 跳转到详情页面
     * @return
     */
    @RequestMapping("/toShopDetail")
    public String toShopDetail(@RequestParam(value = "id") Long id,HttpServletRequest request) {

        try {
            Shop shop = shopService.queryShopById(id);
            if (shop == null) {
                logger.error("toShopDetail error,param id is error : {}" ,id);
                return "error_exception";
            }
            Customer customer = customerService.findByCustomerNumber(shop.getCustomerNumber());

            request.setAttribute("shop",shop);
            request.setAttribute("customer",customer);
            return "shop/shopDetail";
        } catch (Exception e) {
            logger.error("toModifyShop exception：", e);
            return "error_exception";
        }
    }


    /**
     * 跳转到批量新增页面
     * @return
     */
    @RequestMapping("/toBatchAdd")
    public String toBatchAdd() {
        return "shop/shopBatchAdd";
    }


    @RequestMapping("batchAdd")
    @ResponseBody
    public ResponseMessage batchAdd(@RequestParam MultipartFile batchAddFile,
                                         @RequestParam String opCode,
                                         HttpSession session) {
        try {
            if (StringUtils.isBlank(opCode)) {
                return ResponseMessage.error("OP单号不能为空!");
            }
            if (null == batchAddFile) {
                return ResponseMessage.error("上传文件不能为空!");
            }
            List<BatchShopRequest> requestList = POIHelper.read(batchAddFile.getInputStream(), "data",
                    BatchShopRequest.class);
            if (null == requestList || requestList.size() == 0) {
                return ResponseMessage.error("上传文件中没有有效数据");
            }
            if (requestList.size() > 2000) {
                return ResponseMessage.error("一次批量新增最大2000条记录");
            }
            String user = this.getUser(session);
            List<BatchShopResponse> responseList = shopBiz.batchAddShop(requestList,user);
            session.setAttribute(WebConstants.SESSION_KEY_SHOP_BATCH_RESPONSE, responseList);

            return ResponseMessage.data(responseList).setFlag(responseList.size() > 0);
        } catch (Exception e) {
            logger.error("批量新增网点失败", e);
            return ResponseMessage.error("批量新增网点失败", e);
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("downloadBatchShopResponse")
    public void downloadBatchShopResponse(HttpSession session, HttpServletResponse response) {
        Object flag = session.getAttribute(WebConstants.SESSION_KEY_SHOP_BATCH_RESPONSE);
        if (null != flag) {
            List<BatchShopResponse> responseList = (List<BatchShopResponse>) flag;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            POIHelper.write(responseList, baos);
            DownloadUtils.download(response, baos, "downloadBatchShopResponse.xlsx", MediaType.APPLICATION_OCTET_STREAM);
        } else {
            response.setContentType("text/plain");
            try {
                response.getWriter().write("没有文件需要下载");
            } catch (IOException e1) {

            }
        }

    }

}
