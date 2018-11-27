package com.yeepay.g3.ymf.boss.controller.common;

import com.yeepay.g3.core.ymf.ext.AreaExt;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.utils.common.json.JSONUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Description:
 * @Author: xiaobin.liu
 * @Date: 17/7/10
 * @Time: 下午3:38
 */
@Controller
@RequestMapping("/area")
public class AreaControllor {
    @Autowired
    private AreaExt areaExt;

    @ResponseBody
    @RequestMapping("/areaInfo")
    public ResponseMessage queryAreaInfo(@RequestParam String areaCode) {
        if (StringUtils.isBlank(areaCode)) {
            return ResponseMessage.error("参数不能空");
        }
        Map<String, String> map = areaExt.queryAreaInfo(areaCode);
        return ResponseMessage.data(map);
    }

    @ResponseBody
    @RequestMapping("/province")
    public ResponseMessage queryAllProvince() {
        Map<String, String> map = areaExt.queryAllProvince();
        return ResponseMessage.data(JSONUtils.toJsonString(map));
    }
}
