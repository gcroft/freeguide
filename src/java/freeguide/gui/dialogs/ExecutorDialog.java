/*
 *  FreeGuide J2
 *
 *  Copyright (c) 2001-2004 by Andy Balaam and the FreeGuide contributors
 *
 *  Released under the GNU General Public License
 *  with ABSOLUTELY NO WARRANTY.
 *
 *  See the file COPYING for more information.
 */
package freeguide.gui.dialogs;

import freeguide.*;

import freeguide.plugins.ILogger;
import freeguide.plugins.IProgress;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Provides facilities for executing external commands with a GUI for user
 * feedback.
 *
 * @author Andy Balaam
 * @version 9 (Used to be ExecutorFrame)
 */
public class ExecutorDialog extends JDialog implements IProgress, ILogger
{

    private final static String lb = System.getProperty( "line.separator" );

    // ------------------------------------------------------------------------
    private javax.swing.JButton butCancel;
    private javax.swing.JButton butDetails;
    private javax.swing.JLabel labPleaseWait;
    private javax.swing.JProgressBar progressBar;
    private JTextArea log;
    private JScrollPane logScroll;
    protected int stepCount = 0;
    protected Dimension minPreferredSize;
    protected Dimension maxPreferredSize;
    protected int minHeight;
    protected int maxHeight;

    /**
     * Creates a new ExecutorDialog object.
     *
     * @param owner DOCUMENT ME!
     */
    public ExecutorDialog( JFrame owner )
    {
        super( owner, true ); //TODO FreeGuide.prefs.screen.getBoolean( "executor_modal", true ) );
        initComponents(  );

        // Centre the screen
        java.awt.Dimension screenSize =
            java.awt.Toolkit.getDefaultToolkit(  ).getScreenSize(  );
        setLocation( 
            ( screenSize.width - getWidth(  ) ) / 2,
            ( screenSize.height - getHeight(  ) ) / 2 );
    }

    private void initComponents(  )
    {

        java.awt.GridBagConstraints gridBagConstraints;
        getContentPane(  ).setLayout( new java.awt.GridBagLayout(  ) );
        setTitle( FreeGuide.msg.getString( "executing_command" ) );
        butCancel =
            new javax.swing.JButton( FreeGuide.msg.getString( "cancel" ) );
        butCancel.setMaximumSize( new java.awt.Dimension( 115, 23 ) );
        butCancel.setMinimumSize( new java.awt.Dimension( 115, 23 ) );
        butCancel.setPreferredSize( new java.awt.Dimension( 115, 23 ) );
        butCancel.setMnemonic( KeyEvent.VK_C );
        gridBagConstraints = new java.awt.GridBagConstraints(  );
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets( 5, 5, 5, 5 );
        getContentPane(  ).add( butCancel, gridBagConstraints );
        butDetails =
            new javax.swing.JButton( FreeGuide.msg.getString( "show_output" ) );
        butDetails.setFont( new java.awt.Font( "Dialog", 0, 12 ) );
        butDetails.setMaximumSize( new java.awt.Dimension( 115, 23 ) );
        butDetails.setMinimumSize( new java.awt.Dimension( 115, 23 ) );
        butDetails.setPreferredSize( new java.awt.Dimension( 115, 23 ) );
        butDetails.setMnemonic( KeyEvent.VK_S );
        butDetails.addActionListener( 
            new java.awt.event.ActionListener(  )
            {
                public void actionPerformed( java.awt.event.ActionEvent evt )
                {
                    butDetailsActionPerformed( evt );
                }
            } );
        gridBagConstraints = new java.awt.GridBagConstraints(  );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets( 5, 5, 5, 5 );
        getContentPane(  ).add( butDetails, gridBagConstraints );
        labPleaseWait =
            new javax.swing.JLabel( 
                FreeGuide.msg.getString( "please_wait" ),
                javax.swing.SwingConstants.CENTER );
        labPleaseWait.setBorder( 
            javax.swing.BorderFactory.createBevelBorder( 
                javax.swing.border.BevelBorder.LOWERED ) );
        labPleaseWait.setMaximumSize( new java.awt.Dimension( 400, 22 ) );
        labPleaseWait.setMinimumSize( new java.awt.Dimension( 400, 22 ) );
        labPleaseWait.setPreferredSize( new java.awt.Dimension( 400, 22 ) );
        labPleaseWait.setHorizontalTextPosition( 
            javax.swing.SwingConstants.CENTER );
        gridBagConstraints = new java.awt.GridBagConstraints(  );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets( 5, 5, 5, 5 );
        getContentPane(  ).add( labPleaseWait, gridBagConstraints );
        progressBar = new javax.swing.JProgressBar( 0, 100 );
        gridBagConstraints = new java.awt.GridBagConstraints(  );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets( 2, 2, 2, 2 );
        getContentPane(  ).add( progressBar, gridBagConstraints );
        log = new JTextArea(  );

        // log.setRows( 10 );
        logScroll = new JScrollPane( log );
        gridBagConstraints = new java.awt.GridBagConstraints(  );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets( 2, 2, 2, 2 );
        logScroll.setVisible( false );
        log.setVisible( false );
        getContentPane(  ).add( logScroll, gridBagConstraints );

        // Centre the screen
        java.awt.Dimension screenSize =
            java.awt.Toolkit.getDefaultToolkit(  ).getScreenSize(  );
        setLocation( 
            ( screenSize.width - getWidth(  ) ) / 2,
            ( screenSize.height - getHeight(  ) ) / 2 );
        pack(  );
        minHeight = getHeight(  );
        maxHeight = minHeight + 150;

        //minPreferredSize = getSize(  );
        //maxPreferredSize = getSize(  );
        //maxPreferredSize.height += 200;
        //  setSize( maxPreferredSize );
        // To Be Added Shortly (Rob)
        //        GuiUtils.centerDialog( this );
    }

