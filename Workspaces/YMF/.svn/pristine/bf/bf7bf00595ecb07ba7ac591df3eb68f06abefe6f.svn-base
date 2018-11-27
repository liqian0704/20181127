package com.yeepay.g3.core.ymf.support.bean;

import com.yeepay.g3.core.ymf.entity.dictionary.Dictionary;
import com.yeepay.g3.core.ymf.service.DictionaryService;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 1. 这个类配置springbean (非注解), 并且又LazyInit的属性
 * 2. 再afterPropertiesSet这个service, key:value 填充dictMap
 * 3. a. 非spring环境下用
 *         DictionaryHolder holder = ApplicationContextHelper.getApplicationContext().getBean(DictionaryHolder.class);
 * 4. b autowired
 * Created by fei.lu on 16/8/26.
 */
@Lazy
@Component
public class DictionaryHolder implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(DictionaryHolder.class);

    @Autowired
    private DictionaryService dictionaryService;
    /** 简单内存缓存 key-value */
    private Map<String, String> dictMap;
    /** 简单内存缓存 key-selection*/
    private Map<String, List<Dictionary>> selectMap;
    // TODO REDIS/MEMCACHE

    /**
     * 获取字典code对应value
     * @param key value
     * @return
     */
    public String getDictValue(String key) {
        return dictMap.get(key);
    }

    /**
     * 获取字典下拉菜单
     * @param type
     * @return
     */
    public List<Dictionary> getSelection(String type) {
        return selectMap.get(type);
    }

    /**
     * 所有的type
     * @return
     */
    public Set<String> getKeySet() {
        return selectMap.keySet();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        dictMap = new HashMap<String, String>();
        selectMap = new HashMap<String, List<Dictionary>>();
        refresh();
    }

    /**
     * 字典配置刷新缓存方法
     */
    public synchronized void refresh() {
        log.info("开始刷新字典数据缓存...");
        long start = System.currentTimeMillis();
        dictMap.clear();
        selectMap.clear();
        // 读取
        List<Dictionary> list = dictionaryService.getAllDictionary();
        if(list != null){
            for(Dictionary dic:list){
                String type = dic.getType();
                String code = dic.getCode();
                String name = dic.getName();
                dictMap.put(type + code, name);
                List<Dictionary> typeList = selectMap.get(type);
                if (null == typeList) {
                    typeList = new LinkedList<Dictionary>();
                    selectMap.put(type, typeList);
                }
                typeList.add(dic);
            }
        }
        log.info("结束刷新字典数据缓存... dictMap数量" + dictMap.size()
                + ", 耗时:" + (System.currentTimeMillis() - start) + "毫秒");

    }

}
