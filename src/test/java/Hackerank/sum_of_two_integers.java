package Hackerank;

import java.util.ArrayList;

public class sum_of_two_integers
{

	/*
	 * Determine if the sum of two integers is equal to the given value Given an
	 * array of integers and a value, determine if there are any two integers in the
	 * array whose sum is equal to the given value. Return true if the sum exists
	 * and return false if it does not. Consider this array and the target sums:
	 * 
	 * 5 7 1 2 8 4 3 Target Sum 10 7+3=10, 2+8=10 Target Sum 19 No 2 values sum up
	 * to 19
	 */	
	public static void main(String[] args)
	{
		Integer target=15;
		ArrayList<Integer> list=new ArrayList<Integer>();
		list.add(5);
		list.add(7);
		list.add(1);
	    list.add(2);
		list.add(8);
		list.add(4); 
		list.add(3);
		list.add(10);
		
		for (int i = 0; i < list.size()-1; i++) 
		{
			for (int j =i+1; j < list.size(); j++) 
			{
				
				
				  if((list.get(i)+list.get(j))==target) 
				  {
				  System.out.println("Target "+target+" "+list.get(i)+"+"+list.get(j) );
				  }
				 
			}
			
		}
		
	}
}
