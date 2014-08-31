package dose;

import java.util.List;

/**
 * DOSE - JAR File Management
 * Dose can enumerate the contents of a JAR file as if it was a folder,
 * save contents locally, and read from them as if they were a stream.
 * Dose acts identically whether the file is in a JAR or on the disk.
 * Dose ALWAYS uses relative paths, relative to the class specified during initialization.
 */
public final class Doser {
	private List<DoseFile> files;
	private String tempdir = System.getProperty("java.io.tmpdir");
	
	/**
	 * Initialize dose with a class
	 */
	public Doser(Class c) {
		
	}
	
	/**
	 * Initialize Dose with a custom temporary directory.
	 * @param tmp Custom temporary directory, where Dose stores all its files
	 */
	public Doser(String tmp)
	{
		SetTempDir(tmp);
	}
	
	public Boolean IsJAR() {
		return true;
	}
	
	/**
	 * Returns the default temporary directory used by Dose.
	 * @return Returns the default temporary directory used by Dose.
	 */
	public String GetTempDir()
	{
		return tempdir;
	}
	
	/**
	 * Sets the default temporary directory used by Dose.
	 */
	public void SetTempDir(String tmp)
	{
		tempdir = tmp;
	}
}
