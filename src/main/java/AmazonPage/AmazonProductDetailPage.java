package AmazonPage;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import AmazonLocator.AmazonProductDetailPageLocator;
import helpers.CommonActions;

public class AmazonProductDetailPage extends AmazonProductDetailPageLocator 
{
	RemoteWebDriver driver;
	WebDriverWait wait;
	public AmazonProductDetailPage(CommonActions commonActions)
	{
		super(commonActions);
		this.driver=commonActions.getDriver();
		wait=new WebDriverWait(driver, 10);
		PageFactory.initElements(driver, this);
		
		// TODO Auto-generated constructor stub
	}

		public String get_Price() throws Throwable
		{
			return commonActions.getTextFromElement(productPrice);
		}
		 
		public String get_ProductName() throws Throwable
		{
			return commonActions.getTextFromElement(productTitle);
		}
		
		public AmazonAddToCartPage selectAddTocart() throws Throwable
		{
			//System.out.println("In select add to cart");
			try 
			{
				commonActions.click(addToCart);
			}
			catch (Exception e) 
			{
				throw e;
				
			}
			return new AmazonAddToCartPage(commonActions);
		}
		
		public AmazonShoppingCartPage selectLeftSideAddToCart() throws Throwable
		{	
			try
			{commonActions.click(leftSideAddToCart);
			
			}
			catch (Exception e)
			{
				return null;
				
			// TODO: handle exception
		}
			return new AmazonShoppingCartPage(commonActions);
				
		}
}
