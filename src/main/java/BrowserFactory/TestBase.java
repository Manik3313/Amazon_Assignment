package BrowserFactory;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class TestBase 
{
	protected static RemoteWebDriver driver;
	Browserfactory bf= new Browserfactory();
	@BeforeMethod
	public void launchDemo() throws MalformedURLException
	{
		//RemoteWebDriver driverInstance=bf.create_instance("Chrome");
		DriverFactory.getInstance().setdriver(bf.create_instance("Chrome"));
		driver=DriverFactory.getInstance().getdriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to("https://www.wikihow.com/Find-the-IP-Address-of-Your-PC");
	}
	@AfterMethod
	public void tearDown()
	{
		DriverFactory.getInstance().closeBrowser();
	}
}
