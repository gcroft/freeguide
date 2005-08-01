package freeguide.migration;

import freeguide.FreeGuide;

import freeguide.lib.fgspecific.Application;

import freeguide.lib.general.Version;

import java.util.prefs.Preferences;

/**
 * Main class for migration. It checks version and run migrations.
 *
 * @author Alex Bulouichik (alex73 at zaval.org)
 */
public class Migrate
{

    protected static boolean firstTime = true;
    protected static boolean needToRunWizard = false;
    protected static boolean needToRunAfter = false;

    /**
     * DOCUMENT_ME!
     *
     * @throws Exception DOCUMENT_ME!
     */
    public static void migrateBeforeWizard(  ) throws Exception
    {
        firstTime =
            !Preferences.userRoot(  ).nodeExists( "/org/freeguide-tv" );
        FreeGuide.log.finer( "Migration: firstTime=" + firstTime );

        if( firstTime )
        {

            return;
        }

        Version ver = getInstalledVersion(  );

        if( new Version( null ).equals( ver ) )
        {
            needToRunWizard = true;
        }
        else
        {

            if( ver.compareTo( new Version( 0, 10, 0 ) ) < 0 )
            {
                needToRunWizard = true;
                needToRunAfter = true;

                MigrationProcessBase migr =
                    new MigrateOldTo0_10_1( 
                        Preferences.userRoot(  ).node( "/org/freeguide-tv" ) );
                migr.migrate(  );

                migr = new Migrate0_10_1To0_10_2( migr.getResult(  ) );
                migr.migrate(  );
                migr.saveTo( 
                    Preferences.userRoot(  ).node( "/org/freeguide-tv" ) );
            }
            else if( ver.compareTo( new Version( 0, 10, 1 ) ) == 0 )
            {

                MigrationProcessBase migr =
                    new Migrate0_10_1To0_10_2( 
                        Preferences.userRoot(  ).node( "/org/freeguide-tv" ) );
                migr.migrate(  );
                migr.saveTo( 
                    Preferences.userRoot(  ).node( "/org/freeguide-tv" ) );
            }
            else if( ver.compareTo( Application.VERSION ) > 0 )
            {
                needToRunWizard = true;
            }
        }
    }

    /**
     * DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public static boolean isNeedToRunWizard(  )
    {

        return needToRunWizard;
    }

    /**
     * DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public static boolean isFirstTime(  )
    {

        return firstTime;
    }

    /**
     * DOCUMENT_ME!
     *
     * @throws Exception DOCUMENT_ME!
     */
    public static void migrateAfterWizard(  ) throws Exception
    {

        if( needToRunAfter )
        {
            MigrateOldTo0_10_1.migrateAfterWizard(  );
        }
    }

    protected static Version getInstalledVersion(  )
    {

        String storedVersionName =
            Preferences.userRoot(  ).node( "/org/freeguide-tv/misc" ).get( 
                "install_version", null );

        if( storedVersionName == null )
        {
            storedVersionName =
                Preferences.userRoot(  ).node( "/org/freeguide-tv" ).get( 
                    "version", null );
        }

        FreeGuide.log.finer( 
            "Migration: storedVersionName=" + storedVersionName );

        return new Version( storedVersionName );
    }
}
