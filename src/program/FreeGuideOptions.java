/**
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

import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * FreeGuide's technical options screen. Used 
 * when there's a problem on startup, or when the user chooses "Options".
 *
 * @author  Andy Balaam
 * @version 1
 */
public class FreeGuideOptions extends javax.swing.JFrame {
	
	/**
	 * Launch this screen as a normal options screen with everything on it.
	 */
	public FreeGuideOptions(FreeGuideLauncher launcher) {
		this.launcher = launcher;
		
		panels = new FreeGuideWizardPanel[7];
		titles = new String[7];
		
		panels[0] = new FreeGuidePreConfigWizardPanel();
		panels[0].setMessages("Click the button to change your fundamental info.", "This is what most of the \"Guess\" buttons use.");
		titles[0] = "Locale";
		
		panels[1] = new FreeGuideDirectoryWizardPanel();
		panels[1].setMessages("Choose the working directory FreeGuide will use.", "A new directory will be made if necessary.");
		panels[1].setConfig("misc", "working_directory");
		titles[1] = "Working Directory";
		
		panels[2] = new FreeGuideDirectoryWizardPanel();
		panels[2].setMessages("Choose the directory that contains the XMLTV tools.", "This must contain tv_split and tv_grab.");
		panels[2].setConfig("misc", "xmltv_directory");
		titles[2] = "XMLTV Directory";
		
		panels[3] = new FreeGuideCommandsWizardPanel();
		panels[3].setMessages("Enter the command used to grab listings.", "");
		panels[3].setConfig("commandline", "tv_grab");
		titles[3] = "Grabber Command";
		
		panels[4] = new FreeGuideCommandsWizardPanel();
		panels[4].setMessages("Enter the command used to launch a web browser.", "");
		panels[4].setConfig("commandline", "browser_command");
		titles[4] = "Browser Command";
		
		panels[5] = new FreeGuideTextWizardPanel();
		panels[5].setMessages("Enter the time that each day starts.", "Use hh:mm format e.g.06:00 for 6am.");
		panels[5].setConfig("misc", "day_start_time");
		titles[5] = "Day Start";
		
		panels[6] = new FreeGuideFileWizardPanel();
		panels[6].setMessages("Choose the XMLTV tool's configuration file.", "This should contain a list of channels you want to watch.");
		panels[6].setConfig("misc", "grabber_config");
		titles[6] = "Channels File";
	
		// Draw the screen
		initComponents();
		
	}
	
	/**
	 * Launch this screen when there's been a problem on startup and we need
	 * the user to correct some specific problems.
	 */
	public FreeGuideOptions(FreeGuideLauncher launcher, Vector failedWhat) {
		
		this.launcher = launcher;
		
		panels = new FreeGuideWizardPanel[failedWhat.size()+1];
		titles = new String[failedWhat.size()+1];
		
		panels[0] = new FreeGuideLabelWizardPanel("Please correct the configuration entries in the other tabs of this screen.");
		panels[0].setMessages("There has been a problem during startup.", "(You may find it easier to uninstall and install FreeGuide if you have problems.)");
		titles[0] = "Welcome";		
		
		int panelNo=1;
		
		for(int i=0;i<failedWhat.size();i++) {
		
			String what = (String)failedWhat.get(i);
			if(what.equals("misc.working_directory")) {
		
				panels[panelNo] = new FreeGuideDirectoryWizardPanel();
				panels[panelNo].setMessages("Choose the working directory FreeGuide will use.", "A new directory will be made if necessary.");
				panels[panelNo].setConfig("misc", "working_directory");
				titles[panelNo] = "Working Directory";
				
			} else if(what.equals("misc.xmltv_directory")) {
		
				panels[panelNo] = new FreeGuideDirectoryWizardPanel();
				panels[panelNo].setMessages("Choose the directory that contains the XMLTV tools.", "This must contain tv_split and tv_grab.");
				panels[panelNo].setConfig("misc", "xmltv_directory");
				titles[panelNo] = "XMLTV Directory";
				
			} else if(what.equals("commandline.tv_grab")) {
		
				panels[panelNo] = new FreeGuideCommandsWizardPanel();
				panels[panelNo].setMessages("Enter the command used to grab listings.", "");
				panels[panelNo].setConfig("commandline", "tv_grab");
				titles[panelNo] = "Grabber Command";
				
			} else if(what.equals("commandline.browser_command")) {
		
				panels[panelNo] = new FreeGuideCommandsWizardPanel();
				panels[panelNo].setMessages("Enter the command used to launch a web browser.", "");
				panels[panelNo].setConfig("commandline", "browser_command");
				titles[panelNo] = "Browser Command";
				
			} else if(what.equals("misc.day_start_time")) {
		
				panels[panelNo] = new FreeGuideTextWizardPanel();
				panels[panelNo].setMessages("Enter the time that each day starts.", "Use hh:mm format e.g.06:00 for 6am.");
				panels[panelNo].setConfig("misc", "day_start_time");
				titles[panelNo] = "Day Start";
				
			} else if(what.equals("misc.grabber_config")) {
		
				panels[panelNo] = new FreeGuideFileWizardPanel();
				panels[panelNo].setMessages("Choose the XMLTV tool's configuration file.", "This should contain a list of channels you want to watch.");
				panels[panelNo].setConfig("misc", "grabber_config");
				titles[panelNo] = "Channels File";
		
			}
			panelNo++;
		}
		
		initComponents();	

	}
	
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

