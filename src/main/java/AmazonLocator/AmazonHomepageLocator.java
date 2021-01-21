package AmazonLocator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import helpers.CommonActions;
import pages.BasePage;

public class AmazonHomepageLocator extends BasePage
{
	public AmazonHomepageLocator(CommonActions commonActions)
	{
		super(commonActions);
		
	}

	   @FindBy(xpath ="//input[@id='twotabsearchtextbox']")
	    protected WebElement searchTextField;
	   
	   @FindBy(xpath="//span[@id='nav-search-submit-text']")
	   protected WebElement submitButton;
	
	   @FindBy(xpath="//span[@class='a-color-state a-text-bold']")
	   protected WebElement verifyText;
	

}
