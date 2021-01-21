package Assignemt;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import AmazonPage.AmazonAddToCartPage;
import AmazonPage.AmazonHomepage;
import AmazonPage.AmazonProductDetailPage;
import AmazonPage.AmazonSearchPage;
import AmazonPage.AmazonShoppingCartPage;
import helpers.CommonActions;
import helpers.dataProviderUtils;
import io.testproject.sdk.drivers.web.EdgeDriver;
import io.testproject.sdk.internal.exceptions.AgentConnectException;
import io.testproject.sdk.internal.exceptions.InvalidTokenException;
import io.testproject.sdk.internal.exceptions.ObsoleteVersionException;
import sample.BaseTest1;

public class TestAmazon extends BaseTest1 {
	AmazonHomepage amazonHomepage;
	AmazonSearchPage amazonSearchPage;
	AmazonProductDetailPage amazonProductDetailPage;
	AmazonAddToCartPage amazonaddToCartPage;
	AmazonShoppingCartPage amazonShoppingCartPage;
	WebElement element = null;
	String laptop_name = null;
	String laptop_price = null;
	String childWindowLaptopName = null;
	String childWindowLaptopPrice = null;
	boolean flag;

	static String childWindow = null;

	@BeforeClass
	public void setup() throws IOException, InvalidTokenException, AgentConnectException, ObsoleteVersionException {
		setConfig();
		setBrowserCapabilities("Edge");
		amazonHomepage = new AmazonHomepage(commonActions);
		commonActions.report(driver).disableTestAutoReports(true);
		commonActions.report(driver).disableCommandReports(true);

	}

	@BeforeMethod
	public void setuptest() {
		driver.get("https://www.amazon.in/");
	}

	@Test(dataProviderClass = dataProviderUtils.class, dataProvider = "testdata", description = "\\src\\main\\resources\\AmazonData.xlsx,Sheet1")
	public void Test1searchLaptop(Map<String, String> data) throws Throwable {
		amazonSearchPage = amazonHomepage.searchItemByname(data.get("Product"));
		if (amazonSearchPage == null) {

			commonActions.report(driver).step("Failed to enter value in search box", false, true);
			Assert.assertEquals(false, true, "Failed to enter value in search box");
		}
		commonActions.report(driver).step(" value entered in search box", true);

		flag = amazonSearchPage.verifySearchResult(data.get("Product"));
		if (flag)
			commonActions.report(driver).step("Search value match", flag);
		else {
			commonActions.report(driver).step("Search value does not match", flag, true);
			Assert.assertTrue(flag, "Search value does not match");
		}

		/*
		 * amazonSearchPage = amazonHomepage.searchItemByname("Laptop");
		 * amazonSearchPage.verifySearchResult("Laptop");
		 */ }

	@Test(dataProviderClass = dataProviderUtils.class, dataProvider = "testdata", description = "\\src\\main\\resources\\AmazonData.xlsx,Sheet1")
	public void Test2verifyLaptopBrand(Map<String, String> data) throws Throwable {
		amazonSearchPage = amazonHomepage.searchItemByname(data.get("Product"));
		if (amazonSearchPage == null) {
			commonActions.report(driver).step("Failed to enter value in textbox", false, true);
			Assert.assertEquals(false, true, "Failed to enter value in search box");
		}
		commonActions.report(driver).step(" value entered in search box", true);
		flag = amazonSearchPage.verifySearchResult(data.get("Product"));
		if (flag)
			commonActions.report(driver).step("Search value match", flag);
		else {
			commonActions.report(driver).step("Search value does not match", flag, true);
			Assert.assertTrue(flag, "Search value does not match");
		}

		if (!amazonSearchPage.SelectCheckboxByName(data.get("Brand"))) {
			commonActions.report(driver).step("Checkbox is not  selected", false, true);
			commonActions.report(driver).test("Fail", false);
			assertTrue(false, "Checkbox is not selected");
		}
		commonActions.report(driver).step("Checkbox is selected");

		if (!amazonSearchPage.verifybyname(data.get("Brand"))) {
			commonActions.report(driver).step("Checkbox selected and search result does not match", false, true);
			commonActions.report(driver).test("Fail", false);
			assertTrue(false, "Checkbox selected and search result does not match");
		} else
			commonActions.report(driver).step("Checkbox selected and search result matches ", true);

		/*
		 * amazonHomepage.searchItemByname("Laptop"); amazonSearchPage =
		 * amazonHomepage.searchItemByname("Laptop");
		 * amazonSearchPage.verifySearchResult("Laptop"); WebElement checkbox =
		 * amazonSearchPage.SelectCheckboxByName("HP"); if
		 * (amazonSearchPage.verifybyname("HP")) Assert.assertTrue(true); else
		 * Assert.assertTrue(false);
		 */
	}

