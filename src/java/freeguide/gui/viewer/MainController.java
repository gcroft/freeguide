package freeguide.plugins.program.freeguide.viewer;

import freeguide.FreeGuide;

import freeguide.plugins.program.freeguide.gui.ChannelSetListDialog;

import freeguide.common.lib.fgspecific.Application;
import freeguide.common.lib.fgspecific.GrabberController;
import freeguide.common.lib.fgspecific.PluginInfo;
import freeguide.common.lib.fgspecific.PluginsManager;
import freeguide.common.lib.fgspecific.StoragePipe;
import freeguide.common.lib.fgspecific.data.TVChannelsSet;
import freeguide.common.lib.fgspecific.data.TVData;

import freeguide.common.lib.general.LanguageHelper;
import freeguide.common.lib.general.LookAndFeelManager;
import freeguide.common.lib.general.Utils;

import freeguide.common.plugininterfaces.BaseModule;
import freeguide.common.plugininterfaces.IApplication;
import freeguide.common.plugininterfaces.IModuleExport;
import freeguide.common.plugininterfaces.IModuleGrabber;
import freeguide.common.plugininterfaces.IModuleImport;
import freeguide.common.plugininterfaces.IModuleReminder;
import freeguide.common.plugininterfaces.IModuleStorage;
import freeguide.common.plugininterfaces.IModuleViewer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Main window of application.
 *
 * @author Alex Buloichik (alex73 at zaval.org)
 */
public class MainController extends BaseModule implements IApplication
{

    /** DOCUMENT ME! */
    public static final Config config = new Config(  );

    /** DOCUMENT ME! */
    public MainFrame mainFrame;

    /** DOCUMENT ME! */
    public IModuleViewer viewer;
    protected GrabberController grab = new GrabberController(  );
    protected IModuleReminder[] reminders;
    protected JFrame applicationFrame;

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
     * @return DOCUMENT_ME!
     */
    public IMainMenu getMainMenu(  )
    {

        return mainFrame.getMainMenuForExport(  );
    }

    /**
     * DOCUMENT_ME!
     */
    public void saveAllConfigs(  )
    {
        PluginsManager.saveAllConfigs(  );
    }

    /**
     * DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     *
     * @throws Exception DOCUMENT_ME!
     */
    public Locale[] getSuppotedLocales(  ) throws Exception
    {

        return LanguageHelper.getLocaleList( "i18n/MessagesBundle" );
    }

    /**
     * DOCUMENT_ME!
     *
     * @param locale DOCUMENT_ME!
     *
     * @throws Exception DOCUMENT_ME!
     */
    public void setLocale( Locale locale ) throws Exception
    {
        i18n = new LanguageHelper( "i18n/MessagesBundle", locale );
    }

