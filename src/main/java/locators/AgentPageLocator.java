package locators;

import io.testproject.sdk.drivers.web.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class AgentPageLocator extends BasePage {

    public AgentPageLocator(RemoteWebDriver driver)
    {
        super(driver);

    }

	@FindBy(xpath = "//div[@class='agents-state-filter ng-binding ng-scope' and contains(text(),'Ready')]")//(css = "a[href='#/home']")
    protected WebElement ReadyTag;
    

}