	@Test(dataProviderClass = dataProviderUtils.class, dataProvider = "testdata", description = "\\src\\main\\resources\\AmazonData.xlsx,Sheet1")
	public void Test3verifyPriceRange(Map<String, String> data) throws Throwable {
		amazonSearchPage = amazonHomepage.searchItemByname(data.get("Product"));
		if (amazonSearchPage == null) {
			commonActions.report(driver).step("Unable to enter value in search box", false, true);
			Assert.assertEquals(false, true, "Failed to enter value in search box");
		}
		commonActions.report(driver).step("Able to enter value in search box", true);

		flag = amazonSearchPage.verifySearchResult(data.get("Product"));
		if (flag)
			commonActions.report(driver).step("Search value match", flag);
		else {
			commonActions.report(driver).step("Search value does not match", flag, true);
			Assert.assertTrue(flag, "Search value does not match");
		}

		/*
		 * if (!amazonSearchPage.SelectCheckboxByName("HP")) {
		 * commonActions.report(driver).step("Checkbox is not  selected",false,true);
		 * commonActions.report(driver).test("Fail",false);
		 * assertTrue(false,"Checkbox is not selected"); }
		 * commonActions.report(driver).step("Checkbox is selected");
		 * 
		 * if (!amazonSearchPage.verifybyname("HP")) { commonActions.report(driver).
		 * step("Checkbox  selected and search result does not match",false,true);
		 * commonActions.report(driver).test("Fail",false);
		 * assertTrue(false,"Checkbox selected and search result does not match"); }
		 * else commonActions.report(driver).
		 * step("Checkbox selected and search result matches ",true);
		 */

		if (!amazonSearchPage.setPriceFilter(data.get("Min_Price"), data.get("Max_Price"))) {
			commonActions.report(driver).step("Fail to set price", false, true);
			commonActions.report(driver).test("Fail", false);
			assertTrue(false, "Fail to set price");
		}
		commonActions.report(driver).step("Price set Successfully", true);

		if (!amazonSearchPage.verifypriceRange("25000", "35000")) {
			commonActions.report(driver).step("Items more than range specified exist", false, true);
			commonActions.report(driver).test("Fail", false);
			assertTrue(false, "Items more than range specified exist");
		}
		commonActions.report(driver).step("Items is not more than range specified ", true);

		/*
		 * amazonHomepage.searchItemByname("Laptop"); amazonSearchPage =
		 * amazonHomepage.searchItemByname("Laptop");
		 * amazonSearchPage.verifySearchResult("Laptop"); WebElement checkbox =
		 * amazonSearchPage.SelectCheckboxByName("HP");
		 * 
		 * amazonSearchPage.setPriceFilter("25000", "35000");
		 * amazonSearchPage.verifypriceRange("25000", "35000");
		 */}

	@Test(dataProviderClass = dataProviderUtils.class, dataProvider = "testdata", description = "\\src\\main\\resources\\AmazonData.xlsx,Sheet1")

