package com.gurukula.pages;

import org.openqa.selenium.JavascriptExecutor;
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
public class StaffDetailPage extends StaffPage{

	protected WebDriver driver;
	JavascriptExecutor js = null;
    
	 /**
	 * constructor
	 * @param driver
	 */
    public StaffDetailPage(WebDriver driver){
	       super(driver);
	 	   this.driver = driver;
	 	   PageFactory.initElements(driver, this);
    	}
    
    //Elements
    @CacheLookup
    @FindBy(css=".btn.btn-info")
    private WebElement backButton;
    
    //Methods
    /**
     * check name of staff
     * @param staffName
     */
    private void checkStaffName(String staffName)
    { 
    	new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(backButton));
    	
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	Object result = executor.executeScript("return $('[translate=\"gurukulaApp.staff.name\"]').parent().parent().children().eq(1).children().attr(\"value\");");
    	System.out.println("The name of the staff is : " + result);
    	
    	Assert.assertEquals(result,staffName);
    }
    
    /**
     * checkBranchNameRelatedStaff
     * 
     * @param branchName
     */
    private void checkBranchNameRelatedStaff(String branchName)
    {
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	Object result = executor.executeScript("return $('[translate=\"gurukulaApp.staff.related_branch\"]').parent().parent().children().eq(1).children().attr(\"value\");");
    	System.out.println("The branch name, which related with staff, is : " + result);
    	
    	Assert.assertEquals(result,branchName);
    }
    
    /**
     * clickBackButtonOnStaffDetail and checks the staff page
     * @param branchCode
     */
    public void clickBackButtonOnStaffDetail()
    {
    	clickElement(driver, 10, backButton);

    	Assert.assertTrue(btnCreateNewStaff.isDisplayed());
    }
    
    /**
     * Check info on staff detail
     * @param branchName
     * @param codeValue
     * @return
     */
    public BranchPage checkInfoOnStaffDetail(String branchName, String codeValue)
    {
    	checkStaffName(branchName);
    	checkBranchNameRelatedStaff(codeValue);
    	clickBackButtonOnStaffDetail();
    	return new BranchPage(driver);
    }
    
}
