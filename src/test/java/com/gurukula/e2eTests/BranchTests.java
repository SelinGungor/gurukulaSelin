package com.gurukula.e2eTests;

import org.testng.annotations.Test;

import com.gurukula.generic.BaseCase;
import com.gurukula.pages.Factory;

/**
 * @author      Selin Gungor <selingungor01@gmail.com>
 * @version     1.0   
 * @since       1.0 (the version of the package this class was first added to)
 */
public class BranchTests extends BaseCase {
	
	@Test
	public void TC001_checkNewBranch() throws InterruptedException
	{
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .clickLogin()
						  .login("admin", "admin", true, true)
						  .clickBranches()
						  .createNewBranch("selin", "A32")
						  .viewBranchDetail()
						  .checkInfoOnBranchDetail("selin", "A32");					 
	}
	
	@Test
	public void TC002_checkNewBranchNegative() throws InterruptedException
	{
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .clickLogin()
						  .login("admin", "admin", true, true)
						  .clickBranches()
						  .createNewBranch("Sel", "THISISTOOLONGSTRINGWHICHSHOULDNTBEACCEPTED");
	}
	
	@Test
	public void TC003_deleteBranch() throws InterruptedException
	{
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .clickLogin()
						  .login("admin", "admin", true, true)
						  .clickBranches()
						  .createNewBranch("selin", "A32")
						  .viewBranchDetail()
						  .checkInfoOnBranchDetail("selin", "A32")
						  .deleteBranch(1);						  				 
	}
	
	@Test
	public void TC004_editBranch() throws InterruptedException
	{
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .clickLogin()
						  .login("admin", "admin", true, true)
						  .clickBranches()
						  .createNewBranch("selin", "S92")
						  .viewBranchDetail()
						  .checkInfoOnBranchDetail("selin", "S92")
						  .editBranch(1, "deniz", "D91");						  				  				 
	}
	
	@Test
	public void TC005_searchBranch() throws InterruptedException
	{
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .clickLogin()
						  .login("admin", "admin", true, true)
						  .clickBranches()
						  .createNewBranch("selin", "A32")
						  .viewBranchDetail()
						  .checkInfoOnBranchDetail("selin", "A32")
						  .searchBranch("selin");
						  				  				 
	}
}
