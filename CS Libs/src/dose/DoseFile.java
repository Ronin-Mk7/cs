package dose;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
		super(TestProtocol(resource, GetConnection(resource, relativeto).getJarFileURL().toURI(), dir, relativeto));
		callback = relativeto;
		path = resource;
	}
	public DoseFile(String resource, String[] possibledirs, Class relativeto) throws URISyntaxException {
		super(TestProtocol(resource, GetConnection(resource, relativeto).getJarFileURL().toURI(), possibledirs, relativeto));
		callback = relativeto;
		path = resource;
	}
	
	private static File TestLocal(URI resource, String dir, Class relativeto) {
		//test local file first
		File file = new File(resource);
		if (file.exists()) {
			return file;
		}
		return null;
	}
	private static URI TestProtocol(String resource, URI resourceuri, String dir, Class relativeto) {
		//Test local files first
		if (TestLocal(resourceuri, dir, relativeto) != null) 
		{ return MakeLocalURI(resource, dir); }
		//If all tests fail, return URI of file in JAR
		return resourceuri;
	}
	private static URI TestProtocol(String resource, URI resourceuri, String[] possibledirs, Class relativeto) {
		//Test local files first
		for (int i=0; i<possibledirs.length; i++) {
			if (TestLocal(resourceuri, possibledirs[i], relativeto) != null) 
			{ return MakeLocalURI(resource, possibledirs[i]); }
		}
		//If all tests fail, return URI of file in JAR
		return resourceuri;
	}
	private static URI MakeLocalURI(String resource, String dir) {
		File file = new File(dir + resource);
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
	
	public Boolean IsInJAR() {
		return false;
	}
}
