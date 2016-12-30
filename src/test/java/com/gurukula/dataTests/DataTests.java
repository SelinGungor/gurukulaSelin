package com.gurukula.dataTests;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.gurukula.generic.BaseCase;
import com.gurukula.pages.Factory;

import au.com.bytecode.opencsv.CSVReader;

/**
 * @author      Selin Gungor <selingungor01@gmail.com>
 * @version     1.0   
 * @since       1.0 (the version of the package this class was first added to)
 */
public class DataTests extends BaseCase  {
	
	String CSV_PATH="resources\\files\\branchData.csv";

	/**
	 * Gets the branch information from the csv file --> resources\\files\\branchData.csv 
	 * @param branchName
	 * @param branchCode
	 * @throws IOException
	 * @throws InterruptedException
	 */
	 @Test(dataProvider="dataTest")
	 public void csvDataRead(String branchName, String branchCode) throws IOException, InterruptedException{
	         
	        driver.get(gurukulaURL);
			Factory factory = new Factory(driver);
			factory.homePage().clickGurukulaIcon()
							  .clickLogin()
							  .login("admin", "admin", true, true)
							  .clickBranches()
							  .createNewBranch(branchName, branchCode)
						;		
	     
  
	 }
	 
    
	    @DataProvider(name = "dataTest")
	    public Iterator<Object []> provider() throws InterruptedException, IOException
	    {
	        List<Object []> testCases = new ArrayList<Object[]>();
	        String [] csvCell;
	    
	        CSVReader reader = new CSVReader(new FileReader(CSV_PATH));
	        while ((csvCell = reader.readNext()) != null) {
	            testCases.add(csvCell);
	        }

	        return testCases.iterator();
	    }
	    

}
