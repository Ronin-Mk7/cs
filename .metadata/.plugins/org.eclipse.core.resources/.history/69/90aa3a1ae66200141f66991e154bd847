package cs01printinganddebugging;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Random;

import dose.*;
import csglobals.*;

/**
 * Print Name and Town
 * @author Arthur Pachachura
 * Prints my name and hometown, randomly generated of course.
 */
public class PrintNameAndTown 
{
    // method main(): program starting point
    public static void main( String[] args ) 
	{
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
    	//load the textfiles using Dose
    	String[] dirs = new String[] { System.getProperty("user.dir") + "/bin", System.getProperty("user.dir") + "/" };
    	final DoseFile cities = new DoseFile("citiesusa.txt", dirs, PrintNameAndTown.class);
    	final DoseFile famous = new DoseFile("famous.txt", dirs, PrintNameAndTown.class);
    	
    		//count how many lines in the text file
	    	int lcities = IO.countLines(cities);
	    	int lfamous = IO.countLines(famous);
	    	
	    	//randomize which line we're going to select
	    	Random rand = new java.util.Random();
	    	int randcities = rand.nextInt(lcities);
	    	int randfamous = rand.nextInt(lfamous);
	    	
	    	//read each file
	    	String city = IO.readnthline(cities, randcities);
	    	String name = IO.readnthline(famous, randfamous);
	    	String output = String.format("My name is %s and I am from %s.", name, city);
	    	
	    	//output the solution
	    	return out.writeDiag(output, 
	    			"Print Name and Town",
	    			new String[] {"Regenerate", "Close"});
    }
}