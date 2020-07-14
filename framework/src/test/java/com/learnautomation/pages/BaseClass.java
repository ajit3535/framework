package com.learnautomation.pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.learnautomation.utility.BrowserFactory;
import com.learnautomation.utility.ConfigDataProvider;
import com.learnautomation.utility.ExcelDataProvider;
import com.learnautomation.utility.Helper;

public class BaseClass 
{
	
	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;
	
	@BeforeSuite
	public void setUpSuites()
	{
		excel=new ExcelDataProvider();
		config=new ConfigDataProvider();
		ExtentHtmlReporter extent=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/FreeCRM"+Helper.getCurrentDateTime()+".html"));
		report=new ExtentReports();
		report.attachReporter(extent);
	}
	
	@BeforeClass
	public void setUp()
	{
		driver=BrowserFactory.startApplication(driver, config.getBrowser(), config.getStagingURL());
	}
	
	@AfterClass
	public void tearDown()
	{
		BrowserFactory.quitBrowser(driver);
	}
	
    @AfterMethod
    
    public void teardownMethod(ITestResult result)
    {
    	if(result.getStatus()==ITestResult.FAILURE)
    	{
    		//Helper.captureScreenShot(driver);
    		try {
				logger.fail("Test Fail", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenShot(driver)).build());
			} 
    		catch (Exception e) 
    		{
				System.out.println("Unable to capture screen shot"+e.getMessage());
			}
    	}
    		else if(result.getStatus()==ITestResult.SUCCESS)
    		{
    			try {
					logger.pass("Test Pass", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenShot(driver)).build());
				} catch (Exception e) {
					
					System.out.println("unable to take Pass Screen Shot"+e.getMessage());
				}
    		}
    	
    	report.flush();
    }
	
}
