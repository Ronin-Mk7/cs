package dose;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author Arthur Pachachura
 * A file or directory controlled by Dose.  Can be either local or inside a JAR.
 */
public class DoseFile extends File {
	
	/**
	 * Serializable Version UID for Dose
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Path of a DoseFile
	 * Format: {Package}/{Resource.ext}
	 */
	private String path = null;
	
	@SuppressWarnings("rawtypes")
	/**
	 * Relative class callback.  The DoseFile is relative to this class.
	 */
	private Class callback = null;
	
	@SuppressWarnings("rawtypes")
	/**
	 * Initialize a Dose file with a resource, a directory, and a class.
	 * @param resource String resource in the form of {filename}.{ext}.  Ex: "file.txt"
	 * @param dir Directory where the Dose file resides, relative to the project folder. Ex: "bin/" or "" (current folder)
	 * @param relativeto File must be in the same directory as this class.  Usually the class that creates this DoseFile.
	 * @throws URISyntaxException This should never happen.
	 */
	public DoseFile(String resource, String dir, Class relativeto) throws URISyntaxException {
		super(TestProtocol(resource, dir, relativeto));
		
		callback = relativeto;
		final String thispackage = relativeto.getPackage().getName();
		path = thispackage + "/" + resource;
	}
	
	@SuppressWarnings("rawtypes")
	/**
	 * Initialize a Dose file with a resource, a directory, and a class.
	 * @param resource String resource in the form of {filename}.{ext}.  Ex: "file.txt"
	 * @param possibledirs Directories where the Dose file could reside, relative to the project folder. Ex: "bin/" or "" (current folder)
	 * @param relativeto File must be in the same directory as this class.  Usually the class that creates this DoseFile.
	 * @throws URISyntaxException This should never happen.
	 */
	public DoseFile(String resource, String[] possibledirs, Class relativeto) throws URISyntaxException {
		super(TestProtocol(resource, possibledirs, relativeto));
		
		callback = relativeto;
		final String thispackage = relativeto.getPackage().getName();
		path = thispackage + "/" + resource;
	}
	
