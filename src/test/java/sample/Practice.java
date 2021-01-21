package sample;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.testproject.sdk.DriverBuilder;
import io.testproject.sdk.drivers.web.EdgeDriver;

public class Practice
{
	BrowserFactory browserFactory=BrowserFactory.getInstance();
	RemoteWebDriver driver;
	@Parameters("browser")	
	@BeforeTest
	public void setBrowser(String browser) throws MalformedURLException
	{
		if (browser.equals("Chrome"))
			{
			//BrowserFactory browserFactory=BrowserFactory.getInstance();
			browserFactory.setDriver("Chrome");
			driver=browserFactory.getDriver();
			}
		
		else if(browser.equals("Edge"))
			{
			//BrowserFactory browserFactory=BrowserFactory.getInstance();
			browserFactory.setDriver("Edge");
			driver=browserFactory.getDriver();
			}
		else
			{
			;;
			}
	}
	@Test
	public void start()
	{
		browserFactory.getDriver().get("https://www.wikihow.com/Find-the-IP-Address-of-Your-PC");
	}
}
