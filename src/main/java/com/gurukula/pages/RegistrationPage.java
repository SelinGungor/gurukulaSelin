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

public class RegistrationPage extends HomePage {

	protected WebDriver driver;
	private boolean isLoginHealty = false; private boolean isEmailHealty = false; private boolean isPasswordHealty = false; private boolean isConfirmPasswordHealty = false;

	/**
	 * Constructor
	 * @param driver
	 */
	public RegistrationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Web elements
	/**
	 * Login information for registration
	 */
	@CacheLookup
	@FindBy(css="[ng-model=\"registerAccount.login\"]")
	private WebElement txtLoginInfo;
	
	/**
	 * Email for registration
	 */
	@CacheLookup
	@FindBy(css="[ng-model=\"registerAccount.email\"]")
	private WebElement txtEmail;
	
	/**
	 * Password for registration
	 */
	@CacheLookup
	@FindBy(css="[ng-model=\"registerAccount.password\"]")
	private WebElement txtPassword;
	
	/**
	 * Confirm password
	 */
	@CacheLookup
	@FindBy(css="[ng-model=\"confirmPassword\"]")
	private WebElement txtConfirmPassword;
	
	/**
	 *Register button
	 */
	@CacheLookup
	@FindBy(css="[translate=\"register.form.button\"]")
	private WebElement btnRegisterSave;
	
	//errors
	//Login Info
	/**
	 * error required message
	 */
	@CacheLookup
	@FindBy(css="[translate=\"register.messages.validate.login.required\"]")
	private WebElement errLoginInfoRequired;
	
	/**
	 * errLoginInfoMin message
	 */
	@CacheLookup
	@FindBy(css="translate=\"register.messages.validate.login.minlength\"")
	private WebElement errLoginInfoMin;
	
	/**
	 * errLoginInfoMax message
	 */
	@CacheLookup
	@FindBy(css="translate=\"register.messages.validate.login.maxlenght\"")
	private WebElement errLoginInfoMax;
	
	/**
	 * errLoginInfoPattern
	 */
	@CacheLookup
	@FindBy(css="translate=\"register.messages.validate.login.pattern\"")
	private WebElement errLoginInfoPattern;
	
	//Email
	/**
	 * error required message
	 */
	@CacheLookup
	@FindBy(css="translate=\"global.messages.validate.email.required\"")
	private WebElement errEmailInfoRequired;
	
	/**
	 * errEmailInfoMin message
	 */
	@CacheLookup
	@FindBy(css="translate=\"global.messages.validate.email.minlength\"")
	private WebElement errEmailInfoMin;
	
	/**
	 * errEmailInfoMax message
	 */
	@CacheLookup
	@FindBy(css="[translate=\"global.messages.validate.email.maxlength\"]")
	private WebElement errEmailInfoMax;
	
	/**
	 * errEmailInfoValid
	 */
	@CacheLookup
	@FindBy(css="translate=\"global.messages.validate.email.invalid\"")
	private WebElement errEmailInfoValid;
	
	//Password
	/**
	 * error required message
	 */
	@CacheLookup
	@FindBy(css="translate=\"global.messages.validate.newpassword.required\"")
	private WebElement errPasswordInfoRequired;
	
	/**
	 * errPasswordInfoMin message
	 */
	@CacheLookup
	@FindBy(css="translate=\"global.messages.validate.newpassword.minlength\"")
	private WebElement errPasswordInfoMin;
	
	/**
	 * errPasswordnInfoMax message
	 */
	@CacheLookup
	@FindBy(css="translate=\"global.messages.validate.newpassword.maxlength\"")
	private WebElement errPasswordInfoMax;
	
	/**
	 * errPasswordInfoPattern
	 */
	@CacheLookup
	@FindBy(css="translate=\"global.messages.validate.newpassword.pattern\"")
	private WebElement errPasswordInfoPattern;
	
	//Confirm Password
		/**
		 * errConfirmPasswordInfoRequired
		 */
		@CacheLookup
		@FindBy(css="translate=\"global.messages.validate.confirmpassword.required\"")
		private WebElement errConfirmPasswordInfoRequired;
		
		/**
		 * errConfirmPasswordInfoMin message
		 */
		@CacheLookup
		@FindBy(css="translate=\"global.messages.validate.confirmpassword.minlength\"")
		private WebElement errConfirmPasswordInfoMin;
		
		/**
		 * errConfirmPasswordnInfoMax message
		 */
		@CacheLookup
		@FindBy(css="translate=\"global.messages.validate.confirmpassword.maxlength\"")
		private WebElement errConfirmPasswordInfoMax;
		
		/**
		 * errConfirmPasswordInfoPattern
		 */
		@CacheLookup
		@FindBy(css="translate=\"global.messages.validate.confirmpassword.pattern\"")
		private WebElement errConfirmPasswordInfoPattern;
	
		/**
		 * passwordDontMatchMessage
		 */
		@CacheLookup
		@FindBy(css="[ng-show=\"doNotMatch\"]")
		private WebElement passwordDontMatchMessage;
		
