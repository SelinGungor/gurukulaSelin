package com.gurukula.pages;

import org.openqa.selenium.WebDriver;

/**
 * @author      Selin Gungor <selingungor01@gmail.com>
 * @version     1.0   
 * @since       1.0 (the version of the package this class was first added to)
 */
public class Factory {
    protected WebDriver driver;
     
    public Factory(WebDriver driver){
   	 this.driver = driver;	        	 
    }
    
    public HomePage homePage(){
   	 return new HomePage(driver);	        	 
    }
    
    public BranchPage branchPage(){
      	 return new BranchPage(driver);	        	 
       }
    
    public BranchDetailPage branchDetailPage(){
     	 return new BranchDetailPage(driver);	        	 
      }
    
    public StaffPage staffPage(){
    	 return new StaffPage(driver);	        	 
     }
    
    public UserSettingsPage userSettingsPage(){
   	 return new UserSettingsPage(driver);	        	 
    }
}
