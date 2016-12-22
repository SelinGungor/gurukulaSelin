package com.gurukula.pages;

import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage extends HomePage {

    protected WebDriver driver;
    
    public LoginPage(WebDriver driver){
	       super(driver);
	 	   this.driver = driver;
	 	   PageFactory.initElements(driver, this);
    	}
        	
	//Web Elements
	
	 /**
     * Authenticate button
     *
     **/
    @CacheLookup
    @FindBy( css = "[translate='login.form.button']")
    private WebElement btnAuthenticate;
	
	 /**
     * User name text box
     *
     **/
    @CacheLookup
    @FindBy( id = "username")
    private WebElement txtBxUserName;
    
	 /**
     * Password text box
     *
     **/
    @CacheLookup
    @FindBy( id = "password")
    private WebElement txtBxPassword;
    
	 /**
     * Automatic login check box
     *
     **/
    @CacheLookup
    @FindBy( id = "rememberMe")
    private WebElement chckBxAutomaticLogin;
    
	 /**
     * success logged In Message
     *
     **/
    @CacheLookup
    @FindBy( css = "[translate='main.logged.message']")
    private WebElement successLoggedInMessage;
    
    
    //Methods
    
    //private methods
    /**
   	 *  Clicks authenticate button
        *
        **/
   	private LoginPage clickAuthenticateButton()
   	{
   		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(btnAuthenticate)).click();
   		return this;
   	}
   	
   	//public methods
    /**
   	 *  Login scenario successfully
   	 *
   	 * @param  username  the username of the user
   	 * @param  password the password of the user
   	 * @param  automaticLogin should be true, if needed
   	 * @return      
   	 */
   	public LoginPage loginSuccess(String username, String password, boolean automaticLogin)
   	{
   		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtBxUserName)).sendKeys(username);
   		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtBxPassword)).sendKeys(password);
   		if(automaticLogin == true)
   		{
   			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(chckBxAutomaticLogin)).click();
   		}
   		
   		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(btnAuthenticate)).click();
   		String successMessage = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(successLoggedInMessage)).getText();
   		System.out.println("successMessage : " + successMessage);
   		Assert.assertEquals("You are logged in as user \"" + username + "\".", successMessage);
   		return this;
   	}
   	
    /**
   	 *  Clicks authenticate button
        *
        **/
   	public LoginPage checkLoginPageOpened()
   	{
   		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(btnAuthenticate));
   		return this;
   	}
   	
}
