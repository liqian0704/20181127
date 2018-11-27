package cases;

import config.getCommConProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * Created by yp-tc-2646 on 17/1/30.
 */
public class Baidu extends testBase {
    private String url;
    private WebDriver driver;

    @BeforeMethod
    public void testBefore(){
        String url="http://www.baidu.com";
        driver=new FirefoxDriver();

    }

    @Test
    public void testBadidu(Method method){

        String methodName=method.getName();
        log.info(methodName +"is running");
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        System.out.println(getCommConProperties.getDBdir());

    }

    public void init() throws Exception {

    }
}
