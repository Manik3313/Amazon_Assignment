package helpers;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.testproject.sdk.drivers.web.ChromeDriver;
import io.testproject.sdk.drivers.web.EdgeDriver;
import io.testproject.sdk.drivers.web.FirefoxDriver;
import io.testproject.sdk.drivers.web.SafariDriver;
import io.testproject.sdk.internal.reporting.Reporter;

public class CommonActions {
	

	static RemoteWebDriver driver;
   	static WebDriverWait wait;
    private static Integer timeOut = 20;
    Select s;
    public CommonActions(RemoteWebDriver driver) {
        CommonActions.driver = driver;
        wait = new WebDriverWait(driver, timeOut);
    }

    public  RemoteWebDriver getDriver()
    {
    	return driver;
	}
    public void verifyIsElementVisible(WebElement element) throws Throwable {
        try {
        	//System.out.println("In verifyIsElementVisible");
            wait.until(ExpectedConditions.visibilityOf(element));
			/*
			 * System.out.println( new Throwable() .getStackTrace()[2] .getMethodName());
			 */
        } catch (Exception e) 
        {

        	//System.out.println("In verifyIsElementVisible ctach");
            ConsoleLog.info("Element" + element + "is not visible");
            log(driver).info("Element" + element + "is not visible");
            //report(driver).step("Element" + element + "is not visible",false,true);
            ///Assert.assertTrue(false);
            throw e;
        }
    }

    public void typeKeys(WebElement element, String input) throws Throwable {
        verifyIsElementVisible(element);
        ConsoleLog.info(element + "is displayed");
        log(driver).info(element + "is displayed");
        try{
            element.sendKeys(input);
        }
        catch (Exception e){
            ConsoleLog.info("Couldn't perform type action with element: " + element);
            log(driver).info("Couldn't perform type action with element: " + element);
            report(driver).step("Couldn't perform type action with element: " + element,false,true);
            //Assert.assertTrue(false);
            throw e.getCause();
        }

    }

    public void click(WebElement element) throws Throwable
    {
    	//System.out.println("In click");
        verifyIsElementVisible(element);
        ConsoleLog.info(element + "is displayed");
        log(driver).info(element + "is displayed");
        try{
            element.click();
        }
        catch (Exception e)
        {
        	//System.out.println("In click catch");
            ConsoleLog.info("Couldn't perform  click action with element: " + element);
            log(driver).info("Couldn't perform click action with element: " + element);
            report(driver).step("\"Couldn't perform c;lick action with element: \" + element",false,true);
            //Assert.assertTrue(false);
            throw e.getCause();
        }
    }
    
	/*
	 * public void oneTabMovement() {
	 * 
	 * String childWindow=null; driver.getwi String mainWindow =
	 * driver.getWindowHandle(); Set<String> set = driver.getWindowHandles();
	 * Iterator<String> itr = set.iterator();
	 * 
	 * while (itr.hasNext()) { childWindow = itr.next(); if
	 * (!mainWindow.equals(childWindow)) { driver.switchTo().window(childWindow);
	 * break; } }
	 * 
	 * }
	 */    
    public void clear(WebElement element) throws Throwable
    {
    	//System.out.println("In click");
        verifyIsElementVisible(element);
        ConsoleLog.info(element + "is displayed");
        log(driver).info(element + "is displayed");
        try{
            element.clear();
        }
        catch (Exception e)
        {
        	//System.out.println("In click catch");
            ConsoleLog.info("Couldn't perform clear action with element: " + element);
            log(driver).info("Couldn't perform clear action with element: " + element);
            report(driver).step("\"Couldn't perform clear action with element: \" + element",false,true);
            //Assert.assertTrue(false);
            throw e.getCause();
        }
    }

    public String getTextFromElement(WebElement element) throws Throwable
    {
    	//System.out.println("In click");
        verifyIsElementVisible(element);
        ConsoleLog.info(element + "is displayed");
        log(driver).info(element + "is displayed");
        try{
            return element.getText();
        }
        catch (Exception e)
        {
        	//System.out.println("In click catch");
            ConsoleLog.info("Couldn't get text from the element: " + element);
            log(driver).info("Couldn't get text from the element: " + element);
            report(driver).step("Couldn't get text from the element: "+element,false,true);
            //Assert.assertTrue(false);
            throw e.getCause();
        }
    }

