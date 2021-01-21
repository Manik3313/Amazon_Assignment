package sample;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.testproject.sdk.DriverBuilder;
import io.testproject.sdk.drivers.web.ChromeDriver;
import io.testproject.sdk.drivers.web.EdgeDriver;
import io.testproject.sdk.drivers.web.FirefoxDriver;

public class Practice2 
{
	protected ThreadLocal<RemoteWebDriver> wbdriver = new ThreadLocal<RemoteWebDriver>();
	@Parameters("browser")
	@BeforeTest
	private void setDriver(String browser) throws MalformedURLException 
	{
		RemoteWebDriver driver;
		if (browser.equalsIgnoreCase("Firefox")) {
			
			driver = new DriverBuilder<FirefoxDriver>(new FirefoxOptions())
					  .withRemoteAddress(new URL("http://localhost:8585")) .withToken("PNULh4LMV6QhQAuR1LHfNZg0-map8hMVbMBKahMxMAo1")
					  .withProjectName("TestProjectDemo").withJobName("TestProjectDemoJob")
					  .build(FirefoxDriver.class);
			wbdriver.set(driver);
			
			
		} else if (browser.equalsIgnoreCase("Chrome"))
		{
			driver =//new org.openqa.selenium.chrome.ChromeDriver();
					
					  new DriverBuilder<ChromeDriver>(new ChromeOptions()) .withRemoteAddress(new
					  URL("http://localhost:8585")).withToken(
					  "PNULh4LMV6QhQAuR1LHfNZg0-map8hMVbMBKahMxMAo1")
					  .withProjectName("TestProjectDemo").withJobName("TestProjectDemoJob").build(
					  ChromeDriver.class);
					 
			wbdriver.set(driver);
				}
		else if (browser.equalsIgnoreCase("Edge")) {
			driver =//new org.openqa.selenium.edge.EdgeDriver() ;
					
						  new DriverBuilder<EdgeDriver>(new EdgeOptions()) .withRemoteAddress(new
						  URL("http://localhost:8585"))
						  .withToken("PNULh4LMV6QhQAuR1LHfNZg0-map8hMVbMBKahMxMAo1")
						  .withProjectName("TestProjectDemo").withJobName("TestProjectDemoJob")
						  .build(EdgeDriver.class);
						 			wbdriver.set(driver);
		}
			else {
			throw new IllegalArgumentException("Invalid browser value!!");
		}

		// TODO Auto-generated method stub
	}
	
	
	@Test
	public void start()
	{
		wbdriver.get().get("http://tutorials.jenkov.com/java-concurrency/threadlocal.html");
	}
}
