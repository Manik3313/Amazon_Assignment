package AmazonLocator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import helpers.CommonActions;
import pages.BasePage;

public class AmazonProductDetailPageLocator extends BasePage 
{

	public AmazonProductDetailPageLocator(CommonActions commonActions) 
	{
		super(commonActions);
	}

	 @FindBy(xpath ="//h1/span[@id='productTitle']")
	    protected WebElement productTitle;
	   
	   @FindBy(xpath =("//span[contains(@id,'priceblock')]"))//"//tr[@id='priceblock_ourprice_row']/td/span")//"//span[@class='a-size-medium a-color-price priceBlockDealPriceString']")//xpath="//span[@id='priceblock_ourprice']")
	   protected WebElement productPrice;
	
	   @FindBy(xpath="//input[@name='submit.add-to-cart']")//"//input[@id='add-to-cart-button']")
	   protected WebElement addToCart;
	
	   @FindBy(xpath="//span[@id='attach-sidesheet-view-cart-button']/span/input")//"//input[@id='add-to-cart-button']")
	   protected WebElement leftSideAddToCart;
	
	   
}
