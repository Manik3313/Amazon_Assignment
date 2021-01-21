package GreenkartPages;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import GreenKartLocator.GreenKartPlaceOrderLocator;
import helpers.CommonActions;
import helpers.ConsoleLog;

public class GreenKartPlaceOrderPage extends GreenKartPlaceOrderLocator
{
	 RemoteWebDriver driver;

		public GreenKartPlaceOrderPage(CommonActions commonActions) 
		{
			super(commonActions);
	        this.driver = commonActions.getDriver();
	        PageFactory.initElements(driver, this);
			
		}       

		public GreenKartProceedPage selectPlaceOrder() throws Throwable
		{
			commonActions.click(placeOrderButton);
			ConsoleLog.info("Move to PlaceOrderPage");
			return new GreenKartProceedPage(commonActions);
		}
}
