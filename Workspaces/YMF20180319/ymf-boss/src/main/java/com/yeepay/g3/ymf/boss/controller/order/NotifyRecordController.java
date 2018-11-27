package com.yeepay.g3.ymf.boss.controller.order;

import com.yeepay.g3.core.ymf.entity.notify.NotifyRecord;
import com.yeepay.g3.core.ymf.service.notify.NotifyBizService;
import com.yeepay.g3.core.ymf.service.notify.NotifyRecordeService;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.ymf.boss.controller.ValidateController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dongxulu on 17/1/5.
 */
@Controller
@RequestMapping("/notifyRecord")
public class NotifyRecordController extends ValidateController {
    private static Logger logger = LoggerFactory.getLogger(NotifyRecordController.class);
    @Autowired
    private NotifyBizService notifyBizService;
    @Autowired
    private NotifyRecordeService notifyRecordeService;
    @RequestMapping("/doTry")
    public @ResponseBody String doTry(@RequestParam(value = "id") Long  id){
        NotifyRecord notifyRecord = notifyRecordeService.getByid(id);
        if(null==notifyRecord){
            logger.error("补单异常:  no NotifyRecord exsist");
            return Status.FAIL.toString();
        }
        if(Status.SUCCESS.equals(notifyRecord.getStatus())){
            logger.info(" NotifyRecord already SUCCESS! externalID:"+notifyRecord.getExternalId());
            return Status.SUCCESS.toString();
        }
        Status status = notifyBizService.asynNotify(notifyRecord);
        if(status.equals(Status.SUCCESS)){
            return Status.SUCCESS.toString();
        }
        return Status.FAIL.toString();
    }

    @RequestMapping("/query")
    public String orderQuery() {
        return "notify/notifyRecordQuery";
    }


}
