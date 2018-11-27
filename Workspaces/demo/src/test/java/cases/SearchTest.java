package cases;

import business.ymf_login_business;
import business.ymf_orderSearch_business;
import config.getCommConProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by yp-tc-2646 on 17/1/22.
 */
public class SearchTest extends testBase {

    private ymf_orderSearch_business ymf_orderSearch_business;

    @BeforeClass
    public void beforeMethod() throws Exception {



        ymf_login_business ymf_login_business= new ymf_login_business(driver);

        ymf_login_business.login(getCommConProperties.getUname(),getCommConProperties.getPassword());
    }


    @Test(dataProvider = "dp")
    public void search(String orderID,String startTime) throws InterruptedException {


        ymf_orderSearch_business=new ymf_orderSearch_business(driver);

        ymf_orderSearch_business.customerOrderID(orderID,startTime);

        Thread.sleep(1000);

        System.out.println("查询到订单:"+orderID);

        Thread.sleep(1000);

        WebElement element=driver.findElement(By.className("list_tr_even")).findElements(By.tagName("td")).get(3).findElement(By.tagName("span"));

        System.out.println(element.getText());

        Assert.assertTrue(element.getText().equals(orderID));


    }

    public void init() throws Exception {

    }
    @DataProvider(name = "dp")
    public Object[][] dp(){

        return  new Object[][]{

            new Object[]{"2016100901","2016-02-04"},

            new Object[]{"025480767539","2016-02-04"}


        };
    }


}
