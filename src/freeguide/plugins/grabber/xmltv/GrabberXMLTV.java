package freeguide.plugins.grabber.xmltv;

import freeguide.common.lib.fgspecific.Application;
import freeguide.common.lib.general.FileHelper;
import freeguide.common.lib.general.ResourceHelper;
import freeguide.common.lib.general.StringHelper;
import freeguide.common.lib.general.Utils;
import freeguide.common.lib.importexport.XMLTVImport;

import freeguide.common.plugininterfaces.BaseModule;
import freeguide.common.plugininterfaces.ILogger;
import freeguide.common.plugininterfaces.IModuleConfigurationUI;
import freeguide.common.plugininterfaces.IModuleConfigureFromWizard;
import freeguide.common.plugininterfaces.IModuleGrabber;
import freeguide.common.plugininterfaces.IProgress;
import freeguide.common.plugininterfaces.IStoragePipe;

import freeguide.plugins.program.freeguide.FreeGuide;
import freeguide.plugins.program.freeguide.viewer.MainFrame;

import org.xml.sax.SAXException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.text.MessageFormat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Grabber implementation for XMLTV.
 *
 * @author Alex Buloichik (mailto: alex73 at zaval.org)
 */
public class GrabberXMLTV extends BaseModule implements IModuleGrabber,
    IModuleConfigureFromWizard
{
    protected static final String DIR_CONFIG = "xmltv-configs";
    protected static final String DIR_INSTALLED = "xmltv";
    protected static final String REPLACE_CONFIG = "%config_file%";
    protected static final String REPLACE_XMLTV = "%xmltv_path%";
    protected static final String SUBST_LIN = "lin";
    protected static final String SUBST_WIN = "win";
    protected static final String ENV_PATH = "PATH";
    protected static final String CHANNEL_PREFIX = "xmltv/";
    protected static final String CONFIG_KEY_SUFFIX = "cfg";
    protected static final String RUN_KEY_SUFFIX = "run";
    protected static final String LIN_KEY_SUFFIX = "lin";
    protected static final String WIN_KEY_SUFFIX = "win";
    protected static final String PACKAGE_XMLTVWIN = "other/xmltv-win/";
    protected static final String PACKAGE_XMLTVWIN_LIST =
        PACKAGE_XMLTVWIN + "ls-xmltv";

    /** DOCUMENT ME! */
    public final static int REDOWNLOAD_ALWAYS = 0;

    /** DOCUMENT ME! */
    public final static int REDOWNLOAD_NEVER = 1;

    /** DOCUMENT ME! */
    public final static int REDOWNLOAD_ASK = 2;

    // execExternal
    // ----------------------------------------------------------------------
    protected static Properties cmds;
    protected static final String FILE_COMMANDS =
        "resources/plugins/grabber/xmltv/commands.properties";
    protected static final String MODULES_PREFIX = "region.";
    protected static final String MODULES_SUFFIX = ".grabber";
    protected static final String CHANNEL_SELECT_SUFFIX =
        ".allowChannelsSelect";
    protected static final String SUFFIX_FILE_CONFIG = ".conf";

    /** DOCUMENT ME! */
    public XMLTVConfig config = new XMLTVConfig(  );
    private Process pr;
    protected XMLTVConfigureUIPanelModule confUI;
    protected CountryInfo[] countryInfos;

    /**
     * DOCUMENT_ME!
     */
    public void start(  )
    {
        final File xmltvConfigDir =
            new File( 
                Application.getInstance(  ).getWorkingDirectory(  ), DIR_CONFIG );

        //na_dd is the only module currently
        XMLTVConfig.ModuleInfo moduleInfo =
            (XMLTVConfig.ModuleInfo)config.modules.get( 0 );

        String cmd = moduleInfo.commandToRun;

        if( null == cmd )
        {
            cmd = getCommand( moduleInfo.moduleName, RUN_KEY_SUFFIX );

            if( null == cmd )
            {
                Application.getInstance(  ).getLogger(  )
                           .severe( 
                    "Command not defined for " + moduleInfo.moduleName );

                return;
            }
        }

        cmd = StringHelper.replaceAll( 
                cmd, REPLACE_CONFIG,
                new File( xmltvConfigDir, moduleInfo.configFileName )
                .getAbsolutePath(  ) );

        if( null == Application.getInstance(  ).getInstallDirectory(  ) )
        {
            cmd = StringHelper.replaceAll( 
                    cmd, REPLACE_XMLTV,
                    new File( 
                        Application.getInstance(  ).getWorkingDirectory(  ),
                        DIR_INSTALLED ).getAbsolutePath(  ) );
        }
        else
        {
            cmd = StringHelper.replaceAll( 
                    cmd, REPLACE_XMLTV,
                    new File( 
                        Application.getInstance(  ).getInstallDirectory(  ),
                        DIR_INSTALLED ).getAbsolutePath(  ) );
        }

        if( checkXmltvExists( Utils.parseCommand( cmd ) ) ) //extracts xmltv from jar on Windows if needed
        {
            final JMenuItem menuLine =
                ( (MainFrame)Application.getInstance(  ).getApplicationFrame(  ) )
                .getMenuItemChooseXMLTVChannels(  );
            menuLine.setText( i18n.getString( "Menu.Tools.ChooseChannels" ) );
            Application.getInstance(  ).getMainMenu(  ).getTools(  )
                       .insert( menuLine, 0 );

            menuLine.addActionListener( 
                new ActionListener(  )
                {
                    public void actionPerformed( ActionEvent e )
                    {
                        new Thread(  )
                            {
                                public void run(  )
                                {
                                    final List modules = new ArrayList(  );

                                    synchronized( config.modules )
                                    {
                                        modules.addAll( config.modules );
                                    }

                                    for( int i = 0; i < modules.size(  );
                                            i++ )
                                    {
                                        final XMLTVConfig.ModuleInfo moduleInfo =
                                            (XMLTVConfig.ModuleInfo)modules.get( 
                                                i );
                                        configureChannels( moduleInfo );
                                    }
                                }
                            }.start(  );
                    }
                } );
        }
    }

    /**
     * DOCUMENT_ME!
     */
    public void stop(  )
    {
        Application.getInstance(  ).getLogger(  )
                   .exiting( this.getClass(  ).getName(  ), "stop" );
    }

    /**
     * DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public Object getConfig(  )
    {
        return config;
    }

    /**
     * DOCUMENT_ME!
     *
     * @param progress DOCUMENT ME!
     * @param logger DOCUMENT ME!
     * @param storage DOCUMENT ME!
     *
     * @return DOCUMENT_ME!
     *
     * @throws Exception DOCUMENT_ME!
     */
    public boolean grabData( 
        final IProgress progress, final ILogger logger,
        final IStoragePipe storage ) throws Exception
    {
        int code = 0;

        synchronized( config.modules )
        {
            for( int i = 0; i < config.modules.size(  ); i++ )
            {
                if( Thread.interrupted(  ) )
                {
                    return true;
                }

                XMLTVConfig.ModuleInfo moduleInfo =
                    (XMLTVConfig.ModuleInfo)config.modules.get( i );
                code = grabOne( storage, moduleInfo, progress, logger );

                if( code != 0 )
                {
                    break;
                }
            }
        }

        return ( code == 0 ) ? true : false;
    }

    protected void configureChannels( final XMLTVConfig.ModuleInfo moduleInfo )
    {
        final File xmltvConfigDir =
            new File( 
                Application.getInstance(  ).getWorkingDirectory(  ), DIR_CONFIG );
        xmltvConfigDir.mkdirs(  );

        String cmd = moduleInfo.configCommandToRun;

        if( cmd == null )
        {
            cmd = getCommand( moduleInfo.moduleName, CONFIG_KEY_SUFFIX );
        }

        if( cmd == null )
        {
            Application.getInstance(  ).getLogger(  )
                       .severe( 
                MessageFormat.format( 
                    i18n.getString( "Message.CommandNotDefined" ),
                    moduleInfo.moduleName ) );

            return;

        }

        cmd = StringHelper.replaceAll( 
                cmd, REPLACE_CONFIG,
                new File( xmltvConfigDir, moduleInfo.configFileName )
                .getAbsolutePath(  ) );

        if( Application.getInstance(  ).getInstallDirectory(  ) == null )
        {
            cmd = StringHelper.replaceAll( 
                    cmd, REPLACE_XMLTV,
                    new File( 
                        Application.getInstance(  ).getWorkingDirectory(  ),
                        DIR_INSTALLED ).getAbsolutePath(  ) );
        }
        else
        {
            cmd = StringHelper.replaceAll( 
                    cmd, REPLACE_XMLTV,
                    new File( 
                        Application.getInstance(  ).getInstallDirectory(  ),
                        DIR_INSTALLED ).getAbsolutePath(  ) );
        }

        Application.getInstance(  ).getLogger(  ).finest( 
            "Run command: " + cmd );

        int resultCode = execConfigCmd( Utils.parseCommand( cmd ) );
        Application.getInstance(  ).getLogger(  )
                   .finest( "Result code = " + resultCode );
    }

    protected static synchronized Map<String, String> getCommands(  )
    {
        if( cmds == null )
        {
            cmds = new Properties(  );

            try
            {
                cmds.load( ResourceHelper.getUncachedStream( FILE_COMMANDS ) );

            }
            catch( IOException ex )
            {
                Application.getInstance(  ).getLogger(  )
                           .log( 
                    Level.SEVERE, "Error loading commands settings for xmltv",
                    ex );
            }
        }

        return (Map)cmds;

    }

    protected static String getCommand( 
        final String modName, final String suffix )
    {
        return (String)getCommands(  )
                           .get( 
            modName + '.' + suffix + '.'
            + ( Application.getInstance(  ).isUnix(  ) ? SUBST_LIN : SUBST_WIN ) );

    }

    protected static String[] getMods( 
        final String prefix, final String suffix )
    {
        List result = new ArrayList(  );

        Map cs = getCommands(  );

        Iterator it = cs.keySet(  ).iterator(  );

        while( it.hasNext(  ) )
        {
            String key = (String)it.next(  );

            if( key.startsWith( prefix ) && key.endsWith( suffix ) )
            {
                result.add( 
                    key.substring( 
                        prefix.length(  ), key.length(  ) - suffix.length(  ) ) );
            }
        }

        return (String[])result.toArray( new String[result.size(  )] );
    }

    protected int grabOne( 
        final IStoragePipe storage, final XMLTVConfig.ModuleInfo moduleInfo,
        final IProgress progress, final ILogger logger )
        throws Exception
    {
        final File xmltvConfigDir =
            new File( 
                Application.getInstance(  ).getWorkingDirectory(  ), DIR_CONFIG );

        progress.setProgressMessage( 
            Application.getInstance(  ).getLocalizedMessage( "downloading" ) );

        String cmd = moduleInfo.commandToRun;

        if( cmd == null )
        {
            cmd = getCommand( moduleInfo.moduleName, RUN_KEY_SUFFIX );

        }

        if( cmd == null )
        {
            Application.getInstance(  ).getLogger(  )
                       .severe( 
                "Command not defined for " + moduleInfo.moduleName );

            logger.error( 
                MessageFormat.format( 
                    i18n.getString( "Message.CommandNotDefined" ),
                    moduleInfo.moduleName ) );

            return -1;

        }

        cmd = StringHelper.replaceAll( 
                cmd, REPLACE_CONFIG,
                new File( xmltvConfigDir, moduleInfo.configFileName )
                .getAbsolutePath(  ) );

        if( Application.getInstance(  ).getInstallDirectory(  ) == null )
        {
            cmd = StringHelper.replaceAll( 
                    cmd, REPLACE_XMLTV,
                    new File( 
                        Application.getInstance(  ).getWorkingDirectory(  ),
                        DIR_INSTALLED ).getAbsolutePath(  ) );
        }
        else
        {
            cmd = StringHelper.replaceAll( 
                    cmd, REPLACE_XMLTV,
                    new File( 
                        Application.getInstance(  ).getInstallDirectory(  ),
                        DIR_INSTALLED ).getAbsolutePath(  ) );
        }

        Application.getInstance(  ).getLogger(  ).finest( 
            "Run command: " + cmd );

        logger.info( 
            MessageFormat.format( i18n.getString( "Message.Command" ), cmd ) );

        int resultCode =
            execGrabCmd( storage, Utils.parseCommand( cmd ), progress, logger );

        Application.getInstance(  ).getLogger(  )
                   .finest( "Result code = " + resultCode );

        logger.info( 
            MessageFormat.format( 
                i18n.getString( "Message.ResultCode" ), resultCode ) );

        return resultCode;
    }

    protected int execGrabCmd( 
        final IStoragePipe storage, final String[] args,
        final IProgress progress, final ILogger logger )
        throws Exception
    {
        pr = execCmd( args );

        if( pr == null )
        {
            return -1;
        }

        // Get the input and output streams of this process
        BufferedReader prErr =
            new BufferedReader( 
                new InputStreamReader( pr.getErrorStream(  ) ) );

        final Thread threadData =
            new ReadOutput( storage, pr.getInputStream(  ), progress, logger );
        final Thread threadErrors = new ReadErrors( prErr, logger );

        threadData.start(  );
        threadErrors.start(  );

        int res;

        try
        {
            res = pr.waitFor(  );

            while( threadData.isAlive(  ) )
            {
                if( Thread.interrupted(  ) )
                {
                    break;
                }

                Thread.sleep( 200 );
            }
        }

        catch( InterruptedException ex )
        {
            res = -1;
        }

        threadData.interrupt(  );
        threadErrors.interrupt(  );
        pr.destroy(  );

        return res;
    }

    protected Process execCmd( final String[] args ) throws IOException
    {
        if( !checkXmltvExists( args ) )
        {
            if( FreeGuide.runtimeInfo.isUnix )
            {
                JOptionPane.showMessageDialog( 
                    Application.getInstance(  ).getApplicationFrame(  ),
                    i18n.getString( "ErrorBox.Text.Linux" ),
                    i18n.getString( "ErrorBox.Title" ),
                    JOptionPane.ERROR_MESSAGE );
            }
            else
            {
                JOptionPane.showMessageDialog( 
                    Application.getInstance(  ).getApplicationFrame(  ),
                    i18n.getString( "ErrorBox.Text.Windows" ),
                    i18n.getString( "ErrorBox.Title" ),
                    JOptionPane.ERROR_MESSAGE );
            }

            return null;
        }

        try
        {
            return Runtime.getRuntime(  ).exec( args );
        }
        catch( IOException ex )
        {
            Application.getInstance(  ).getLogger(  )
                       .log( Level.WARNING, "Error execute xmltv grabber", ex );
            throw ex;
        }
    }

    /**
     * Check if xmltv exists in path.
     *
     * @param args command line for run
     *
     * @return true if exists
     */
    protected boolean checkXmltvExists( final String[] args ) //throws IOException
    {
        if( ( args == null ) || ( args.length == 0 ) )
        {
            //throw new IOException( "Invalid command line" );
            return false;
        }

        if( !Application.getInstance(  ).isUnix(  ) )
        {
            final File xmltvDir =
                new File( 
                    Application.getInstance(  ).getWorkingDirectory(  ),
                    DIR_INSTALLED );

            if( !xmltvDir.exists(  ) )
            {
                if( 
                    !FileHelper.unpackFiles( 
                            PACKAGE_XMLTVWIN_LIST, PACKAGE_XMLTVWIN, xmltvDir ) )
                {
                    return false;
                }
            }
        }

        final String xmltvName = args[0];

        if( new File( xmltvName ).exists(  ) )
        {
            return true;
        }

        final String path;

        try
        {
            path = System.getenv( ENV_PATH );

        }
        catch( Error ex )
        {
            // Ugly hack to work around brokenness in Java 1.4.x - we assume
            // the XMLTV EXE exists if getenv is not supported.
            return true;
        }

        if( path != null )
        {
            final String[] dirs = path.split( File.pathSeparator );

            for( int i = 0; i < dirs.length; i++ )
            {
                final File check = new File( dirs[i], args[0] );

                if( check.exists(  ) )
                {
                    return true;
                }
            }
        }

        return false;
    }

    protected int execConfigCmd( final String[] args )
    {
        try
        {
            Process pr = execCmd( args );

            if( pr != null )
            {
                new ReadProcess( pr.getInputStream(  ), Level.FINEST ).start(  );
                new ReadProcess( pr.getErrorStream(  ), Level.FINE ).start(  );

                return pr.waitFor(  );
            }
            else
            {
                return -1;
            }
        }
        catch( Exception ex )
        {
            Application.getInstance(  ).getLogger(  )
                       .log( Level.WARNING, "Error execute xmltv grabber", ex );

            return -1;
        }
    }

    /**
     * DOCUMENT_ME!
     *
     * @param parentDialog DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public IModuleConfigurationUI getConfigurationUI( JDialog parentDialog )
    {
        return new XMLTVConfigureUIController( this );

    }

    /**
     * DOCUMENT_ME!
     *
     * @param regionName DOCUMENT_ME!
     * @param runSelectChannels DOCUMENT ME!
     */
    public void configureFromWizard( 
        final String regionName, final boolean runSelectChannels )
    {
        XMLTVConfig.ModuleInfo info = new XMLTVConfig.ModuleInfo(  );
        info.moduleName = (String)getCommands(  )
                                      .get( 
                MODULES_PREFIX + regionName + MODULES_SUFFIX );
        info.configFileName = (String)getCommands(  )
                                          .get( 
                MODULES_PREFIX + regionName + MODULES_SUFFIX )
            + SUFFIX_FILE_CONFIG;

        synchronized( config.modules )
        {
            config.modules.clear(  );
            config.modules.add( info );
        }

        if( runSelectChannels )
        {
            configureChannels( info );
        }
    }

    protected String listToString( final List list )
    {
        final StringBuffer result = new StringBuffer(  );

        for( int i = 0; i < list.size(  ); i++ )
        {
            result.append( (String)list.get( i ) );

            result.append( ' ' );

        }

        result.setLength( result.length(  ) - 1 );

        return result.toString(  );

    }

    /**
     * DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public CountryInfo[] getSupportedCountries(  )
    {
        if( countryInfos == null )
        {
            String[] mods = getMods( MODULES_PREFIX, MODULES_SUFFIX );
            countryInfos = new CountryInfo[mods.length];

            for( int i = 0; i < mods.length; i++ )
            {
                countryInfos[i] = new CountryInfo( 
                        mods[i], 0,
                        new Boolean( 
                            getCommands(  )
                                .get( 
                                MODULES_PREFIX + mods[i]
                                + CHANNEL_SELECT_SUFFIX ) ).booleanValue(  ) );
            }
        }

        return countryInfos;
    }

    protected class ReadProcess extends Thread
    {
        final protected BufferedReader stream;
        final protected Level logLevel;

/**
         * Creates a new ReadProcess object.
         * 
         * @param stream
         *            DOCUMENT ME!
         * @param level
         *            DOCUMENT ME!
         */
        public ReadProcess( final InputStream stream, final Level level )
        {
            this.stream = new BufferedReader( new InputStreamReader( stream ) );
            this.logLevel = level;
        }

        /**
         * DOCUMENT_ME!
         */
        public void run(  )
        {
            String line;

            try
            {
                while( ( line = stream.readLine(  ) ) != null )
                {
                    Application.getInstance(  ).getLogger(  )
                               .log( logLevel, line );
                }
            }
            catch( IOException ex )
            {
                Application.getInstance(  ).getLogger(  )
                           .log( 
                    Level.WARNING, "Error on read xmltv console stream", ex );
            }
        }
    }

    protected static class ReadErrors extends Thread
    {
        BufferedReader rd;
        ILogger logger;

/**
         * Creates a new Read object.
         * 
         * @param rd
         *            DOCUMENT ME!
         * @param logger
         *            DOCUMENT ME!
         */
        public ReadErrors( BufferedReader rd, final ILogger logger )
        {
            this.rd = rd;

            this.logger = logger;

        }

        /**
         * DOCUMENT_ME!
         */
        public void run(  )
        {
            String line;

            try
            {
                while( ( line = rd.readLine(  ) ) != null )
                {
                    logger.warning( line );

                }
            }

            catch( IOException ex )
            {
                Application.getInstance(  ).getLogger(  )
                           .log( 
                    Level.WARNING, "Error read output from xmltv grabber", ex );
            }
        }
    }

    protected class ReadOutput extends Thread
    {
        final protected ILogger logger;
        final protected InputStream in;
        final protected IProgress progress;
        final protected IStoragePipe storage;

/**
         * Creates a new ReadOutputData object.
         * 
         * @param storage
         *            DOCUMENT ME!
         * @param in
         *            DOCUMENT ME!
         * @param logger
         *            DOCUMENT ME!
         */
        public ReadOutput( 
            final IStoragePipe storage, final InputStream in,
            final IProgress progress, final ILogger logger )
        {
            this.in = in;
            this.logger = logger;
            this.progress = progress;
            this.storage = storage;
        }

        /**
         * DOCUMENT_ME!
         */
        public void run(  )
        {
            final XMLTVImport.ProgrammesCountCallback callback =
                new XMLTVImport.ProgrammesCountCallback(  )
                {
                    public void onProgramme( int count )
                    {
                        if( ( count % 10 ) == 0 )
                        {
                            progress.setProgressMessage( 
                                MessageFormat.format( 
                                    i18n.getString( "Message.Count" ), count ) );
                        }
                    }
                };

            try
            {
                new XMLTVImport(  ).process( 
                    in, storage, callback, new XMLTVImport.Filter(  ),
                    CHANNEL_PREFIX );
            }
            catch( SAXException ex )
            {
                if( !Thread.interrupted(  ) && ( ex.getException(  ) != null ) )
                {
                    String msg = ex.getException(  ).getMessage(  );

                    logger.error( 
                        MessageFormat.format( 
                            i18n.getString( "Message.ExecuteError" ), msg ) );

                    Application.getInstance(  ).getLogger(  )
                               .log( 
                        Level.WARNING, "SAX Error executing grabber: " + msg,
                        ex.getException(  ) );
                }
            }
            catch( ParserConfigurationException ex )
            {
                String msg = ex.getMessage(  );

                logger.error( 
                    MessageFormat.format( 
                        i18n.getString( "Message.ExecuteError" ), msg ) );

                Application.getInstance(  ).getLogger(  )
                           .log( 
                    Level.WARNING,
                    "ParserConfigurationException executing grabber: " + msg,
                    ex );
            }
            catch( IOException ex )
            {
                String msg = ex.getMessage(  );

                logger.error( 
                    MessageFormat.format( 
                        i18n.getString( "Message.ExecuteError" ), msg ) );

                Application.getInstance(  ).getLogger(  )
                           .log( 
                    Level.WARNING, "Error executing grabber: " + msg, ex );
            }
        }
    }
}
