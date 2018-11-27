package business;


import config.getCommConProperties;
import page.ymf_loginPage;
import org.openqa.selenium.WebDriver;

/**
 * Created by yp-tc-2646 on 17/1/22.
 */
public class ymf_login_business {

    private WebDriver webDriver;

    public ymf_login_business(WebDriver webDriver){

        this.webDriver=webDriver;

    }

    /**
     * 打开测试地址
     */

    public  void gotoUrl(){

        webDriver.get(getCommConProperties.getTestUrl());


    }

    /**
     *登录
     * @param name
     * @param pwd
     */

    public void login(String name,String pwd){

        webDriver.get(getCommConProperties.getTestUrl());
        
        ymf_loginPage loginPage=new ymf_loginPage(webDriver);

        loginPage.setUserName(name);

       loginPage.setPassWord(pwd);

        loginPage.clickButton();

    }
}
