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

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.JTextArea;
import java.awt.Container;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * Provides facilities for executing external commands with a GUI
 * for user feedback.
 *
 * @author Andy Balaam
 * @version 7
 * (used to be FreeGuideDownloader)
 */

public class FreeGuideExecutor extends javax.swing.JFrame implements Runnable {

	/**
	 * Constructs the UI elements.
	 *
	 * @param parent the frame that launched this command
	 * @param a description of the command, e.g. "Downloading"
	 */
    public FreeGuideExecutor(FreeGuideLauncher launcher, String[] cmds,
			String commandType, Calendar date) {
		this.date=date;
		this.launcher = launcher;
		this.cmds = cmds;
		java.text.SimpleDateFormat showdate = new java.text.SimpleDateFormat("yyyyMMdd");
		System.out.print("executor w/ date "+showdate.format(date.getTime())+"\n");
        initComponents();

		// Centre the screen
		java.awt.Dimension screenSize =
			java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		
		setLocation( (screenSize.width-getWidth())/2,
			(screenSize.height-getHeight())/2 );

		// Set the please wait message
		labPleaseWait.setText(commandType + ", please wait...");
		setTitle(commandType);

		viewer = new StringViewer("Command Output (stdout):" + lb,
			"Command Output (stderr):" + lb);

		start();
    }
	public FreeGuideExecutor(FreeGuideLauncher launcher, String[] cmds,
			String commandType) {

		this.launcher = launcher;
		this.cmds = cmds;
		
		System.out.print("executor w/o date\n");

        initComponents();

		// Centre the screen
		java.awt.Dimension screenSize =
			java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		
		setLocation( (screenSize.width-getWidth())/2,
			(screenSize.height-getHeight())/2 );

		// Set the please wait message
		labPleaseWait.setText(commandType + ", please wait...");
		setTitle(commandType);

		viewer = new StringViewer("Command Output (stdout):" + lb,
			"Command Output (stderr):" + lb);

		start();

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        butCancel = new javax.swing.JButton();
        butDetails = new javax.swing.JButton();
        labPleaseWait = new javax.swing.JLabel();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setTitle("Executing Command");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        butCancel.setText("Cancel");
        butCancel.setMaximumSize(new java.awt.Dimension(115, 23));
        butCancel.setMinimumSize(new java.awt.Dimension(115, 23));
        butCancel.setPreferredSize(new java.awt.Dimension(115, 23));
        butCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butCancelActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(butCancel, gridBagConstraints);

        butDetails.setFont(new java.awt.Font("Dialog", 0, 12));
        butDetails.setText("Show Output");
        butDetails.setMaximumSize(new java.awt.Dimension(115, 23));
        butDetails.setMinimumSize(new java.awt.Dimension(115, 23));
        butDetails.setName("null");
        butDetails.setPreferredSize(new java.awt.Dimension(115, 23));
        butDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butDetailsActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(butDetails, gridBagConstraints);

        labPleaseWait.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labPleaseWait.setText("Please Wait");
        labPleaseWait.setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.LOWERED));
        labPleaseWait.setMaximumSize(new java.awt.Dimension(400, 22));
        labPleaseWait.setMinimumSize(new java.awt.Dimension(400, 22));
        labPleaseWait.setPreferredSize(new java.awt.Dimension(400, 22));
        labPleaseWait.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(labPleaseWait, gridBagConstraints);

        pack();
    }//GEN-END:initComponents

	private void butDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butDetailsActionPerformed
		
		showDetails();
		
	}//GEN-LAST:event_butDetailsActionPerformed

	/**
	 * Dump the contents of both textareas to standard output.
	 */
	/*public void dumpInfo() {
		FreeGuide.log.warning("There was an error during command execution");
		System.out.println("An error ocurred during command execution.  The command output is shown below:");
		System.out.println("OUTPUT:");
		System.out.print(txaCmdOutput.getText());
		System.out.println("ERROR:");
		System.out.print(txaCmdError.getText());
	}*/
	
	// -----------------------------------------------------------------------
	
	private void showDetails() {
		
		viewer.setVisible(true);

	}
	
	private void butCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butCancelActionPerformed
		
		stop();
		clearUp();
		
	}//GEN-LAST:event_butCancelActionPerformed
	
	//------------------------------------------------------------------------
	
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm

		stop();
		clearUp();
		
    }//GEN-LAST:event_exitForm
	
	private void clearUp() {
		
		if(pr!=null) {
			pr.destroy();
		}
		
		if(readOutput!=null) {
			readOutput.stop();
		}
		
		if(readError!=null) {
			readError.stop();
		}
		
		setVisible(false);
		viewer.dispose();
		launcher.reShow();
		dispose();
		
	}
	
	public void start() {
        runner = new Thread(this);
		runner.start();
    }

    public void stop() {
		
		runner = null;
		
    }
	
	/** run
	 *
	 * Execute several external applications via the command line interface.
	 * (called from the thread launched in the constructor)
	 * 
	 */
	public void run() {
		
		Thread thisThread = Thread.currentThread();
		readOutput = new StreamReaderThread();
		readError = new StreamReaderThread();
		
		// Step through each command
		for(int i=0;i<cmds.length;i++) {
			
			// Exit if we've been stopped externally
			if(runner!=thisThread) {
				break;
			}
			
			// Run the command and exit if there's an error
			if( !exec(cmds[i]) ) {
				
				dumpOutputAndError();
				return;
				
			}
			
		}
		
		clearUp();
		
	}
	
	/** exec
	 *
	 * Execute an external application via the command line interface.
	 *
	 * @param cmdstr  the command to execute
	 * @return true if the command finished successfully
	 */
	public boolean exec(String cmdstr) {
		
		if(cmdstr==null || cmdstr.equals("")) {
			// No command to execute: say it was successful
			return true;
		}
		Calendar thisDate=GregorianCalendar.getInstance();
		thisDate.setTime(date.getTime());
		System.out.print("exec: "+cmdstr+"\n");
		// Check for any elements that mean this command must be called multiple
		// times, once for each day.
		if( (cmdstr.indexOf("%date%")!=-1)
				|| (cmdstr.indexOf("%offset%")!=-1) ) {
				
			boolean didOK = true;
			
			//Calendar date = GregorianCalendar.getInstance();
			
			for(int i=0;i<FreeGuide.prefs.misc.getInt("days_to_grab", 7);i++) {
				
				// FIXME - don't execute if a file already exists?
				// What about files that are half empty?
				
				// Recursive call to this function
				if(	!exec(FreeGuide.prefs.performSubstitutions(
						cmdstr, thisDate) ) ) {
						
						didOK=false;
					}
						
				
				thisDate.add(Calendar.DATE, 1);
				
			}
			
			return didOK;
			
		}
		
		// this is only necessary if the above condition doesn't match.
		// It won't hurt to call it an extra time at this point if the
		// above condition does match.  The date is needed though.
		cmdstr = FreeGuide.prefs.performSubstitutions(cmdstr, thisDate);
		
		// Log what we're about to do
		FreeGuide.log.info(
			"FreeGuide - Executing system command: "+cmdstr+" ...");

		try {
					
			// Execute the command (after parsing it into tokens)
			pr = Runtime.getRuntime().exec(FreeGuideUtils.parseCommand(cmdstr));
				
			// Get the input and output streams of this process
			BufferedReader prOut = new BufferedReader(
				new InputStreamReader(pr.getInputStream()));
			BufferedReader prErr = new BufferedReader(
				new InputStreamReader(pr.getErrorStream()));

			// Suck the output from the command
			readOutput.begin(prOut, cmdstr, viewer, viewer.getOutput());
			readError.begin(prErr, viewer, viewer.getError());
				
			// Actually wait for it to finish
			int exitCode = pr.waitFor();
			boolean retVal = (exitCode==0);
				
			// Log it finishing
			FreeGuide.log.info("FreeGuide - Finished execution with exit code "
				+ exitCode + "." );
				
			return retVal;
		
		} catch(java.io.IOException e) {
			e.printStackTrace();
			return false;
		} catch(java.lang.InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	
    }//execExternal
	
	// ----------------------------------------------------------------------
	
	private void dumpOutputAndError() {
		
		try {
			Thread.sleep(1000);
		} catch(java.lang.InterruptedException e) {
			e.printStackTrace();
		}

		labPleaseWait.setText("Execution Error.");

		FreeGuide.log.warning("Command Output (stdout):" + lb + readOutput.getStoredOutput());
		FreeGuide.log.warning("Command Error (stderr):" + lb + readError.getStoredOutput());		
		
	}
	
	private Process pr;

	private StreamReaderThread readOutput;
	private StreamReaderThread readError;
	private Thread runner;
	private FreeGuideLauncher launcher;

	private String commandType;

	private static final String lb = System.getProperty("line.separator");
	private String[] cmds;

	private StringViewer viewer;
	
	private Calendar date;
	
	
	//------------------------------------------------------------------------

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labPleaseWait;
    private javax.swing.JButton butCancel;
    private javax.swing.JButton butDetails;
    // End of variables declaration//GEN-END:variables
		
}
