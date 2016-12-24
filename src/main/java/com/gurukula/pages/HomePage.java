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
     * Login page icon
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
    @FindBy( css = "[href=\"#/branch\"]")
    private WebElement navBarBranch;
    
	 /**
     * Staff from navigation bar when Entities visible
     *
     **/
    @CacheLookup
    @FindBy( css = "[href=\"#/staff\"]")
    private WebElement navBarStaff;
    
	 /**
     * Accounts from navigation bar
     *
     **/
    @CacheLookup
    @FindBy(linkText = "Account")
	protected WebElement navBarAccount;
    
    /**
     * Settings from navigation bar
     *
     **/
    @CacheLookup
    @FindBy(css = "[translate=\"global.menu.account.settings\"]")
    private WebElement navBarSettings;

    /**
     * Logout from navigation bar
     *
     **/
    @CacheLookup
    @FindBy(css = " [translate=\"global.menu.account.logout\"]")
    protected WebElement navBarLogout;
   
       
    
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
		clickElement(driver,10,iconGurukula);
		return this;
	}
	
	 /**
	 *  Clicks login on the page,
     * and turns to loginpage.
     *
     **/
	public LoginPage clickLogin()
	{
		clickElement(driver,10,loginOnPage);
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
		clickElement(driver,10,navBarEntities);
		isTextExist("Branch");
		clickElement(driver,10,navBarBranch);
		return new BranchPage(driver);
	}
	
	public StaffPage clickStaff()
	{
		clickElement(driver,10,navBarEntities);
		isTextExist("Staff");
		clickElement(driver,10,navBarStaff);
		return new StaffPage(driver);
	}
	
	
	public UserSettingsPage clickSettings()
	{
		clickElement(driver,10,navBarAccount);
		isTextExist("Staff");
		clickElement(driver,10,navBarSettings);
		String pageSource = driver.getPageSource();
		Assert.assertTrue(pageSource.contains("User settings"));
		return new UserSettingsPage(driver);
	}
	
	public void clickElement(WebDriver driver,int timeout, WebElement element)
	{
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element)).click();
	}
	
	
	
}
