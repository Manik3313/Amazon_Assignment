package AmazonPage;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import AmazonLocator.AmazonAddToCartLocator;
import helpers.CommonActions;

public class AmazonAddToCartPage extends AmazonAddToCartLocator
{
	RemoteWebDriver driver;
	WebDriverWait wait;

	public AmazonAddToCartPage(CommonActions commonActions) 
	{
		super(commonActions);
		this.driver=commonActions.getDriver();
		wait=new WebDriverWait(driver,10);
		PageFactory.initElements(driver, this);
		
		// TODO Auto-generated constructor stub
	}
	
	public AmazonShoppingCartPage clickAddToCart() throws Throwable
	{
		try
		{
			
			commonActions.click(addTocartButton);
		} catch (Exception e) 
		{
			throw e;
			// TODO: handle exception
		}
		return new AmazonShoppingCartPage(commonActions);
	}
}
