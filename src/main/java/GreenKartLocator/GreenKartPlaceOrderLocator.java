package GreenKartLocator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import helpers.CommonActions;
import pages.BasePage;

public class GreenKartPlaceOrderLocator extends BasePage
{

	public GreenKartPlaceOrderLocator(CommonActions commonActions) {
		super(commonActions);
		// TODO Auto-generated constructor stub
	}
	
	  @FindBy(xpath ="//button[contains(text(),'Place Order')]")
	    protected WebElement placeOrderButton;
	   
}
