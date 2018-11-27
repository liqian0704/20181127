package cases;

import config.DriverManage;
import config.getCommConProperties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import util.DBservice;
import util.Selenium2Proxy;

import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * Created by yp-tc-2646 on 17/1/22.
 */
public abstract class testBase {
    protected static WebDriver driver = null;
    protected String browser;
    protected final Logger log = Logger.getLogger(getClass());
    protected boolean captureflag;
    protected Connection conn = null;
    protected DBservice ds= new DBservice();

    String dbusername;
    String dbpassword;
    String dbtype;
    String dburl;


    @BeforeClass
    @Parameters({"driverType"})
    public void beforeMethod(@Optional("1") String driverType) throws Exception {
        captureflag = true;
        driverType = "1" ;
        dbusername = getCommConProperties.getDbUname();
        dbpassword = getCommConProperties.getDbPwd();
        dbtype = getCommConProperties.getDbType();
        dburl = getCommConProperties.getTestUrl();
        conn = ds.dbConnection(dbtype, dbusername, dbpassword, dburl);
        //browser = getCommConProperties.getBrowserType();
        browser = driverType;

        log.info("Start to run test class " + this.getClass().getName()
                + " on driverType" + browser);
        driver = DriverManage.getWebDriver(browser);

        // driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.manage().window().maximize();

        captureflag=false;
    }

    abstract public void init() throws Exception;

    @AfterMethod
    public void afterMethod() throws Exception {
        try {
            Selenium2Proxy se = new Selenium2Proxy(driver);
            if (captureflag) {
                se.captureScreenshot();
            }
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                Thread.sleep(500);
                if (!browser.equals("1")) {
                    driver.quit();
                }
            }
        }
    }




    @AfterClass
    public void afterClass() throws Exception {
        ds.closeDBDriver(conn);
        driver.quit();

    }

    @BeforeTest
    public void beforeTest() throws Exception {
       // PropertyConfigurator.configure(CommMethod.getRealath()+ "conf/log4j.properties");
        PropertyConfigurator.configure("/Users/yp-tc-2646/Workspaces/demo/src/test/java/conf/log4j.properties");

    }

    public String getBrowserProcess(String bs) throws Exception {
        String process = null;

        switch (Integer.parseInt(bs)) {

            case 1:
                process = "firefox.exe";
                break;

            case 2:
                process = "iexplore.exe";
                break;

            case 3:
                process = "chrome.exe";
                break;
        }
        return process;
    }


}
