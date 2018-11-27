package com.page;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Kfgl_SearchPage {
	private WebDriver driver;

	public Kfgl_SearchPage(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * 进入人员维护界面
	 * 
	 */
	public void intoRYWHPage(){
		driver.switchTo().frame(1);
		driver.findElement(By.className("menu-item")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("main");
		
	}
	
	/**
	 * 设置查询条件
	 * @param keytext
	 */
	public void setSerchkey(String keytext) throws NotFoundException{
		
		//driver.findElement(By.id("select-key:useruuid")).sendKeys("lvbin");
		driver.findElement(By.id("select-key:useruuid")).sendKeys(keytext);
		
	}
	
	/**
	 * 点击查询按钮
	 * 
	 */
	public void clickButton(){
		driver.findElement(By.name("select-key_submit")).click();
		
	}
	
	/**
	 * 进入新增界面
	 * 
	 */
	public void addPage(){
		driver.findElement(By.name("record_record_addRecord")).click();
		Set<String> a=driver.getWindowHandles();
		for(int i=0;i<9;i++){
			for(String a2:a){
				driver.switchTo().window(a2);
				if(driver.getTitle().equals("增加人员维护")){
					i=10;
					break;
				}
				
			}
		}
	}
	
	/**
	 * 设置新增参数
	 * 
	 */
	public void setAddcontent(){
		
		driver.findElement(By.id("record:useruuid")).sendKeys("renqin1");
		driver.findElement(By.id("record:name")).sendKeys("renqin1");
		driver.findElement(By.name("record:department")).sendKeys("renqin1");
		//下拉单选框的处理Select
		Select s1=new Select(driver.findElement(By.id("record:roleuuid")));
		s1.selectByIndex(2);
		Select s2=new Select(driver.findElement(By.id("record:ability")));
		s2.selectByIndex(2);
		driver.findElement(By.name("record_record_saveAndExit")).click();
	}
	

}
