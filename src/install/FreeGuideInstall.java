/*
 * FreeGuide J2
 *
 * Copyright (c) 2001 by Andy Balaam
 *
 * freeguide-tv.sourceforge.net
 *
 * Released under the GNU General Public License
 * with ABSOLUTELY NO WARRANTY.
 *
 * See the file COPYING for more information.
 */

import java.io.File;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.prefs.Preferences;
import java.util.Vector;
import javax.swing.JOptionPane;

/*
 * An installer for FreeGuide
 *
 * @author  Andy Balaam
 * @version 5
 */
public class FreeGuideInstall implements FreeGuideLauncher {
	
	public FreeGuideInstall() {
		
		// Make sure we have the right Java version etc.
		FreeGuideEnvironmentChecker.runChecks();
		
		prefs = new FreeGuidePreferencesGroup(); 
		
		// Branch into Reinstall or first time
		String install_directory = prefs.performSubstitutions(
			prefs.misc.get("install_directory") );
		
		if( install_directory==null || 
			!(new File(install_directory + File.separator + 
			"FreeGuide.jar").exists()) ) {
			
			// First time install	
			install(install_directory, false);
		
		} else {
			
			String txt = "There is a version of FreeGuide installed.";
			txt += System.getProperty( "line.separator" );
			txt += "Would  you like to uninstall it, or install the new version?";
			
			String[] options = { 
				"Complete Install (overwrite prefs - recommended)",
				"Lite Install (keep old preferences)",
				"Uninstall"};
			
			Object response = JOptionPane.showInputDialog(null,
				txt,
                "Install question",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
				"Complete Install (overwrite prefs - recommended)");
			
			if(response==null) {
				System.err.println("Exiting installer without doing anything.");
				System.exit(0);
			} else if(response.equals("Complete Install (overwrite prefs - recommended)")) {
				install(install_directory, false);
			} else if(response.equals("Lite Install (keep old preferences)")) {
				install(install_directory, true);
			} else {
				uninstall(install_directory);
			}
			
		}
		
	}
	
	public void reShow() {
		
	}
	
	public FreeGuideLauncher getLauncher() {
		return null;
	}
	
	public void setVisible(boolean show) {
		System.exit(0);
	}
	
	private void install(String install_directory, boolean keepOldPreferences) {
		
		keepOldPrefs = keepOldPreferences;
		
		try {
		
			getAllRegions();
		
			FreeGuideWizardPanel[] panels = new FreeGuideWizardPanel[4];
			
			panels[0] = new FreeGuideLabelWizardPanel("");
			panels[0].setMessages("You are about to install FreeGuide.",
				"Click \"Next\" to continue.");
		
			panels[1] = new FreeGuideChoiceWizardPanel( allRegions );
			Class[] clses = new Class[1];
			clses[0] = Object.class;
			panels[1].setOnExit( this, 
				getClass().getMethod( "SetProps", clses ) );
			panels[1].setMessages("Choose your region.",
				"This affects which listings grabber will be used.");
		
			panels[2] = new FreeGuideDirectoryWizardPanel();
			panels[2].setMessages("Choose your installation directory.",
				"This will be created if it doesn't exist.");
			panels[2].setConfig("misc", "install_directory");
		
			panels[3] = new FreeGuideLabelWizardPanel("Now you need to configure your grabber before you can start using it.");
			panels[3].setMessages("FreeGuide will be installed when you click \"Finish\".", "Read the README in the directory you chose to find out how.");
	
			new FreeGuideWizard("FreeGuide Setup Wizard", panels ,this, this, getClass().getMethod("doInstall", new Class[0])).setVisible(true);
		
		} catch(java.lang.NoSuchMethodException e) {
			e.printStackTrace();
		} catch(java.lang.SecurityException e) {
			e.printStackTrace();
		}
		
	}
	
	public void SetProps( Object boxValue ) {
	
		String region = (String)boxValue;
		
		for( int i=0; i<allRegions.length; i++ ) {
			
			if( allRegions[i].equals(region) ) {
				
				// Load the install.props file
				props = new Properties();
				
				try{
					
					props.load(new BufferedInputStream(getClass().getResourceAsStream(
					"/install-" + i + ".props")));		

				} catch(java.io.IOException e) {
					e.printStackTrace();
				}
					
				return;
				
			}
			
		}
		
		System.err.println(
			"FreeGuideInstall.SetProps - Invalid region chosen." );
	
	}
	
