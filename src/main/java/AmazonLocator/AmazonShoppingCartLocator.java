package AmazonLocator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import helpers.CommonActions;
import pages.BasePage;

public class AmazonShoppingCartLocator extends BasePage
{

	public AmazonShoppingCartLocator(CommonActions commonActions) {
		super(commonActions);
		// TODO Auto-generated constructor stub
	}
	   @FindBy(xpath ="//span[@id='sc-subtotal-amount-activecart']/span/span")
	    protected WebElement product_Price;
	
	   @FindBy(xpath ="//a[contains(text(),'Cart') and @class='a-button-text']")
	    protected WebElement product_name;
	
	   @FindBy(xpath ="//span[@id='nav-cart-count']")
	    protected WebElement product_count;
	
}
