package com.gurukula.pages;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * @author      Selin Gungor <selingungor01@gmail.com>
 * @version     1.0   
 * @since       1.0 (the version of the package this class was first added to)
 */
public class HomePage {

	protected WebDriver driver;
    
    public HomePage(WebDriver driver){
 	   this.driver = driver;
 	   PageFactory.initElements(driver, this);
    }
    

    //WebElements


    /**
     * Home page icon
     *
     **/
    @CacheLookup
    @FindBy( css = "[title|='Gurukula']:first-child")
    private WebElement iconGurukula;

    /**
     * Home page icon
     *
     **/
    @CacheLookup
    @FindBy( xpath = "html/body/div[3]/div[1]/div/div/div[2]/div/div[1]/a")
    private WebElement loginOnPage;

    //Navigation Bar Elements
	 /**
     * Entities from navigation bar 
     *
     **/
    @CacheLookup
    @FindBy( linkText = "Entities")
    private WebElement navBarEntities;
    
	 /**
     * Branch from navigation bar when Entities visible
     *
     **/
    @CacheLookup
    @FindBy( linkText = "Branch")
    private WebElement navBarBranch;
    
	 /**
     * Staff from navigation bar when Entities visible
     *
     **/
    @CacheLookup
    @FindBy( linkText = "Staff")
    private WebElement navBarStaff;
    
    //Methods
    
    /**
     * Checks if the text exist on the page.
     *
     * @param  textOnThePage  the string which should be on the page**/
	public void isTextExist(String textOnThePage)
	{
	   Assert.assertTrue(driver.getPageSource().contains(textOnThePage));
    }
	
    /**
     * Clicks the Gurukula icon on top of the page,
     * and turns to home page.
     *
     **/
	public HomePage clickGurukulaIcon()
	{
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(iconGurukula)).click();
		return this;
	}
	
	 /**
	 *  Clicks login on the page,
     * and turns to loginpage.
     *
     **/
	public LoginPage clickLogin()
	{
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(loginOnPage)).click();
		isTextExist("Authenticate");
		return new LoginPage(driver);
	}
	
	 /**
		 *  Clicks branch from entities menu,
	     * and returns branch page.
	     *
	     **/
	public BranchPage clickBranches()
	{
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(navBarEntities)).click();
		isTextExist("Branch");
		new Select(navBarEntities).selectByVisibleText("Branch");
		//new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(navBarBranch)).click();
		return new BranchPage(driver);
	}
	
}
