package com.gurukula.e2eTests;

import org.testng.annotations.Test;

import com.gurukula.generic.BaseCase;
import com.gurukula.pages.Factory;

/**
 * @author      Selin Gungor <selingungor01@gmail.com>
 * @version     1.0   
 * @since       1.0 (the version of the package this class was first added to)
 */
public class LoginTests extends BaseCase {
	
	@Test
	public void TC001_checkHomePageTest()
	{
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .isTextExist("Welcome to Gurukula!");						 
	}
	
	@Test
	public void TC002_loginPositiveTest()
	{
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .clickLogin()
						  .login("admin","admin", true, true);
	}
	
	@Test
	public void TC003_loginNegativeTest()
	{
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .clickLogin()
						  .login("admin", "admo", true, false);
	}
	
	@Test
	public void TC004_logOutTest()
	{
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .clickLogin()
						  .login("admin","admin", true, true)
						  .logOut();
	}
}