    /**
     * DOCUMENT_ME!
     *
     * @param key DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public String getLocalizedMessage( String key )
    {

        return i18n.getLocalizedMessage( key );
    }

    /**
     * DOCUMENT_ME!
     *
     * @param key DOCUMENT_ME!
     * @param params DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public String getLocalizedMessage( String key, Object[] params )
    {

        return i18n.getLocalizedMessage( key, params );
    }

    /**
     * DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public IModuleReminder[] getReminders(  )
    {

        if( reminders == null )
        {

            PluginInfo[] infos = PluginsManager.getReminders(  );
            reminders = new IModuleReminder[infos.length];

            for( int i = 0; i < reminders.length; i++ )
            {
                reminders[i] = (IModuleReminder)infos[i].getInstance(  );
            }
        }

        return reminders;
    }

    /**
     * DOCUMENT_ME!
     *
     * @param viewer DOCUMENT_ME!
     * @param grabberFromWizard DOCUMENT ME!
     */
    public void start( 
        final IModuleViewer viewer, final String grabberFromWizard )
    {
        applicationFrame = FreeGuide.getPleaseWaitFrame(  );

        this.viewer = viewer;

        mainFrame = new MainFrame(  );

        mainFrame.setTitle( 
            "FreeGuide " + Application.VERSION.getDotFormat(  ) );

        new MenuHandler( this );

        mainFrame.getContentPane(  ).add( 
            viewer.getPanel(  ), BorderLayout.CENTER );

        mainFrame.addWindowListener( 
            new java.awt.event.WindowAdapter(  )
            {
                public void windowClosing( java.awt.event.WindowEvent evt )
                {
                    saveConfigNow(  );

                    stopModules(  );

                    System.exit( 0 );
                }
            } );
        mainFrame.getProgressBar(  ).addMouseListener( 
            new MouseListener(  )
            {
                public void mouseClicked( MouseEvent e )
                {
                    doShowGrabbers(  );
                }

                public void mouseEntered( MouseEvent e )
                {
                }

                public void mouseExited( MouseEvent e )
                {
                }

                public void mousePressed( MouseEvent e )
                {
                }

                public void mouseReleased( MouseEvent e )
                {
                }
            } );

        if( grabberFromWizard != null )
        {
            config.activeGrabberIDs.add( grabberFromWizard );
        }

        setLookAndFeel(  );

        mainFrame.pack(  );

        mainFrame.setBounds( config.ui.mainWindowPosition );

        startModules(  );

        mainFrame.getRootPane(  ).setDefaultButton( 
            viewer.getDefaultButton(  ) );
        mainFrame.setVisible( true );

        FreeGuide.hidePleaseWait(  );

        applicationFrame = mainFrame;

        //checkForNoData(  );
        mainFrame.waitForClose(  );

        stopModules(  );
    }

    /**
     * Set a viewer
     *
     * @param viewerId DOCUMENT ME!
     */
    public void setViewer( String viewerId )
    {

        if( 
            ( (MainController.Config)this.getConfig(  ) ).viewerId.equals( 
                    viewerId ) )
        {

            //Viewer is already active
            return;
        }

        if( this.viewer != null )
        {
            mainFrame.getContentPane(  ).remove( this.viewer.getPanel(  ) );
            this.viewer.close(  );
        }

        ( (MainController.Config)this.getConfig(  ) ).viewerId = viewerId;
        this.viewer = (IModuleViewer)PluginsManager.getModuleByID( viewerId );
        mainFrame.getContentPane(  ).add( 
            this.viewer.getPanel(  ), BorderLayout.CENTER );
        this.viewer.open(  );

        this.viewer.getPanel(  ).updateUI(  );
    }

    /**
     * Checks whether the XMLTVLoader managed to get any data, and asks the
     * user to download more if not.
     */
    protected void checkForNoData(  )
    {

        if( 
            !Application.getInstance(  ).getDataStorage(  ).getInfo(  ).channelsList
                .isEmpty(  ) )
        {

            return;
        }

        int r =
            JOptionPane.showConfirmDialog( 
                Application.getInstance(  ).getApplicationFrame(  ),
                Application.getInstance(  ).getLocalizedMessage( 
                    "there_are_missing_listings_for_today" ),
                Application.getInstance(  ).getLocalizedMessage( 
                    "download_listings_q" ), JOptionPane.YES_NO_OPTION );

        if( r == 0 )
        {
            Application.getInstance(  ).doStartGrabbers(  );
        }
    }

    /**
     * Starts up all grabbers and reminders via their start() method.
     */
    protected void startModules(  )
    {
        viewer.open(  );

        final PluginInfo[] grabbers = PluginsManager.getGrabbers(  );

        for( int i = 0; i < grabbers.length; i++ )
        {

            IModuleGrabber grabber =
                (IModuleGrabber)grabbers[i].getInstance(  );
            grabber.start(  );
        }

        final PluginInfo[] reminders = PluginsManager.getReminders(  );

        for( int i = 0; i < reminders.length; i++ )
        {

            IModuleReminder reminder =
                (IModuleReminder)reminders[i].getInstance(  );
            reminder.addItemsToMenu( mainFrame.getMenuTools(  ) );
            reminder.start(  );
        }
    }

