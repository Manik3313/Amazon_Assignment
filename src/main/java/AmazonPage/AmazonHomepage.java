package AmazonPage;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.beust.jcommander.internal.Console;

import AmazonLocator.AmazonHomepageLocator;
import helpers.CommonActions;
import helpers.ConsoleLog;

public class AmazonHomepage extends AmazonHomepageLocator
{
	RemoteWebDriver driver;

	 public AmazonHomepage(CommonActions commonActions) 
	 {
		super(commonActions);
		this.driver = commonActions.getDriver();
        PageFactory.initElements(driver, this);
		
		// TODO Auto-generated constructor stub
	}

	
	public AmazonSearchPage searchItemByname(String name) throws Throwable
	{
		if(name.isEmpty())
		{
			commonActions.report(commonActions.getDriver()).step("Search text should be valid value",false, true);
			ConsoleLog.info("Search text should be valid value");
			return null;
		}
		try 
		{
			commonActions.clear(searchTextField);
			commonActions.typeKeys(searchTextField, name);
			ConsoleLog.info(name+" has been entered in serach field");
			commonActions.click(submitButton);
			ConsoleLog.info("SubmitButton has been clicked");
			return new AmazonSearchPage(commonActions);
		}
		catch (Exception e) 
		{
			String message="Error in  "+new Throwable()
	                    .getStackTrace()[0]
	                    .getMethodName();
			 ConsoleLog.info(message);
			//System.out.println("Manik"+e.getCause());
			//Assert.assertTrue(false);
			// TODO: handle exception
			return null;
		}
			
	}
	
		
		public boolean verifyResult(String expected_value) throws Throwable
		{
			try 
			{

				String value=commonActions.getTextFromElement(verifyText);
				if (value.contains(expected_value)) 
					{
					ConsoleLog.info("Value matches");
					return true;
					}
				else
					{
					ConsoleLog.info("Value displayed is "+value+" and expected value is"+expected_value);
					return false;
					}
				}
			catch (Exception e) 
			{
				String message="Error in  "+new Throwable()
		                    .getStackTrace()[0]
		                    .getMethodName();
				 ConsoleLog.info(message);
				//System.out.println("Manik"+e.getCause());
				Assert.assertTrue(false);
				// TODO: handle exception
				return false;
				
			}
		
	}
	
	
}
