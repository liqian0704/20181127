package com.yeepay.g3.core.ymf.service.dictionary;

import com.yeepay.g3.core.ymf.entity.dictionary.Dictionary;
import com.yeepay.g3.core.ymf.junit.ApplicationContextAwareTest;
import com.yeepay.g3.core.ymf.service.DictionaryService;
import org.junit.Test;

import java.util.List;

/**
 * Created by dongxulu on 16/11/22.
 */
public class DictionaryServiceTest extends ApplicationContextAwareTest {
    @Test
    public void findByCode(){
        DictionaryService service  =  getBean(DictionaryService.class);
        List<Dictionary> list = service.getByDictionaryCode("NORMAL");
        for(Dictionary en : list ){
            System.out.println("###"+en.getType());
            System.out.println("###"+en.getValue());
        }
    }
}
