package GreenKartLocator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import helpers.CommonActions;
import pages.BasePage;

public class GreenKartProceedLocator extends BasePage 
{

	public GreenKartProceedLocator(CommonActions commonActions) {
		super(commonActions);
		// TODO Auto-generated constructor stub
	}

	
	  @FindBy(xpath ="//input")
	    protected WebElement agreeTo;
	  
	  @FindBy(xpath ="//select")
	    protected WebElement selectCountry;
	  
	  @FindBy(xpath ="//button[contains(text(),'Proceed')]")
	    protected WebElement proceedButton;
	  
	  
	  
}
