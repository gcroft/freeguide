package freeguide.plugins.program.freeguide.options;

import freeguide.common.base.IModuleConfigurationUI;
import freeguide.common.lib.fgspecific.Application;


import freeguide.plugins.program.freeguide.FreeGuide;
import freeguide.plugins.program.freeguide.lib.general.LookAndFeelManager;
import freeguide.plugins.program.freeguide.viewer.MainController;

import java.awt.Component;

import java.util.List;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;

/**
 * DOCUMENT ME!
 *
 * @author Alex Buloichik (mailto: alex73 at zaval.org)
 */
public class PanelGeneralController implements IModuleConfigurationUI
{
    PanelGeneralUI panel;
    Locale[] locales;

    /**
     * DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public Component getPanel(  )
    {
        if( panel == null )
        {
            panel = new PanelGeneralUI(  );

            List lfs = LookAndFeelManager
                .getAvailableLooksAndFeels(  );
            lfs.add(
                0,
                Application.getInstance(  )
                           .getLocalizedMessage( "Options.General.LF.default" ) );
            panel.getCbLF(  )
                 .setModel( new DefaultComboBoxModel( lfs.toArray(  ) ) );

            //locales = Application.getInstance(  ).getSupportedLocales(  );

            //String[] langNames = new String[locales.length + 1];
            //langNames[0] = Application.getInstance(  )
            //                          .getLocalizedMessage(
            //        "Options.General.Language.default" );

            //for( int i = 0; i < locales.length; i++ )
            //{
            //    langNames[i + 1] = locales[i].getDisplayName(  );
            //}

            //panel.getCbLang(  ).setModel(
            //    new DefaultComboBoxModel( langNames ) );
            resetToDefaults(  );

            if( MainController.config.ui.LFname != null )
            {
                panel.getCbLF(  )
                     .setSelectedItem( MainController.config.ui.LFname );
            }

            //if( FreeGuide.config.lang != null )
            //{
            //    panel.getCbLang(  )
            //         .setSelectedItem(
            //        FreeGuide.config.lang.getDisplayName(  ) );
            //}
        }

        return panel;
    }

    /**
     * DOCUMENT_ME!
     */
    public void resetToDefaults(  )
    {
        if( panel != null )
        {
            panel.getTextWorkingDir(  )
                 .setText( FreeGuide.config.workingDirectory );

            panel.getCbLF(  ).setSelectedIndex( 0 );

            //panel.getCbLang(  ).setSelectedIndex( 0 );
        }
    }

    /**
     * DOCUMENT_ME!
     */
    public void save(  )
    {
        if( panel == null )
        {
            return;
        }

        FreeGuide.config.workingDirectory = panel.getTextWorkingDir(  )
                                                 .getText(  );

        if( panel.getCbLF(  ).getSelectedIndex(  ) != -1 )
        {
            if( panel.getCbLF(  ).getSelectedIndex(  ) == 0 )
            {
                MainController.config.ui.LFname = null;
            }
            else
            {
                MainController.config.ui.LFname = panel.getCbLF(  )
                                                       .getSelectedItem(  )
                                                       .toString(  );
            }
        }

        //if( panel.getCbLang(  ).getSelectedIndex(  ) != -1 )
        //{
        //    if( panel.getCbLang(  ).getSelectedIndex(  ) == 0 )
        //    {
        //        FreeGuide.config.lang = null;
        //    }
        //    else
        //    {
        //        FreeGuide.config.lang = locales[panel.getCbLang(  )
        //                                             .getSelectedIndex(  ) - 1];
        //    }
        //}
    }

    /**
     * DOCUMENT_ME!
     */
    public void cancel(  )
    {
    }
}
