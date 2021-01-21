package GreenkartPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.beust.jcommander.internal.Console;

import GreenKartLocator.GreenKartHomePageLocator;
import helpers.CommonActions;
import helpers.ConsoleLog;

public class GreenKartHomepage extends GreenKartHomePageLocator 
{
	 RemoteWebDriver driver;

	public GreenKartHomepage(CommonActions commonActions) 
	{
		super(commonActions);
        this.driver = commonActions.getDriver();
        PageFactory.initElements(driver, this);
		
	}       
	
	
	public boolean selectVegetableByName(String name) throws Throwable
	{
		try {
			commonActions.click(getVegetableByName(name));
			ConsoleLog.info("Vegetable selected");
			return true;
			}
		catch (Exception e) 
		{
			String message="Error in  "+new Throwable()
	                    .getStackTrace()[0]
	                    .getMethodName();
			 ConsoleLog.info(message);
			//System.out.println("Manik"+e.getCause());
			Assert.assertTrue(false);
			// TODO: handle exception
			return false;
			
		}
		
	}
	
	public boolean clickOnCart() throws Throwable
	{
		commonActions.click(cart_icon);
		ConsoleLog.info("Click on cart successfully");
		
		return true;
	}
	
	public GreenKartPlaceOrderPage clickOnProceedCheckOut() throws Throwable
	{
		commonActions.click(proceedCheckOut);
		ConsoleLog.info("Order Place Successfully");
		return new GreenKartPlaceOrderPage(commonActions);
	}
	
	
}
