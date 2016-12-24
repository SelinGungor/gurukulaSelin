package com.gurukula.pages;

import org.apache.http.util.Asserts;
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
    @FindBy(css = "[ng-show=\"form.lastName.$error.required\"]")
    private WebElement errMessageLastNameRequired;
    
	 /**
     * email name text box min
     *
     **/
    @CacheLookup
    @FindBy(css = "[ng-show=\"form.email.$error.minlength\"]")
    private WebElement errMessageEmailMin;
    
	 /**
     * email name text box max
     *
     **/
    @CacheLookup
    @FindBy(css = "[ng-show=\"form.email.$error.maxlength\"]")
    private WebElement errMessageEmailMax;
    
	 /**
     * email name required
     *
     **/
    @CacheLookup
    @FindBy(css = "[ng-show=\"form.email.$error.required\"]")
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
    	Thread.sleep(250);
    	updateLastName(lastName);
    	Thread.sleep(250);
    	updateEmail(email);
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

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtFirstName));
		txtFirstName.clear();
		txtFirstName.sendKeys(firstName);
		
		boolean isDisplayed = false;
		String minLength = txtFirstName.getAttribute("ng-minlength");
		String maxLength = txtFirstName.getAttribute("ng-maxlength");

		Thread.sleep(1000);
		if (firstName.length()==0){
			System.out.println("Checking empty string..");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(errMessageFirstNameRequired));

			System.out.println(errMessageFirstNameRequired.getText());
			isFirstNameHealty = false;
			Assert.assertEquals(errMessageFirstNameRequired.getText(), requiredMessage);
		}
		else if (firstName.length() < Integer.parseInt(String.valueOf(minLength))) {
			System.out.println("Checking min value..");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(errMessageFirstNameMin));

			System.out.println(errMessageFirstNameMin.getText());
			isFirstNameHealty = false;
			Assert.assertEquals(errMessageFirstNameMin.getText(), errorMessageMin);
		}
		else if (firstName.length() > Integer.parseInt(String.valueOf(maxLength))) {
			System.out.println("Checking max value..");
			isDisplayed = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(errMessageFirstNameMax))
					.isDisplayed();
			System.out.println(errMessageFirstNameMax);
			Assert.assertTrue(isDisplayed);
			isFirstNameHealty = false;
			Assert.assertEquals(errMessageFirstNameMax.getText(), errorMessageMax);
		}
		else{
			isFirstNameHealty=true;
		}
		return this;
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

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtLastName));
		txtLastName.clear();
		txtLastName.sendKeys(lastName);
		
		boolean isDisplayed = false;
		String minLength = txtLastName.getAttribute("ng-minlength");
		String maxLength = txtLastName.getAttribute("ng-maxlength");

		Thread.sleep(1000);
		if (lastName.length()==0){
			System.out.println("Checking empty string..");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(errMessageLastNameRequired));

			System.out.println(errMessageLastNameRequired.getText());
			isLastNameHealty = false;
			Assert.assertEquals(errMessageLastNameRequired.getText(), requiredMessage);
		}
		else if (lastName.length() < Integer.parseInt(String.valueOf(minLength))) {
			System.out.println("Checking min value..");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(errMessageLastNameMin));

			System.out.println(errMessageLastNameMin.getText());
			isLastNameHealty = false;
			Assert.assertEquals(errMessageLastNameMin.getText(), errorMessageMin);
		}
		else if (lastName.length() > Integer.parseInt(String.valueOf(maxLength))) {
			System.out.println("Checking max value..");
			isDisplayed = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(errMessageLastNameMax))
					.isDisplayed();
			System.out.println(errMessageLastNameMax);
			Assert.assertTrue(isDisplayed);
			isLastNameHealty = false;
			Assert.assertEquals(errMessageLastNameMax.getText(), errorMessageMax);
		}
		else{
			isLastNameHealty=true;
		}
		return this;	}
	
	/**
	 * Update email address
	 * @param email update email
	 * @return
	 * @throws InterruptedException
	 */
	private UserSettingsPage updateEmail(String email) throws InterruptedException {
		String requiredMessage = "Your e-mail is required.";
		String invalidEmail = "Your e-mail is invalid.";
		String errorMessageMin = "Your e-mail is required to be at least 5 characters.";
		String errorMessageMax = "Your e-mail cannot be longer than 50 characters.";

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtEmail));
		txtEmail.clear();
		txtEmail.sendKeys(email);
		
		boolean isDisplayed = false;
		String minLength = txtEmail.getAttribute("ng-minlength");
		String maxLength = txtEmail.getAttribute("ng-maxlength");

		Thread.sleep(1000);
		if (email.length() < Integer.parseInt(String.valueOf(minLength))) {
			System.out.println("Checking min value..");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(errMessageEmailMin));

			System.out.println(errMessageEmailMin.getText());
			isEmailHealty = false;
			Assert.assertEquals(errMessageEmailMin.getText(), errorMessageMin);
		}
		else if (email.length() > Integer.parseInt(String.valueOf(maxLength))) {
			System.out.println("Checking max value..");
			isDisplayed = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(errMessageEmailMax))
					.isDisplayed();
			System.out.println(errMessageEmailMax);
			Assert.assertTrue(isDisplayed);
			isEmailHealty = false;
			Assert.assertEquals(errMessageEmailMax.getText(), errorMessageMax);
		}
		else{
			isEmailHealty=true;
		}
		return this;	}
	
	
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
