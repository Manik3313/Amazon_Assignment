package sample;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.testproject.sdk.internal.exceptions.AgentConnectException;
import io.testproject.sdk.internal.exceptions.InvalidTokenException;
import io.testproject.sdk.internal.exceptions.ObsoleteVersionException;

public class PracticeGroup extends BaseTest1
{

	@BeforeSuite
		public void suite() throws IOException, InvalidTokenException, AgentConnectException, ObsoleteVersionException
		{
			System.out.println("Before Suite");
			setConfig();
			setBrowserCapabilities("Edge");
			
		}
	@Parameters(value="value1")
	@BeforeTest
	public void test(String value1)
	{
		System.out.println("Before test");
		System.out.println(driver.getCurrentUrl());
		System.out.println("Manik "+value1);
	}

	@Parameters(value="value")
	@BeforeClass
	public void tclass(String value)
	{
		System.out.println("Before class");

		System.out.println(driver.getCurrentUrl());
		System.out.println("Manik "+value);
		
	}
	
	@Parameters(value="value")
	@Test(groups = {"Test1"})
	public void test1(String value)
	{
		System.out.println("In test"+value);

		//System.out.println(driver.getCurrentUrl());
	}
	@Test(groups = {"Test2"})
	public void test2()
	{
		System.out.println("In test2");

		System.out.println(driver.getCurrentUrl());
	}
	@Test(groups = {"Test3"})
	public void test3() throws InvalidTokenException, AgentConnectException, IOException, ObsoleteVersionException
	{	
		
		setBrowserCapabilities("Edge");
		System.out.println("In test3");

		System.out.println(driver.getCurrentUrl());
	}

	@AfterClass
	public void aclass()
	{
		System.out.println("After class");

		System.out.println(driver.getCurrentUrl());
	}
	
	@AfterTest
	public void atest()
	{
		System.out.println("After Test");

		System.out.println(driver.getCurrentUrl());
	}
	
	@AfterSuite
	public void aSuite()
	{
		System.out.println("After suite");

	}
	

	
}
