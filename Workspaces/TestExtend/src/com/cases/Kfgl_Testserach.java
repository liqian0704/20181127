package com.cases;

import org.testng.annotations.Test;

import com.business.Kfgl_LoginBusiness_New;
import com.business.Kfgl_SearchBusiness;
import com.config.GetTestProperties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Kfgl_Testserach extends TestBase {
	  /**
	   * 查询测试用例
	 * @throws Exception
	 */
  @Test
  public void testserch() throws Exception {
	  init();
	  Kfgl_SearchBusiness serach_business=new Kfgl_SearchBusiness(driver);
	  serach_business.search("lvbin");
	  Assert.assertEquals(driver.findElement(By.id("record_totalRow")).getText(), "1");//验证点
	  System.out.println("查询完成");
	  captureflag = false;
	  
  }
  @BeforeClass
	public void beforeClass() throws Exception {
		
	}
/* 
 * 初始化登录
 * 
 */
@Override
public void init() throws Exception {
	// TODO Auto-generated method stub
	Kfgl_LoginBusiness_New login=new Kfgl_LoginBusiness_New(driver);
	Assert.assertTrue(login.defaultLogin());
	
	
}

}