	/**
	 * Get an InputStream resource from this DoseFile.
	 * @return Returns an InputStream representing this file.
	 */
	public InputStream GetStream() {
		if (IsInJAR()) {
			return callback.getResourceAsStream("/" + path);
		}
		else {
			try {
				return new BufferedInputStream(new FileInputStream((File)this));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	/**
	 * Get an InputStreamReader from this Dose File.
	 * @return Returns an InputStreamReader representing this file.
	 */
	public InputStreamReader GetReader() {
		return new InputStreamReader(GetStream());
	}
	
	/**
	 * Returns true if this file is located in a JAR.
	 * @return Returns true if this file is located in a JAR, false otherwise.
	 */
	public Boolean IsInJAR() {
		Boolean isinjar = this.toString().contains("!/" + path);
		return isinjar;
	}
	
	@SuppressWarnings("rawtypes")
	/**
	 * Gets the absolute file location of a resource.
	 * @param resource String resource in the form of {filename}.{ext}.  Ex: "file.txt"
	 * @param dir Directory where the Dose file resides, relative to the project folder. Ex: "bin/" or "" (current folder)
	 * @param relativeto File must be in the same directory as this class.  Usually the class that creates this DoseFile.
	 * @return
	 */
	private static String GetAbsoluteFile(String resource, String dir, Class relativeto) {
		final String thispackage = relativeto.getPackage().getName();
		return dir + "/" + thispackage + "/" + resource;
	}
	@SuppressWarnings("rawtypes")
	/**
	 * Gets an absolute URI for a JAR file.
	 * @param baseURI 
	 * @param resource String resource in the form of {filename}.{ext}.  Ex: "file.txt"
	 * @param relativeto File must be in the same directory as this class.  Usually the class that creates this DoseFile.
	 * @return Returns a URI of the precise JAR file in the form {baseURI}!/{package}/{resource}.  If you want to add this directly to a web browser, convert this to a string and prepend "jar:".
	 * @throws URISyntaxException Throws an error if the file mentioned in baseURIs does not exist.
	 */
	private static URI GetAbsoluteJar(URI baseURI, String resource, Class relativeto) throws URISyntaxException {
		final String thispackage = relativeto.getPackage().getName();
		String stringuri = baseURI.toString();
		stringuri = stringuri + "!/" + thispackage + "/" + resource;
		URI uri = new URI(stringuri);
		return uri;
	}
	
	@SuppressWarnings("rawtypes")
	/**
	 * Tests whether a local file exists.
	 * @param resource String resource in the form of {filename}.{ext}.  Ex: "file.txt"
	 * @param dir Directory where the Dose file resides, relative to the project folder. Ex: "bin/" or "" (current folder)
	 * @param relativeto File must be in the same directory as this class.  Usually the class that creates this DoseFile.
	 * @return Returns an instance of the File if it exists, false otherwise.
	 */
	private static File TestLocal(String resource, String dir, Class relativeto) {
		//test local file first
		File file = new File(GetAbsoluteFile(resource, dir, relativeto));
		if (file.exists()) {
			return file;
		}
		//if fails, returns null
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	/**
	 * Tests whether the file is local or inside a JAR and returns its URI.
	 * @param resource String resource in the form of {filename}.{ext}.  Ex: "file.txt"
	 * @param dir Directory where the Dose file resides, relative to the project folder. Ex: "bin/" or "" (current folder)
	 * @param relativeto File must be in the same directory as this class.  Usually the class that creates this DoseFile.
	 * @return Returns an absolute URI.
	 * @throws URISyntaxException This should never happen.
	 */
	private static URI TestProtocol(String resource, String dir, Class relativeto) throws URISyntaxException {		
		//Test local files first
		if (TestLocal(resource, dir, relativeto) != null) 
		{ return MakeLocalURI(resource, dir, relativeto); }
		
		//If all tests fail, return URI of file in JAR
		URI JarURI = GetAbsoluteJar(GetConnection(resource, relativeto).getJarFileURL().toURI(), resource, relativeto);
		return JarURI;
	}
	
	@SuppressWarnings("rawtypes")
	/**
	 * Tests whether multiple files are local or inside a JAR and returns the URI of the first file that exists.
	 * @param resource String resource in the form of {filename}.{ext}.  Ex: "file.txt"
	 * @param possibledirs Directories where the Dose file could reside, relative to the project folder. Ex: "bin/" or "" (current folder)
	 * @param relativeto File must be in the same directory as this class.  Usually the class that creates this DoseFile.
	 * @return Returns the absolute URI of the first file that exists.
	 * @throws URISyntaxException
	 */
	private static URI TestProtocol(String resource, String[] possibledirs, Class relativeto) throws URISyntaxException {

		//Test local files first
		for (int i=0; i<possibledirs.length; i++) {
			if (TestLocal(resource, possibledirs[i], relativeto) != null) 
			{ return MakeLocalURI(resource, possibledirs[i], relativeto); }
		}
		
		//If all tests fail, return URI of file in JAR
		URI JarURI = GetAbsoluteJar(GetConnection(resource, relativeto).getJarFileURL().toURI(), resource, relativeto);
		return JarURI;
	}
	
	@SuppressWarnings("rawtypes")
	/**
	 * Returns an absolute URI instance of a local file.
	 * @param resource String resource in the form of {filename}.{ext}.  Ex: "file.txt"
	 * @param dir Directory where the Dose file resides, relative to the project folder. Ex: "bin/" or "" (current folder)
	 * @param relativeto File must be in the same directory as this class.  Usually the class that creates this DoseFile.
	 * @return Returns an absolute URI instance of a local file.
	 */
	private static URI MakeLocalURI(String resource, String dir, Class relativeto) {
		File file = new File(GetAbsoluteFile(resource, dir, relativeto));
		return file.toURI();
	}
	
	@SuppressWarnings("rawtypes")
	/**
	 * Opens a Jar URL Connection to allow a file to accessed.
	 * @param resource String resource in the form of {filename}.{ext}.  Ex: "file.txt"
	 * @param relativeto File must be in the same directory as this class.  Usually the class that creates this DoseFile.
	 * @return Throws an insane exception if the file does not exist.
	 */
	private static JarURLConnection GetConnection(String resource, Class relativeto) {
		try {
			final String thispackage = relativeto.getPackage().getName();
			URL url = ClassLoader.getSystemResource(thispackage + "/" + resource);
			JarURLConnection connection = (JarURLConnection) url.openConnection();
			return connection;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
