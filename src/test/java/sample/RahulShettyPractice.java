package sample;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.testproject.sdk.DriverBuilder;
import io.testproject.sdk.drivers.web.EdgeDriver;

public class RahulShettyPractice {

	@Test
	public void test() throws MalformedURLException

	{
		RemoteWebDriver driver = new DriverBuilder<EdgeDriver>(new EdgeOptions())
				.withRemoteAddress(new URL("http://localhost:8585"))
				.withToken("PNULh4LMV6QhQAuR1LHfNZg0-map8hMVbMBKahMxMAo1").withProjectName("Practice1")
				.withJobName("PracticeJob1").build(EdgeDriver.class);
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		((EdgeDriver)driver).report().step("fail", false, true);
		
		/*
		 * File screenshotFile = ((TakesScreenshot)
		 * driver).getScreenshotAs(OutputType.FILE); String screenshotBase64 =
		 * ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		 */ 
			/*
			 * if(driver instanceof EdgeDriver) {
			 * ((EdgeDriver)driver).report().step("Fail",false,true);
			 * ((EdgeDriver)driver).getScreen }
			 */	
		 }
}
