package com.gurukula.e2eTests;

import org.testng.annotations.Test;

import com.gurukula.generic.BaseCase;
import com.gurukula.pages.Factory;

/**
* @author      Selin Gungor <selingungor01@gmail.com>
* @version     1.0   
* @since       1.0 (the version of the package this class was first added to)
*/
public class RegistrationTests extends BaseCase {
	
	@Test
	public void TC001_registerANewAccountTest() throws InterruptedException
	{
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .clickRegisterANewAccount()
						  .registerANewAccount("celine", "celine@account", "90128", "90128");				 
	}
	
	@Test
	public void TC002_registerANewAccountNegativeLoginTest() throws InterruptedException
	{
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .clickRegisterANewAccount()
						  .registerANewAccount(" ", "celine@account", "90128", "90128");				 
	}
	
	@Test
	public void TC003_registerANewAccountNegativeEmailTest() throws InterruptedException
	{
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .clickRegisterANewAccount()
						  .registerANewAccount("denisse", "celine@accountinofrmationistoolongstringshouldntbeacceptedlalaalalalaalalalaalalalaalalalaalalalaalalalalala"
								  , "90128", "90128");				 
	}
	
	@Test
	public void TC004_registerANewAccountNegativePasswordMatchTest() throws InterruptedException
	{
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .clickRegisterANewAccount()
						  .registerANewAccount("denisse", "celine@accountalala"
								  , "mypassword", "myPasswordDifferent");				 
	}
}
