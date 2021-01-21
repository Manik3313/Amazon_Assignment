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
import org.testng.annotations.BeforeSuite;
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

public class BaseTest {

	protected static ThreadLocal<RemoteWebDriver> localDriver = new ThreadLocal<RemoteWebDriver>();  
	private static Properties properties;
	private static String baseURL;
	private static String devToken;
	private static ChromeOptions options=new ChromeOptions();
	private static String browserStackUserName;
	private static String browserStackAccessKey;
	public static String cloudUrl;
	protected static String baseUrlApi;
	protected static RemoteWebDriver driver;
	private static boolean isBrowserStackRequired;
	EdgeOptions caps = new EdgeOptions();
	protected static CommonActions commonActions;

	@BeforeSuite
	public void setConfig() throws IOException {
		properties = new Properties();
		String propFileName = "common.properties";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

		if (inputStream != null) {
			properties.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
		baseURL = properties.getProperty("url");
		devToken = properties.getProperty("dev_token");
		baseUrlApi = properties.getProperty("baseUrl_api");
		isBrowserStackRequired = Boolean.parseBoolean(properties.getProperty("isBrowserStackRequired"));

		// Setting the browser stack property if required
		if (isBrowserStackRequired) {
			browserStackUserName = properties.getProperty("browserStackUserName");
			browserStackAccessKey = properties.getProperty("browserStackAccessKey");
			cloudUrl = "https://" + browserStackUserName + ":" + browserStackAccessKey
					+ "@hub-cloud.browserstack.com/wd/hub";
		}
	}

	
	
	//@Parameters("browser")
	@BeforeTest
	public void setBrowserCapabilities()//String browser)
			throws InvalidTokenException, AgentConnectException, IOException, ObsoleteVersionException
	{
		String browser="Edge";
		if (isBrowserStackRequired) {
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "10");
			caps.setCapability("browser", "Firefox");
			caps.setCapability("browser_version", "80");
			caps.setCapability("name", "manikgupta5's First Test");
			caps.setCapability(TestProjectCapabilityType.CLOUD_URL, cloudUrl);
			driver = new DriverBuilder<EdgeDriver>(new ChromeOptions())
					.withRemoteAddress(new URL("http://localhost:8585")).withToken(devToken)
					.withProjectName("TestProjectDemo").withJobName("TestProjectDemoJob").build(EdgeDriver.class);
					//localDriver.set(driver);
			
		} else {
			if (browser.equalsIgnoreCase("Firefox")) {
				driver = new DriverBuilder<FirefoxDriver>(new FirefoxOptions())
						  .withRemoteAddress(new URL("http://localhost:8585")) .withToken(devToken)
						 // .withProjectName("TestProjectDemo").withJobName("TestProjectDemoJob")
						  .build(FirefoxDriver.class);
				localDriver.set(driver);
				
				
			} else if (browser.equalsIgnoreCase("Chrome"))
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
			else if (browser.equalsIgnoreCase("Edge")) {
				driver = new DriverBuilder<EdgeDriver>(new EdgeOptions())
						  .withRemoteAddress(new URL("http://localhost:8585")) .withToken(devToken)
						  //.withProjectName("TestProjectManik").withJobName("TestProjectManikJob")
						  .build(EdgeDriver.class);
				localDriver.set(driver);
			}
				else {
				throw new IllegalArgumentException("Invalid browser value!!");
			}
		}
		driver.get(baseURL);
		driver.manage().window().maximize();
		commonActions=new CommonActions(localDriver.get());
		

	}

	/*
	 * @BeforeMethod public void launchBrowser() throws InvalidTokenException,
	 * AgentConnectException, ObsoleteVersionException, IOException,
	 * InterruptedException { if (isBrowserStackRequired) driver1=new
	 * FirefoxDriver(); driver1.report().step(); driver1.report().test().submit();
	 * 
	 * driver = new EdgeDriver(devToken, caps, "browserstackProject",
	 * "browserstackjob"); else caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS,
	 * true);
	 * 
	 * driver = new EdgeDriver(devToken, caps);//, "browserstackProject",
	 * "browserstackjob");
	 * 
	 * 
	 * driver = new DriverBuilder<ChromeDriver>(new ChromeOptions())
	 * .withRemoteAddress(new URL("http://localhost:8585")) .withToken(devToken)
	 * .withProjectName("TestProjectDemo").withJobName("TestProjectDemoJob")
	 * .build(ChromeDriver.class);
	 * 
	 * driver.manage().window().maximize(); driver.navigate().to(baseURL); //
	 * driver.manage().window().setPosition(new Point(-2000, 0)); }
	 */
	/*
	 * @AfterTest public void tearDown() { System.out.println("In tear down");
	 * //driver.quit(); }
	 */
}

