package BrowserFactory;

import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory 
{
	
	//ThreadLocal-->java.lang-->Threading
	//Design Pattern -->Represent Best Practices 
	//Singleton Design Pattern -only one instance exist ever,provide global access to that instance by creating getInstance method 
	//factory Design Pattern

	//private constructor so that no one else can create an object of this class 
	
	protected static ThreadLocal<RemoteWebDriver> driver= new ThreadLocal<RemoteWebDriver>();
	
	private DriverFactory()
	{
		
	}
	private static DriverFactory instance= new DriverFactory();
	public static DriverFactory getInstance()
	{
		return instance;
	}
	//factory design pattern-->define seperate factory methods for creating objects and creating object by calling that methods 
	
	public RemoteWebDriver getdriver()
	{
		return driver.get();
	}
	public void setdriver(RemoteWebDriver driveparam)
	{
		driver.set(driveparam);
	}
	
	public void closeBrowser()
	{
		driver.get().close();
		driver.remove();
	}
	
	
	
	/*
	 * private static final AtomicInteger nextId=new AtomicInteger(0);
	 * 
	 * //Thread Local variable containing each Thread Id private static final
	 * ThreadLocal<Integer> threadId=new ThreadLocal<Integer>() {
	 * 
	 * @Override protected Integer initialvalue() { return nextId.getAndIncrement();
	 * 
	 * } };
	 * 
	 * //Returns the current thread's unique ID,assigning it if necesaary public
	 * static int get() { return threadId.get(); }
	 */
			
}
