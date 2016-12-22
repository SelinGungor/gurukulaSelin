package com.gurukula.e2eTests;

import org.testng.annotations.Test;

import com.gurukula.generic.BaseCase;
import com.gurukula.pages.Factory;

public class BranchTests extends BaseCase {
	@Test
	public void TC001_checkHomePage()
	{
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .clickLogin()
						  .loginSuccess("admin", "admin", true)
						  .clickBranches()
						  .enterBranchName("sel","This field is required to be at least 5 characters.");					 
	}
}
