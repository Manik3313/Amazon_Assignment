package GreenkartPages;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import GreenKartLocator.GreenKartProceedLocator;
import helpers.CommonActions;
import helpers.ConsoleLog;

public class GreenKartProceedPage extends GreenKartProceedLocator 
{
	RemoteWebDriver driver;

	public GreenKartProceedPage(CommonActions commonActions) {
		super(commonActions);
		 this.driver = commonActions.getDriver();
	     PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	public boolean clickAgreeTo() throws Throwable
	{
		commonActions.click(agreeTo);
		ConsoleLog.info("Click on AgreeTo");
		return true;
	}
	public boolean selectCountry(String name) throws Throwable
	{
		commonActions.selectFromVisibleText(name, selectCountry);
		ConsoleLog.info("Copuntry selected");
		return true;
	}
	
	public boolean clickProceed() throws Throwable
	{
		commonActions.click(proceedButton);
		ConsoleLog.info("Click on proceed successfully");
		return true;
	}
	
}
