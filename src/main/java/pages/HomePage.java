package pages;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import helpers.CommonActions;
import locators.HomePageLocators;

public class HomePage extends HomePageLocators {
    RemoteWebDriver chromeDriver;

    public HomePage(CommonActions commonActions) {
        super(commonActions);
        this.chromeDriver = commonActions.getDriver();
        PageFactory.initElements(chromeDriver, this);
    }

    public boolean isHomePageLogoDisplayed() throws Throwable {
        return commonActions.verifyElementIsDisplayed(homePageLogo);
    }

    public MyProjectDashBoard selectOptionInMyProjectDropdown(String optionText) throws Throwable {
        commonActions.selectOptionForNoSelectTag(myProjectsDropdown, searchAProjectInputFieldInMyProjectDropdown, filteredElementInMyProjectsDropDown, optionText);
        return new MyProjectDashBoard(commonActions);
    }

    public void hoverAndSelectFromMenuBar(String element, String subelement) throws Throwable {
        commonActions.hoverAndSelectElemnt(projectMenuBar(element), subelement);
    }

    public IntegrationPage selectIntegrationFromMenuBar() throws Throwable {
        commonActions.click(projectMenuBar("Integrations"));
        return new IntegrationPage(chromeDriver);
    }
        public void clickFromMenubar(String element) throws Throwable {
        commonActions.click(projectMenuBar(element));
    }
        public AgentPage selectAgentFromMenuBar() throws Throwable
        {
        	commonActions.click(fromMenubarAgent);

        	//commonActions.click(fromMenubarAgent);
        	return new AgentPage(chromeDriver);
        }
    /*public void hoverOverAndClickProject(String element,String subelement) throws Throwable {

        commonActions.hoverOverAndClick(element,subelement);
        //, driver.findElement(By.xpath("//span[contains(text(),'Demo')]")));

    }

    public void selectProjectFromMenuBar(String value) throws Throwable
    {

        commonActions.click(projectNameFromMenuBar(value));
    }*/
}
