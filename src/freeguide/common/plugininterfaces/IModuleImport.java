package freeguide.common.plugininterfaces;

import java.io.File;

import javax.swing.JFrame;

import freeguide.common.base.IModule;

/**
 * Load data from file(s). Each import module should implement this interface.
 *
 * @author Alex Buloichik (mailto: alex73 at zaval.org)
 */
public interface IModuleImport extends IModule
{
    /**
     * Load data by UI.
     *
     * @param parent
     * @param storage DOCUMENT ME!
     */
    void importDataUI( final JFrame parent, final IStoragePipe storage,
        ILogger logger );

    /**
     * Load data from file.
     *
     * @param path
     * @param storage DOCUMENT ME!
     *
     * @throws Exception
     */
    void importData( final File path, final IStoragePipe storage,
        ILogger logger )
        throws Exception;
}
