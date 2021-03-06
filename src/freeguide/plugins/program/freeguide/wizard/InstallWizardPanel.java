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
package freeguide.plugins.program.freeguide.wizard;

import freeguide.common.lib.fgspecific.Application;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A JPanel to go on a FreeGuideWizard that is the final screen in the
 * installation wizard.
 *
 * @author Andy Balaam
 * @version 2
 */
public class InstallWizardPanel extends WizardPanel
{
    // -------------------------------------------
    /** DOCUMENT ME! */
    public JCheckBox readmeCheckBox;

    /** DOCUMENT ME! */
    public JCheckBox configgrabberCheckBox;
    private JPanel middlePanel;

    /**
     * Creates a new InstallWizardPanel object.
     */
    public InstallWizardPanel(  )
    {
        super(  );

    }

    /**
     * Construct the GUI of this Wizard Panel.
     */
    public void construct(  )
    {
        JLabel topLabel = new JLabel(  );

        middlePanel = new JPanel(  );

        readmeCheckBox = new JCheckBox(
                Application.getInstance(  )
                           .getLocalizedMessage( "display_the_readme" ), true );

        readmeCheckBox.setMnemonic( KeyEvent.VK_R );

        readmeCheckBox.setSelected( true );

        configgrabberCheckBox = new JCheckBox(
                Application.getInstance(  )
                           .getLocalizedMessage( "choose_channels_now" ), true );

        configgrabberCheckBox.setMnemonic( KeyEvent.VK_G );

        configgrabberCheckBox.setSelected( true );

        JLabel bottomLabel = new JLabel(  );

        setLayout( new GridLayout( 3, 0 ) );

        topLabel.setFont( new java.awt.Font( "Dialog", 0, 12 ) );

        topLabel.setHorizontalAlignment( javax.swing.SwingConstants.CENTER );

        topLabel.setText( topMessage );

        add( topLabel );

        middlePanel.setLayout( new GridLayout( 2, 0 ) );

        middlePanel.add( readmeCheckBox );

        middlePanel.add( configgrabberCheckBox );

        add( middlePanel );

        bottomLabel.setFont( new java.awt.Font( "Dialog", 0, 12 ) );

        bottomLabel.setHorizontalAlignment( javax.swing.SwingConstants.CENTER );

        bottomLabel.setText( bottomMessage );

        add( bottomLabel );

    }

    /**
     * DOCUMENT_ME!
     */
    public void onEnter(  )
    {
        super.onEnter(  );

        /*boolean showConfigCheck =


        ( FreeGuide.prefs.commandline.get( "tv_config.1", null ) != null );




        if( !showConfigCheck )


        {


        configgrabberCheckBox.setSelected( false );


        middlePanel.remove( configgrabberCheckBox );


        }*/
    }

    // ----------------------------------
    /**
     * A little cheat here - we return a reference to this Panel so
     * that observers can access the values of the checkboxes.
     *
     * @return DOCUMENT_ME!
     */
    protected Object getBoxValue(  )
    {
        return this;

    }
}
