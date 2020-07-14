package com.learnautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage 
{
    WebDriver driver;
    
	public LoginPage(WebDriver ldriver)
	{
		this.driver=ldriver;
	}
	
	@FindBy(name="email") WebElement uname;
	@FindBy(name="password") WebElement pass;
	@FindBy(xpath="//div[@class='ui fluid large blue submit button']") WebElement Login;
	
	public void loginToCRM(String usernameApp,String passwordApp)
	{
		uname.sendKeys(usernameApp);
		pass.sendKeys(passwordApp);
		Login.click();
		
	}
	
}
