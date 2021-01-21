package sample;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import GreenkartPages.GreenKartHomepage;
import GreenkartPages.GreenKartPlaceOrderPage;
import GreenkartPages.GreenKartProceedPage;
import helpers.dataProviderUtils;
import io.testproject.sdk.internal.exceptions.AgentConnectException;
import io.testproject.sdk.internal.exceptions.InvalidTokenException;
import io.testproject.sdk.internal.exceptions.ObsoleteVersionException;

public class Rahulshetty extends BaseTest1
{

	
	
	@Parameters(value="Browser")
    @BeforeClass(description="Rahul Shetty1")
	public void RahulshettySite( String Browser ) throws InvalidTokenException, AgentConnectException, IOException, ObsoleteVersionException 
    {
    	//setConfig();
    	setBrowserCapabilities(Browser);
    }
	@Test( groups = {"Greenkart"},testName ="Greenkart", dataProviderClass = dataProviderUtils.class, dataProvider = "testdata", 
			description ="\\src\\main\\resources\\Greenkartdata.xlsx,Sheet1")
	public  void GreenKart(Map<String, String> data) throws Throwable
	{	
		GreenKartHomepage greenKartHomepage=new GreenKartHomepage(commonActions);
		RemoteWebDriver driver=commonActions.getDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		commonActions.report(driver).disableCommandReports(true);
		
		commonActions.report(driver).step("Url Opened",true);
		//commonActions.getDriver().navigate().refresh();
		greenKartHomepage.selectVegetableByName(data.get("Greenkart"));
		commonActions.report(driver).step("Product added to cart",true);
		//driver.findElement(By.xpath("//h4[contains(text(),'Brocolli')]/../div/button")).click();
		//driver.report().step("Product added to cart",true);
		greenKartHomepage.clickOnCart();
		//driver.findElement(By.xpath("//a[@class='cart-icon']")).click();
		GreenKartPlaceOrderPage greenKartPlaceOrderPage=greenKartHomepage.clickOnProceedCheckOut();
		commonActions.report(driver).step("Clicked on Proceed Checkout",true);
		//driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click();
		//driver.report().step("Clicked on Proceed Checkout",true);
		//driver.findElement(By.xpath("//button[contains(text(),'Place Order')]")).click();
		GreenKartProceedPage greenKartProceedPage=greenKartPlaceOrderPage.selectPlaceOrder();
		commonActions.report(driver).step("Clicked on Place order",true);
		//driver.report().step("Clicked on Place order",true);
		greenKartProceedPage.clickAgreeTo();
		commonActions.report(driver).step("Click on agree to ",true);
		//driver.findElement(By.xpath("//input")).click();
		//driver.report().step("Click on agree to ",true);
		//driver.findElement(By.xpath("//select"));
		//Select country=new Select(driver.findElement(By.xpath("//select")));
		greenKartProceedPage.selectCountry("India");
		commonActions.report(driver).step("Country Selected",true);
		//country.selectByValue("India");
		//driver.report().step("Country Selected",true);
		//driver.findElement(By.xpath("//button[contains(text(),'Proceed')]")).click();
		greenKartProceedPage.clickProceed();
		commonActions.report(driver).step("Click on proceed",true);
		//driver.report().step("Click on proceed",true);
		commonActions.log(driver).info("In end");
		}
	
}
