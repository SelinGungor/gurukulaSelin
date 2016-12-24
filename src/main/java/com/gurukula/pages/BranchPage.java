package com.gurukula.pages;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * @author Selin Gungor <selingungor01@gmail.com>
 * @version 1.0
 * @since 1.0 (the version of the package this class was first added to)
 */
public class BranchPage extends LoginPage {

	protected WebDriver driver;
	private static int id = 0;

	public BranchPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private Pattern pattern;
	private Matcher matcher;
	private static String patternString = "";

	// Web Elements
	/**
	 * create a new branch button
	 *
	 **/
	@CacheLookup
	@FindBy(css = "[data-target='#saveBranchModal']")
	public WebElement btnCreateANewBranch;

	// Add new branch form elements

	/**
	 * add new branch dialog
	 *
	 **/
	@CacheLookup
	@FindBy(id = "myBranchLabel")
	private WebElement dialogAddNewBranch;

	/**
	 * name text box
	 *
	 **/
	@CacheLookup
	@FindBy(css = "[ng-model='branch.name']")
	private WebElement txtBxName;

	/**
	 * code text box
	 *
	 **/
	@CacheLookup
	@FindBy(css = "[ng-model='branch.code']")
	private WebElement txtBxCode;

	/**
	 * save button
	 *
	 **/
	@CacheLookup
	@FindBy(css = "[class=\"modal-footer\"] [class=\"btn btn-primary\"]")
	private WebElement btnSave;

	// Help blocks
	/**
	 * error case
	 *
	 **/
	@CacheLookup
	@FindBy(css = "[ng-show='editForm.name.$error.required']")
	private WebElement txtErrorMessage;

	/**
	 * error min text case
	 *
	 **/
	@CacheLookup
	@FindBy(css = "[ng-show=\"editForm.name.$error.minlength\"]")
	private WebElement txtErrNameMinChar;

	/**
	 * error max text case
	 *
	 **/
	@CacheLookup
	@FindBy(css = "[ng-show=\"editForm.name.$error.maxlength\"]")
	private WebElement txtErrNameMaxChar;

	/**
	 * error name pattern message
	 *
	 **/
	@CacheLookup
	@FindBy(css = "[ng-show=\"editForm.name.$error.pattern\"]")
	private WebElement txtErrNamePatternChar;

	/**
	 * error min code case
	 *
	 **/
	@CacheLookup
	@FindBy(css = "[ng-show=\"editForm.code.$error.minlength\"]")
	private WebElement txtErrCodeMinChar;

	/**
	 * error max code case
	 *
	 **/
	@CacheLookup
	@FindBy(css = "[ng-show=\"editForm.code.$error.maxlength\"]")
	private WebElement txtErrCodeMaxChar;

	/**
	 * error code pattern message
	 *
	 **/
	@CacheLookup
	@FindBy(css = "[ng-show=\"editForm.code.$error.pattern\"]")
	private WebElement txtErrCodePatternChar;

	String cssBranch = "";
	/**
	 * branchID info gets from id variable
	 *
	 **/
	// @FindBy( how = How.CSS, using = cssBranch)
	// private WebElement branchID;

	/**
	 * View detail button
	 */
	@CacheLookup
	@FindBy(css = "[ui-sref=\"branchDetail({id:branch.id})\"][type=\"submit\"]")
	private WebElement btnViewDetail;

	//Confirm Delete Operatin Dialog
	/**
	 * Confirm Delete Dialog
	 */
	@CacheLookup
	@FindBy(css = "[ng-submit=\"confirmDelete(branch.id)\"]")
	private WebElement dialogConfirmDelete;
	
	
	/**
	 * Confirm Delete Operation Dialog Delete Buttom
	 */
	@CacheLookup
	@FindBy(css = "[ng-disabled=\"deleteForm.$invalid\"]")
	private WebElement btnDeleteOnDialog;

	
	
	// public methods

