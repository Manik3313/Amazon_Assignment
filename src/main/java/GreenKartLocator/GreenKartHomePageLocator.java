package GreenKartLocator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import helpers.CommonActions;
import helpers.ConsoleLog;
import pages.BasePage;

public class GreenKartHomePageLocator extends BasePage
{

	public GreenKartHomePageLocator(CommonActions commonActions)
	{
		super(commonActions);
		
	}

	   @FindBy(xpath ="//a[@class='cart-icon']")
	    protected WebElement cart_icon;
	   
	   @FindBy(xpath="//button[contains(text(),'PROCEED TO CHECKOUT')]")
	   protected WebElement proceedCheckOut;
	
	 public WebElement getVegetableByName(String name) throws Throwable
	 {
		 //System.out.println("In get vegetable");
		 commonActions.getDriver().navigate().refresh();
		 WebElement element = null;
		 WebDriverWait wait= new WebDriverWait(commonActions.getDriver(), 30);
		 try
		 {
			 element=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[contains(text(),"+name+")]/../div[@class='product-action']/button")));
			 //element=commonActions.getDriver().findElement(By.xpath("//h4[contains(text(),"+name+")]/../div[@class='product-action']/button"));
					
		 } 
		 catch (Exception e) 
		 {
			 commonActions.report(driver).step("Unable to get element "+element,false,true);
			 ConsoleLog.info("Unable to get element: "+"\"//h3[contains(text(),\"+name+\")]/../div[@class='product-action']/button\"");
			 //Assert.assertTrue(false);
			 throw e.getCause();
			 
			// TODO: handle exception
		}
		//System.out.println("In getVegetable BY Name"+name);
		 commonActions.verifyIsElementVisible(element);
		return element;
	 }
	

}
