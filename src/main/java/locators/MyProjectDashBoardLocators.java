package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import helpers.CommonActions;
import pages.BasePage;

public class MyProjectDashBoardLocators extends BasePage {

    public MyProjectDashBoardLocators(CommonActions commonActions){
        super(commonActions);
    }

    protected static String applicationNameXpath = "//div//span[contains(text(),''{0}'')]";

    @FindBy(xpath = "//div//span[contains(text(),'Applications')]")
    protected WebElement applicationsLink;

    @FindBy(css = "div.content-items-wrapper")
    protected WebElement contentItemWrapper;
}
