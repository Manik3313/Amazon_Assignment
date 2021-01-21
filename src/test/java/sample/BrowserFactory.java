package sample;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.testproject.sdk.DriverBuilder;
import io.testproject.sdk.drivers.web.ChromeDriver;
import io.testproject.sdk.drivers.web.EdgeDriver;
import io.testproject.sdk.drivers.web.FirefoxDriver;

public class BrowserFactory 
{
	private static BrowserFactory instance=null;
	ThreadLocal<RemoteWebDriver> webdriver=new ThreadLocal<RemoteWebDriver>();
	private BrowserFactory()
	{
		
	}
	
	public static BrowserFactory getInstance()
	{
		if (instance==null)
		{
			instance=new BrowserFactory();	
		}
		return instance;
	}
	

	public final void setDriver(String browser) throws MalformedURLException
	{
		RemoteWebDriver driver;
		if (browser.equalsIgnoreCase("Firefox")) {
			
			driver = new DriverBuilder<FirefoxDriver>(new FirefoxOptions())
					  .withRemoteAddress(new URL("http://localhost:8585")) .withToken("PNULh4LMV6QhQAuR1LHfNZg0-map8hMVbMBKahMxMAo1")
					  .withProjectName("TestProjectDemo").withJobName("TestProjectDemoJob")
					  .build(FirefoxDriver.class);
			webdriver.set(driver);
			
			
		} else if (browser.equalsIgnoreCase("Chrome"))
		{
			driver = new DriverBuilder<ChromeDriver>(new ChromeOptions())
					.withRemoteAddress(new URL("http://localhost:8585")).withToken("PNULh4LMV6QhQAuR1LHfNZg0-map8hMVbMBKahMxMAo1")
					.withProjectName("TestProjectDemo").withJobName("TestProjectDemoJob").build(ChromeDriver.class);

			webdriver.set(driver);
				}
		else if (browser.equalsIgnoreCase("Edge")) {
			driver = new DriverBuilder<EdgeDriver>(new EdgeOptions())
					  .withRemoteAddress(new URL("http://localhost:8585")) .withToken("PNULh4LMV6QhQAuR1LHfNZg0-map8hMVbMBKahMxMAo1")
					  .withProjectName("TestProjectDemo").withJobName("TestProjectDemoJob")
					  .build(EdgeDriver.class);
			webdriver.set(driver);
		}
			else {
			throw new IllegalArgumentException("Invalid browser value!!");
		}

	}
	
	public RemoteWebDriver getDriver()
	{
		return webdriver.get();
	}

}