    /**
     * Shuts down all running reminders and grabbers via their stop() method.
     * The viewer is closed after all, too. It's called before FreeGuide is
     * closed.
     */
    protected void stopModules(  )
    {

        //stop reminders
        final PluginInfo[] reminders = PluginsManager.getReminders(  );

        for( int i = 0; i < reminders.length; i++ )
        {
            ( (IModuleReminder)reminders[i].getInstance(  ) ).stop(  );
        }

        //stop grabbers
        final PluginInfo[] grabbers = PluginsManager.getGrabbers(  );

        for( int i = 0; i < grabbers.length; i++ )
        {

            IModuleGrabber grabber =
                (IModuleGrabber)grabbers[i].getInstance(  );
            grabber.stop(  );
        }

        //clean up storages
        final PluginInfo[] storages = PluginsManager.getStorages(  );

        for( int i = 0; i < storages.length; i++ )
        {

            IModuleStorage storage =
                (IModuleStorage)storages[i].getInstance(  );
            storage.cleanup(  );
        }

        //close viewer
        viewer.close(  );
    }

    /**
     * DOCUMENT_ME!
     */
    public static void remindersReschedule(  )
    {

        final PluginInfo[] reminders = PluginsManager.getReminders(  );

        for( int i = 0; i < reminders.length; i++ )
        {
            ( (IModuleReminder)reminders[i].getInstance(  ) ).reschedule(  );
        }
    }

    /**
     * DOCUMENT_ME!
     */
    public void saveConfigNow(  )
    {
        config.ui.mainWindowPosition = mainFrame.getBounds(  );
        super.saveConfigNow(  );
    }

    /**
     * Set L&F as described in config.
     */
    public void setLookAndFeel(  )
    {

        final String inspectedLFClassName;
        final String currentLAFClassName;

        if( config.ui.LFname == null )
        {
            inspectedLFClassName = UIManager.getSystemLookAndFeelClassName(  );
        }
        else
        {
            inspectedLFClassName =
                LookAndFeelManager.getLookAndFeelClassName( config.ui.LFname );
        }

        final LookAndFeel currentLAF = UIManager.getLookAndFeel(  );

        if( currentLAF != null )
        {
            currentLAFClassName = currentLAF.getClass(  ).getName(  );
        }
        else
        {
            currentLAFClassName = null;
        }

        if( !inspectedLFClassName.equals( currentLAFClassName ) )
        {

            try
            {
                UIManager.setLookAndFeel( inspectedLFClassName );

                SwingUtilities.updateComponentTreeUI( mainFrame );
            }
            catch( Exception ex )
            {
                FreeGuide.log.log( 
                    Level.WARNING, "Error setup L&F to "
                    + inspectedLFClassName, ex );
            }
        }
    }

    /**
     * DOCUMENT_ME!
     */
    public void doEditChannelsSets(  )
    {

        ChannelSetListDialog dialog =
            new ChannelSetListDialog( 
                mainFrame, getDataStorage(  ).getInfo(  ).channelsList,
                config.channelsSetsList );

        Utils.centreDialog( mainFrame, dialog );

        boolean updated = dialog.showDialog(  );

        if( updated )
        {
            config.channelsSetsList = dialog.getChannelsSets(  );
            viewer.onChannelsSetsChanged(  );
        }
    }

    /**
     * Activates the grabber controller. That basically shows the grabber
     * dialog if grabbing is runnning, or starts the grabbing process
     */
    public void doStartGrabbers(  )
    {
        grab.activate( this );
    }

    /**
     * Activates the grabber controller.
     *
     * @see this.doStartGrabbers()
     */
    public void doShowGrabbers(  )
    {
        grab.activate( this );
    }

