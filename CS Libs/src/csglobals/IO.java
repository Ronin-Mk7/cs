package csglobals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import dose.DoseFile;

/**
 * I/O Manipulation
 * @author Arthur Pachachura
 * More powerful I/O manipulation.
 */
public final class IO {
	/**
	 * Read Nth line of a text file
	 * @param file Dose File class
	 * @param n Read this line
	 * @return Returns the string of the nth line
	 * @throws IOException Throws if file does not exist.
	 */
    public static String readnthline(DoseFile file, int n) throws IOException {
    	BufferedReader in = new BufferedReader(file.getReader());
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
    
    /**
     * Counts the number of lines in a text file
     * @param file Dose File class
     * @return Returns the number of lines in a file
     * @throws IOException Throws if file does not exist.
     */
    public static int countLines(DoseFile file) throws IOException {
        InputStream is = file.getStream();
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
