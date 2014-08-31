package cs01printinganddebugging;
// name: Print Name and Town
// purpose: Prints you name and your hometown.

import java.io.*;
import java.net.URISyntaxException;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dose.DoseFile;

public class PrintNameAndTown 
{
    // method main(): program starting point
    public static void main( String[] args ) 
	{
    	//Print stuff
    	System.out.println("(C) 2014 Arthur Pachachura");
    	System.out.println("This program uses the Dose library by Arthur Pachachura.");
    	System.out.println("");
    	
    	//Run until "Close" button is pressed
    	try {
			do { } while (GetName() == 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	//Exit with status 0 (OK)
    	System.exit(0);
	}
    
    public static int GetName() throws IOException, URISyntaxException
    {
    	//Create a frame to output everything to
    	JFrame frame = new JFrame("FrameDemo");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.pack(); //Size the frame
    	frame.setVisible(false);
    	
    	//load the textfiles using Dose
    	String[] dirs = new String[] { System.getProperty("user.dir") + "/bin", System.getProperty("user.dir") + "/" };
    	final DoseFile cities = new DoseFile("citiesusa.txt", dirs, PrintNameAndTown.class);
    	final DoseFile famous = new DoseFile("famous.txt", dirs, PrintNameAndTown.class);
    	
    	try 
    	{
    		//count how many lines in the text file
	    	int lcities = countLines(cities);
	    	int lfamous = countLines(famous);
	    	
	    	//randomize which line we're going to select
	    	Random rand = new java.util.Random();
	    	int randcities = rand.nextInt(lcities);
	    	int randfamous = rand.nextInt(lfamous);
	    	
	    	//read each file
	    	String city = readnthline(cities, randcities);
	    	String name = readnthline(famous, randfamous);
	    	String output = String.format("My name is %s and I am from %s.", name, city);
	    	
	    	//output the solution to stdout
	    	System.out.println(String.format("My name is %s and I am from %s.", name, city));
	    	System.out.println("(Run this program again to regenerate.)");
	    	
	    	//display the output dialog
	    	Object[] options = {"Regenerate",
                    "Close"};
			return JOptionPane.showOptionDialog(frame, output, 
			    "Print Name and Town",
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.PLAIN_MESSAGE,
			    null,
			    options,
			    options[0]);
	    	
    	} catch (IOException e)
    	{
    		//Catch an exception
    		e.printStackTrace();
    		return -1;
    	}
    }

    //read the Nth line from a text file
    public static String readnthline(DoseFile file, int n) throws IOException {
    	BufferedReader in = new BufferedReader(file.GetReader());
    	try
    	{
    		for (int i=0; i<n; i++)
    		{
    			in.readLine();
    		}
    		return in.readLine().trim();
    	} finally {
    		in.close();
    	}
    }
    
    //counts the amount of lines in a text file
    public static int countLines(DoseFile file) throws IOException {
        InputStream is = file.GetStream();
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        } finally {
            is.close();
        }
    }
}