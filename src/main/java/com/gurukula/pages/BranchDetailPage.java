package com.gurukula.pages;

import org.openqa.selenium.By;
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
public class BranchDetailPage extends BranchPage{
	
	protected WebDriver driver;
	JavascriptExecutor js = null;
    
	/**
	 * constructor
	 * @param driver
	 */
    public BranchDetailPage(WebDriver driver){
	       super(driver);
	 	   this.driver = driver;
	 	   PageFactory.initElements(driver, this);
    	}
    
    //WebElements
    /**
     * nameValue of branch
     *
     **/
    @CacheLookup
    @FindBy( css = "[data-target='#saveBranchModal']")
    private WebElement nameValue;
    
    /**
     * code value of branch
     *
     **/
    @CacheLookup
    @FindBy( css = "[data-target='#saveBranchModal']")
    private WebElement codeValue;
    
    
    @CacheLookup
    @FindBy(css="[class=\"btn btn-info\"]")
    private WebElement backButton;
    
    
    //Methods
    /**
     * check name value
     * @param branchName
     */
    private void checkNameValue(String branchName)
    {
    	By byXpath = By.xpath("//*[contains(text(),'Back')]"); 
    	new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(byXpath));
    	
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	Object result = executor.executeScript("return $('[translate=\"gurukulaApp.branch.name\"]').parent().parent().children().eq(1).children().attr(\"value\");");
    	System.out.println("The name of the branch is : " + result);
    	
    	Assert.assertEquals(result,branchName);
    }
    
    /**
     * check code value
     * @param branchCode
     */
    private void checkCodeValue(String branchCode)
    {
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	Object result = executor.executeScript("return $('[translate=\"gurukulaApp.branch.code\"]').parent().parent().children().eq(1).children().attr(\"value\");");
    	System.out.println("The code of the branch is : " + result);
    	
    	Assert.assertEquals(result,branchCode);
    }
    
    /**
     * clickBackButtonOnBranchDetail and checks the branch page
     * @param branchCode
     */
    public void clickBackButtonOnBranchDetail()
    {
    	new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(backButton)).click();

    	Assert.assertTrue(btnCreateANewBranch.isDisplayed());
    }
    
    public BranchPage checkInfoOnBranchDetail(String branchName, String codeValue)
    {
    	checkNameValue(branchName);
    	checkCodeValue(codeValue);
    	clickBackButtonOnBranchDetail();
    	return new BranchPage(driver);
    }
    
}
