package com.gurukula.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.gurukula.generic.MemoryStorage;

public class StaffPage extends LoginPage {
	
	protected WebDriver driver;
	private boolean isNameHealty = false;
	String cssStaff = "";

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
	protected WebElement btnCreateNewStaff;
	 
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
	 @FindBy(css = "[translate=\"entity.validation.minlength\"]")
	 private WebElement txtErrNameStaffMinChar;
	 
	 /**
	 * error max text staff case
	 *
	 **/
	 @CacheLookup
	 @FindBy(css = "[translate=\"entity.validation.maxlength\"]")
	 private WebElement txtErrNameStaffMaxChar;
	 
	 /**
	 * error max text staff case
	 *
	 **/
	 @CacheLookup
	 @FindBy(css = "[translate=\"entity.validation.required\"]")
	 private WebElement txtErrStaffRequired;
	 
	 
	 /**
	 * pattern text error 
	 *
	 **/
	 @CacheLookup
	 @FindBy(css = "[translate=\"entity.validation.pattern\"]")
	 private WebElement txtErrPatternStaff;
	 
	 //Pagination Elements
	 /**
	 * nextPage
	 *
	 **/
	 @CacheLookup
	 @FindBy(css = ".pager li:nth-child(3)")
	 private WebElement nextPage;
	 
	
	//Methods
	 
	 /**
	  * Creates new staff
	 * @throws InterruptedException 
	  */
	 public StaffPage createNewStaff(String staffName,String branchName) throws InterruptedException
	 {
		 clickElement(driver, 10, btnCreateNewStaff);
		 createAStaff(staffName,branchName);
		 return this;
	}
	 
	 
	 /**
	  * create a staff internal function
	  * @param staffName
	  * @param branchName
	  * @throws InterruptedException
	  */
	   private void createAStaff(String staffName, String branchName) throws InterruptedException {
		    enterStaffName(staffName);
			 isNameHealty = MemoryStorage.isHealty;
			 selectBranch(branchName);
			 if(isNameHealty){
				 clickSaveButton();
			 }
			 else{
				 clickCancelButton();
			 }
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
			//String errorMessagePattrn = "This field should follow pattern ^[a-zA-Z\\s]*$.";
			String requiredMessage = "This field is required.";
			
			enterText(StaffPage.class, txtNameStaff, staffName, txtErrStaffRequired, requiredMessage, txtErrNameStaffMinChar, errorMessageMin, txtErrNameStaffMaxChar, errorMessageMax);
			

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
	    	Thread.sleep(1000);
	    	createAStaff(updatedName, updatedBranch);
			return new StaffPage(driver);
		}
		
		/**
		 * view staff detail
		 * 
		 * @return
		 * @throws InterruptedException
		 */
		public StaffDetailPage viewStaffDetail() throws InterruptedException {
			System.out.println("Looking at branch detail page..");
			Thread.sleep(100);
			WebElement element = getLastItem();
			String staffID =getID(element.getText());
			System.out.println("the id of last item : "+staffID);

	    	String css = cssStaff(staffID);
	    	
	    	
	    	WebElement elementItemBranch = driver.findElement(By.cssSelector(css));
	    	JavascriptExecutor executor = (JavascriptExecutor)driver;
	    	System.out.println(staffID);
	    	executor.executeScript("$('[href=\"#/staff/"+staffID+"\"]').click();", elementItemBranch);
	    	
	  
			return new StaffDetailPage(driver);

		}
		
		private String cssStaff(String id) {
			return cssStaff = "a[href=\"#/staff/" + id + "\"]";
		}
		
		/**
		 * Gets the last staff
		 * @return
		 * @throws InterruptedException
		 */
		private WebElement getLastItem() throws InterruptedException {
			Thread.sleep(1000);
			List<WebElement> allStaffs = driver.findElements( By.cssSelector(".ng-scope [ng-repeat=\"staff in staffs\"]") );

			int itemCountStaffList = allStaffs.size() - 1;

			System.out.println("branch size is : " +itemCountStaffList);
			allStaffs.get(itemCountStaffList);
			System.out.println("last element is : " +allStaffs.get(itemCountStaffList));
			return allStaffs.get(itemCountStaffList);
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
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(btnCreateNewStaff)).isDisplayed();
			return this;
		}
		
		/**
		 * click Cancel Button Close Dialog
		 */
		private StaffPage clickCancelButton()
		{
			clickElement(driver, 10, btnCancel);
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(btnCreateNewStaff)).isDisplayed();
			return this;
		}
		
		/**
		 * Check pagination
		 * @return
		 * @throws InterruptedException
		 */
		public StaffPage checkPagination(boolean goNextPage) throws InterruptedException
		{	
			Thread.sleep(1000);
			List<WebElement> allStaffs = driver.findElements( By.cssSelector(".ng-scope [ng-repeat=\"staff in staffs\"]") );
	
			int itemCountStaffList = allStaffs.size();
			
			if(itemCountStaffList == 20)
			{
				checkNextPageButton();
				if(goNextPage)
				{
					 goNextPage();
				}
			}	
			return this;
			
		}
		
		/**
		 * check pagination exist
		 */
		private void checkNextPageButton()
		{
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(nextPage)).isDisplayed();
		}
		
		/**
		 * go next page
		 */
		public void goNextPage()
		{
			clickElement(driver, 10, nextPage);
		}
		
}
