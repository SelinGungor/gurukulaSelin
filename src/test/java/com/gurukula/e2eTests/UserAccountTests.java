package com.gurukula.e2eTests;

import org.testng.annotations.Test;

import com.gurukula.generic.BaseCase;
import com.gurukula.pages.Factory;

public class UserAccountTests extends BaseCase{

	@Test
	public void TC001_updateUserAccountInfoTest() throws InterruptedException
	{
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .clickLogin()
						  .login("admin", "admin", true, true)
						  .clickSettings()
						  .updateAccountInfo("UpdatedAdmin","UpdatedAdmin","updatedadmin@localhost","English");						  						 
	}
	
	@Test
	public void TC002_updateUserAccountInfoNegativeTest() throws InterruptedException
	{
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .clickLogin()
						  .login("admin", "admin", true, true)
						  .clickSettings()
						  .updateAccountInfo("","","updatedadmin@localhost","English");						  						 
	}
}
