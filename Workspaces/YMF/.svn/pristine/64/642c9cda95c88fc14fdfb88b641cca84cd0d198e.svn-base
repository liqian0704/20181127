package com.yeepay.g3.core.ymf.service.gratuity; /**
 * 类名称: GratuityServiceTest <br>
 * 类描述: <br>
 *
 * @author: dongxu.lu
 * @since: 16/11/17 下午1:44
 * @version: 1.0.0
 */

import com.yeepay.g3.core.ymf.entity.gratuity.Gratuity;
import com.yeepay.g3.core.ymf.junit.ApplicationContextAwareTest;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import org.junit.Test;

import java.util.List;

public class GratuityServiceTest extends ApplicationContextAwareTest {
    @Test
    public void save(){
        GratuityService service  =  getBean(GratuityService.class);
        Gratuity entity = new Gratuity();
        entity.setCustomerNumber("10040011560");
        entity.setGratuityRemark("");
        entity.setGratuityTemplate("1,2,3,5");
        entity.setStatus(Status.ACTIVE);
        service.save(entity);

    }
    @Test
    public void findByCustomer(){
        GratuityService service  =  getBean(GratuityService.class);
        List<Gratuity> list = service.getByCustomerNumber("10040011560");
        for(Gratuity en : list ){
            System.out.println(en.getCustomerNumber());
            System.out.println(en.getGratuityTemplate());
        }
    }
}


