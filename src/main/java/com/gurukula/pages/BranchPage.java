package com.gurukula.pages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * @author      Selin Gungor <selingungor01@gmail.com>
 * @version     1.0   
 * @since       1.0 (the version of the package this class was first added to)
 */
public class BranchPage extends LoginPage {
	
	protected WebDriver driver;
    
    public BranchPage(WebDriver driver){
	       super(driver);
	 	   this.driver = driver;
	 	   PageFactory.initElements(driver, this);
    	}
        	
    private Pattern pattern;
	private Matcher matcher;
	private static String patternString = "";
    
	//Web Elements
    /**
     * create a new branch button
     *
     **/
    @CacheLookup
    @FindBy( css = "[data-target='#saveBranchModal']")
    private WebElement btnCreateANewBranch;
    
    //Add new branch form elements
    
    /**
     * add new branch dialog
     *
     **/
    @CacheLookup
    @FindBy( id = "myBranchLabel")
    private WebElement dialogAddNewBranch;
    
    /**
     * name text box
     *
     **/
    @CacheLookup
    @FindBy( css = "[ng-model='branch.name']")
    private WebElement txtBxName;
     
    /**
    * code text box
    *
    **/
    @CacheLookup
    @FindBy( css = "[ng-model='branch.code']")
    private WebElement txtBxCode;
    
    /**
    * save button
    *
    **/
	@CacheLookup
	@FindBy( css = "[class=\"modal-footer\"] [class=\"btn btn-primary\"]")
	private WebElement btnSave;
 
    //Help blocks
    /**
    *error case
    *
    **/
	@CacheLookup
	@FindBy( css = "[ng-show='editForm.name.$error.required']")
	private WebElement txtErrorMessage;
	
    /**
    *error min text case
    *
    **/
	@CacheLookup
	@FindBy( css = "[ng-show=\"editForm.name.$error.minlength\"]")
	private WebElement txtErrNameMinChar;
	
    /**
    *error max text case
    *
    **/
	@CacheLookup
	@FindBy( css = "[ng-show=\"editForm.name.$error.maxlength\"]")
	private WebElement txtErrNameMaxChar;
	
    /**
    *error name pattern message
    *
    **/
	@CacheLookup
	@FindBy( css = "[ng-show=\"editForm.name.$error.pattern\"]")
	private WebElement txtErrNamePatternChar;
	
    /**
    *error min code case
    *
    **/
	@CacheLookup
	@FindBy( css = "[ng-show=\"editForm.code.$error.minlength\"]")
	private WebElement txtErrCodeMinChar;
	
    /**
    *error max code case
    *
    **/
	@CacheLookup
	@FindBy( css = "[ng-show=\"editForm.code.$error.maxlength\"]")
	private WebElement txtErrCodeMaxChar;
	
    /**
    *error code pattern message
    *
    **/
	@CacheLookup
	@FindBy( css = "[ng-show=\"editForm.code.$error.pattern\"]")
	private WebElement txtErrCodePatternChar;

  	
 	//public methods
    
