package dose;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author Arthur Pachachura
 * A file or directory controlled by Dose.  Can be either local or inside a JAR.
 */
public class DoseFile extends File {
	private String path = null;
	private Class callback = null;
	private Boolean injar = false;
	//private URI fileuri = null;
	
	/*public DoseFile(String localfile) {
		super(localfile);
		path = localfile;
		callback = null;
	}
	public DoseFile(String localfile, String relativeto) {
		super(relativeto + localfile);
		path = relativeto + localfile;
		callback = null;
	}
	public DoseFile(String resource, Class relativeto) throws URISyntaxException {
		super(GetConnection(relativeto).getJarFileURL().toURI());
		callback = relativeto;
		path = resource;
	}*/
	public DoseFile(String resource, String dir, Class relativeto) throws URISyntaxException {
		super(TestProtocol(resource, dir, relativeto));
		
		callback = relativeto;
		final String thispackage = relativeto.getPackage().getName();
		path = thispackage + "/" + resource;
	}
	public DoseFile(String resource, String[] possibledirs, Class relativeto) throws URISyntaxException {
		super(TestProtocol(resource, possibledirs, relativeto));
		
		callback = relativeto;
		final String thispackage = relativeto.getPackage().getName();
		path = thispackage + "/" + resource;
	}
	
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
	public InputStreamReader GetReader() {
		if (IsInJAR()) {
			return new InputStreamReader(callback.getResourceAsStream("/" + path));
		}
		else {
			try {
				return new InputStreamReader(new FileInputStream((File)this));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	public Boolean IsInJAR() {
		Boolean isinjar = this.toString().contains("!/" + path);
		return isinjar;
	}
	
	private static String GetAbsoluteFile(String resource, String dir, Class relativeto) {
		final String thispackage = relativeto.getPackage().getName();
		return dir + "/" + thispackage + "/" + resource;
	}
	
	private static URI GetAbsoluteJar(URI baseURI, String resource, Class relativeto) throws URISyntaxException {
		final String thispackage = relativeto.getPackage().getName();
		String stringuri = baseURI.toString();
		stringuri = stringuri + "!/" + thispackage + "/" + resource;
		URI uri = new URI(stringuri);
		return uri;
	}
	
	private static File TestLocal(String resource, String dir, Class relativeto) {
		//test local file first
		File file = new File(GetAbsoluteFile(resource, dir, relativeto));
		if (file.exists()) {
			return file;
		}
		//if fails, returns null
		return null;
	}
	
	private static URI TestProtocol(String resource, String dir, Class relativeto) throws URISyntaxException {		
		//Test local files first
		if (TestLocal(resource, dir, relativeto) != null) 
		{ return MakeLocalURI(resource, dir, relativeto); }
		
		//If all tests fail, return URI of file in JAR
		URI JarURI = GetAbsoluteJar(GetConnection(resource, relativeto).getJarFileURL().toURI(), resource, relativeto);
		return JarURI;
	}
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
	
	private static URI MakeLocalURI(String resource, String dir, Class relativeto) {
		File file = new File(GetAbsoluteFile(resource, dir, relativeto));
		return file.toURI();
	}
	
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
