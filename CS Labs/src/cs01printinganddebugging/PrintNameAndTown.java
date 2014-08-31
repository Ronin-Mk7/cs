package cs01printinganddebugging;
// name: Print Name and Town
// purpose: Prints you name and your hometown.

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PrintNameAndTown 
{
    // method main(): program starting point
    public static void main( String[] args ) 
	{
    	try {
			do { } while (GetName() == 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	
	}
    
    public static int GetName() throws IOException
    {
    	//Global directory information
    	final String thispackage = PrintNameAndTown.class.getPackage().getName();
    	
    	//Create a frame to output everything to
    	JFrame frame = new JFrame("FrameDemo");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.pack(); //Size the frame
    	frame.setVisible(false);
    	   	
    	//find correct directory for text files
    	//System.out.println(System.getProperty("user.dir")); //print current directory
    	File dir = new File("bin/" + thispackage + "/");
    	if (!dir.exists())
    	{
    		dir = new File("/" + thispackage + "/");
    		if (!dir.exists())
        	{
    			System.out.println(String.format("Folder %s not found, this must be a JAR", dir.getName()));
    			// Load the directory as a resource and extract files locally
    			File file = new File("citiesusa.txt");
    			if (!file.exists()) {
    			    InputStream link = (PrintNameAndTown.class.getResourceAsStream("citiesusa.txt"));
    			    Files.copy(link, file.getAbsoluteFile().toPath());
    			}
    			file = new File("famous.txt");
    			if (!file.exists()) {
    			    InputStream link = (PrintNameAndTown.class.getResourceAsStream("famous.txt"));
    			    Files.copy(link, file.getAbsoluteFile().toPath());
    			}
    			//URL dir_url = ClassLoader.getSystemResource(thispackage + "/");
    			//String dir_url = PrintNameAndTown.class.getResource(thispackage + "/").toString();
    			// Turn the resource into a File object
    			dir = new File(".");
        	}
    	}
    	
    	//set text files to correct directory
    	final String citiestxt = dir + "/citiesusa.txt";
    	final String famoustxt = dir + "/famous.txt";
    	
    	try 
    	{
    		//count how many lines in the text file
	    	int lcities = countLines(citiestxt);
	    	int lfamous = countLines(famoustxt);
	    	
	    	//randomize which line we're going to select
	    	Random rand = new java.util.Random();
	    	int randcities = rand.nextInt(lcities);
	    	int randfamous = rand.nextInt(lfamous);
	    	
	    	//read each file
	    	String city = readnthline(citiestxt, randcities);
	    	String name = readnthline(famoustxt, randfamous);
	    	String output = String.format("My name is %s and I am from %s.", name, city);
	    	
	    	//output the solution to stdout
	    	System.out.println(String.format("My name is %s and I am from %s.", name, city));
	    	System.out.println("(Run this program again to regenerate.)");
	    	
	    	//display the output dialog
	    	Object[] options = {"Regenerate",
                    "Close"};
			return JOptionPane.showOptionDialog(frame, output, 
			    "",
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.PLAIN_MESSAGE,
			    null,
			    options,
			    options[0]);
	    	
    	} catch (IOException e)
    	{
    		System.err.println(String.format("%s", e.getMessage()));
    		return -1;
    	}
    }

    //read the Nth line from A text file
    public static String readnthline(String filename, int n) throws IOException {
    	BufferedReader in = new BufferedReader(new FileReader(filename), 1024);
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
    public static int countLines(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
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