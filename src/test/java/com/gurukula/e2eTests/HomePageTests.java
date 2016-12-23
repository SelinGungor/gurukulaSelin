package com.gurukula.e2eTests;

import org.testng.annotations.Test;

import com.gurukula.generic.BaseCase;
import com.gurukula.pages.Factory;

/**
 * @author      Selin Gungor <selingungor01@gmail.com>
 * @version     1.0   
 * @since       1.0 (the version of the package this class was first added to)
 */
public class HomePageTests extends BaseCase {

	
	@Test
	public void TC001_checkHomePage()
	{
		driver.get(gurukulaURL);
		Factory factory = new Factory(driver);
		factory.homePage().clickGurukulaIcon()
						  .isTextExist("Welcome to Gurukula!");						 
	}
}
