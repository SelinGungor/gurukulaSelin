package com.gurukula.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StaffPage extends LoginPage {
	
	protected WebDriver driver;
	private boolean isHealty = false;

	public StaffPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Web Elements
	

	/**
	 * Create new staff button
	 */
	 @CacheLookup
	 @FindBy( css = "[data-target=\"#saveStaffModal\"]")
	 private WebElement btnCreateNewStaff;
	 
	 /**
	  * Staff Dialog
	  */
	 @CacheLookup
	 @FindBy( id = "myStaffLabel")
	 private WebElement dialogStaff;
	 
	 /**
	  * Save Button on Dialog
	  */
	 @CacheLookup
	 @FindBy( css = "[translate=\"entity.action.save\"]")
	 private WebElement saveButtonOnDialog;
	 
	 /**
	 * cancel button
	 *
	 **/
	 @CacheLookup
	 @FindBy(css = "#saveStaffModal [class=\"btn btn-default\"]")
	 private WebElement btnCancel;
	 
	 /**
	  * textbox name of staff
	  */
	 @CacheLookup
	 @FindBy( css = "[ng-model=\"staff.name\"]")
	 private WebElement txtNameStaff;
	 
	 /**
	  * branch drop down list
	  */
	 @CacheLookup
	 @FindBy( css = "[ng-model=\"staff.related_branchId\"]")
	 private WebElement dropDownListBranch;
	 
	 /**
	 * error min text staff case
	 *
	 **/
	 @CacheLookup
	 @FindBy(css = "[ng-show=\"editForm.name.$error.minlength\"]")
	 private WebElement txtErrNameStaffMinChar;
	 
	 /**
	 * error max text staff case
	 *
	 **/
	 @CacheLookup
	 @FindBy(css = "[ng-show=\"editForm.name.$error.maxlength\"]")
	 private WebElement txtErrNameStaffMaxChar;
	 
	 /**
	 * pattern text error 
	 *
	 **/
	 @CacheLookup
	 @FindBy(css = "ng-show=\"editForm.name.$error.pattern\"")
	 private WebElement txtErrPatternStaff;
	
	//Methods
	 
	 /**
	  * Creates new staff
	 * @throws InterruptedException 
	  */
	 public StaffPage createNewStaff(String staffName,String branchName) throws InterruptedException
	 {
		 clickElement(driver, 10, btnCreateNewStaff);
		 enterStaffName(staffName);
		 selectBranch(branchName);
		 if(isHealty){
			 clickSaveButton();
		 }
		 else{
			 clickCancelButton();
		 }
		return this;		 
	 }
	 
	 
	   /**
		 * enterStaffhName
		 *
		 * @param staffName name of the branch		 *            
		 * @return
		 * @throws InterruptedException
		 */
		private StaffPage enterStaffName(String staffName) throws InterruptedException {
			String errorMessageMin = "This field is required to be at least 5 characters.";
			String errorMessageMax = "This field cannot be longer than 50 characters.";
			String errorMessagePattrn = "This field should follow pattern ^[a-zA-Z\\s]*$.";

			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtNameStaff));

			txtNameStaff.clear();
			txtNameStaff.sendKeys(staffName);

			boolean isDisplayed = false;
			String minLength = txtNameStaff.getAttribute("ng-minlength");
			String maxLength = txtNameStaff.getAttribute("ng-maxlength");
			String patternString = txtNameStaff.getAttribute("ng-pattern");
			Thread.sleep(1000);
			
			System.out.println(minLength);
			if (staffName.length() < Integer.parseInt(String.valueOf(minLength))) { 
				System.out.println("Checking min value..");
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtErrNameStaffMinChar));

				System.out.println(txtErrNameStaffMinChar.getText());
				isHealty=false;
				Assert.assertEquals(txtErrNameStaffMinChar.getText(), errorMessageMin);
			}
			else if (staffName.length() > Integer.parseInt(String.valueOf(maxLength))) {
				System.out.println("Checking max value..");
				isDisplayed = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtErrNameStaffMaxChar))
						.isDisplayed();
				System.out.println(txtErrNameStaffMaxChar);
				Assert.assertTrue(isDisplayed);
				isHealty=false;
				Assert.assertEquals(txtErrNameStaffMaxChar.getText(), errorMessageMax);
			}
			else{
				isHealty=true;
			}

			// Assert.assertTrue(validatePattern(patternString));

			return this;
		}
		
		
		/**
		 * edit branch 
		 * 
		 * @param ID id of branch to edit
		 * @throws InterruptedException
		 */
		public StaffPage editStaff(int ID, String updatedName, String updatedBranch) throws InterruptedException{
			System.out.println("Updating staff..");
			Thread.sleep(100);
			    	
			List<WebElement> allBranches = driver.findElements(By.cssSelector(".ng-scope [ng-repeat=\"staff in staffs\"]") );

			System.out.println("Staff will be updated : " +allBranches.get(ID));

	    	JavascriptExecutor executor = (JavascriptExecutor)driver;
	    	executor.executeScript("$('[class=\"table table-striped\"] tbody tr:nth-child("+ID+") [ng-click=\"showUpdate(staff.id)\"]').click()");
	    	
	    	enterStaffName(updatedName);
	    	selectBranch(updatedBranch);
	    	 if(isHealty){
				 clickSaveButton();
			 }
			 else{
				 clickCancelButton();
			 }
			return new StaffPage(driver);
		}
		
		/**
		 * selects the branch from dropDownMenu
		 * @param branchName name of one of existing branches 
		  */
		private void selectBranch(String branchName)
		{
			clickElement(driver, 10, dropDownListBranch);
			Select select = new Select(dropDownListBranch);
			select.selectByVisibleText(branchName);
		}

		/**
		 * Save button on create new staff dialog
		 */
		private StaffPage clickSaveButton()
		{
			clickElement(driver, 10, saveButtonOnDialog);
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(btnCreateNewStaff));
			return this;
		}
		
		/**
		 * click Cancel Button Close Dialog
		 */
		private StaffPage clickCancelButton()
		{
			clickElement(driver, 10, btnCancel);
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(btnCreateNewStaff));
			return this;
		}
		
}
