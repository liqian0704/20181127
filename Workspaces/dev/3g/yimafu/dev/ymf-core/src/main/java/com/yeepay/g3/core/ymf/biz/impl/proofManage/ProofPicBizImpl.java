package com.yeepay.g3.core.ymf.biz.impl.proofManage;
/**
 * Created by jiwei.lv on 16/10/27.
 */

import com.yeepay.g3.core.ymf.biz.proofManage.ProofPicBiz;
import com.yeepay.g3.core.ymf.entity.dictionary.Dictionary;
import com.yeepay.g3.core.ymf.service.DictionaryService;
import com.yeepay.g3.core.ymf.service.order.OrderService;
import com.yeepay.g3.core.ymf.utils.ConfigureSetting;
import com.yeepay.g3.facade.ymf.dto.order.OrderProofDTO;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jiwei.lv
 * @create 2016-10-16/10/27
 */
@Service
public class ProofPicBizImpl implements ProofPicBiz {
    private static final Logger log = LoggerFactory.getLogger(ProofPicBizImpl.class);
    @Autowired
    private OrderService orderService;
    @Autowired
    private DictionaryService dictionaryService;
    @Override
    public void createProofPic() {
        log.info("####电子凭证生成开始!");
//        String customerNumbers=ConfigureSetting.getValue("YMF_PROOOF_CUSTOMER",String.class);

//        String customerNumbers="10040007800";

        StringBuilder customerNumberBuilder= new StringBuilder();
        Dictionary dict=dictionaryService.getDictByTypeAndCode("Proof","ProofCustomer");
        if(null != dict){
            String dictvalue=dict.getValue();
            if(StringUtils.isNotBlank(dictvalue)){
                String[] dictvl=dictvalue.split(",");
                if (null != dictvl && dictvl.length>0){
                    for (int i=0;i<dictvl.length;i++){
                        Dictionary cd=dictionaryService.getDictByTypeAndCode("Proof",dictvl[i]);
                        customerNumberBuilder.append(cd.getValue());
                        customerNumberBuilder.append(",");
                    }
                }else{
                    customerNumberBuilder.append("");
                }

            }
        }
//        if(null != dict && dict.size()>0){
//            for (int i=0;i<dict.size()-1;i++){
//                customerNumberBuilder.append(dict.get(i).getCode());
//                customerNumberBuilder.append(",");
//            }
//            customerNumberBuilder.append(dict.get(dict.size()-1).getCode());
//        }
        if(null != customerNumberBuilder && !"".equals(customerNumberBuilder.toString())){
            String customerNumbers=customerNumberBuilder.toString();
            log.info(customerNumberBuilder.toString());

            if(StringUtils.isBlank(customerNumbers)){
                log.info("获取电子凭证标识商户编码失败,请配置!");
            }else{
                String customerNumberlist= customerNumbers.trim().replaceAll(" ","").replaceAll("\n",",").replaceAll("\t",",").replaceAll("\r",",").replace("，", ",");
                String[] cust =  customerNumberlist.split(",");
                if(cust.length>0){
                    for (int i=0;i<cust.length; i++){
                        log.info(cust[i]+"商户生成电子凭证开始!");
                        try {
                            List<OrderProofDTO> proofList=orderService.findByCustomer(cust[i]);
                            if(null != proofList && proofList.size()>0){
                                 String pcount = orderService.createProofPicTime(proofList);
                            }else{
                                log.info(cust[i]+"无可匹配的交易");
                            }
                            log.info(cust[i]+"商户生成电子凭证结束!");
                        }catch (Exception e){
                            log.error(cust[i]+"商户生成电子凭证发生异常"+e);
                        }

                    }
                }
            }
        }else{
            log.info("未配置电子凭证商户编码,定时结束");
        }


    }
}
