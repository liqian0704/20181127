package business;


import org.openqa.selenium.WebDriver;
import page.ymf_searchPage;


/**
 * Created by yp-tc-2646 on 17/1/22.
 */
public class ymf_orderSearch_business {

    private static WebDriver webDriver;

    public ymf_orderSearch_business(WebDriver webDriver){

        this.webDriver=webDriver;

    }



    /**
     *  通过商户订单号查询
     * @param CustomerOrderID
     *
     */

    public void customerOrderID(String CustomerOrderID,String starTime){


        ymf_searchPage searchPage=new ymf_searchPage(webDriver);

        searchPage.openOrderPage();

        searchPage.setStaTime(starTime);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       // searchPage.setEndTime(endTime);

        searchPage.setcustomerOrderID(CustomerOrderID);

        searchPage.clickButton();


    }
    static {


    }
}
