package com.gurukula.generic;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * @author      Selin Gungor <selingungor01@gmail.com>
 * @version     1.0   
 * @since       1.0 (the version of the package this class was first added to)
 */
public class BaseCase {
	  
	   public WebDriver driver;
	   protected String gurukulaURL;
	   final String pathSeparator = File.separator;
	 
		@BeforeSuite
		@Parameters("gurukulaURL")
		public void setEnv(
				@Optional("http://127.0.0.1:8080/") String gurukulaURL) {
			this.gurukulaURL = gurukulaURL;
		}
		
		
		protected void openPartialUrl(String partialurl) {
			//driver.get(getUrlPrefix() + partialurl + "?siteLanguage=" + this.settings.getLanguage());
		}
		//private static String getUrlPrefix() {
		 //   return StringUtils.replace(System.getProperty("selenium.website.url", HTTP_PREFIX), "@localhost@", getCanonicalHostName());
		//}
		private static String getCanonicalHostName() {
		    try {
		        return java.net.InetAddress.getLocalHost().getCanonicalHostName();
		    } catch (Exception e) {
		        return "127.0.0.1";
		    }
		}
		
	    /**
	     * sets up the test environment
	     *
	     **/
		@BeforeMethod
		public void setUp()
		{
			System.setProperty("webdriver.chrome.driver", "resources\\drivers\\chromedriver.exe");
		    driver= new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(gurukulaURL);
		}
		
	    /**
	     * ends up the test environment
	     *
	     **/
		@AfterMethod
		public void closeBrowser(ITestResult result) throws IOException {
			
			String  failureImageFolder = result.getMethod().getMethodName() 
	                 + "_" + new SimpleDateFormat("dd-MM-yyyy_HH-ss").format(new GregorianCalendar().getTime());
			String reportsFolderName = "gurukula_" + failureImageFolder;
	        String path = getPath(reportsFolderName);
			
	        //Creates the report folder
	        File folder = new File(path);
	        boolean b = false;
	        if(!folder.exists()){
	        	b = folder.mkdirs();
	        }
	        	                
	        if (!result.isSuccess()) {

	            try {
	                
	            	//captures the screenshot of failure
	                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	               
	    					                    
	                String failureImageFileName = failureImageFolder + ".png";
	          
	                if(b){                
	                	FileUtils.moveFile(scrFile, new File(path + "/" + failureImageFileName) );
	                	Reporter.log(result + "<br/><a href='" + path + "'> <img src='" + path + "' height='100' width='100'/> </a>");
	              }
	           } catch (IOException e1) {
	                e1.printStackTrace();
	              }
	        }
			
			File sourceFile = new File("test-output/emailable-report.html"); 
		    String emailableReportName = reportsFolderName + ".html";
	        if(b){                
	        	FileUtils.copyFile(sourceFile, new File(path + "/" + emailableReportName) );
	        	Reporter.log(result + "<br/><a href='" + path + "'> <img src='" + path + "' height='100' width='100'/> </a>");
	        }
			
			driver.close();
			driver.quit();
		}

		private String getPath(String failureImagePreFolderName) throws IOException {
			File directory = new File(".");

		    String newFileNamePath =
		        directory.getCanonicalPath() + pathSeparator + "target" + pathSeparator
		            + "surefire-reports" + pathSeparator + "screenShots" + pathSeparator + failureImagePreFolderName;
		    return newFileNamePath;
		}

}
