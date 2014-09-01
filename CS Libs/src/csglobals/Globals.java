package csglobals;

import dose.DoseOutput;

/**
 * Globals
 * @author arthur
 * Every Computer Science program uses these!
 */
public final class Globals {
	
	/**
	 * Displays a copyright dialog.
	 * @return A DoseOutput method.
	 * @throws Exception if something weird happens.
	 */
    public static final DoseOutput PrintCopyright() throws Exception {
    	//Create a frame to output everything to
    	DoseOutput out = new DoseOutput(System.out, DoseOutput.createBlankFrame());
    	
    	//Prepare console
    	out.writeDiag("(C) 2014 Arthur Pachachura\r\n" + 
			"This program uses the Dose library by Arthur Pachachura.\r\nLicensed under MIT.\r\n\r\n" + 
			"You can find the source for this program at https://github.com/smo-key/cs/tree/master/CS%20Labs.\r\n",
			"Copyright Notice",
			new Object[] {"OK"});

    	return out;
    }
}