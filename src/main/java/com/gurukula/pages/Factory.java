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
}
