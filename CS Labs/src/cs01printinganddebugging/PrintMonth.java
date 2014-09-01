package cs01printinganddebugging;

import java.util.Random;

import dose.*;
import csglobals.*;

/**
 * Print Month
 * @author Arthur Pachachura
 * Prints my favorite winter month, randomly generated of course.
 */
public class PrintMonth {

	public static void main(String[] args) {
		try {
    		
			//Prepare window
			DoseOutput out = Globals.PrintCopyright();
			
			//Run once
			run(out);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	//Exit with status 0 (OK)
    	System.exit(0);
	}
    
	public static void run(DoseOutput out) throws Exception
    {    	
		String[] months = new String[] { "November", "December", "January", "February", "March!\r\nThat's also my birthday" };
		Random rand = new java.util.Random();
    	int randmonth = rand.nextInt(months.length);
		out.println(String.format("My favorite winter month is %s.", months[randmonth]));
		out.writeDiag("Print Month", new String[] { "That's cool!" });
    }

}
