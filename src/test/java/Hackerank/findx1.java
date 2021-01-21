package Hackerank;

import java.util.Collections;
import java.util.LinkedList;


/*
Find the missing number in the array
You are given an array of positive numbers from 1 to n, such that all numbers from 1 to n are present except one number x. You have to find x. The input array is not sorted. Look at the below array and give it a try before checking the solution.

3
7
1
2
8
4
5
n = 8 missing number = 6

*/
public class findx1
{

	public static void main(String[] args) 
	{
		LinkedList< Integer>list= new LinkedList<Integer>();
		list.add(2);
		list.add(8);
		list.add(5);
		list.add(1);
		list.add(6);
		list.add(4);
		list.add(7);
		
		Collections.sort(list);
		for (int i = 0; i < list.size()-1; i++) 
		{			
			  if((list.get(i)+1)!=list.get(i+1))
			  {
				  list.add(i+1, list.get(i)+1);
			  }
			 
	  }
		System.out.println(list);
		
		
	}
}
