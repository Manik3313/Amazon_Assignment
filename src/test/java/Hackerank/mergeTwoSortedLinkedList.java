package Hackerank;

import java.util.Collections;
import java.util.LinkedList;

/*Merge two sorted linked lists
Given two sorted linked lists, merge them so that the resulting linked list is also sorted. Consider two sorted linked lists and the merged list below them as an example.
*/
public class mergeTwoSortedLinkedList
{
	public static void main(String[] args) 
	{
		LinkedList<Integer> list1=new LinkedList<Integer>();
		LinkedList<Integer> list2=new LinkedList<Integer>();
		list1.add(4);
		list1.add(8);
		list1.add(15);
		list1.add(19);
		
		list2.add(7);
		list2.add(9);
		list2.add(10);
		list2.add(16);
		
		list1.addAll(list2);
		Collections.sort(list1);
		System.out.println(list1);
	}
}
