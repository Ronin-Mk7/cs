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
		String[] months = new String[] { "November", "December", "January", "February" };
		Random rand = new java.util.Random();
    	int randmonth = rand.nextInt(months.length);
		out.println(String.format("My favorite winter month is %s.", months[randmonth]));
		return out.writeDiag("Print Month", new String[] { "That's cool!" });
    }
}
