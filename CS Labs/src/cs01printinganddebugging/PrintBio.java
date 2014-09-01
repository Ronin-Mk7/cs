package cs01printinganddebugging;

import java.util.Random;

import dose.*;
import csglobals.*;

/**
 * Print Biography
 * @author Arthur Pachachura
 * My biography, some of it could be randomly generated... of course.
 */
public class PrintBio
{
	public static void main(String[] args) {
		try {
    		
			//Prepare window
			DoseOutput out = Globals.PrintCopyright();
			
			//Run until "Close" button is pressed
			do { } while (run(out) == 0);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	//Exit with status 0 (OK)
    	System.exit(0);
	}
    
	public static int run(DoseOutput out) throws Exception
    {    	
		//prepare data sources
		final String[] dirs = new String[] { System.getProperty("user.dir") + "/bin", System.getProperty("user.dir") + "/" };
    	final DoseFile cities = new DoseFile("citiesusa.txt", dirs, PrintNameAndTown.class);
    	final DoseFile names = new DoseFile("famous.txt", dirs, PrintNameAndTown.class);
		final String[] months = new String[] { "January", "February", "March", "April", "May", "June",
				"July", "August", "September", "October", "November", "December" };
		
		//count size of data sources
    	int lcities = IO.countLines(cities);
    	int lnames = IO.countLines(names);
		
		//randomize data sources
    	Random rand = new java.util.Random();
    	String name1 = IO.readnthline(names, rand.nextInt(lnames));
    	String city1 = IO.readnthline(cities, rand.nextInt(lcities));
    	String city2 = IO.readnthline(cities, rand.nextInt(lcities));
    	String date1 = months[rand.nextInt(months.length)];
    	
		//print lines to buffer
		out.println(String.format("Hi, my name is %s.", name1));
		out.println(String.format("I was born on %s %d, %d in %s.", date1, 1 + rand.nextInt(27), 1996 + rand.nextInt(3), city1));
		out.println(String.format("I used to live in %s, but moved %d years ago to Austin, Texas.", city2, 2 + rand.nextInt(8)));
		out.println(String.format("I now attend the Liberal Arts and Science Academy.\r\n"));
		out.println(String.format("And I <3 Computer Science."));
		
		//output the solution
    	return out.writeDiag( "Print Biography",
    			new String[] {"Regenerate", "Close"});
    }
}
