package pages;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import locators.AgentPageLocator;

public class AgentPage extends AgentPageLocator 
{
	 RemoteWebDriver chromeDriver;
   
	
    public AgentPage(RemoteWebDriver driver) {
        super(driver);
        this.chromeDriver = driver;
        PageFactory.initElements(chromeDriver, this);
    }

    public void clickReady() throws Throwable
    {
    	commonActions.click(ReadyTag);
    }
}
