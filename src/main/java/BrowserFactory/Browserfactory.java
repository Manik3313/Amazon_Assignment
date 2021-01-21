package BrowserFactory;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.testproject.sdk.DriverBuilder;
import io.testproject.sdk.drivers.web.ChromeDriver;
import io.testproject.sdk.drivers.web.EdgeDriver;


public class Browserfactory 
{
	//create webdriver object for given browser
	public RemoteWebDriver create_instance(String browser) throws MalformedURLException
	{
		RemoteWebDriver driver=null;
		if(browser.equals("Chrome"))
		{
			/*
			 * WebDriverManager.chromedriver().setup(); driver=new ChromeDriver();
			 */
			  driver = new DriverBuilder<ChromeDriver>(new ChromeOptions())
			  .withRemoteAddress(new URL("http://localhost:8585")).withToken(
			  "PNULh4LMV6QhQAuR1LHfNZg0-map8hMVbMBKahMxMAo1")
			  .withProjectName("TestProjectDemo").withJobName("TestProjectDemoJob").build(
			  ChromeDriver.class);
			 
			
		}
		else if(browser.equals("Edge"))
		{
			/*
			 * WebDriverManager.edgedriver().setup(); driver=new EdgeDriver();
			 */			
			  driver = new DriverBuilder<EdgeDriver>(new EdgeOptions())
			  .withRemoteAddress(new URL("http://localhost:8585"))
			  .withToken("PNULh4LMV6QhQAuR1LHfNZg0-map8hMVbMBKahMxMAo1")
			  .withProjectName("TestProjectDemo").withJobName("TestProjectDemoJob")
			  .build(EdgeDriver.class);
			 	
		}
		else
		{
			System.out.println("No data");
		}
		return driver;
	}
}

