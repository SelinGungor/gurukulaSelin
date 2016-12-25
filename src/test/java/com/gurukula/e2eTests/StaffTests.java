package com.gurukula.e2eTests;

import org.testng.annotations.Test;

import com.gurukula.generic.BaseCase;
import com.gurukula.pages.Factory;

/**
 * @author      Selin Gungor <selingungor01@gmail.com>
 * @version     1.0   
 * @since       1.0 (the version of the package this class was first added to)
 */
public class StaffTests extends BaseCase {
	
	@Test
	public void TC001_checkStaff() throws InterruptedException
	{
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .clickLogin()
						  .login("admin", "admin", true, true)
						  .clickStaff()
						  .createNewStaff("deno","deniz");							 
	}
	
	@Test
	public void TC002_checkStaffDetail() throws InterruptedException
	{
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .clickLogin()
						  .login("admin", "admin", true, true)
						  .clickStaff()
						  .createNewStaff("selingungor","deniz")
						  .viewStaffDetail()
						  .checkInfoOnStaffDetail("selingungor", "deniz");							 
	}
	
	@Test
	public void TC003_checkStaffNegative() throws InterruptedException
	{
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .clickLogin()
						  .login("admin", "admin", true, true)
						  .clickStaff()
						  .createNewStaff("THISISTOOLONGSTRINGWHICHSHOULDNTBEACCEPTETHISISTOOLONGSTRINGWHICHSHOULDNTBEACCEPTETHISIS","deniz");						 
	}
	
	@Test
	public void TC004_editStaff() throws InterruptedException
	{
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .clickLogin()
						  .login("admin", "admin", true, true)
						  .clickStaff()
						  .createNewStaff("deno","deniz")	
						  .editStaff(1,"updatedName","mybranchname");						 
	}
}