	/**
	 * Creates new branch
	 *
	 * @param name
	 *            name of the branch
	 * @param password
	 *            the password of the user
	 * @param automaticLogin
	 *            should be true, if needed
	 * @return
	 * @throws InterruptedException
	 */
	public BranchPage createNewBranch(String name, String code) throws InterruptedException {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(btnCreateANewBranch)).click();
		boolean checkDialogOpened = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtBxName))
				.isDisplayed();
		System.out.println("Creates new branch..");
		Assert.assertTrue(checkDialogOpened);

		enterBranchName(name);
		enterBranchCode(code);
		clickSaveButton();

		return this;
	}

	/**
	 * enterBranchName
	 *
	 * @param branchName
	 *            name of the branch
	 * @return
	 * @throws InterruptedException
	 */
	private BranchPage enterBranchName(String branchName) throws InterruptedException {
		String errorMessageMin = "This field is required to be at least 5 characters.";
		String errorMessageMax = "This field cannot be longer than 20 characters.";
		String errorMessagePattrn = "This field should follow pattern ^[a-zA-Z\\s]*$.";

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtBxName));

		txtBxName.sendKeys(branchName);

		boolean isDisplayed = false;
		String minLength = txtBxName.getAttribute("ng-minlength");
		String maxLength = txtBxName.getAttribute("ng-maxlength");
		patternString = txtBxName.getAttribute("ng-pattern");
		Thread.sleep(1000);
		if (branchName.length() < Integer.parseInt(String.valueOf(minLength))) {
			System.out.println("Checking min value..");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtErrNameMinChar));

			System.out.println(txtErrNameMinChar.getText());
			// Assert.assertTrue(isDisplayed);
			Assert.assertEquals(txtErrNameMinChar.getText(), errorMessageMin);
		}
		if (branchName.length() > Integer.parseInt(String.valueOf(maxLength))) {
			System.out.println("Checking max value..");
			isDisplayed = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtErrNameMaxChar))
					.isDisplayed();
			System.out.println(txtErrNameMaxChar);
			Assert.assertTrue(isDisplayed);
			Assert.assertEquals(txtErrNameMaxChar.getText(), errorMessageMax);
		}

		// Assert.assertTrue(validatePattern(patternString));

		return this;
	}

	/**
	 * enterBranchCode
	 *
	 * @param branchCode
	 *            code of the branch
	 * @return
	 * @throws InterruptedException
	 */
	private BranchPage enterBranchCode(String branchCode) throws InterruptedException {
		String errorMessageMin = "This field is required to be at least 2 characters.";
		String errorMessageMax = "This field cannot be longer than 10 characters.";
		String errorMessagePattern = "This field should follow pattern ^[A-Z0-9]*$.";

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtBxCode));
		Thread.sleep(100);
		txtBxCode.sendKeys(branchCode);

		boolean isDisplayed = false;
		String minLength = txtBxCode.getAttribute("ng-minlength");
		String maxLength = txtBxCode.getAttribute("ng-maxlength");
		patternString = txtBxCode.getAttribute("ng-pattern");
		Thread.sleep(1000);
		if (branchCode.length() < Integer.parseInt(String.valueOf(minLength))) {
			System.out.println("Checking min value..");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtErrCodeMinChar));

			System.out.println(txtErrCodeMinChar.getText());
			// Assert.assertTrue(isDisplayed);
			Assert.assertEquals(txtErrCodeMinChar.getText(), errorMessageMin);
		}
		if (branchCode.length() > Integer.parseInt(String.valueOf(maxLength))) {
			System.out.println("Checking max value..");
			isDisplayed = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtErrCodeMaxChar))
					.isDisplayed();
			System.out.println(txtErrCodeMaxChar);
			Assert.assertTrue(isDisplayed);
			Assert.assertEquals(txtErrCodeMaxChar.getText(), errorMessageMax);
		}

		// TO DO: Assert.assertTrue(validatePattern(patternString));

		return this;
	}

	/**
	 * click save button
	 */
	private void clickSaveButton() {
		//increaseID();
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(btnSave)).click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(btnCreateANewBranch));
	}

	/**
	 * view branch detail
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public BranchDetailPage viewBranchDetail() throws InterruptedException {
		System.out.println("Looking at branch detail page..");
		Thread.sleep(100);
		WebElement element = getLastItem();
		String branchID =getID(element.getText());
		System.out.println("the id of last item : "+branchID);

    	String css = cssBranch(branchID);
    	
    	
    	WebElement elementItemBranch = driver.findElement(By.cssSelector(css));
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	System.out.println(branchID);
    	executor.executeScript("$('[href=\"#/branch/"+branchID+"\"]').click();", elementItemBranch);
    	
  
		return new BranchDetailPage(driver);

	}
	
	/**
	 * delete branch 
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public BranchDetailPage deleteBranch(int ID) throws InterruptedException {
		System.out.println("Deleting branch..");
		Thread.sleep(100);
		    	
		List<WebElement> allBranches = driver.findElements(By.cssSelector(".ng-scope [ng-repeat=\"branch in branches\"]") );

		System.out.println("Branch will be deleted : " +allBranches.get(ID));

    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("$('[class=\"table table-striped\"] tbody tr:nth-child("+ID+") [ng-click=\"delete(branch.id)\"]').click()");
    	
    	boolean exist = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(dialogConfirmDelete)).isDisplayed();
    	if(exist)
    	{
    		btnDeleteOnDialog.click();
    	}    	
  
		return new BranchDetailPage(driver);

	}

	public void PatternValidator() {
		pattern = Pattern.compile(patternString);
	}

	/**
	 * Validate username with regular expression
	 * 
	 * @param username
	 *            username for validation
	 * @return true valid username, false invalid username
	 */
	public boolean validatePattern(final String username) {

		matcher = pattern.matcher(username);
		return matcher.matches();

	}

	private String cssBranch(String id) {
		return cssBranch = "a[href=\"#/branch/" + id + "\"]";
	}

	private WebElement getLastItem() throws InterruptedException {
		Thread.sleep(1000);
		List<WebElement> allBranches = driver.findElements( By.cssSelector(".ng-scope [ng-repeat=\"branch in branches\"]") );

		int itemCountBranchList = allBranches.size() - 1;

		System.out.println("branch size is : " +itemCountBranchList);
		allBranches.get(itemCountBranchList);
		System.out.println("last element is : " +allBranches.get(itemCountBranchList));
		return allBranches.get(itemCountBranchList);
	}
	
	/**
	 * Gets the last item
	 * @param text
	 * @return
	 */
	private String getID(String text)
	{
		int iend = text.indexOf(" "); //this finds the first occurrence of "." 
		
		String subString = "";
		if (iend != -1){ 
		subString = text.substring(0 , iend); //this will give abc
		}
		return subString;
	}

}
