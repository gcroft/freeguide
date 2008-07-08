package freeguide.plugins.grabber.xmltv;

import java.nio.channels.ClosedByInterruptException;
import java.util.Iterator;
import java.util.logging.Level;

import freeguide.common.plugininterfaces.ILogger;
import freeguide.common.plugininterfaces.IModuleGrabber;
import freeguide.common.plugininterfaces.IProgress;
import freeguide.common.plugininterfaces.IStoragePipe;
import freeguide.plugins.grabber.xmltv.XMLTVConfig.ModuleInfo;
import freeguide.plugins.program.freeguide.FreeGuide;
import freeguide.plugins.program.freeguide.lib.fgspecific.PluginsManager;
import freeguide.plugins.program.freeguide.lib.fgspecific.StoragePipe;
import freeguide.plugins.program.freeguide.lib.general.ICommandRunner;
import freeguide.plugins.program.freeguide.viewer.MainController;

public class SingleConfigCommandRunner implements ICommandRunner
{
    IModuleGrabber grabber;
    String moduleName;
    
    SingleConfigCommandRunner( IModuleGrabber grabber, String moduleName )
    {
        this.grabber = grabber;
        this.moduleName = moduleName;
    }

	public boolean run( IProgress progress, ILogger logger )
	{
		boolean cmdSucceeded = true;

		try
		{
            int retCode = grabber.chooseChannelsOne( moduleName, progress, logger );
            cmdSucceeded = ( retCode == 0 );
		}
		/*catch( ClosedByInterruptException ex )
		{
			break;
		}
		catch( InterruptedException ex )
		{
			break;
		}*/
		catch( Throwable ex )
		{
			cmdSucceeded = false;

			if( logger != null )
			{
				if( ex instanceof Exception )
				{
					logger.error(
						"Error configuring grabber '"
						+ "'", (Exception)ex );
				}
				else
				{
					logger.error(
						"Error configuring grabber '"
						+ "': " + ex.getClass(  ).getName(  ) );
				}
			}

			FreeGuide.log.log(
				Level.WARNING,
				"Error configuring by grabber '", ex );
		}

		return cmdSucceeded;
	}
}
