package com.yeepay.g3.core.ymf.facade.impl.fielManage;/**
 * Created by jiwei.lv on 16/9/22.
 */

import com.yeepay.g3.core.ymf.biz.FileManage.FileSupplyBiz;
import com.yeepay.g3.facade.ymf.facade.FileManageFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiwei.lv
 * @create 2016-09-16/9/22
 */
@Service
public class FileManageFacadeImpl implements FileManageFacade{
    @Autowired
    private FileSupplyBiz supplyBiz;

    @Override
    public void fileDownDelete() {
        supplyBiz.deleteDownedFile();
    }
}