	public void Test4clickDiscountandVerify(Map<String, String> data) throws Throwable {
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
		int dis = (int) Double.parseDouble(data.get("Discount"));

		if (!amazonSearchPage.selectdiscount(dis)) {
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

		/*
		 * amazonHomepage.searchItemByname("Laptop"); // amazonHomepage=new
		 * AmazonHomepage(commonActions); amazonSearchPage =
		 * amazonHomepage.searchItemByname("Laptop");
		 * amazonSearchPage.verifySearchResult("Laptop"); WebElement checkbox =
		 * amazonSearchPage.SelectCheckboxByName("HP");
		 * 
		 * amazonSearchPage.selectdiscount("50"); laptop_name =
		 * amazonSearchPage.getFirstItemName();//
		 * driver.findElements(By.xpath("//h2/a/span")).get(0).getText();
		 * amazonProductDetailPage = amazonSearchPage.selectFirstItem(); String
		 * mainWindow = driver.getWindowHandle(); Set<String> set =
		 * driver.getWindowHandles(); Iterator<String> itr = set.iterator();
		 * 
		 * while (itr.hasNext()) { childWindow = itr.next(); // Compare whether the main
		 * windows is not equal to child window. If not equal, // we will close. if
		 * (!mainWindow.equals(childWindow)) { driver.switchTo().window(childWindow);
		 * childWindowLaptopName = amazonProductDetailPage.get_ProductName();//
		 * driver.findElement(By.xpath("//h1/span[@id='productTitle']")).getText(); //
		 * childWindowLaptopPrice=amazonProductDetailPage.get_Price();//driver.
		 * findElement(By.xpath("//span[@id='priceblock_ourprice']")).getText();
		 * System.out.println("Child laptopname" + childWindowLaptopName);
		 * System.out.println("Parent Laptopname" + laptop_name); break; } } if
		 * (childWindowLaptopName.contains(laptop_name) (
		 * childWindowLaptopPrice.contains(laptop_price) &&
		 * childWindowLaptopName.contains(laptop_name)) Assert.assertEquals(true, true);
		 * else Assert.assertEquals(true, false); // This is to switch to the main
		 * window driver.close(); driver.switchTo().window(mainWindow);
		 */ }

	@Test(dataProviderClass = dataProviderUtils.class, dataProvider = "testdata", description = "\\src\\main\\resources\\AmazonData.xlsx,Sheet1")
	public void Test5GetPriceAndVerify(Map<String, String> data) throws Throwable {
		amazonSearchPage = amazonHomepage.searchItemByname(data.get("Product"));
		if (amazonSearchPage == null) {
			commonActions.report(driver).step("Failed to enter value in search box", false, true);

			commonActions.report(driver).test("Fail", false).submit();
			Assert.assertEquals(false, true, "Failed to enter value in search box");
		}
		flag = amazonSearchPage.verifySearchResult(data.get("Product"));
		if (flag)
			commonActions.report(driver).step("Search value match", flag);
		else {
			commonActions.report(driver).step("Search value does not match", false, true);
			commonActions.report(driver).test("Fail", flag).submit();
			Assert.assertTrue(flag, "Search value does not match");
		}
		if (!amazonSearchPage.SelectCheckboxByName(data.get("Brand"))) {
			commonActions.report(driver).step("Checkbox is not  selected", false, true);
			commonActions.report(driver).test("Fail", false);
			assertTrue(false, "Checkbox is not selected");
		}
		commonActions.report(driver).step("Checkbox is selected");

		/*
		 * if(!amazonSearchPage.selectdiscount("50")) {
		 * commonActions.report(driver).step("Unable to select discount filter ",
		 * false,true); commonActions.report(driver).test("fail",false).submit();
		 * Assert.assertTrue(false,"Unable to select discount filter"); }
		 * commonActions.report(driver).step("discount filter selected successfully ",
		 * true);
		 */
		laptop_price = amazonSearchPage.getFirstItemPrice();// driver.findElements(By.xpath("//h2/a/span")).get(0).getText();
		if (laptop_price == null) {
			commonActions.report(driver).step("Unable to get laptopPrice ", false, true);
			commonActions.report(driver).test("fail", false).submit();
			Assert.assertTrue(false, "Unable to get laptop price");
		}

		commonActions.report(driver).step("Able to get laptopPrice ", true);
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
				childWindowLaptopPrice = amazonProductDetailPage.get_Price();// driver.findElement(By.xpath("//h1/span[@id='productTitle']")).getText();
				// childWindowLaptopPrice=amazonProductDetailPage.get_Price();//driver.findElement(By.xpath("//span[@id='priceblock_ourprice']")).getText();
				System.out.println("Child laptopprice" + childWindowLaptopPrice);
				System.out.println("Parent laptopprice" + laptop_price);
				break;
			}
		}
		if (childWindowLaptopPrice.contains(laptop_price))
			commonActions.report(driver).step("Laptop Price Matches ", true);
		else {
			commonActions.report(driver).step("Laptop Price does not Matches  ", false, true);
			commonActions.report(driver).test("fail", false).submit();
			Assert.assertTrue(false, "Laptop Price does not Matches ");
		}

