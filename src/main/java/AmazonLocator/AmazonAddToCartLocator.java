package AmazonLocator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import helpers.CommonActions;
import pages.BasePage;

public class AmazonAddToCartLocator extends BasePage 
{

	public AmazonAddToCartLocator(CommonActions commonActions) 
	{
		super(commonActions);
		// TODO Auto-generated constructor stub
	}
	   @FindBy(xpath ="//a[@id='hlb-view-cart-announce']")

			   //"//input[@name='submit.add-to-cart']")
			   //"//a[contains(text(),'Cart') and @class='a-button-text']")
	    protected WebElement addTocartButton;
	   
	   
	   
}
