package sample;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import helpers.CommonActions;
import io.testproject.sdk.DriverBuilder;
import io.testproject.sdk.drivers.TestProjectCapabilityType;
import io.testproject.sdk.drivers.web.ChromeDriver;
import io.testproject.sdk.drivers.web.EdgeDriver;
import io.testproject.sdk.drivers.web.FirefoxDriver;
import io.testproject.sdk.internal.exceptions.AgentConnectException;
import io.testproject.sdk.internal.exceptions.InvalidTokenException;
import io.testproject.sdk.internal.exceptions.ObsoleteVersionException;

public class BaseTest1
{
	private static Properties properties;
	private static String baseURL;
	private static String devToken;
	private static ChromeOptions options;
	private static String browserStackUserName;
	private static String browserStackAccessKey;
	public static String cloudUrl;
	protected static String baseUrlApi;
	private static boolean isBrowserStackRequired;
	EdgeOptions caps = new EdgeOptions();
	protected static CommonActions commonActions;
	protected static ThreadLocal<RemoteWebDriver> localDriver = new ThreadLocal<RemoteWebDriver>();  
	protected static RemoteWebDriver driver;

	public void setConfig() throws IOException
	{
		properties = new Properties();
		String propFileName = "common.properties";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

		if (inputStream != null) 
		{
			properties.load(inputStream);
		}
		else 
		{
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
		
		baseURL = properties.getProperty("url");
		devToken = properties.getProperty("dev_token");
		baseUrlApi = properties.getProperty("baseUrl_api");
		isBrowserStackRequired = Boolean.parseBoolean(properties.getProperty("isBrowserStackRequired"));

		// Setting the browser stack property if required
		if (isBrowserStackRequired)
		{
			browserStackUserName = properties.getProperty("browserStackUserName");
			browserStackAccessKey = properties.getProperty("browserStackAccessKey");
			cloudUrl = "https://" + browserStackUserName + ":" + browserStackAccessKey
					+ "@hub-cloud.browserstack.com/wd/hub";
		}
	}
	//@Parameters(value="browser")
	//@BeforeTest
	public void setBrowserCapabilities(String browser)
			throws InvalidTokenException, AgentConnectException, IOException, ObsoleteVersionException 
	{
		setConfig();
		if (isBrowserStackRequired) 
		{
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "10");
			caps.setCapability("browser", "Firefox");
			caps.setCapability("browser_version", "80");
			caps.setCapability("name", "manikgupta5's First Test");
			caps.setCapability(TestProjectCapabilityType.CLOUD_URL, cloudUrl);
			driver = new DriverBuilder<EdgeDriver>(new ChromeOptions())
					.withRemoteAddress(new URL("http://localhost:8585")).withToken(devToken)
					//.withProjectName("TestProjectDemo").withJobName("TestProjectDemoJob")
					.build(EdgeDriver.class);

		}
		else 
		{
			if (browser.equalsIgnoreCase("Firefox")) 
			{
				driver = new DriverBuilder<FirefoxDriver>(new FirefoxOptions())
						  .withRemoteAddress(new URL("http://localhost:8585")) .withToken(devToken)
						  //.withProjectName("TestProjectDemo").withJobName("TestProjectDemoJob")
						  .build(FirefoxDriver.class);
				localDriver.set(driver);
				
				
			}
			else if (browser.equalsIgnoreCase("Chrome"))
			{
				driver = new DriverBuilder<ChromeDriver>(new ChromeOptions())
						.withRemoteAddress(new URL("http://localhost:8585")).withToken(devToken)
						//.withProjectName("TestProjectDemo").withJobName("TestProjectDemoJob")
						.build(ChromeDriver.class);

				localDriver.set(driver);
				/*
				 * options.setCapability("os", "Windows"); options.setCapability("os_version",
				 * "10"); options.setCapability("browser", "Chrome");
				 * options.setCapability("name", "manikgupta5's First Test");
				 * 
				 * driver=new ChromeDriver(devToken,options);
				 */
				
				/*
				 * driver = new DriverBuilder<ChromeDriver>(new ChromeOptions())
				 * .withRemoteAddress(new URL("http://localhost:8585")) .withToken(devToken)
				 * .withProjectName("TestProjectDemo").withJobName("TestProjectDemoJob")
				 * .build(ChromeDriver.class);
				 */
			}
			else if (browser.equalsIgnoreCase("Edge")) 
			{
				driver = new DriverBuilder<EdgeDriver>(new EdgeOptions())
						  .withRemoteAddress(new URL("http://localhost:8585")) .withToken(devToken)
						  //.withProjectName("TestProjectManik").withJobName("TestProjectManikJob")
						  .build(EdgeDriver.class);
				localDriver.set(driver);
			}
				else  
				{
				throw new IllegalArgumentException("Invalid browser value!!");
				}
		

		driver.get(baseURL);
		driver.manage().window().maximize();
		commonActions=new CommonActions(localDriver.get());
		commonActions.report(driver).disableTestAutoReports(true);
		}
	}
	 @AfterTest
	    static void tearDown()
	 {
	        if (driver != null) 
	        {
	            driver.quit();
	        }
	   
}
	
}