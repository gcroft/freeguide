/*
 * FreeGuide J2
 *
 * Copyright (c) 2001-2003 by Andy Balaam and the FreeGuide contributors
 *
 * Released under the GNU General Public License
 * with ABSOLUTELY NO WARRANTY.
 *
 * See the file COPYING for more information.
 */

import java.io.*;
import java.net.*;
import javax.swing.*;
 
/**
 * A thread that checks the FreeGuide web site for what the latest version is,
 * and informs the user if they are using an old version.
 *
 * @author Andy Balaam
 * @version 1
 */
public class VersionCheckerThread implements Runnable {

	public VersionCheckerThread(JFrame parent) {
		this.parent = parent;
	}

	public void start() {

        new Thread(this).start();

    }

    public void run() {
		
		int ans;
	
		int major;
		int minor;
		int revision;
		
		// Load the version number from the Internet
		try {
		
			URL fgversion = new URL("http://freeguide-tv.sourceforge.net/VERSION.php?ip=0.0.0.0&version=" + FreeGuide.getVersion());
			BufferedReader in = new BufferedReader( new InputStreamReader(
				fgversion.openStream() ) );

			major = Integer.parseInt( in.readLine() );
			minor = Integer.parseInt( in.readLine() );
			revision = Integer.parseInt( in.readLine() );

			in.close();
			
		} catch( java.net.MalformedURLException e ) {
			e.printStackTrace();
			return;
		} catch( java.io.IOException e ) {
			FreeGuide.log.info( "Unable to check version - couldn't access http://freeguide-tv.sourceforge.net/VERSION" );
			return;
		}
		
		// Check the loaded version number against the current version
		if( major < FreeGuide.version_major ) {
			warnFutureVersion();
		} else if( major > FreeGuide.version_major ) {
			warnOldVersion();
		} else if( minor < FreeGuide.version_minor ) {
			warnFutureVersion();
		} else if( minor > FreeGuide.version_minor ) {
			warnOldVersion();
		} else if( revision < FreeGuide.version_revision ) {
			warnFutureVersion();
		} else if( revision > FreeGuide.version_revision ) {
			warnOldVersion();
		}
		

    }

	/**
	 * Warn the user that they are using a futuristic version of FreeGuide.
	 * (Only warns in a log as time travel is perfectly acceptable behaviour.)
	 */
	private void warnFutureVersion() {
		
		FreeGuide.log.info( "Congratulations!  You are using a version of FreeGuide that hasn't been invented yet!" );
		
	}

	/**
	 * Warn the user that they are using a obselete version of FreeGuide.
	 * (This is virtually unforgivable in this day and age.)
	 */
	private void warnOldVersion() {
		
		new NewVersionDialog(parent).show();
		
	}

	JFrame parent;
	
}
