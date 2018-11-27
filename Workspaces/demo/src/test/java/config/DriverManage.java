package config;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


/**
 * Created by yp-tc-2646 on 17/1/23.
 */
public class DriverManage {

    public static WebDriver driver=null;

    private static Logger log=Logger.getLogger("DriverManage");

    public static WebDriver getWebDriver(String runDriver) throws Exception {

       // PropertyConfigurator.configure(CommMethod.getRealath()+"conf/log4j.properties");

        PropertyConfigurator.configure("/Users/yp-tc-2646/Workspaces/demo/src/test/java/conf/log4j.properties");

        switch (Integer.parseInt(runDriver)){

            case 1:
                //System.setProperty("webdriver.firefox.bin","C:\\Program Files\\Mozilla Firefox\\firefox.exe");

                driver=new FirefoxDriver();

                log.info("this is FireFoxDriver");

                break;


            case 2:
                System.setProperty("webdriver.ie.driver","res/IEDriverServer_32.exe");

                driver=new InternetExplorerDriver();

                log.info("this is IEDriver");

                break;

            case 3:

                //webdriver.chrome.bin
                System.setProperty("webdriver.chrome.driver","/Users/yp-tc-2646/Workspaces/demo/src/test/java/res/chromedriver.exe");

                driver=new ChromeDriver();


                log.info("this is ChromeDriver");

        }

        return  driver;

    }

}
