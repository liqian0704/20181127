package page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by yp-tc-2646 on 17/1/8.
 */
public class ymf_loginPage {
    /**
     *  设置用户名
     */
    private WebDriver driver ;

    public ymf_loginPage(WebDriver driver){this.driver=driver;}

    public void setUserName(String userText){
        driver.findElement(By.name("loginName")).sendKeys(userText);



    }
    public void setPassWord(String pwd){
        driver.findElement(By.name("password")).sendKeys(pwd);
    }

    public void clickButton(){

        driver.findElement(By.id("loginButton")).click();

    }

}
