package sample;

import java.util.ArrayList;

public class HackerRank 
{
public static void main(String[] args) 
	{
	ArrayList<Integer> arrayList=new ArrayList<Integer>();
	ArrayList<Integer> arrayFront=new ArrayList<Integer>();
	ArrayList<Integer> arrayBack=new ArrayList<Integer>();
	ArrayList<Integer> result=new ArrayList<Integer>();
	
	arrayList.add(10);
	arrayList.add(20);
	arrayList.add(30);
	arrayList.add(40);
	arrayList.add(50);
	arrayList.add(60);
	arrayList.add(70);
	arrayList.add(80);
	arrayList.add(90);
	arrayList.add(100);
	arrayList.add(110);
	
	Integer move=5;
	for (int i = move; i < arrayList.size(); i++)
	{
		arrayBack.add(arrayList.get(i));
	}
	for (int i = 0; i < move; i++)
	{
		arrayFront.add(arrayList.get(i));
	}
	
	result.addAll(arrayBack);
	result.addAll(arrayFront);
	System.out.println("Result"+result);
	}
}