		JPanel panButtons = new JPanel();
        JTabbedPane tabs = new JTabbedPane();
        JButton butCancel = new JButton();
        JButton butOK = new JButton();

		// Set up the panels ready to be used
		for(int i=0;i<panels.length;i++) {
			panels[i].construct();
			panels[i].onEnter();
		}
		
        getContentPane().setLayout(new java.awt.GridBagLayout());

        setTitle("FreeGuide Options");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

		for(int i=0;i<panels.length;i++) {
			tabs.addTab(titles[i], panels[i]);
		}
		
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.9;
        gridBagConstraints.weighty = 0.9;
        getContentPane().add(tabs, gridBagConstraints);

		butOK.setFont(new java.awt.Font("Dialog", 0, 12));
        butOK.setText("OK");
        butOK.setMaximumSize(new java.awt.Dimension(85, 26));
        butOK.setMinimumSize(new java.awt.Dimension(85, 26));
        butOK.setPreferredSize(new java.awt.Dimension(85, 26));
        butOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butOKActionPerformed(evt);
            }
        });

        panButtons.add(butOK);
		
        butCancel.setFont(new java.awt.Font("Dialog", 0, 12));
        butCancel.setText("Cancel");
        butCancel.setMaximumSize(new java.awt.Dimension(85, 26));
        butCancel.setMinimumSize(new java.awt.Dimension(85, 26));
        butCancel.setPreferredSize(new java.awt.Dimension(85, 26));
        butCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butCancelActionPerformed(evt);
            }
        });

        panButtons.add(butCancel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(panButtons, gridBagConstraints);

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new java.awt.Dimension(500, 350));
        setLocation((screenSize.width-500)/2,(screenSize.height-350)/2);
		
    }
	
	private void butCancelActionPerformed(java.awt.event.ActionEvent evt) {	
		quit();
	}

	private void butOKActionPerformed(java.awt.event.ActionEvent evt) {
		ok();
	}
	
	private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
		quit();	
	}//GEN-LAST:event_exitForm
	
	private void ok() {
		// Check that every control is happy and exit if so.
		for(int i=0;i<panels.length;i++) {
			if(!panels[i].onExit()) {
				// One of the panels is unhappy and the user chose to correct it.
				return;
			}
		}
		quit();
	}
	
	private void quit() {
		setVisible(false);
		if(launcher!=null) {launcher.reShow();}
		dispose();
	}
	
	private FreeGuideLauncher launcher;
	private FreeGuideWizardPanel[] panels;
	private String[] titles;
	
	public static final int PROBLEM_SCREEN = 0;
	public static final int OPTIONS_SCREEN = 1;
	
}