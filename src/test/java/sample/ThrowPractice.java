package sample;

public class ThrowPractice 
{
	static void rethrow()
	{
		try 
		{
			System.out.println("In rethrow");
			int x=3/0;
		} catch (ArithmeticException e) 
		{
			System.out.println("Unable to divide");
			throw e;
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args)
	{
		try 
		{
			rethrow();
		} catch (Exception  e)
		{
			System.out.println("Caught in main");
			// TODO: handle exception
		}
	}
}
