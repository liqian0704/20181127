package com.yeepay.g3.core.ymf.support.bean;

import com.yeepay.g3.core.ymf.dao.common.OperateLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * Created by chen.liu on 2016/8/29.
 */
@Lazy
@Component
public class LocalSimpleCache {

    private Map<String, String> entityLogNameMap;

    @Autowired
    OperateLogDao operateLogDao;

    public Set<Map.Entry<String, String>> getEntrySet() {
        return entityLogNameMap.entrySet();
    }

    @PostConstruct
    public void init() {
        entityLogNameMap = new HashMap<String, String>();
        List<String> list = operateLogDao.findAllEntityName();
        for (String str : list) {
            String simple = str.substring(str.lastIndexOf(".") + 1);
            entityLogNameMap.put(simple, str);
        }
    }
}
