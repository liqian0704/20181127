package page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by yp-tc-2646 on 17/1/8.
 */
public class ymf_searchPage {
    /**
     *  打开易码付订单页面
     */
    private WebDriver driver ;
    public ymf_searchPage(WebDriver driver){
        this.driver=driver;
    }
    public void openOrderPage(){

        this.moveRight(20);

        Actions builder=new Actions(driver) ;

        builder.moveToElement(driver.findElement(By.linkText("易码付"))).perform();

        driver.findElement(By.linkText("易码付订单管理")).click();




    }

    /**
     * 通过商户订单id查询
     * @param customerOrderID
     */
    public void setcustomerOrderID(String customerOrderID){

        driver.findElement(By.id("customerOrder")).sendKeys(customerOrderID);

    }
    /**
     * 设置查询开始时间
     */
    public  void setStaTime(String staTime){


       WebElement star= driver.findElement(By.id("timeStart"));
        star.clear();
        star.sendKeys(staTime);

    }

    /**
     * 设置查询截止时间
     * @param endTime
     */

    public void setEndTime(String endTime){

       WebElement end= driver.findElement(By.id("timeEnd"));
        end.clear();
        end.sendKeys(endTime);
    }

    /**
     * 向右侧移动菜单
     * @param step
     */
    public  void  moveRight(int step){

        for (int i=0;i<step;i++){

            driver.findElement(By.id("_scrollNextBtn")).click();

        }

    }

    /**
     * 点击查询按钮
     * @param
     */


    public void clickButton(){
        driver.findElement(By.id("queryid")).click();

    }

}
