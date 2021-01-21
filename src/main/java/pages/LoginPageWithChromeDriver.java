package pages;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import helpers.CommonActions;
import locators.LoginPageLocators;

public class LoginPageWithChromeDriver extends LoginPageLocators {
    RemoteWebDriver chromeDriver;

    public LoginPageWithChromeDriver(CommonActions commonActions) {
        super(commonActions);
        this.chromeDriver = commonActions.getDriver();
        PageFactory.initElements(chromeDriver, this);
    }

    /*
	 * public LoginPageWithChromeDriver(RemoteWebDriver driver) { super(driver);
	 * this.chromeDriver = driver; PageFactory.initElements(chromeDriver, this); }
	 */
    public LoginPageWithChromeDriver setUserName(String userNameText) throws Throwable {
        commonActions.typeKeys(userName, userNameText);
        return this;
    }

    public LoginPageWithChromeDriver setPassword(String passwordText) throws Throwable {
        commonActions.typeKeys(password, passwordText);
        return this;
    }

    public HomePage clickSignInButton() throws Throwable {
        commonActions.click(signInButton);
        return new HomePage(commonActions);
    }

    public HomePage login(String userName, String password) throws Throwable {
        return setUserName(userName).setPassword(password).clickSignInButton();
    }
}
