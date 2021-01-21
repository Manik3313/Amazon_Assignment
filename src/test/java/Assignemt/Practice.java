package Assignemt;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import AmazonPage.AmazonAddToCartPage;
import AmazonPage.AmazonHomepage;
import AmazonPage.AmazonProductDetailPage;
import AmazonPage.AmazonSearchPage;
import AmazonPage.AmazonShoppingCartPage;
import groovyjarjarantlr4.v4.parse.ANTLRParser.throwsSpec_return;
import helpers.dataProviderUtils;
import io.testproject.sdk.internal.exceptions.AgentConnectException;
import io.testproject.sdk.internal.exceptions.InvalidTokenException;
import io.testproject.sdk.internal.exceptions.ObsoleteVersionException;
import sample.BaseTest1;

public class Practice extends BaseTest1 
{
	 AmazonHomepage amazonHomepage;
	 AmazonSearchPage amazonSearchPage;
	 AmazonProductDetailPage amazonProductDetailPage;
	 AmazonAddToCartPage amazonaddToCartPage;
	 AmazonShoppingCartPage amazonShoppingCartPage;
	 WebElement element=null;
	 String laptop_name=null;
	 String laptop_price=null;
	 String childWindowLaptopName=null;
     String childWindowLaptopPrice=null;
     String childWindow=null;
     boolean flag;
	
     @BeforeClass
     public void setup() throws InvalidTokenException, AgentConnectException, IOException, ObsoleteVersionException
     {
    	 setConfig();
    	 setBrowserCapabilities("Edge");
    	 amazonHomepage=new AmazonHomepage(commonActions);
    	 driver.get("https://www.amazon.in/ref=nav_logo");
      }
     @Test(dataProviderClass=dataProviderUtils.class ,dataProvider ="testdata",description = "\\src\\main\\resources\\AmazonData.xlsx,Sheet1")
     public void discount(Map<String, String> data) throws Throwable
     { 
    	 amazonSearchPage = amazonHomepage.searchItemByname(data.get("Product"));
		 	if (amazonSearchPage==null)
		 	{
		 		commonActions.report(driver).step("Failed to enter value in search box", false,true);
		 		
		 		commonActions.report(driver).test("Fail", false).submit();
		 		Assert.assertEquals(false, true, "Failed to enter value in search box");
				}
		 	flag=amazonSearchPage.verifySearchResult(data.get("Product"));
		 	if(flag)
		 		commonActions.report(driver).step("Search value match",flag);
		 	else
		 	{
		 		commonActions.report(driver).step("Search value does not match", false,true);
		 		commonActions.report(driver).test("Fail",flag).submit();
		  		Assert.assertTrue(flag,"Search value does not match");	
		 	}
		 	amazonSearchPage.SelectCheckboxByName("HP");
	//laptop_name = amazonSearchPage.getFirstItemName();
		 	amazonProductDetailPage = amazonSearchPage.selectFirstItem();
			if(amazonProductDetailPage==null)
			{
				commonActions.report(driver).step("Unable to select item ", false,true);
		 		commonActions.report(driver).test("fail",false).submit();
		  		Assert.assertTrue(false,"Unable to select item");
			}
			commonActions.report(driver).step("Able to select item ", true);
	 			
		String mainWindow = driver.getWindowHandle();
		Set<String> set = driver.getWindowHandles();
		Iterator<String> itr = set.iterator();

		while (itr.hasNext()) {
			childWindow = itr.next();
			if (!mainWindow.equals(childWindow)) {
				driver.switchTo().window(childWindow);
				break;
			}
		}
		laptop_name = amazonProductDetailPage.get_ProductName();
		if (laptop_name==null)
		{
				commonActions.report(driver).step("Unable to get product name ", false,true);
		 		commonActions.report(driver).test("fail",false).submit();
		  		Assert.assertTrue(false,"Unable to get product name");
		}
		commonActions.report(driver).step("Able to get product name ",true);

		amazonaddToCartPage = amazonProductDetailPage.selectAddTocart();
		if(amazonaddToCartPage==null)
		{
			commonActions.report(driver).step("Unable to click add to cart ", false,true);
	 		commonActions.report(driver).test("fail",false).submit();
	  		Assert.assertTrue(false,"Unable to click add to cart");
		}
		commonActions.report(driver).step("Able to click add to cart ",true);
	 	
		
		  try {
			  System.out.println("In try");
			  amazonShoppingCartPage =  amazonProductDetailPage.selectLeftSideAddToCart();
			  if (amazonShoppingCartPage==null)
			  {
				throw new Exception();
			}
			  
		  } catch (Exception e)
		  {
			  amazonShoppingCartPage = amazonaddToCartPage.clickAddToCart();
		  }
		 		
		if (amazonShoppingCartPage==null)
		{
			commonActions.report(driver).step("Unable to move to shoppingcartpage ", false,true);
	 		commonActions.report(driver).test("fail",false).submit();
	  		Assert.assertTrue(false,"Unable to move to shoppingcartpage ");	
		}
		commonActions.report(driver).step("Able to move to shoppingcartpage ",true);
	 	
		System.out.println(laptop_name);
		if(!amazonShoppingCartPage.verifyProductName(laptop_name))
		{
			commonActions.report(driver).step("Product name does not matches ", false,true);
	 		commonActions.report(driver).test("fail",false).submit();
	  		Assert.assertTrue(false,"Product Nam edoes not matches ");	
		}
			commonActions.report(driver).step("Product name  matches ",true);


		driver.close();
		driver.switchTo().window(mainWindow);
 	}

     }