    /**
     * DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public JButton getActionButton(  )
    {

        return butCancel;
    }

    /**
     * Description of the Method
     *
     * @param evt Description of the Parameter
     */
    private void butDetailsActionPerformed( java.awt.event.ActionEvent evt )
    {
        showDetails(  );
    }

    // -----------------------------------------------------------------------
    private void showDetails(  )
    {
        logScroll.setVisible( !logScroll.isVisible(  ) );
        log.setVisible( !log.isVisible(  ) );
        butDetails.setText( 
            FreeGuide.msg.getString( 
                log.isVisible(  ) ? "hide_output" : "show_output" ) );

        int width = getWidth(  );
        int height;

        if( log.isVisible(  ) )
        {
            height = maxHeight;
        }
        else
        {
            maxHeight = getHeight(  );
            height = minHeight;
        }

        setSize( width, height );
        validate(  );
    }

    /**
     * DOCUMENT_ME!
     *
     * @param percent DOCUMENT_ME!
     */
    public void setProgressValue( final int percent )
    {
        SwingUtilities.invokeLater( 
            new Runnable(  )
            {
                public void run(  )
                {
                    progressBar.setValue( percent );
                }
            } );
    }

    /**
     * DOCUMENT_ME!
     *
     * @param stepCount DOCUMENT_ME!
     */
    public void setStepCount( final int stepCount )
    {
        this.stepCount = stepCount;
        setProgressValue( 0 );
    }

    /**
     * DOCUMENT_ME!
     *
     * @param stepNumber DOCUMENT_ME!
     */
    public void setStepNumber( final int stepNumber )
    {

        if( stepCount < stepNumber )
        {
            stepCount = stepNumber;
        }

        if( stepCount > 0 )
        {
            setProgressValue( ( 100 * stepNumber ) / stepCount );
        }
        else
        {
            setProgressValue( 0 );
        }
    }

    /**
     * DOCUMENT_ME!
     *
     * @param message DOCUMENT_ME!
     */
    public void setProgressMessage( final String message )
    {
        SwingUtilities.invokeLater( 
            new Runnable(  )
            {
                public void run(  )
                {

                    // Set the please wait message
                    Object[] messageArguments = { message };
                    labPleaseWait.setText( 
                        FreeGuide.msg.getLocalizedMessage( 
                            "comma_please_wait_template", messageArguments ) );
                    setTitle( message );

                    //labPleaseWait.setText(message);
                }
            } );
    }

    /**
     * DOCUMENT_ME!
     *
     * @param label DOCUMENT_ME!
     */
    public void setButtonLabel( final String label )
    {
        SwingUtilities.invokeLater( 
            new Runnable(  )
            {
                public void run(  )
                {
                    butCancel.setText( label );
                }
            } );
    }

    protected void addToLog( final String msg )
    {
        SwingUtilities.invokeLater( 
            new Runnable(  )
            {
                public void run(  )
                {
                    log.append( msg + '\n' );
                }
            } );
    }

    /**
     * DOCUMENT_ME!
     *
     * @param message DOCUMENT_ME!
     */
    public void error( String message )
    {
        addToLog( message );
    }

    /**
     * DOCUMENT_ME!
     *
     * @param message DOCUMENT_ME!
     * @param ex DOCUMENT_ME!
     */
    public void error( String message, Exception ex )
    {
        addToLog( message + " : " + ex.getMessage(  ) );
    }

    /**
     * DOCUMENT_ME!
     *
     * @param message DOCUMENT_ME!
     */
    public void info( String message )
    {
        addToLog( message );
    }

    /**
     * DOCUMENT_ME!
     *
     * @param message DOCUMENT_ME!
     */
    public void warning( String message )
    {
        addToLog( message );
    }
}