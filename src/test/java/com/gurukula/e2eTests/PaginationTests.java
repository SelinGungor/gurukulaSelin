package com.gurukula.e2eTests;

import org.testng.annotations.Test;

import com.gurukula.generic.BaseCase;
import com.gurukula.pages.Factory;

public class PaginationTests extends BaseCase {

	@Test(invocationCount = 25)
	public void TC001_checkPaginationTest() throws InterruptedException
	{
		
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .clickLogin()
						  .login("admin", "admin", true, true)
						  .clickStaff()
						  .createNewStaff("selingungor","deniz")
						  .checkPagination(false);
	}
	
	@Test(invocationCount = 21)
	public void TC002_paginationTest() throws InterruptedException
	{
		
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .clickLogin()
						  .login("admin", "admin", true, true)
						  .clickStaff()
						  .createNewStaff("selingungor","deniz")
						  .checkPagination(true);
	}
}
