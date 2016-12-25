package com.gurukula.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.gurukula.generic.MemoryStorage;

/**
 * @author      Selin Gungor <selingungor01@gmail.com>
 * @version     1.0   
 * @since       1.0 (the version of the package this class was first added to)
 */
public class UserSettingsPage extends LoginPage {

	protected WebDriver driver;
	private boolean isFirstNameHealty = false; private boolean isLastNameHealty = false; private boolean isEmailHealty = false;

	/**
	 * Constructor
	 * @param driver
	 */
	public UserSettingsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Web elements
	 /**
     * text box first name
     *
     **/
    @CacheLookup
    @FindBy(css = "[ng-model=\"settingsAccount.firstName\"")
    private WebElement txtFirstName;
	
	 /**
     * text box last name
     *
     **/
    @CacheLookup
    @FindBy(css = "[ng-model=\"settingsAccount.lastName\"]")
    private WebElement txtLastName;
    
	 /**
     * text box email	
     *
     **/
    @CacheLookup
    @FindBy(css = "[ng-model=\"settingsAccount.email\"]")
    private WebElement txtEmail;
    
	 /**
     * drop down list language
     *
     **/
    @CacheLookup
    @FindBy(css = "[ng-model=\"settingsAccount.langKey\"]")
    private WebElement dropDownListLanguage;
   
	 /**
     * save button on user settings page
     *
     **/
    @CacheLookup
    @FindBy(css = "[translate=\"settings.form.button\"]")
    private WebElement btnSaveUserSettings;
    
    //Error messages
	 /**
     * first name text box min
     *
     **/
    @CacheLookup
    @FindBy(css = "[translate=\"settings.messages.validate.firstname.minlength\"]")
    private WebElement errMessageFirstNameMin;
    
	 /**
     * first name text box max
     *
     **/
    @CacheLookup
    @FindBy(css = "[translate=\"settings.messages.validate.firstname.maxlength\"]")
    private WebElement errMessageFirstNameMax;
    
	 /**
     * first name required
     *
     **/
    @CacheLookup
    @FindBy(css = "[translate=\"settings.messages.validate.firstname.required\"]")
    private WebElement errMessageFirstNameRequired;
    
	 /**
     * last name text box min
     *
     **/
    @CacheLookup
    @FindBy(css = "[translate=\"settings.messages.validate.lastname.minlength\"]")
    private WebElement errMessageLastNameMin;
    
	 /**
     * last name text box max
     *
     **/
    @CacheLookup
    @FindBy(css = "[translate=\"settings.messages.validate.lastname.maxlength\"]")
    private WebElement errMessageLastNameMax;
    
	 /**
     * last name required
     *
     **/
    @CacheLookup
    @FindBy(css = "[translate=\"settings.messages.validate.lastname.required\"]")
    private WebElement errMessageLastNameRequired;
    
	 /**
     * email name text box min
     *
     **/
    @CacheLookup
    @FindBy(css = "[translate=\"global.messages.validate.email.minlength\"]")
    private WebElement errMessageEmailMin;
    
	 /**
     * email name text box max
     *
     **/
    @CacheLookup
    @FindBy(css = "[translate=\"global.messages.validate.email.maxlength\"]")
    private WebElement errMessageEmailMax;
    
	 /**
     * email name required
     *
     **/
    @CacheLookup
    @FindBy(css = "[translate=\"global.messages.validate.email.required\"]")
    private WebElement errMessageEmailRequired;
    
	 /**
     *warnings after save button
     *
     **/
    @CacheLookup
    @FindBy(css = "[translate=\"settings.messages.error.fail\"]")
    private WebElement warningsSaving;
    
    
    
	//Methods
    /**
     * Update the user info using parameters
     * @param firstName first name will be updated
     * @param lastName last name will be updated
     * @param email email name will be updated
     * @param language language name will be updated
     * @return
     * @throws InterruptedException
     */
    public UserSettingsPage updateAccountInfo(String firstName,String lastName,String email, String language) throws InterruptedException
    {
    	Thread.sleep(250);
    	updateFirstName(firstName);
    		isFirstNameHealty = MemoryStorage.isHealty;
    	Thread.sleep(250);
    	updateLastName(lastName);
    		isLastNameHealty = MemoryStorage.isHealty;
    	Thread.sleep(250);
    	updateEmail(email);
    		isEmailHealty = MemoryStorage.isHealty;
    	Thread.sleep(250);
    	updateLanguage(language);
    		
    	Thread.sleep(100);
    	System.out.println("isEmailHealty" + isEmailHealty);
    	System.out.println("isFirstNameHealty" + isFirstNameHealty);
    	System.out.println("isLastNameHealty" + isLastNameHealty);
    	 if(isEmailHealty && isFirstNameHealty && isLastNameHealty){
			 clickSaveButton();
			 String message = warningsSaving.getText();
			 Assert.assertEquals(message, "Successfull");
		 }
		 else{
			Assert.assertFalse(btnSaveUserSettings.isEnabled());
		 }
    	return new UserSettingsPage(driver);
    }
    
    /**update first name
     * 
     * @param firstName name of the user
     * @return
     * @throws InterruptedException
     */
	private UserSettingsPage updateFirstName(String firstName) throws InterruptedException {
		String requiredMessage = "Your first name is required.";
		String errorMessageMin = "Your first name is required to be at least 1 character";
		String errorMessageMax = "Your first name cannot be longer than 50 characters";
		
		return enterText(UserSettingsPage.class, txtFirstName, firstName, errMessageFirstNameRequired,
				requiredMessage, errMessageFirstNameMin, errorMessageMin, errMessageFirstNameMax, errorMessageMax);
		}
	
	/**
	 * update Last Name
	 * @param lastName
	 * @return
	 * @throws InterruptedException
	 */
	private UserSettingsPage updateLastName(String lastName) throws InterruptedException {
		String requiredMessage = "Your last name is required.";
		String errorMessageMin = "Your last name is required to be at least 1 character";
		String errorMessageMax = "Your last name cannot be longer than 50 characters";

		return enterText(UserSettingsPage.class, txtLastName, lastName, errMessageLastNameRequired, requiredMessage, 
				errMessageLastNameMin, errorMessageMin, errMessageLastNameMax, errorMessageMax);
		}
	
	/**
	 * Update email address
	 * @param email update email
	 * @return
	 * @throws InterruptedException
	 */
	private UserSettingsPage updateEmail(String email) throws InterruptedException {
		String requiredMessage = "Your e-mail is required.";
		//String invalidEmail = "Your e-mail is invalid.";
		String errorMessageMin = "Your e-mail is required to be at least 5 characters.";
		String errorMessageMax = "Your e-mail cannot be longer than 50 characters.";

		return enterText(UserSettingsPage.class, txtEmail, email, errMessageEmailRequired, requiredMessage, 
				errMessageEmailMin, errorMessageMin, errMessageEmailMax, errorMessageMax);
		}
	
	
	/**
	 * Update language
	 * @param language language information
	 * @return
	 */
	private UserSettingsPage updateLanguage(String language)
	{
		clickElement(driver, 10, dropDownListLanguage);
		Select select = new Select(dropDownListLanguage);
		select.selectByVisibleText(language);
		return this;	
	}

	private UserSettingsPage clickSaveButton()
	{
		clickElement(driver, 10, btnSaveUserSettings);
		return this;
	}
}
