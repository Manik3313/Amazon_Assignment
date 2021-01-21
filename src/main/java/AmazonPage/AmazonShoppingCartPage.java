package AmazonPage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.beust.jcommander.internal.Console;

import AmazonLocator.AmazonShoppingCartLocator;
import helpers.CommonActions;
import helpers.ConsoleLog;

public class AmazonShoppingCartPage extends AmazonShoppingCartLocator
{
	RemoteWebDriver driver;
	WebDriverWait wait;
	List<WebElement> elements;
	
	public AmazonShoppingCartPage(CommonActions commonActions)
	{
		super(commonActions);
		this.driver=commonActions.getDriver();
		wait=new WebDriverWait(driver,10);
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	public String getProductName() throws Throwable
	{
		return commonActions.getTextFromElement(product_name);
	}
	public String getProductPrice() throws Throwable
	{
		return commonActions.getTextFromElement(product_Price);
	}

	public int getTotalProductCount() throws Throwable
	{
		 return Integer.parseInt(commonActions.getTextFromElement(product_count));
	}
	
	public List<WebElement> getAllProductName() throws Throwable
	{
		try
		{

			driver.navigate().refresh();
			elements=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.xpath("//span[@class='a-size-medium sc-product-title a-text-bold']"))));
		} 
		catch (Exception e) 
		{
			ConsoleLog.info("Error in getAllProductName");
			 throw e.getCause();
		}
		return elements;
	}
	public boolean verifyProductName (String productname) throws Throwable
	{	
		boolean flag=false;
	
		try 
			{
			elements=getAllProductName();
			int count=getTotalProductCount();
			String name;
			for (int i = 0; i < count; i++)
				{
				name=commonActions.getTextFromElement(elements.get(i));
				System.out.println("In verify"+name);
				if (name.contains(productname))
					{
					flag=true;
					break;
					}
				}
			}
		catch (Exception e)
			{
				String message="Error in  "+new Throwable()
	                .getStackTrace()[0]
	                .getMethodName();
				ConsoleLog.info(message);
				//Assert.assertTrue(false);
			} 
		return flag;
		}
}
