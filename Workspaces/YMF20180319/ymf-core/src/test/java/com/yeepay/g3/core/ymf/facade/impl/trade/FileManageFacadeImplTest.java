package com.yeepay.g3.core.ymf.facade.impl.trade;/**
 * Created by jiwei.lv on 16/9/23.
 */

import com.yeepay.g3.core.ymf.junit.ApplicationContextAwareTest;
import com.yeepay.g3.facade.ymf.facade.FileManageFacade;
import org.junit.Test;

/**
 * @author jiwei.lv
 * @create 2016-09-16/9/23
 */
public class FileManageFacadeImplTest extends ApplicationContextAwareTest {
    @Test
    public void testdeleteFile(){
        FileManageFacade facade = getBean(FileManageFacade.class);
        facade.fileDownDelete();
    }
}