    public void selectOptionForNoSelectTag(WebElement dropDownElement, WebElement textFieldElement, WebElement filteredElement, String optionText) throws Throwable {
        click(dropDownElement);
        typeKeys(textFieldElement, optionText);
        click(filteredElement);
    }

    public boolean verifyElementIsDisplayed(WebElement element) throws Throwable {
        verifyIsElementVisible(element);
        return element.isDisplayed();
    }

    public void hoverAndSelectElemnt(WebElement element, String subelement) throws Throwable {
        verifyIsElementVisible(element);
        log(driver).info(element + "is displayed");
        ConsoleLog.info(element + "is displayed");
        try {
            (new Actions(this.driver)).moveToElement(element).build().perform();
            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath("//div[@class='project-row']/span[contains(text(),'" + subelement + "')]"))).click();
            //Thread.sleep(5000);
            //System.out.println("Waiting in hover and select");
            //driver.findElement(By.xpath("//div[@class='project-row']/span[contains(text(),"+element+")]")).click();
            //Thread.sleep(5000000);
            //  driver.findElement(By.xpath("//span[contains(text(),"+subelement+")]")).click();

        } catch (Exception e) {
            System.out.println("Error in hover and select element");
            log(driver).info("Error in hover and select element");
            report(driver).step("Error in hover and select element",false,true);
            throw e;
        }
        
      

    }

    public Reporter report(RemoteWebDriver driver)
    {
    	Reporter reporter=null;
    	if(driver instanceof ChromeDriver){
           return ((ChromeDriver) driver).report();
        }
    	else if(driver instanceof EdgeDriver)
    	{
    		return((EdgeDriver) driver).report();
    	}
    	else if(driver instanceof SafariDriver)
    	{
    		return((SafariDriver) driver).report();
    	}
    	else if(driver instanceof FirefoxDriver)
    	{
    		return((FirefoxDriver) driver).report();
    	}
    	else 
    		return reporter;
		
    }
    
    public org.slf4j.Logger log(RemoteWebDriver driver) 
    {
    	if(driver instanceof ChromeDriver){
           return ((ChromeDriver) driver).LOG;
        }
    	else if(driver instanceof EdgeDriver)
    	{
    		return((EdgeDriver) driver).LOG;
    	}
    	else if(driver instanceof SafariDriver)
    	{
    		return((SafariDriver) driver).LOG;
    	}
    	else if(driver instanceof FirefoxDriver)
    	{
    		return((FirefoxDriver) driver).LOG;
    	}
    	else 
    		return null;
		
	}
    
   
    
    public boolean selectFromVisibleText(String name,WebElement element) throws Throwable
    {
    	verifyElementIsDisplayed(element);
    	Select option=new Select(element);
    	option.selectByVisibleText(name);
    	return true;
    	
    }
    
    

    /*public void hoverOverAndClick(String element,String subelement) throws Throwable
            {
                //HomePageLocators h=new HomePageLocators(driver);
                verifyIsElementVisible(element);
                ConsoleLog.info(element + "is displayed");
                try {

            //wait.until(ExpectedConditions.visibilityOf(element));

            (new Actions(this.driver)).moveToElement(element).build().perform();
                    //WebDriverWait wait = new WebDriverWait(driver, 15);
                   // Thread.sleep(5000);
                    //WebElement element1=driver.findElement(By.xpath("//span[contains(text(),"+s+")]"));
                    //verifyIsElementVisible(element1);
                    //element1.click();
                    //Thread.sleep(5000);
                    //Thread.sleep(5000);

                    //driver.findElement(By.xpath("//*[@id='project-expanded-side-bar']/div[3]/div[2]/div/div/span")).click();
                    //System.out.println("Executed");


                } catch (Exception e)
                {
                    System.out.println(e.getCause());
                                ConsoleLog.info("Hover over element did not work as expected." + element);
                    throw e;
        }*/
}

