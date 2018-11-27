package cases;

import business.ymf_login_business;
import config.getCommConProperties;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * Created by yp-tc-2646 on 17/1/22.
 */
public class loginTest extends testBase {

    @Test
    public void login(Method method) throws InterruptedException {


        String methodName="";

        methodName= method.getName();

        log.info(methodName +"is running");

        ymf_login_business  login_business=new ymf_login_business(driver);

        login_business.login(getCommConProperties.getUname(),getCommConProperties.getPassword());

        Thread.sleep(1000);

        System.out.println("登录完成");
        Thread.sleep(3000);

        Assert.assertTrue(driver.getTitle().equals("易宝支付运营后台"));








    }

    public void init() throws Exception {

    }
}
