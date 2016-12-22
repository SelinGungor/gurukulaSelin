package com.gurukula.pages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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
	@FindBy( linkText = "Save")
	private WebElement btnSave;
 
    //Help blocks
    /**
    *error case
    *
    **/
	@CacheLookup
	@FindBy( css = "[ng-show='editForm.name.$error.required']")
	private WebElement txtErrorMessage;
	
  	
 	//public methods
    /**
   	 *  Login scenario successfully
   	 *
   	 * @param  name name of the branch
   	 * @param  password the password of the user
   	 * @param  automaticLogin should be true, if needed
   	 * @return      
   	 */
   	public BranchPage enterBranchName(String branchName, String errorMessage)
   	{
   		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtBxName));
   		
   		txtBxName.sendKeys(branchName);
   		
   		
   		String minLength = txtBxName.getAttribute("ng-minlength");
   		String maxLength = txtBxName.getAttribute("ng-maxlength");
   		patternString  = txtBxName.getAttribute("ng-pattern");
   		
   		if(branchName.length() < Integer.parseInt(String.valueOf(minLength)))
   		{
   			Assert.assertEquals(txtErrorMessage.getText(), errorMessage);
   		}   		
   		if(branchName.length() > Integer.parseInt(String.valueOf(maxLength)))
   		{
   			Assert.assertEquals(txtErrorMessage.getText(), errorMessage);
   		}
  
   			Assert.assertTrue(validatePattern(patternString));
   		
   		return this;
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
