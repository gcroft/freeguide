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
package freeguide.gui.options;

import freeguide.*;

import freeguide.gui.dialogs.*;

import freeguide.lib.general.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

/*

 *  A panel full of options about privacy and checking for new version of

 *  FreeGuide

 *

 * @author     Andy Balaam

 * @created    12 Dec 2003

 * @version    1

 */
public class PrivacyOptionPanel extends OptionPanel implements ActionListener
{

    // ----------------------------------
    private JComboBox checkComboBox;
    private JComboBox provideComboBox;
    private JButton infoButton;
    private JTextField nicknameTextField;

    /**
     * Creates a new PrivacyOptionPanel object.
     *
     * @param parent DOCUMENT ME!
     */
    public PrivacyOptionPanel( FGDialog parent )
    {
        super( parent );

    }

    /**
     * DOCUMENT_ME!
     */
    public void doConstruct(  )
    {

        // Make the objects
        JLabel checkLabel =
            newLeftJLabel( 
                FreeGuide.msg.getString( "check_for_new_versions" ) );

        Object[] options = new Object[2];

        options[0] = FreeGuide.msg.getString( "yes" );

        options[1] = FreeGuide.msg.getString( "no" );

        checkComboBox = newRightJComboBox( options );

        checkLabel.setLabelFor( checkComboBox );

        checkLabel.setDisplayedMnemonic( KeyEvent.VK_V );

        JLabel provideLabel =
            newLeftJLabel( FreeGuide.msg.getString( "provide_info" ) + ":" );

        options = new Object[3];

        options[0] = FreeGuide.msg.getString( "none" );

        options[1] = FreeGuide.msg.getString( "ip" );

        options[2] = FreeGuide.msg.getString( "nickname" ) + ":";

        provideComboBox = newRightJComboBox( options );

        provideLabel.setLabelFor( provideComboBox );

        provideLabel.setDisplayedMnemonic( KeyEvent.VK_P );

        JLabel nicknameLabel =
            newLeftJLabel( FreeGuide.msg.getString( "nickname" ) + ":" );

        nicknameTextField = newRightJTextField(  );

        nicknameLabel.setLabelFor( nicknameTextField );

        nicknameLabel.setDisplayedMnemonic( KeyEvent.VK_N );

        infoButton = newRightJButton( FreeGuide.msg.getString( "more_info" ) );

        infoButton.setMnemonic( KeyEvent.VK_M );

        // Lay them out in a GridBag layout
        GridBagEasy gbe = new GridBagEasy( this );

        gbe.default_insets = new Insets( 1, 1, 1, 1 );

        gbe.default_ipadx = 5;

        gbe.default_ipady = 5;

        gbe.addFWX( checkLabel, 0, 0, gbe.FILL_HOR, 0.2 );

        gbe.addFWX( checkComboBox, 1, 0, gbe.FILL_HOR, 0.8 );

        gbe.addFWX( provideLabel, 0, 1, gbe.FILL_HOR, 0.2 );

        gbe.addFWX( provideComboBox, 1, 1, gbe.FILL_HOR, 0.8 );

        gbe.addFWX( nicknameLabel, 0, 2, gbe.FILL_HOR, 0.2 );

        gbe.addFWX( nicknameTextField, 1, 2, gbe.FILL_HOR, 0.8 );

        gbe.addAWX( infoButton, 1, 3, gbe.ANCH_WEST, 0.8 );

        // Events
        checkComboBox.addActionListener( this );

        provideComboBox.addActionListener( this );

        infoButton.addActionListener( this );

        // Load in the values from config
        load(  );

    }

    protected void doLoad( String prefix )
    {

        String privacy = FreeGuide.config.privacyInfo;

        if( privacy.startsWith( "yes_nick:" ) )
        {
            checkComboBox.setSelectedIndex( 0 );

            provideComboBox.setSelectedIndex( 2 );

            nicknameTextField.setText( privacy.substring( 9 ) );

        }

        else if( privacy.equals( "yes_ip" ) )
        {
            checkComboBox.setSelectedIndex( 0 );

            provideComboBox.setSelectedIndex( 1 );

        }

        else if( privacy.equals( "yes_nothing" ) )
        {
            checkComboBox.setSelectedIndex( 0 );

            provideComboBox.setSelectedIndex( 0 );

        }

        else if( privacy.equals( "no" ) )
        {
            checkComboBox.setSelectedIndex( 1 );

        }
    }

    /**
     * Saves the values in this option pane.
     *
     * @return false since nothing changes the view
     */
    public boolean doSave(  )
    {

        if( checkComboBox.getSelectedIndex(  ) == 1 )
        {
            FreeGuide.config.privacyInfo = "no";

        }

        else
        {

            switch( provideComboBox.getSelectedIndex(  ) )
            {

            case 0:
                FreeGuide.config.privacyInfo = "yes_nothing";

                break;

            case 1:
                FreeGuide.config.privacyInfo = "yes_ip";

                break;

            case 2:
                FreeGuide.config.privacyInfo =
                    "yes_nick:" + nicknameTextField.getText(  );

                break;
            }
        }

        return false;

    }

    /**
     * DOCUMENT_ME!
     *
     * @param e DOCUMENT_ME!
     */
    public void actionPerformed( ActionEvent e )
    {

        if( e.getSource(  ) == infoButton )
        {
            new PrivacyInfoDialog(  ).setVisible( true );

        }

        else
        {

            boolean check = ( checkComboBox.getSelectedIndex(  ) == 0 );

            boolean provide = ( provideComboBox.getSelectedIndex(  ) == 2 );

            provideComboBox.setEnabled( check );

            nicknameTextField.setFocusable( check && provide );

        }
    }

    /**
     * Used to find the name of this panel when displayed in a JTree.
     *
     * @return DOCUMENT_ME!
     */
    public String toString(  )
    {

        return FreeGuide.msg.getString( "privacy" );

    }
}