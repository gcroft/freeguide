/*
 * FreeGuide J2
 *
 * Copyright (c) 2001 by Andy Balaam
 *
 * Released under the GNU General Public License
 * with ABSOLUTELY NO WARRANTY.
 *
 * See the file COPYING for more information.
 */

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * Provides a progress bar and downloads the programme listings from
 * the Internet to local XMLTV files.
 *
 * @author Andy Balaam
 * @version 2
 */

public class FreeGuideDownloader extends javax.swing.JFrame implements Runnable {

	/**
	 * Constructs the UI with a progress bar and two buttons.
	 */
    public FreeGuideDownloader() {
		
        initComponents();
		
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
	private void initComponents() {//GEN-BEGIN:initComponents
		prgProgress = new javax.swing.JProgressBar();
		butStart = new javax.swing.JButton();
		butCancel = new javax.swing.JButton();
		butOptions = new javax.swing.JButton();
		
		getContentPane().setLayout(new java.awt.GridBagLayout());
		java.awt.GridBagConstraints gridBagConstraints1;
		
		setTitle("FreeGuide Downloader");
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});
		
		gridBagConstraints1 = new java.awt.GridBagConstraints();
		gridBagConstraints1.gridx = 0;
		gridBagConstraints1.gridy = 1;
		gridBagConstraints1.gridwidth = 2;
		gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints1.insets = new java.awt.Insets(5, 25, 5, 25);
		gridBagConstraints1.weightx = 0.1;
		gridBagConstraints1.weighty = 0.5;
		getContentPane().add(prgProgress, gridBagConstraints1);
		
		butStart.setText("Start downloading");
		butStart.setSelected(true);
		butStart.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				butStartActionPerformed(evt);
			}
		});
		
		gridBagConstraints1 = new java.awt.GridBagConstraints();
		gridBagConstraints1.gridx = 0;
		gridBagConstraints1.gridy = 2;
		gridBagConstraints1.gridwidth = 2;
		gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints1.weightx = 0.1;
		gridBagConstraints1.weighty = 0.25;
		getContentPane().add(butStart, gridBagConstraints1);
		
		butCancel.setFont(new java.awt.Font("Dialog", 0, 12));
		butCancel.setText("Cancel");
		butCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				butCancelActionPerformed(evt);
			}
		});
		
		gridBagConstraints1 = new java.awt.GridBagConstraints();
		gridBagConstraints1.gridx = 1;
		gridBagConstraints1.gridy = 3;
		gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints1.weightx = 0.1;
		gridBagConstraints1.weighty = 0.25;
		getContentPane().add(butCancel, gridBagConstraints1);
		
		butOptions.setFont(new java.awt.Font("Dialog", 0, 12));
		butOptions.setText("Options...");
		butOptions.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				butOptionsActionPerformed(evt);
			}
		});
		
		gridBagConstraints1 = new java.awt.GridBagConstraints();
		gridBagConstraints1.gridx = 0;
		gridBagConstraints1.gridy = 3;
		gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints1.weightx = 0.1;
		gridBagConstraints1.weighty = 0.25;
		getContentPane().add(butOptions, gridBagConstraints1);
		
		pack();
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setSize(new java.awt.Dimension(300, 200));
		setLocation((screenSize.width-300)/2,(screenSize.height-200)/2);
	}//GEN-END:initComponents

	private void butOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butOptionsActionPerformed
		
		stop();
		new FreeGuideOptions().show();
		dispose();
		
	}//GEN-LAST:event_butOptionsActionPerformed

	private void butCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butCancelActionPerformed
		
		quit();
		
	}//GEN-LAST:event_butCancelActionPerformed

	private void butStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butStartActionPerformed
		
		butStart.setEnabled(false);
		butOptions.setEnabled(false);
		start();
		
	}//GEN-LAST:event_butStartActionPerformed
	
	//------------------------------------------------------------------------
	
	public void start() {
		thread = new Thread(this);
		thread.start();
	}//start
	
	public void stop() {
		thread=null;
	}//stop
	
	public void run() {
		// Get the current running thread
		Thread thisThread = Thread.currentThread();
		
		int day=0;	// The counter of what day we'return on
		int nodays;	// The number of days to download
		
		// Find out how many days to download
		String dlAmt = FreeGuide.config.getValue("downloadAmount");
		if(dlAmt.equals("All")) {
			nodays = 14;
		} else if(dlAmt.equals("Week")) {
			nodays = 7;
		} else {
			nodays = 1;
		}
		
		Vector channels = FreeGuide.config.getListValue("channels");
		
		int channel=0;
		int nochannels = channels.size();
		
		// While the thread is active and we haven't finished
		while(thisThread == thread && day<nodays){
			
			downloadListings(day, (String)channels.get(channel));
			
			// Go to next channel
			channel++;

			// Update the progress bar
			prgProgress.setValue( (int)( 100*( (double)((day*nochannels)+channel) / (double)(nochannels*nodays) ) ) );
			
			// If channels are done, go to next day
			if(channel>=channels.size()) {
				day++;
				channel=0;
			}
			
		}//while
		
		// Close the form if we've finished
		if(day>=nodays) {
			quit();
		}
		
	}//run
	
	//------------------------------------------------------------------------
	
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        
		quit();
		
    }//GEN-LAST:event_exitForm

	private void quit() {
		
		stop();
		hide();
		new FreeGuideViewer().show();
		dispose();
		
	}
	
	/**
	 * Downloads a given day's listings for a given channel and saves
	 * to a file whose name is determined by the day and channel.
	 *
	 * @param day the number of days after today the required day is
	 * @param channel the name of the required channel
	 */
	private void downloadListings(int day, String channel) {

		// Find the required date: today plus "day" days
		Date theDate = new Date();
		theDate.setDate(theDate.getDate()+day);
		
		// Format the date appropriately
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		String datestr = fmt.format(theDate);
	
		// INSERT HERE: not bothering to download if it's already there?
		
		// Format the channel to be a filename
		String channelFilename = FreeGuide.makeRightFilenameLength(channel.toLowerCase());
		
		// Get the configuration options
		String xmlFilename = FreeGuide.config.getValue("freeguideDir")+"data/"+channelFilename+"-"+datestr+".fgd";	    
		String channelsFile = FreeGuide.config.getValue("channelsFile");
		Vector parserCmds = FreeGuide.config.getListValue("downloadCommandLine");
		
		// Execute the commands to download and save listings
		for(int i=0;i<parserCmds.size();i++) {
		
			FreeGuide.execExternal(((String)parserCmds.get(i))+" "+channel+" "+datestr+" "+xmlFilename+" "+channelsFile);
			
		}
		
	}
	
	//------------------------------------------------------------------------

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JProgressBar prgProgress;
	private javax.swing.JButton butStart;
	private javax.swing.JButton butCancel;
	private javax.swing.JButton butOptions;
	// End of variables declaration//GEN-END:variables
	
	private Thread thread;
	
}
