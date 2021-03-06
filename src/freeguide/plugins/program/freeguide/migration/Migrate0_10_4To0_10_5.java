package freeguide.plugins.program.freeguide.migration;

import freeguide.plugins.program.freeguide.FreeGuide;

import java.util.prefs.BackingStoreException;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class Migrate0_10_4To0_10_5 extends MigrationProcessBase
{
    /**
     * Creates a new Migrate0_10_4To0_10_5 object.
     *
     * @param nodeName DOCUMENT ME!
     *
     * @throws BackingStoreException DOCUMENT ME!
     */
    public Migrate0_10_4To0_10_5( final String nodeName )
        throws BackingStoreException
    {
        super( nodeName );
    }

    /**
     * DOCUMENT_ME!
     *
     * @throws Exception DOCUMENT_ME!
     */
    public void migrate(  ) throws Exception
    {
        FreeGuide.log.info( "Upgrading preferences 0.10.4 -> 0.10.5" );

        moveNode( "" );

        getAndRemoveKey( "version" );
        putKey( "version", "0.10.5" );
    }
}
