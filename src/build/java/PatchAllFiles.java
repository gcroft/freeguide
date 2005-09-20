import freeguide.lib.fgspecific.PluginInfo;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class PatchAllFiles
{

    /**
     * DOCUMENT_ME!
     *
     * @param args DOCUMENT_ME!
     *
     * @throws Exception DOCUMENT_ME!
     */
    public static void main( final String[] args ) throws Exception
    {

        PluginInfo[] plugins = loadPluginsInfo(  );
        PatchRepository.patch( plugins );
        PatchBuild.patch( plugins );
        PatchApplication_java.patch( plugins );

        new File( "build/dist/windows" ).mkdirs(  );
        new File( "build/dist/linux/rpm" ).mkdirs(  );

        PatchFile.patch( "doc/VERSION.php.in", "doc/VERSION.php", plugins );
        PatchFile.patch( 
            "src/install/windows/freeguide.nsi.in",
            "build/dist/windows/freeguide.nsi", plugins );
        PatchFile.patch( 
            "src/install/linux/rpm/freeguide.spec.in",
            "build/dist/linux/rpm/freeguide.spec", plugins );
        PatchFile.patch( 
            "src/install/linux/rpm/freeguide-base.spec",
            "build/dist/linux/rpm/freeguide-base.spec", plugins );
        PatchFile.patch( 
            "src/install/linux/rpm/freeguide-plugin.spec.template",
            "build/dist/linux/rpm/freeguide-plugin.spec.template", plugins );
        PatchFile.patch( 
            "src/install/linux/freeguide.1.in", "build/dist/linux/freeguide.1",
            plugins );
        PatchFile.patchAllPlugins( 
            "build/dist/linux/rpm/freeguide-plugin.spec.template",
            "build/dist/linux/rpm/freeguide-", ".spec", plugins );
    }

    protected static PluginInfo[] loadPluginsInfo(  ) throws Exception
    {

        SAXParser saxParser =
            SAXParserFactory.newInstance(  ).newSAXParser(  );
        File[] dirs =
            new File( "src/plugins/" ).listFiles( 
                new FileFilter(  )
                {
                    public boolean accept( File pathname )
                    {

                        return pathname.isDirectory(  )
                        && new File( pathname, "java/plugin.xml" ).exists(  );
                    }
                } );

        List files = new ArrayList(  );
        files.add( new File( "src/java/plugin.xml" ) );

        if( dirs != null )
        {

            for( int i = 0; i < dirs.length; i++ )
            {
                files.add( new File( dirs[i], "java/plugin.xml" ) );
            }
        }

        PluginInfo[] result = new PluginInfo[files.size(  )];

        for( int i = 0; i < files.size(  ); i++ )
        {

            File xmlFile = (File)files.get( i );
            result[i] = new PluginInfo(  );

            saxParser.parse( xmlFile, result[i] );
        }

        return result;
    }

    protected static void changeOldFile( String baseFileName )
        throws IOException
    {
        new File( baseFileName + ".bak" ).delete(  );

        if( 
            !new File( baseFileName ).renameTo( 
                    new File( baseFileName + ".bak" ) ) )
        {
            throw new IOException( "Error rename file to .bak" );
        }

        if( 
            !new File( baseFileName + ".new" ).renameTo( 
                    new File( baseFileName ) ) )
        {
            throw new IOException( "Error rename .new to file" );
        }

        new File( baseFileName + ".bak" ).delete(  );
    }
}