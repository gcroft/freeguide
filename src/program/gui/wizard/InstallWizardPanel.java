/*
 *  FreeGuide J2
 *
 *  Copyright (c) 2001-2004 by Andy Balaam and the FreeGuide contributors
 *
 *  freeguide-tv.sourceforge.net
 *
 *  Released under the GNU General Public License
 *  with ABSOLUTELY NO WARRANTY.
 *
 *  See the file COPYING for more information.
 */

package freeguide.gui.wizard;

import freeguide.*;
import freeguide.gui.wizard.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *  A JPanel to go on a FreeGuideWizard that is the final screen in the
 *  installation wizard.
 *
 *@author     Andy Balaam
 *@created    11 Dec 2003
 *@version    2
 */
public class InstallWizardPanel extends WizardPanel {

    /**
     *
     */
    public InstallWizardPanel() {
        super();
    }


    /**
     *  Construct the GUI of this Wizard Panel.
     */
    public void construct() {
		
        java.awt.GridBagConstraints gridBagConstraints;

        JLabel topLabel = new JLabel();
        middlePanel = new JPanel();
		
		readmeCheckBox = new JCheckBox( "Display the README", true );
		readmeCheckBox.setMnemonic(KeyEvent.VK_R);
		readmeCheckBox.setSelected( true );
		
		configgrabberCheckBox = new JCheckBox( "Choose channels now (connect to the Internet first)",
			true );
		configgrabberCheckBox.setMnemonic(KeyEvent.VK_G);
		configgrabberCheckBox.setSelected( true );
		
        JLabel bottomLabel = new JLabel();

        setLayout(new GridLayout(3, 0));

        topLabel.setFont(new java.awt.Font("Dialog", 0, 12));
        topLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        topLabel.setText(topMessage);
        add(topLabel);

        middlePanel.setLayout( new GridLayout( 2, 0 ) );
		middlePanel.add( readmeCheckBox );
		middlePanel.add( configgrabberCheckBox );
		
        add(middlePanel);

        bottomLabel.setFont(new java.awt.Font("Dialog", 0, 12));
        bottomLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bottomLabel.setText(bottomMessage);
        add(bottomLabel);

    }

	public void onEnter() {
		super.onEnter();
		
		boolean showConfigCheck = ( FreeGuide.prefs.commandline.get(
			"tv_config.1", null ) != null );
			
		if( !showConfigCheck ) {
			configgrabberCheckBox.setSelected( false );
			middlePanel.remove( configgrabberCheckBox );
		}
		
		
	}
	
	// ----------------------------------
	
	/**
	 * A little cheat here - we return a reference to this Panel so that
	 * observers can access the values of the checkboxes.
	 */
	protected Object getBoxValue() {
        return this;
    }

    // -------------------------------------------

	public JCheckBox readmeCheckBox;
    public JCheckBox configgrabberCheckBox;

	private JPanel middlePanel;
	
}
