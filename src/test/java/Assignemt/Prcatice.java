package Assignemt;

import java.io.IOException;
import java.util.Iterator;
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
import io.testproject.sdk.internal.exceptions.AgentConnectException;
import io.testproject.sdk.internal.exceptions.InvalidTokenException;
import io.testproject.sdk.internal.exceptions.ObsoleteVersionException;
import sample.BaseTest1;

public class Prcatice extends BaseTest1 
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
     boolean flag=false;
	
     @BeforeClass
     public void setup() throws InvalidTokenException, AgentConnectException, IOException, ObsoleteVersionException
     {
			
			  setConfig(); setBrowserCapabilities("Edge"); amazonHomepage=new
			  AmazonHomepage(commonActions);
			  driver.get("https://www.amazon.in/ref=nav_logo");
			  }
     @Test
     public void discount() throws Throwable
     {

    	 amazonSearchPage = amazonHomepage.searchItemByname("Laptop");
 		if (amazonSearchPage == null) {
 			commonActions.report(driver).step("Failed to enter value in search box", false, true);

 			commonActions.report(driver).test("Fail", false).submit();
 			Assert.assertEquals(false, true, "Failed to enter value in search box");
 		}
 		flag = amazonSearchPage.verifySearchResult("Laptop");
 		if (flag)
 			commonActions.report(driver).step("Search value match", flag);
 		else {
 			commonActions.report(driver).step("Search value does not match", false, true);
 			commonActions.report(driver).test("Fail", flag).submit();
 			Assert.assertTrue(flag, "Search value does not match");
 		}

 		if (!amazonSearchPage.selectdiscount("30")) {
 			commonActions.report(driver).step("Unable to select discount filter ", false, true);
 			commonActions.report(driver).test("fail", false).submit();
 			Assert.assertTrue(false, "Unable to select discount filter");
 		}
 		commonActions.report(driver).step("discount filter selected successfully ", true);
 		laptop_name = amazonSearchPage.getFirstItemName();// driver.findElements(By.xpath("//h2/a/span")).get(0).getText();
 		amazonProductDetailPage = amazonSearchPage.selectFirstItem();
 		if (amazonProductDetailPage == null) {
 			commonActions.report(driver).step("Unable to select item ", false, true);
 			commonActions.report(driver).test("fail", false).submit();
 			Assert.assertTrue(false, "Unable to select item");
 		}
 		commonActions.report(driver).step("Able to select item ", true);

 		String mainWindow = driver.getWindowHandle();
 		Set<String> set = driver.getWindowHandles();
 		Iterator<String> itr = set.iterator();

 		while (itr.hasNext()) {
 			childWindow = itr.next();
 			// Compare whether the main windows is not equal to child window. If not equal,
 			// we will close.
 			if (!mainWindow.equals(childWindow)) {
 				driver.switchTo().window(childWindow);
 				childWindowLaptopName = amazonProductDetailPage.get_ProductName();// driver.findElement(By.xpath("//h1/span[@id='productTitle']")).getText();
 				// childWindowLaptopPrice=amazonProductDetailPage.get_Price();//driver.findElement(By.xpath("//span[@id='priceblock_ourprice']")).getText();
 				System.out.println("Child laptopname" + childWindowLaptopName);
 				System.out.println("Parent Laptopname" + laptop_name);
 				break;
 			}
 		}
 		if (childWindowLaptopName.contains(laptop_name))
 			commonActions.report(driver).step("Laptop name Matches ", true);
 		else {
 			commonActions.report(driver).step("Laptop name does not Matches  ", false, true);
 			commonActions.report(driver).test("fail", false).submit();
 			Assert.assertTrue(false, "Laptop name does not Matches ");
 		}

 		// This is to switch to the main window
 		driver.close();
 		driver.switchTo().window(mainWindow);
		 
     }
}
