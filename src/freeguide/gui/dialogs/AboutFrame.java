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
package freeguide.gui.dialogs;

import freeguide.*;

/**
 * A small About box.
 *
 * @author Andy Balaam
 * @version 2
 */
public class AboutFrame extends javax.swing.JDialog
{

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;

    /**
     * Constructor for the About object
     *
     * @param parent Description of the Parameter
     * @param modal Description of the Parameter
     */
    public AboutFrame( java.awt.Frame parent, boolean modal )
    {
        super( parent, modal );
        initComponents(  );

        jTextPane1.setContentType( "text/html" );

        String str = "";

        str += "<font face='verdana, arial, helvetica, helv, sans serif' size=3>";
        str += "<table width=\"100%\" height=\"100%\" border=\"0\"><tr><td height=\"100%\" align=\"center\">";
        str += ( "<h1><font face='arial, helvetica, helv, sans serif' size=\"5\">FreeGuide "
        + FreeGuide.version.getDotFormat(  ) + "</font></h1>" );
        str += "<p>";
        str += FreeGuide.msg.getString( "free_software_by_contributors" );
        str += "</p><p>";
        str += FreeGuide.msg.getString( "web" );
        str += ": <a href=\"http://freeguide-tv.sourceforge.net\">freeguide-tv.sourceforge.net</a></p><p>";
        str += FreeGuide.msg.getString( "mail" );
        str += ": <a href=\"mailto:freeguide-tv-devel@lists.sourceforge.net\">freeguide-tv-devel@lists.sourceforge.net</a></p>";
        str += "</td></tr></table>";
        str += "</font>";

        jTextPane1.setText( str );
    }

    private void initComponents(  )
    {

        //GEN-BEGIN:initComponents
        jButton1 = new javax.swing.JButton(  );
        jButton2 = new javax.swing.JButton(  );
        jScrollPane1 = new javax.swing.JScrollPane(  );
        jTextPane1 = new javax.swing.JTextPane(  );

        jButton1.setText( "jButton1" );

        getContentPane(  ).setLayout( new java.awt.BorderLayout( 2, 2 ) );

        addWindowListener( 
            new java.awt.event.WindowAdapter(  )
            {
                public void windowClosing( java.awt.event.WindowEvent evt )
                {
                    closeDialog( evt );
                }
            } );

        jButton2.setText( "OK" );
        jButton2.addActionListener( 
            new java.awt.event.ActionListener(  )
            {
                public void actionPerformed( java.awt.event.ActionEvent evt )
                {
                    jButton2ActionPerformed( evt );
                }
            } );

        getContentPane(  ).add( jButton2, java.awt.BorderLayout.SOUTH );

        jTextPane1.setBackground( new java.awt.Color( 225, 255, 255 ) );
        jTextPane1.setEditable( false );
        jTextPane1.setContentType( "text/html\n" );
        jScrollPane1.setViewportView( jTextPane1 );

        getContentPane(  ).add( jScrollPane1, java.awt.BorderLayout.CENTER );

        pack(  );

        java.awt.Dimension screenSize =
            java.awt.Toolkit.getDefaultToolkit(  ).getScreenSize(  );
        setSize( new java.awt.Dimension( 416, 245 ) );
        setLocation( 
            ( screenSize.width - 416 ) / 2, ( screenSize.height - 245 ) / 2 );
    }

    //GEN-END:initComponents

    /**
     * Description of the Method
     *
     * @param evt Description of the Parameter
     */
    private void jButton2ActionPerformed( java.awt.event.ActionEvent evt )
    {

        //GEN-FIRST:event_jButton2ActionPerformed
        setVisible( false );
        dispose(  );
    }

    //GEN-LAST:event_jButton2ActionPerformed

    /**
     * Closes the dialog
     *
     * @param evt Description of the Parameter
     */
    private void closeDialog( java.awt.event.WindowEvent evt )
    {

        //GEN-FIRST:event_closeDialog
        setVisible( false );
        dispose(  );
    }

    //GEN-LAST:event_closeDialog

    /**
     * DOCUMENT ME!
     *
     * @param args the command line arguments
     */
    public static void main( String[] args )
    {
        new AboutFrame( new javax.swing.JFrame(  ), true ).setVisible( true );

    }

    // End of variables declaration//GEN-END:variables
}
