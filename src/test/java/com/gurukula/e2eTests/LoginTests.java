package com.gurukula.e2eTests;

import org.testng.annotations.Test;

import com.gurukula.generic.BaseCase;
import com.gurukula.pages.Factory;

public class LoginTests extends BaseCase {
	
	@Test
	public void TC001_checkHomePage()
	{
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .isTextExist("Welcome to Gurukula!");						 
	}
	
	@Test
	public void TC002_loginPositive()
	{
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .clickLogin()
						  .loginSuccess("admin", "admin", true);
	}
	
	@Test
	public void TC003_loginNegative()
	{
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .clickLogin()
						  .loginSuccess("admin", "admo", true);
	}
}