    /**
     * DOCUMENT_ME!
     *
     * @param exp DOCUMENT_ME!
     */
    public void exportTo( final IModuleExport exp )
    {
        new Thread(  )
            {
                public void run(  )
                {

                    IModuleStorage.Info info =
                        getDataStorage(  ).getInfo(  ).cloneInfo(  );

                    try
                    {
                        info.channelsList = null;

                        TVData data = getDataStorage(  ).get( info );
                        exp.exportData( data, mainFrame );
                    }
                    catch( Exception ex )
                    {
                        FreeGuide.log.log( 
                            Level.WARNING, "Error export data", ex );
                    }
                }
            }.start(  );
    }

    /**
     * DOCUMENT_ME!
     *
     * @param imp DOCUMENT_ME!
     */
    public void importFrom( final IModuleImport imp )
    {
        new Thread(  )
            {
                public void run(  )
                {

                    try
                    {

                        final StoragePipe pipe = new StoragePipe(  );
                        imp.importDataUI( mainFrame, pipe );
                        pipe.finish(  );
                        viewer.onDataChanged(  );
                    }
                    catch( Exception ex )
                    {
                        FreeGuide.log.log( 
                            Level.WARNING, "Error import data", ex );
                    }
                }
            }.start(  );
    }

    /**
     * DOCUMENT_ME!
     */
    public void doPrint(  )
    {
        viewer.printHTML(  );

    }

    /**
     * DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public JFrame getApplicationFrame(  )
    {

        return mainFrame;

    }

    /**
     * DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public JFrame getCurrentFrame(  )
    {

        return applicationFrame;

    }

    /**
     * DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public List getChannelsSetsList(  )
    {

        return config.channelsSetsList;

    }

    /**
     * DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public IModuleStorage getDataStorage(  )
    {

        return FreeGuide.storage;

    }

    /**
     * DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public IModuleViewer getViewer(  )
    {

        return viewer;
    }

    /**
     * DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public boolean isUnix(  )
    {

        // TODO Auto-generated method stub
        return FreeGuide.runtimeInfo.isUnix;
    }

    /**
     * DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public TimeZone getTimeZone(  )
    {

        return FreeGuide.getTimeZone(  );
    }

    /**
     * DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public String getBrowserCommand(  )
    {

        return FreeGuide.config.browserCommand;
    }

    /**
     * DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public String getInstallDirectory(  )
    {

        return FreeGuide.runtimeInfo.installDirectory;
    }

    /**
     * DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public Logger getLogger(  )
    {

        // TODO Auto-generated method stub
        return FreeGuide.log;
    }

    /**
     * DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public String getWorkingDirectory(  )
    {

        // TODO Auto-generated method stub
        return FreeGuide.config.workingDirectory;
    }

    /**
     * DOCUMENT_ME!
     */
    public void restart(  )
    {
        mainFrame.dispose(  );
    }

    /**
     * DOCUMENT ME!
     *
     * @author $author$
     * @version $Revision$
     */
    public static class Config
    {

        /** The list of available channel sets (all are ChannelSet objects) */
        public static final Class channelsSetsList_TYPE = TVChannelsSet.class;

        /** DOCUMENT ME! */
        public static final Class activeGrabberIDs_TYPE = String.class;

        /** DOCUMENT ME! */
        public UI ui = new UI(  );

        /** DOCUMENT ME! */
        public List channelsSetsList = new ArrayList(  );

        /** DOCUMENT ME! */
        public Set activeGrabberIDs = new TreeSet(  );

        /** The default selected viewer */
        public String viewerId = FreeGuide.VIEWER_ID;

        /**
         * DOCUMENT ME!
         *
         * @author $author$
         * @version $Revision$
         */
        public static class UI
        {

            /** DOCUMENT ME! */
            public Rectangle mainWindowPosition;

            /** DOCUMENT ME! */
            public String LFname;

            /**
             * Creates a new UI object.
             */
            public UI(  )
            {

                Dimension screenSize =
                    Toolkit.getDefaultToolkit(  ).getScreenSize(  );

                mainWindowPosition = new Rectangle( 640, 400 );

                mainWindowPosition.setLocation( 
                    ( screenSize.width - mainWindowPosition.width ) / 2,
                    ( screenSize.height - mainWindowPosition.height ) / 2 );
            }
        }
    }
}
