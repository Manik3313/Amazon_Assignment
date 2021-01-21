package Hackerank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*Reverse Words in a Sentence
Reverse the order of words in a given sentence (an array of characters).

"Hello World"
"World Hello"
*/
public class ReverseWordsInASentence 
{
	public static void main(String[] args) 
	{
		String sentence="Hello World My name is manik";
		String reverse = "";
		ArrayList<String> list=new ArrayList<String>();
		Scanner scan = new Scanner(sentence);  
        scan.useDelimiter(" ");
        while(scan.hasNext())
        {
        	list.add(scan.next());
        }
       Collections.reverse(list);
       for (String element :list)
       {
    	reverse=reverse.concat(element);
		reverse=reverse.concat(" ");
       }
      System.out.println(reverse);
	}

}
