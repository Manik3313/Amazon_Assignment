package AmazonPage;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import AmazonLocator.AmazonSearchPageLocator;
import helpers.CommonActions;
import helpers.ConsoleLog;

public class AmazonSearchPage extends AmazonSearchPageLocator
{
	RemoteWebDriver driver;
	WebDriverWait wait;
	WebElement element=null;
	   
	   
	public AmazonSearchPage(CommonActions commonActions)
	{
		super(commonActions);
		this.driver = commonActions.getDriver();
	    PageFactory.initElements(driver, this);
	    wait=new WebDriverWait(driver,20);

	}

	public boolean SelectCheckboxByName(String value) throws Throwable
	   {
		   try 
		   {
			   commonActions.getDriver().navigate().refresh();
			   element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'"+value+"')]")));
			   commonActions.click(element);
			   return true;
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
			return false;
			// TODO: handle exception
		   }
			
	   }
	public boolean verifySearchResult(String expected_value) throws Throwable
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


	
	public boolean verifybyname(String value) throws Throwable
		{
		try 
			{
			commonActions.getDriver().navigate().refresh();
			String output=commonActions.getTextFromElement(firstElement);
			System.out.println("Output:"+output);
			System.out.println("Value:"+value);
			
			if (output.contains(value))
				return true;
			else 
				return false;
	
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
		return false;
			// TODO: handle exception
		}
	}

	public boolean setPriceFilter(String min,String max) throws Throwable
	{
	try 
		{
			commonActions.typeKeys(minPrice, min);
			commonActions.typeKeys(maxPrice, max);
			commonActions.click(submitPrice);
			return true;
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
			return false;
		// TODO: handle exception
		}
	}
	
	public boolean verifypriceRange(String min,String max) throws Throwable
	{
		int low= Integer.parseInt(min);
		int high= Integer.parseInt(max);
		try 
		{

			List<WebElement> elements=null;
			commonActions.getDriver().navigate().refresh();
			elements=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='a-price-whole']")));
			//elements=driver.findElements(By.xpath("//span[@class='a-price-whole']"));
			for (WebElement element: elements)
			{
				String sprice="";
				Scanner s= new Scanner(element.getText());//commonActions.getTextFromElement(element));
				s.useDelimiter(",");
		        while (s.hasNext())
		        	{
		        		sprice=sprice.concat(s.next());
		            
		        	}
		        //System.out.println(sprice);
					int price=Integer.parseInt(sprice);
					if (!(price>=low && price<=high))
					{
						System.out.println(sprice);
					//Assert.assertEquals(true, false);
					return false;
					}
					
			}

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
				return false;
			// TODO: handle exception
			}
		return true;	
	}

	public boolean selectdiscount(int dis) throws Throwable
	{
		
		String sdis=null;
		try
		{
			//int dis=Integer.parseInt(discount);
			
			if(dis>=50)
				{sdis="50% Off or more";}
			else if(dis>=35 && dis<50)
				{sdis="35% Off or more";}
			else if(dis>=25 && dis<35)
				{sdis="25% Off or more";}
			else if(dis>=10 && dis<25)
				{sdis="10% Off or more";}
			else
				{sdis="Any Discount";}
				
				/*
				 * JavascriptExecutor js = (JavascriptExecutor) driver;
				 * js.executeScript("window.scrollBy(0,10000)");
				 */ commonActions.getDriver().navigate().refresh();
			 System.out.println(sdis);
			 element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'"+sdis+"')]")));//"//a[@class='a-link-normal s-navigation-item']/span[contains(text(),'"+sdis+"')]")));
			 commonActions.click(element);  
			 
		
		} catch (Exception e) 
		{
			String message="Error in  "+new Throwable()
	                .getStackTrace()[0]
	                .getMethodName();
				ConsoleLog.info(message);
				//System.out.println("Manik"+e.getCause());
				//Assert.assertTrue(false);
				// TODO: handle exception
				return false;
			
			// TODO: handle exception
		}
		return true;		 
		
	}

	public String getFirstItemName() throws Throwable
	{
		return commonActions.getTextFromElement(firstElement);
	}
	
	public String getFirstItemPrice() throws Throwable
	{
		try 
		{
			return commonActions.getTextFromElement(firstItemPrice);

		} catch (Exception e) 
			{
				ConsoleLog.info("Error in getFirstItemPrice");
				throw e;
			}
	}
	
	public AmazonProductDetailPage selectFirstItem() throws Throwable
	{
		try 
		{
			commonActions.click(firstElement);
			
		} catch (Exception e)
		{
			String message="Error in  "+new Throwable()
	                .getStackTrace()[0]
	                .getMethodName();
				ConsoleLog.info(message);
				//System.out.println("Manik"+e.getCause());
				//Assert.assertTrue(false);
				// TODO: handle exception
				return null;
			
			// TODO: handle exception
		}
		return new AmazonProductDetailPage(commonActions);
	}
	
	
	
	
}
