package dose;

import java.io.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

//TODO it would be nice to have every print() be intercepted until a flush() in WriteDiag(), where both stream and dialog would be written simultaneously.

/**
 * @author Arthur Pachachura
 * DOSE output doubler.  Makes outputting to both modals and streams really easy.
 */
public class DoseOutput extends PrintStream {	
	/**
	 * Default message style as found in JOptionPane.xxx_MESSAGE
	 */
	private int messagestyle = JOptionPane.PLAIN_MESSAGE;
	/**
	 * JFrame to write everything to.
	 */
	public JFrame frame = null;
	
	/**
	 * Initialize an output doubler stream straight from another PrintStream (recommended: System.out).
	 */
	public DoseOutput(PrintStream stream, JFrame frame) {
		super((OutputStream) stream, true);
		this.frame = frame;
	}
	/**
	 * Initialize an output doubler stream with the default settings.
	 * @throws FileNotFoundException 
	 */
	public DoseOutput(File file, JFrame frame) throws FileNotFoundException 
	{
		super((OutputStream) new PrintStream(file), true);
		this.frame = frame;
	}
	/**
	 * Initialize an output doubler stream with the default settings.
	 */
	public DoseOutput(OutputStream out, JFrame frame) 
	{
		super(out, true);
		this.frame = frame;
	}
	/**
	 * Initialize an output doubler stream with the default settings.
	 * @throws FileNotFoundException
	 */
	public DoseOutput(String file, JFrame frame) throws FileNotFoundException 
	{
		super((OutputStream) new PrintStream(file), false);
		this.frame = frame;
	}
	
	/**
	 * Explicitly set defaults of this output doubler.
	 * @param messagestyle Message style as defined by JOptionPane.xxx_MESSAGE. (Defaults to PLAIN_MESSAGE)
	 */
	public void SetDefaults(int messagestyle) {
		this.messagestyle = messagestyle;
	}

	/**
	 * Write to stream then display a dialog.
	 * @param text Text to write
	 * @param title Title of the modal dialog
	 * @param options A list of 1 to 3 options, preferably as a String[]
	 * @return Selected value of the dialog.
	 * @throws Exception Throws exception on any error (most likely: defaultoption or options out of range)
	 */
	public int WriteDiag(String text, String title, Object[] options) throws Exception {
		//Write Text to Stdout
		super.println(text);
    	
    	//Display primary dialog
		final int[] possible = new int[] { JOptionPane.OK_OPTION, JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_CANCEL_OPTION };
    	if (options.length < 1 || options.length > 3) { throw new Exception("Dialog only allows 1 to 3 options!"); }
    	int opt = possible[options.length];
		
    	return JOptionPane.showOptionDialog(frame,
			text, 
		    title,
		    opt,
		    messagestyle,
		    null,
		    options,
		    options[0]);
	}
	
	/**
	 * Write to stream then display a dialog, with additional parameters.
	 * @param text Text to write
	 * @param title Title of the modal dialog
	 * @param options A list of 1 to 3 options, preferably as a String[]
	 * @param defaultoption The default option, as an integer from 0 to 2. Default: 0
	 * @param messagestyle Message style as found in JOptionPane.xxx_MESSAGE.  Default: JOptionPane.PLAIN_MESSAGE
	 * @return Selected value of the dialog.
	 * @throws Exception Throws exception on any error (most likely: defaultoption or options out of range)
	 */
	public int WriteDiag(String text, String title, Object[] options, int defaultoption, int messagestyle) throws Exception {
		//Write Text to Stdout
		this.println(text);
    	
    	//Display primary dialog
		final int[] possible = new int[] { JOptionPane.OK_OPTION, JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_CANCEL_OPTION };
    	if (options.length == 0 || options.length > 3) { throw new Exception("Dialog only allows 1 to 3 options!"); }
    	int opt = possible[options.length];
		
    	return JOptionPane.showOptionDialog(frame,
			text, 
		    title,
		    opt,
		    messagestyle,
		    null,
		    options,
		    options[defaultoption]);
	}
	
	/**
	 * Creates and returns a blank, invisible JFrame.
	 * @return A blank, invisible JFrame.
	 */
	public static JFrame CreateBlankJFrame() {
		JFrame frame = new JFrame();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.pack(); //Size the frame
    	frame.setVisible(false);
    	return frame;
	}
	
}
