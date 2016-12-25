package com.gurukula.pages;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.gurukula.generic.MemoryStorage;

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
    
    /**
     * Register New Account
     *
     **/
    @CacheLookup
    @FindBy( css = "[translate=\"global.messages.info.register\"]")
    private WebElement registerNewAccount;
    

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
	
	
	public UserSettingsPage clickSettings() throws InterruptedException
	{
		clickElement(driver,10,navBarAccount);
		isTextExist("Staff");
		clickElement(driver,10,navBarSettings);
		String pageSource = driver.getPageSource();
		Thread.sleep(1000);
		Assert.assertTrue(pageSource.contains("User settings"));
		return new UserSettingsPage(driver);
	}
	
	public void clickElement(WebDriver driver,int timeout, WebElement element)
	{
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element)).click();
	}
	
	public RegistrationPage clickRegisterANewAccount()
	{
		clickGurukulaIcon();
		clickElement(driver, 10, registerNewAccount);
		return new RegistrationPage(driver);
	}
	
	
	/**
	 * 
	 * @param expectedPage
	 * @param textBox
	 * @param text
	 * @param requiredWarnMesageElement
	 * @param requiredMessage
	 * @param minCharWarnMessageElement
	 * @param minCharWarnMessage
	 * @param maxCharWarnMessageElement
	 * @param maxCharWarnMessage
	 * @param isHealty
	 * @return
	 * @throws InterruptedException
	 */
	public <T> T enterText(Class<T> expectedPage,WebElement textBox, String text, 
									   WebElement requiredWarnMesageElement, String requiredMessage,
									   WebElement minCharWarnMessageElement, String minCharWarnMessage, 
									   WebElement maxCharWarnMessageElement, String maxCharWarnMessage) throws InterruptedException {
		MemoryStorage.isHealty= false;
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(textBox));
		textBox.click();
		textBox.clear();
		textBox.sendKeys(text);
		
		boolean isDisplayed = false;
		String minLength = textBox.getAttribute("ng-minlength");
		String maxLength = textBox.getAttribute("ng-maxlength");
		
		Thread.sleep(1000);
		if (text.length()==0 || text == " "){
		System.out.println("Checking empty string..");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(requiredWarnMesageElement));
		
		System.out.println(requiredWarnMesageElement.getText());
		MemoryStorage.isHealty= false;
		Assert.assertEquals(requiredWarnMesageElement.getText(), requiredMessage);
		}
		else if (text.length() < Integer.parseInt(String.valueOf(minLength))) {
		Thread.sleep(200);
		System.out.println("Checking min value..");
		new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(minCharWarnMessageElement));
		
		System.out.println(minCharWarnMessageElement.getText());
		MemoryStorage.isHealty= false;
		Assert.assertEquals(minCharWarnMessageElement.getText(), minCharWarnMessage);
		}
		else if (text.length() > Integer.parseInt(String.valueOf(maxLength))) {
			Thread.sleep(200);
		System.out.println("Checking max value..");
		isDisplayed = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(maxCharWarnMessageElement))
		.isDisplayed();
		System.out.println(maxCharWarnMessageElement);
		Assert.assertTrue(isDisplayed);
		MemoryStorage.isHealty= false;
		Assert.assertEquals(maxCharWarnMessageElement.getText(), maxCharWarnMessage);
		}
		else{
			MemoryStorage.isHealty=true;
		}
		return PageFactory.initElements(driver, expectedPage);	
		}
	
	

	/**
	 * Gets the last item
	 * @param text
	 * @return
	 */
	public String getID(String text)
	{
		int iend = text.indexOf(" "); //this finds the first occurrence of "." 
		
		String subString = "";
		if (iend != -1){ 
		subString = text.substring(0 , iend); //this will give abc
		}
		return subString;
	}
	
	
	
}
