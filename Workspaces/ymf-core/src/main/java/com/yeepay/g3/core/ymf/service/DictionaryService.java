package com.yeepay.g3.core.ymf.service;

import com.yeepay.g3.core.ymf.entity.dictionary.Dictionary;
import com.yeepay.g3.core.ymf.service.base.GenericBatchService;

import java.util.List;

/**
 *
 * Created by yp-tc-m-2762 on 16/8/12.
 */
public interface DictionaryService extends GenericBatchService<Dictionary> {
    /**
     * 根据类型获取状态可用的数据字典
     *  add by fei.lu
     * @param type
     * @return
     */
    List<Dictionary> getDictionariesByType(String type);

    /**
     *  根据编码获得字典。请尽量使用Type和code一起过滤，code容易重复造成混淆
     *   add by fei.lu
     * @param code
     * @return
     */
    @Deprecated
    Dictionary getDictionaryByCode(String code);

    /**
     * 根据名称获得字典.请尽量使用Type和name一起过滤，name容易重复造成混淆
     * @param name
     * @return
     */
    @Deprecated
    Dictionary getDictionaryByName(String name);

    /**
     * 根据type和code查询字典
     * @param type 类型
     * @param code 编码
     * @return 字典
     */
    Dictionary getDictByTypeAndCode(String type, String code);

    /**
     * 获得所有的数据字典值
     * @return
     */
    List<Dictionary> getAllDictionary();

    /**
     * 根据id获取
     * @param id 主键
     * @return
     */
    Dictionary getById(Long id);

    /**
     * 根据商编查询支付权限的字典
     * @param customerNumber        商编
     * @return
     */
    List<Dictionary> getCustomerFunctionList(String customerNumber) ;

    /**
     * 根据编码查询字典..请尽量使用Type和code一起过滤，code容易重复造成混淆
     * @param code
     * @return
     */
//    @Deprecated
//     List<Dictionary> getByDictionaryCode(String code);

}
