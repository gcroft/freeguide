package freeguide.common.gui;

import java.awt.Component;
import javax.swing.JOptionPane;

import freeguide.common.lib.fgspecific.Application;
import freeguide.common.lib.fgspecific.DisplayDocs;

/**
 * A class to call DisplayDocs.displayDocs and display an error to the user if
 * it fails.
 */
public class DisplayDocsOrError
{
    public static void displayDocsOrError()
    {
        // TODO:
        Component parentComponent = null;

        try
        {
            DisplayDocs.displayDocs();
        }
        catch( DisplayDocs.UnableToDisplayDocsException e )
        {
            e.printStackTrace();

            Object[] params = { e.docsDirectory };
            JOptionPane.showMessageDialog( parentComponent, Application
                .getInstance().getLocalizedMessage(
                    "unable_to_find_the_documentation_files_in_directory",
                    params ), Application.getInstance().getLocalizedMessage(
                "unable_to_find_documentation" ), JOptionPane.ERROR_MESSAGE );
        }
        catch( Exception e )
        {
            e.printStackTrace();

            Object[] params = { e.toString() };
            JOptionPane.showMessageDialog( parentComponent,
                Application.getInstance()
                    .getLocalizedMessage(
                        "unable_to_launch_browser_to_display_documentation",
                        params ), Application.getInstance()
                    .getLocalizedMessage( "unable_to_launch_browser" ),
                JOptionPane.ERROR_MESSAGE );
        }
    }
}