	private void getAllRegions() {
		
		int i=0;
		
		Vector regs = new Vector();
		
		Properties pr = new Properties();
		InputStream is;
		
		is = getClass().getResourceAsStream( "/install-" + i + ".props" );

		while( is!=null ) {
			
			try {
				pr.load( new BufferedInputStream(is) );
			
			} catch(java.io.IOException e) {
				e.printStackTrace();
				return;
			}

			regs.add( pr.getProperty("region") );
			
			i++;
			
			is = getClass().getResourceAsStream( "/install-" + i + ".props" );
			
		}
		
		allRegions = FreeGuideUtils.arrayFromVector_String( regs );
		
	}
	
	private void uninstall(String install_directory) {
		
		File inst = new File(install_directory);
		deleteDir(inst);
		
		String w = prefs.misc.get("working_directory");
		if(w!=null) {
			File work = new File(w);
			deleteDir(work);
		}
		
		Preferences node = Preferences.userRoot().node("/org/freeguide-tv");
		
		try {
		
			node.removeNode();
			
		} catch(java.util.prefs.BackingStoreException e) {
			e.printStackTrace();
		}
		
		JOptionPane.showMessageDialog(null,
			"FreeGuide has been successfully uninstalled.");
		
		System.err.println("Finished uninstall.");
		System.exit(0);
		
	}
	
	/**
	 * Deletes a whole directory recursively (also deletes a single file).
	 */
	private void deleteDir(File dir) {
		
        if(!dir.exists()) return;
		
        if(dir.isDirectory()) {
			String[] list = dir.list();
			for(int i = 0; i < list.length; i++) {
            	deleteDir(new File(dir.getPath() + File.separator + list[i]));
        	}
		}

        dir.delete();
		
	}
	
	public static void main(String[] args) {
		
		new FreeGuideInstall();
		
	}
	
	public void doInstall() {
		
		// Do all the preferences in the properties file - set the defaults
		// to them, and set the real values too if we're not keeping old
		// ones.
		int i=1;
		String prefString="";
		while( (prefString=props.getProperty("prefs."+i)) != null ) {
			
			doPref(prefString);
			i++;
				
		}
		
		try {
		
			String install_directory = prefs.performSubstitutions( 
					prefs.misc.get("install_directory") );
		
			// Make the required directories
			new File( install_directory ).mkdirs();
			new File( prefs.performSubstitutions( 
				prefs.misc.get("xmltv_directory") ) ).mkdirs();
			new File( prefs.performSubstitutions( 
				prefs.misc.get("working_directory") ) ).mkdirs();
		
			// Copy in the files
			i=1;
			String filename="";
			while( (filename=props.getProperty("file."+i)) != null ) {
				installFile(filename);
				i++;
			}
		
			System.err.println("Finished install.");
			System.exit(0);
			
		} catch(java.io.IOException e) {
			e.printStackTrace();
		}
		
	}
		
	private void installFile(String command) throws java.io.IOException {

		//System.out.println("Installing file: " + name);
		
		String[] srcdest = command.split(">");
		String src = srcdest[0];
		String dest = prefs.performSubstitutions( srcdest[1] );
		
		byte[] buf = new byte[32768];
		
		// make the directory if it doesn't exist
		
		int i = dest.lastIndexOf('/');
		if(i>-1) { 
			new File(dest.substring(0, i)).mkdirs();
		}
		
		BufferedInputStream in = new BufferedInputStream(getClass().getResourceAsStream("/" + src));
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dest));

		int count;
		while((count = in.read(buf, 0, buf.length)) > -1) {
			out.write(buf,0,count);
		}
			
		in.close();
		out.close();
		
	}
	
	private void doPref(String prefString) {
		
		// Split this string into its constituent parts
		
		int i = prefString.indexOf('=');
		String key = prefString.substring(0, i);
		String value = prefString.substring(i+1);
		
		i = key.indexOf('.');
		String keyCategory = key.substring(0, i);
		key = key.substring(i+1);
				
		// Find out what preferences category we're dealing with
		FreeGuidePreferences pr;
		if(keyCategory.equals("misc")) {
			pr = prefs.misc;
		} else if(keyCategory.equals("commandline")) {
			pr = prefs.commandline;
		} else {
			// Following is to make it compile ok.
			pr = prefs.misc;
			System.err.println("Unknown preferences group: " + keyCategory
				+ " - Aborting");
			System.exit(1);
		}
		
		// Set the default value always
		pr.put("default-" + key, value);
		
		// Only set the real value if we're not keeping old prefs
		if(!keepOldPrefs) {
			pr.put(key, value);
		}
		
	}
	
	private boolean keepOldPrefs;
	private String[] allRegions;
	private Properties props;
	private FreeGuidePreferencesGroup prefs;
	private String fs = System.getProperty("file.separator");
	private String lb = System.getProperty("line.separator");
	
}
