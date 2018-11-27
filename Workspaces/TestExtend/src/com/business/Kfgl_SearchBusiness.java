package com.business;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.page.Kfgl_SearchPage;

public class Kfgl_SearchBusiness {
	private WebDriver driver;

	public Kfgl_SearchBusiness(WebDriver driver){
		this.driver = driver;
	}
	
	/**
	 * 查询业务
	 * @param serachtext查询条件
	 * @throws Exception
	 */
	public void search(String serachtext) throws Exception{
		Kfgl_SearchPage serach_page=new Kfgl_SearchPage(driver);
		//切换为人员维护界面
		serach_page.intoRYWHPage();
		serach_page.setSerchkey(serachtext);
		serach_page.clickButton();
	}
	

}