/*
 * package sample; import java.io.FileNotFoundException; import
 * java.io.IOException; import java.io.InputStream; import
 * java.net.MalformedURLException; import java.net.URL; import
 * java.util.Properties;
 * 
 * import org.openqa.selenium.Point; import
 * org.openqa.selenium.chrome.ChromeOptions; import
 * org.openqa.selenium.edge.EdgeOptions; import
 * org.openqa.selenium.remote.CapabilityType; import
 * org.testng.annotations.AfterTest; import org.testng.annotations.BeforeMethod;
 * import org.testng.annotations.BeforeSuite; import
 * org.testng.annotations.BeforeTest;
 * 
 * import helpers.CommonActions; import io.testproject.sdk.DriverBuilder; import
 * io.testproject.sdk.drivers.TestProjectCapabilityType; import
 * io.testproject.sdk.drivers.web.ChromeDriver; import
 * io.testproject.sdk.drivers.web.EdgeDriver; import
 * io.testproject.sdk.internal.exceptions.AgentConnectException; import
 * io.testproject.sdk.internal.exceptions.InvalidTokenException; import
 * io.testproject.sdk.internal.exceptions.ObsoleteVersionException;
 * 
 * public class BaseTest {
 * 
 * private static Properties properties; private static String baseURL; private
 * static String devToken; private static String browserStackUserName; private
 * static String browserStackAccessKey; public static String cloudUrl; protected
 * static String baseUrlApi; protected static EdgeDriver driver; private static
 * boolean isBrowserStackRequired; EdgeOptions caps = new EdgeOptions();
 * 
 * 
 * @BeforeSuite public void setConfig() throws IOException { properties = new
 * Properties(); String propFileName = "common.properties"; InputStream
 * inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 * 
 * if (inputStream != null) { properties.load(inputStream); } else { throw new
 * FileNotFoundException("property file '" + propFileName +
 * "' not found in the classpath"); } baseURL = properties.getProperty("url");
 * devToken = properties.getProperty("dev_token"); baseUrlApi =
 * properties.getProperty("baseUrl_api"); isBrowserStackRequired =
 * Boolean.parseBoolean(properties.getProperty("isBrowserStackRequired"));
 * 
 * // Setting the browser stack property if required if (isBrowserStackRequired)
 * { browserStackUserName = properties.getProperty("browserStackUserName");
 * browserStackAccessKey = properties.getProperty("browserStackAccessKey");
 * cloudUrl = "https://" + browserStackUserName + ":" + browserStackAccessKey +
 * "@hub-cloud.browserstack.com/wd/hub"; } }
 * 
 * @BeforeTest public void setBrowserCapabilities() throws MalformedURLException
 * { if (isBrowserStackRequired) { caps.setCapability("os", "Windows");
 * caps.setCapability("os_version", "10"); caps.setCapability("browser",
 * "Firefox"); caps.setCapability("browser_version", "80");
 * caps.setCapability("name", "manikgupta5's First Test"); caps.setCapability(
 * TestProjectCapabilityType.CLOUD_URL, cloudUrl); driver = new
 * DriverBuilder<EdgeDriver>(new ChromeOptions()) .withRemoteAddress(new
 * URL("http://localhost:8585")) .withToken(devToken)
 * .withProjectName("TestProjectDemo").withJobName("TestProjectDemoJob")
 * .build(EdgeDriver.class); driver.navigate().to(baseURL); }
 * 
 * }
 * 
 * @BeforeMethod public void launchBrowser() throws InvalidTokenException,
 * AgentConnectException, ObsoleteVersionException, IOException,
 * InterruptedException { if (isBrowserStackRequired) driver1=new
 * FirefoxDriver(); driver1.report().step(); driver1.report().test().submit();
 * 
 * driver = new EdgeDriver(devToken, caps, "browserstackProject",
 * "browserstackjob"); else caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS,
 * true);
 * 
 * driver = new EdgeDriver(devToken, caps);//, "browserstackProject",
 * "browserstackjob");
 * 
 * 
 * driver = new DriverBuilder<ChromeDriver>(new ChromeOptions())
 * .withRemoteAddress(new URL("http://localhost:8585")) .withToken(devToken)
 * .withProjectName("TestProjectDemo").withJobName("TestProjectDemoJob")
 * .build(ChromeDriver.class);
 * 
 * driver.manage().window().maximize(); driver.navigate().to(baseURL); //
 * driver.manage().window().setPosition(new Point(-2000, 0)); }
 * 
 * 
 * @AfterTest public void tearDown() { System.out.println("In tear down");
 * //driver.quit(); }
 * 
 * }
 */