		// This is to switch to the main window
		driver.close();
		driver.switchTo().window(mainWindow);

		/*
		 * amazonHomepage.searchItemByname("Laptop"); // amazonHomepage=new
		 * AmazonHomepage(commonActions); amazonSearchPage =
		 * amazonHomepage.searchItemByname("Laptop");
		 * amazonSearchPage.verifySearchResult("Laptop"); WebElement checkbox =
		 * amazonSearchPage.SelectCheckboxByName("HP"); laptop_price =
		 * amazonSearchPage.getFirstItemPrice(); amazonProductDetailPage =
		 * amazonSearchPage.selectFirstItem(); String mainWindow =
		 * driver.getWindowHandle(); Set<String> set = driver.getWindowHandles();
		 * Iterator<String> itr = set.iterator();
		 * 
		 * while (itr.hasNext()) { childWindow = itr.next(); // Compare whether the main
		 * windows is not equal to child window. If not equal, // we will close. if
		 * (!mainWindow.equals(childWindow)) { driver.switchTo().window(childWindow);
		 * childWindowLaptopPrice = amazonProductDetailPage.get_Price();//
		 * driver.findElement(By.xpath("//span[@id='priceblock_ourprice']")).getText();
		 * System.out.println("Child laptopprice" + childWindowLaptopPrice);
		 * System.out.println("Parent Laptopprice" + laptop_price); break; } } if
		 * (childWindowLaptopName.contains(laptop_name) (
		 * childWindowLaptopPrice.contains(laptop_price) &&
		 * childWindowLaptopPrice.contains(laptop_price)) Assert.assertEquals(true,
		 * true); else Assert.assertEquals(true, false); // This is to switch to the
		 * main window driver.close(); driver.switchTo().window(mainWindow);
		 */ }

	@Test(dataProviderClass = dataProviderUtils.class, dataProvider = "testdata", description = "\\src\\main\\resources\\AmazonData.xlsx,Sheet1")
	public void Test6AddToCartAndVerify(Map<String, String> data) throws Throwable
	{amazonSearchPage = amazonHomepage.searchItemByname(data.get("Product"));
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

		/*
		 * amazonSearchPage = amazonHomepage.searchItemByname(data.get("Product")); if
		 * (amazonSearchPage==null) {
		 * commonActions.report(driver).step("Failed to enter value in search box",
		 * false,true);
		 * 
		 * commonActions.report(driver).test("Fail", false).submit();
		 * Assert.assertEquals(false, true, "Failed to enter value in search box"); }
		 * flag=amazonSearchPage.verifySearchResult(data.get("Product")); if(flag)
		 * commonActions.report(driver).step("Search value match",flag); else {
		 * commonActions.report(driver).step("Search value does not match", false,true);
		 * commonActions.report(driver).test("Fail",flag).submit();
		 * Assert.assertTrue(flag,"Search value does not match"); }
		 * 
		 * //laptop_name = amazonSearchPage.getFirstItemName(); amazonProductDetailPage
		 * = amazonSearchPage.selectFirstItem(); if(amazonProductDetailPage==null) {
		 * commonActions.report(driver).step("Unable to select item ", false,true);
		 * commonActions.report(driver).test("fail",false).submit();
		 * Assert.assertTrue(false,"Unable to select item"); }
		 * commonActions.report(driver).step("Able to select item ", true);
		 * 
		 * String mainWindow = driver.getWindowHandle(); Set<String> set =
		 * driver.getWindowHandles(); Iterator<String> itr = set.iterator();
		 * 
		 * while (itr.hasNext()) { childWindow = itr.next(); if
		 * (!mainWindow.equals(childWindow)) { driver.switchTo().window(childWindow);
		 * break; } } laptop_name = amazonProductDetailPage.get_ProductName(); if
		 * (laptop_name==null) {
		 * commonActions.report(driver).step("Unable to get product name ", false,true);
		 * commonActions.report(driver).test("fail",false).submit();
		 * Assert.assertTrue(false,"Unable to get product name"); }
		 * commonActions.report(driver).step("Able to get product name ",true);
		 * 
		 * amazonaddToCartPage = amazonProductDetailPage.selectAddTocart();
		 * if(amazonaddToCartPage==null) {
		 * commonActions.report(driver).step("Unable to click add to cart ",
		 * false,true); commonActions.report(driver).test("fail",false).submit();
		 * Assert.assertTrue(false,"Unable to click add to cart"); }
		 * commonActions.report(driver).step("Able to click add to cart ",true);
		 * 
		 * 
		 * try { amazonShoppingCartPage =
		 * amazonProductDetailPage.selectLeftSideAddToCart();
		 * 
		 * } catch (Exception e) {
		 * 
		 * } amazonShoppingCartPage = amazonaddToCartPage.clickAddToCart(); if
		 * (amazonShoppingCartPage==null) {
		 * commonActions.report(driver).step("Unable to move to shoppingcartpage ",
		 * false,true); commonActions.report(driver).test("fail",false).submit();
		 * Assert.assertTrue(false,"Unable to move to shoppingcartpage "); }
		 * commonActions.report(driver).step("Able to move to shoppingcartpage ",true);
		 * 
		 * System.out.println(laptop_name);
		 * if(!amazonShoppingCartPage.verifyProductName(laptop_name)) {
		 * commonActions.report(driver).step("Product name does not matches ",
		 * false,true); commonActions.report(driver).test("fail",false).submit();
		 * Assert.assertTrue(false,"Product Nam edoes not matches "); }
		 * commonActions.report(driver).step("Product name  matches ",true);
		 * 
		 * 
		 * driver.close(); driver.switchTo().window(mainWindow);
		 */
		/*
		 * amazonHomepage.searchItemByname("Laptop"); amazonSearchPage =
		 * amazonHomepage.searchItemByname("Laptop");
		 * amazonSearchPage.verifySearchResult("Laptop"); //WebElement checkbox =
		 * amazonSearchPage.SelectCheckboxByName("HP"); laptop_price =
		 * amazonSearchPage.getFirstItemPrice(); amazonProductDetailPage =
		 * amazonSearchPage.selectFirstItem(); String mainWindow =
		 * driver.getWindowHandle(); Set<String> set = driver.getWindowHandles();
		 * Iterator<String> itr = set.iterator();
		 * 
		 * while (itr.hasNext()) { childWindow = itr.next(); if
		 * (!mainWindow.equals(childWindow)) { driver.switchTo().window(childWindow);
		 * break; } } laptop_name = amazonProductDetailPage.get_ProductName();
		 * amazonaddToCartPage = amazonProductDetailPage.selectAddTocart(); try {
		 * amazonShoppingCartPage = amazonProductDetailPage.selectLeftSideAddToCart();
		 * 
		 * } catch (Exception e) { amazonShoppingCartPage =
		 * amazonaddToCartPage.clickAddToCart(); }
		 * 
		 * System.out.println(laptop_name);
		 * amazonShoppingCartPage.verifyProductName(laptop_name);
		 * 
		 * driver.close(); driver.switchTo().window(mainWindow);
		 */ }

}