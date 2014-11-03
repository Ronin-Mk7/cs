package cslabs;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class cs11alice {

	public static void main(String[] args) throws FileNotFoundException {
		// Prepares files
		Scanner in = new Scanner(new File("src/cslabs/alice.txt"));
				
		String[] letters = { "a", "e", "i", "o", "t" }; //letter list
		@SuppressWarnings("unchecked")
		List<String>[] sorted = (List<String>[]) new List<?>[5]; //final sorted lists
		
		for (int i=0; i<5; i++)
		{
			//initialize list
			sorted[i] = new ArrayList<String>();
		}		
				
		//read the file and add words to arrays
		int wordsTotal = 0;
		while (in.hasNext())
		{
			String word = in.next();
			for (int i=0; i<5; i++)
			{
				if (word.contains(letters[i]))
				{
					sorted[i].add(word);
				}
			}
			wordsTotal++;
		}

		//delete the file
		File aliceout = new File("src/cslabs/aliceoutput.txt");
		if (aliceout.exists()){
			aliceout.delete();
		}
		
		//prepare the file
		PrintWriter out = new PrintWriter(new FileOutputStream(aliceout.getAbsolutePath()));
		out.println("ALICE OUTPUT\r\nWord Count: " + wordsTotal);
		
		for (int i=0; i<5; i++)
		{
			double percent = (double)(sorted[i].size())/(double)(wordsTotal)*100D;
			out.println("Words with " + letters[i] + ": " + sorted[i].size() + 
						" (" + percent + "%)");
			
			//STRIP
			for (int j=0; j<sorted[i].size(); j++)
			{
				sorted[i].set(j, sorted[i].get(j).toLowerCase().trim()
						.replaceAll("\\W", "")); //strip anything that's not a legal text-char
			}
			
			sorted[i] = new ArrayList<String>(new HashSet<String>(sorted[i])); //remove duplicated
			java.util.Collections.sort(sorted[i]); //sort alphabetically
			out.println("Unique words with " + letters[i] + ": " + sorted[i].size());
		}
		
		for (int i=0; i<5; i++)
		{
			out.println("\r\n--------------------");
			out.println("--- WORDS WITH " + letters[i] + " ---");
			out.println("--------------------\r\n");
			
			for (int j=0; j<sorted[i].size(); j++)
			{
				out.println(sorted[i].get(j));
			}
		}
		
	}

}