		/**
		 * successRegistrationMessage 
		 */
		@CacheLookup
		@FindBy(css="[ng-show=\"success\"]")
		private WebElement successRegistrationMessage;
		
		
		
	
	  //Methods
		
		/**
		 * Register a new account
		 * @param login
		 * @param emailAddress
		 * @param password
		 * @param confirmPassword
		 * @return
		 * @throws InterruptedException
		 */
		public RegistrationPage registerANewAccount(String login, String emailAddress, String password, String confirmPassword) throws InterruptedException
		{
			enterLoginInfo(login);
					isLoginHealty = MemoryStorage.isHealty;	
			enterEmail(emailAddress);
					isEmailHealty = MemoryStorage.isHealty;
			enterPassword(password);
					isPasswordHealty = MemoryStorage.isHealty;
			enterConfirmPassword(confirmPassword);
					isConfirmPasswordHealty = MemoryStorage.isHealty;
			if(isLoginHealty && isEmailHealty && isPasswordHealty && isConfirmPasswordHealty)
			{
				clickSaveRegistrationButton();
				if(checkPasswordMatch(password, confirmPassword))
				{
				checkIfAccountCreated();
				}
			}
			else{
				Assert.assertFalse(btnRegisterSave.isEnabled());
			}
			
			return new RegistrationPage(driver);
		}
		
		/**
		 * Write text on login text box and controls if the warnings are okay
		 * @param loginInfoText
		 * @return
		 * @throws InterruptedException
		 */
		public RegistrationPage enterLoginInfo(String loginInfoText) throws InterruptedException
		{
			System.out.println("Checking name field..");
			return enterText(RegistrationPage.class,txtLoginInfo,loginInfoText, errLoginInfoRequired, "Your login is required.",
													errLoginInfoMin,"Your login is required to be at least 1 character.",
												    errLoginInfoMax,"Your login cannot be longer than 50 characters."
												   // errLoginInfoPattern, "Your login can only contain lower-case letters and digits.",
												    );

		}
		
		/**
		 * write text on emailAddress text box and controls if the warnings are okay
		 * @param emailAddress
		 * @return
		 * @throws InterruptedException
		 */
		public RegistrationPage enterEmail(String emailAddress) throws InterruptedException
		{
			System.out.println("Checking email field..");
			return enterText(RegistrationPage.class,txtEmail,emailAddress, errEmailInfoRequired, "Your e-mail is required.",
													errEmailInfoMin,"Your e-mail is required to be at least 5 characters.",
												    errEmailInfoMax,"Your e-mail cannot be longer than 50 characters."
												    );

		}
		
		/**
		 * write text on password text box and controls if the warnings are okay
		 * @param emailAddress
		 * @return
		 * @throws InterruptedException
		 */
		public RegistrationPage enterPassword(String password) throws InterruptedException
		{
			System.out.println("Checking password field..");
			return enterText(RegistrationPage.class,txtPassword,password, errPasswordInfoRequired, "Your password is required.",
													errPasswordInfoMin,"Your password is required to be at least 5 characters.",
												    errPasswordInfoMax,"Your password cannot be longer than 50 characters."
												    );

		}

		/**
		 * write text on confirm password text box and controls if the warnings are okay
		 * @param emailAddress
		 * @return
		 * @throws InterruptedException
		 */
		public RegistrationPage enterConfirmPassword(String confirmPassword) throws InterruptedException
		{
			System.out.println("Checking password field..");
			return enterText(RegistrationPage.class,txtConfirmPassword,confirmPassword, errConfirmPasswordInfoRequired, "Your confirmation password is required.",
													errConfirmPasswordInfoMin,"Your confirmation password is required to be at least 5 characters.",
												    errConfirmPasswordInfoMax,"Your confirmation password cannot be longer than 50 characters."
												    );

		}

		/**
		 * Check if passwords match
		 * @param password
		 * @param confirmPassword
		 * @return
		 */
		private boolean checkPasswordMatch(String password, String confirmPassword)
		{
			if(password != confirmPassword)
			{
				boolean isDisplayed = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(passwordDontMatchMessage))
						.isDisplayed();
				Assert.assertTrue(isDisplayed);
				System.out.println(passwordDontMatchMessage.getText());
				if(passwordDontMatchMessage.getText()=="The password and its confirmation do not match!")
				{
					return true;
				}
				else{
					return false;
				}				
			}
			else
			{
				return true;
			}
		}
		
		private RegistrationPage clickSaveRegistrationButton()
		{
			clickElement(driver, 10, btnRegisterSave);
			return this;
		}
		
		private RegistrationPage checkIfAccountCreated()
		{
			boolean isDisplayed = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(successRegistrationMessage))
					.isDisplayed();
			Assert.assertTrue(isDisplayed);
			System.out.println(successRegistrationMessage.getText());
			Assert.assertEquals(passwordDontMatchMessage.getText(), "Registration saved!");
			return new RegistrationPage(driver);
			
		}
			
}
