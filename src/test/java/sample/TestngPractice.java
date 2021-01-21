package sample;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.testproject.sdk.drivers.web.ChromeDriver;
import io.testproject.sdk.internal.exceptions.AgentConnectException;
import io.testproject.sdk.internal.exceptions.InvalidTokenException;
import io.testproject.sdk.internal.exceptions.ObsoleteVersionException;

/**
 * Runs tests on {@link ChromeDriver}.
 */
public class TestngPractice extends BaseTest1 {

    

    /**
     * Driver instance.
     */
	/*
	 * @Parameters(value="Browser")
	 * 
	 * @BeforeClass(description="Rahul Shetty") public void PracticeSetup( String
	 * Browser ) throws InvalidTokenException, AgentConnectException, IOException,
	 * ObsoleteVersionException { setConfig(); setBrowserCapabilities(Browser); }
	 */
	 @BeforeClass(description="Rahul Shetty")    
    @Test(groups = {"Greenkart"}, testName = "Example Test #1")
    void testExample1()
    {
    	
    	driver.get("https://www.javatpoint.com/testng-groups");// AutomationFlows.runFlow(driver);
    	
    }

    @Test( testName = "Example Test #2")
    void testExample()
    {
    	
    	driver.get("https://www.javatpoint.com/testng-groups");// AutomationFlows.runFlow(driver);
    	    }

}