package dose;

import java.util.List;

/**
 * DOSE - JAR File Management
 * Dose can enumerate the contents of a JAR file as if it was a folder,
 * save contents locally, and read from them as if they were a stream.
 * Dose acts identically whether the file is in a JAR or on the disk.
 * Dose ALWAYS uses relative paths, relative to the class specified during initialization.
 */
public final class Dose {
	private Class callback;
	private List<DoseFile> files;
	
	/**
	 * Initialize dose with an initial relative path
	 * @param A class which all paths are relative to
	 */
	public Dose(Class c) {
		callback = c;
	}
	
	/**
	 * Get the underlying class used by Dose
	 * @return Returns the underlying class used for Dose calls
	 */
	public Class GetClass()
	{
		return callback;
	}
	
	/**
	 * Get the name of the package which the underlying class resides
	 * @return Returns the name of the package which the underlying class resides
	 */
	public String GetPackageName()
	{
		return callback.getPackage().getName();
	}
}
