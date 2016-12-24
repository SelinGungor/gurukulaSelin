package com.gurukula.pages;

import org.openqa.selenium.By;
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

public class StaffPage extends HomePage {
	
	protected WebDriver driver;


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
	 public void createNewStaff(String staffName,String branchName) throws InterruptedException
	 {
		 clickElement(driver, 10, btnCreateNewStaff);
		 enterStaffName(staffName);
		 selectBranch(branchName);
		 
		 
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

			txtNameStaff.sendKeys(staffName);
			txtNameStaff.sendKeys(Keys.RETURN);

			boolean isDisplayed = false;
			String minLength = txtNameStaff.getAttribute("ng-minlength");
			String maxLength = txtNameStaff.getAttribute("ng-maxlength");
			String patternString = txtNameStaff.getAttribute("ng-pattern");
			Thread.sleep(1000);
			
			System.out.println(minLength);
			if (staffName.length() < Integer.parseInt(String.valueOf(minLength))) { //should fail here, min char should not be allowed!
				System.out.println("Checking min value..");
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtErrNameStaffMinChar));

				System.out.println(txtErrNameStaffMinChar.getText());
				// Assert.assertTrue(isDisplayed);
				Assert.assertEquals(txtErrNameStaffMinChar.getText(), errorMessageMin);
			}
			if (staffName.length() > Integer.parseInt(String.valueOf(maxLength))) {
				System.out.println("Checking max value..");
				isDisplayed = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtErrNameStaffMaxChar))
						.isDisplayed();
				System.out.println(txtErrNameStaffMaxChar);
				Assert.assertTrue(isDisplayed);
				Assert.assertEquals(txtErrNameStaffMaxChar.getText(), errorMessageMax);
			}

			// Assert.assertTrue(validatePattern(patternString));

			return this;
		}
		
		/**
		 * selects the branch from dropDownMenu
		 * @param branchName name of one of existing branches 
		  */
		private void selectBranch(String branchName)
		{
			clickElement(driver, 10, dropDownListBranch);
			Select select = new Select(dropDownListBranch);
			 //Select select = new Select(driver.findElement(By.id("DeliveryHour")));
        	 //select.selectByValue("5");
			//select.deselectAll();
			select.selectByVisibleText(branchName);
		}

		
}