	/**
   	 *  Creates new branch
   	 *
   	 * @param  name name of the branch
   	 * @param  password the password of the user
   	 * @param  automaticLogin should be true, if needed
   	 * @return      
	 * @throws InterruptedException 
   	 */
	public BranchPage createNewBranch(String name, String code) throws InterruptedException
	{
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(btnCreateANewBranch)).click();
		boolean checkDialogOpened = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtBxName)).isDisplayed();
		System.out.println("Creates new branch..");
		Assert.assertTrue(checkDialogOpened);
		
		enterBranchName(name);
		enterBranchCode(code);
		clickSaveButton();
		
		return this;
	}
	
	
	
	
   	/**
   	 *  enterBranchName
   	 *
   	 * @param  branchName name of the branch
   	 * @return      
	 * @throws InterruptedException 
   	 */
   	private BranchPage enterBranchName(String branchName) throws InterruptedException
   	{
		String errorMessageMin = "This field is required to be at least 5 characters.";
		String errorMessageMax = "This field cannot be longer than 20 characters.";
		String errorMessagePattrn = "This field should follow pattern ^[a-zA-Z\\s]*$.";
   		
   		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtBxName));
   		
   		txtBxName.sendKeys(branchName);
   		
   		boolean isDisplayed = false;
   		String minLength = txtBxName.getAttribute("ng-minlength");
   		String maxLength = txtBxName.getAttribute("ng-maxlength");
   		patternString  = txtBxName.getAttribute("ng-pattern");
   		Thread.sleep(1000);
   		if(branchName.length() < Integer.parseInt(String.valueOf(minLength)))
   		{
   			System.out.println("Checking min value..");
   			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtErrNameMinChar));

   			System.out.println(txtErrNameMinChar.getText());
   			//Assert.assertTrue(isDisplayed);
   			Assert.assertEquals(txtErrNameMinChar.getText(), errorMessageMin);
   		}   		
   		if(branchName.length() > Integer.parseInt(String.valueOf(maxLength)))
   		{
   			System.out.println("Checking max value..");
   			isDisplayed = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtErrNameMaxChar)).isDisplayed();
   			System.out.println(txtErrNameMaxChar);
   			Assert.assertTrue(isDisplayed);
   			Assert.assertEquals(txtErrNameMaxChar.getText(), errorMessageMax);
   		}
  
   			//Assert.assertTrue(validatePattern(patternString));
   		
   		return this;
   	}
   	
   	/**
   	 *  enterBranchCode
   	 *
   	 * @param  branchCode code of the branch
   	 * @return      
	 * @throws InterruptedException 
   	 */
   	private BranchPage enterBranchCode(String branchCode) throws InterruptedException
   	{
		String errorMessageMin = "This field is required to be at least 2 characters.";
		String errorMessageMax = "This field cannot be longer than 10 characters.";
		String errorMessagePattern = "This field should follow pattern ^[A-Z0-9]*$.";
   		
   		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtBxCode));
   		Thread.sleep(100);
   		txtBxCode.sendKeys(branchCode);
   		
   		boolean isDisplayed = false;
   		String minLength = txtBxCode.getAttribute("ng-minlength");
   		String maxLength = txtBxCode.getAttribute("ng-maxlength");
   		patternString  = txtBxCode.getAttribute("ng-pattern");
   		Thread.sleep(1000);
   		if(branchCode.length() < Integer.parseInt(String.valueOf(minLength)))
   		{
   			System.out.println("Checking min value..");
   			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtErrCodeMinChar));

   			System.out.println(txtErrCodeMinChar.getText());
   			//Assert.assertTrue(isDisplayed);
   			Assert.assertEquals(txtErrCodeMinChar.getText(), errorMessageMin);
   		}   		
   		if(branchCode.length() > Integer.parseInt(String.valueOf(maxLength)))
   		{
   			System.out.println("Checking max value..");
   			isDisplayed = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtErrCodeMaxChar)).isDisplayed();
   			System.out.println(txtErrCodeMaxChar);
   			Assert.assertTrue(isDisplayed);
   			Assert.assertEquals(txtErrCodeMaxChar.getText(), errorMessageMax);
   		}
  
   		//TO DO:	Assert.assertTrue(validatePattern(patternString));
   		
   		return this;
   	}
   	
    /**
	   * click save button
	   */
   	private void clickSaveButton(){
   	 new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(btnSave)).click();   		
   	}

	  public void PatternValidator(){
		  pattern = Pattern.compile(patternString);
	  }

	  /**
	   * Validate username with regular expression
	   * @param username username for validation
	   * @return true valid username, false invalid username
	   */
	  public boolean validatePattern(final String username){

		  matcher = pattern.matcher(username);
		  return matcher.matches();

	  }
	  
	  


}
