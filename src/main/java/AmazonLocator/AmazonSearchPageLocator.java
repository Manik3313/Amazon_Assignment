package AmazonLocator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import helpers.CommonActions;
import pages.BasePage;

public class AmazonSearchPageLocator extends BasePage
{
 
	public AmazonSearchPageLocator(CommonActions commonActions) {
		super(commonActions);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath ="//h2/a/span")
	    protected WebElement firstElement;
	
	@FindBy(xpath ="//input[@id='low-price']")
    protected WebElement minPrice;

	@FindBy(xpath ="//input[@id='high-price']")
    protected WebElement maxPrice;
	
	@FindBy(xpath ="//span[contains(text(),'Go')]/../input")
    protected WebElement submitPrice;
	
	@FindBy(xpath ="//span[@class='a-price-whole']")
    protected WebElement firstItemPrice;
	
	 @FindBy(xpath="//span[@class='a-color-state a-text-bold']")
	   protected WebElement verifyText;
	
	
	
	   
}
