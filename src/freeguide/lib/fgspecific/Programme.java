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
package freeguide.lib.fgspecific;

import freeguide.*;

import org.xml.sax.Attributes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.*;

/**
 * FreeGuideProgramme A class that holds info about a particular programme.
 *
 * @author Andy Balaam
 * @version 5
 */
public class Programme
{

    /** Description of the Field */
    public final String stars = "***********";

    /** Is this programme displayed in the personalised guide? */
    private boolean inGuide;

    /** The start time */
    private Calendar start;

    /** The end time */
    private Calendar end;

    /** The programme title */
    private String title;

    /** The programme subtitle */
    private String subtitle;

    /** The programme description (short) */
    private String shortDesc;

    /** The programme description (long) */
    private String longDesc;

    /** The channel the prog's on */
    private Channel channel;

    /** The URL of the programme's icon */
    private String iconURL;

    /** The categories it fits into */
    private Vector category;

    /** Is it a movie? */
    private boolean isMovie;

    /** Is it a repeat? */
    private boolean previouslyShown;

    /** Its star rating if it's a movie */
    private String starRating;

    /** Does it have subtitles? */
    private boolean isSubtitled;

    /** A URL to more info about the programme */
    private URL link;

    /** Any unrecognised tags go in here. */
    private Hashtable extraTags;

    /**
     * Constructor for the Programme object
     */
    public Programme(  )
    {
    }

    /**
     * Sets the start attribute of the Programme object
     *
     * @param start The new start value
     */
    public void setStart( Calendar start )
    {
        this.start = start;
    }

    /**
     * Gets the start attribute of the Programme object
     *
     * @return The start value
     */
    public Calendar getStart(  )
    {

        return start;
    }

    /**
     * Sets the end attribute of the Programme object
     *
     * @param end The new end value
     */
    public void setEnd( Calendar end )
    {
        this.end = end;
    }

    /**
     * Gets the end attribute of the Programme object
     *
     * @return The end value
     */
    public Calendar getEnd(  )
    {

        return end;
    }

    /**
     * Sets the isMovie attribute of the Programme object
     *
     * @param isMovie The new isMovie value
     */
    public void setIsMovie( boolean isMovie )
    {
        this.isMovie = isMovie;
    }

    /**
     * Gets the isMovie attribute of the Programme object
     *
     * @return The isMovie value
     */
    public boolean getIsMovie(  )
    {

        return this.isMovie;
    }

    /**
     * Sets the previouslyShown attribute of the Programme object
     *
     * @param repeat The new previouslyShown value
     */
    public void setPreviouslyShown( boolean repeat )
    {
        this.previouslyShown = repeat;
    }

    /**
     * Gets the previouslyShown attribute of the Programme object
     *
     * @return The previouslyShown value
     */
    public boolean getPreviouslyShown(  )
    {

        return this.previouslyShown;
    }

    /**
     * Sets the starRating attribute of the Programme object
     *
     * @param rating The new starRating value
     */
    public void setStarRating( String rating )
    {
        this.starRating = rating;
    }

    /**
     * Gets the starRating attribute of the Programme object
     *
     * @return The starRating value
     */
    public String getStarRating(  )
    {

        return this.starRating;
    }

    /**
     * Gets the starString attribute of the Programme object
     *
     * @return The starString value
     */
    public String getStarString(  )
    {

        String rating = getStarRating(  );

        if( rating == null )
        {

            return "";
        }

        int i = rating.indexOf( '/' );

        if( i > 0 )
        {

            try
            {

                double num = Double.parseDouble( rating.substring( 0, i ) );

                if( num == 0 )
                {

                    return FreeGuide.msg.getString( "no_stars" );
                }

                if( Math.floor( num ) == num )
                {

                    return "("
                    + stars.substring( 
                        0, (int)Math.round( Math.floor( num ) ) ) + ")";
                }
                else
                {

                    return "("
                    + stars.substring( 
                        0, (int)Math.round( Math.floor( num ) ) ) + " 1/2)";
                }
            }
            catch( Exception ex )
            {

                return "";
            }
        }

        return "";
    }

    /**
     * Adds to the title attribute of the Programme object
     *
     * @param title The title of the programme
     */
    public void setTitle( String title )
    {

        if( this.title == null )
        {
            this.title = new String(  );
            this.title = title;

        } // FIXME else use the one with the correct lang tag
    }

    /**
     * Adds more to the subtitle of the programme
     *
     * @param subtitle The subtitle of the programme
     */
    public void setSubTitle( String subtitle )
    {

        if( this.subtitle == null )
        {
            this.subtitle = new String(  );
            this.subtitle = subtitle;

        } // FIXME else use the one with the correct lang tag
    }

    /**
     * Gets the title attribute of the Programme object
     *
     * @return The title value
     */
    public String getTitle(  )
    {

        return title;
    }

    /**
     * Gets the subtitle attribute of the Programme object
     *
     * @return The subtitle value
     */
    public String getSubTitle(  )
    {

        return subtitle;
    }

    /**
     * Adds a feature to the Desc attribute of the Programme object
     *
     * @param desc The feature to be added to the Desc attribute
     */
    public void addDesc( String desc )
    {

        if( ( longDesc == null ) || ( desc.length(  ) > longDesc.length(  ) ) )
        {
            longDesc = desc;
        }

        if( 
            ( shortDesc == null ) || ( desc.length(  ) < shortDesc.length(  ) ) )
        {
            shortDesc = desc;
        }
    }

    /**
     * Gets the longDesc attribute of the Programme object
     *
     * @return The longDesc value
     */
    public String getLongDesc(  )
    {

        return longDesc;
    }

    /**
     * Gets the shortDesc attribute of the Programme object
     *
     * @return The shortDesc value
     */
    public String getShortDesc(  )
    {

        return shortDesc;
    }

    /**
     * Gets the channel attribute of the Programme object
     *
     * @return The channel Object
     */
    public Channel getChannel(  )
    {

        return channel;
    }

    /**
     * Sets the channel attribute of the Programme object
     *
     * @param channel The new channel value
     */
    public void setChannel( Channel channel )
    {
        this.channel = channel;
    }

    /**
     * Sets whether the programme has subtitles
     *
     * @param isSubtitled DOCUMENT ME!
     */
    public void setSubtitled( boolean isSubtitled )
    {
        this.isSubtitled = isSubtitled;

    }

    /**
     * DOCUMENT ME!
     *
     * @return true if this programme has subtitles
     */
    public boolean isSubtitled(  )
    {

        return isSubtitled;

    }

    /**
     * Adds a feature to the Category attribute of the Programme object
     *
     * @param newCategory The feature to be added to the Category attribute
     */
    public void addCategory( String newCategory )
    {

        if( category == null )
        {
            category = new Vector(  );
        }

        category.add( newCategory );
    }

    /**
     * Gets the category attribute of the Programme object
     *
     * @return The category value
     */
    public String getCategory(  )
    {

        // FIXME just returns first one
        if( ( category != null ) && ( category.size(  ) > 0 ) )
        {

            return (String)category.get( 0 );
        }
        else
        {

            return null;
        }
    }

    /**
     * Sets a URL with more info about this programme
     *
     * @param link the URL to follow for more info
     */
    public void setLink( URL link )
    {
        this.link = link;

    }

    /**
     * Gets a link to follow for more info about this programme
     *
     * @return The URL of more info about the programme
     */
    public URL getLink(  )
    {

        return link;

    }

    /**
     * Decides whether two programme objects refer to the same programme
     * Programmes are assumed to be uniquely identified by their title, start
     * time and channel.
     *
     * @param obj Description of the Parameter
     *
     * @return Description of the Return Value
     */
    public boolean equals( Object obj )
    {

        if( obj == null )
        {

            return false;
        }

        if( !( obj instanceof Programme ) )
        {

            return false;
        }

        Programme other = (Programme)obj;

        if( 
            title.equals( other.getTitle(  ) )
                && start.equals( other.getStart(  ) )
                && channel.equals( other.getChannel(  ) ) )
        {

            return true;
        }

        return false;
    }

    /**
     * Returns a hashcode for the programme. Implemented to keep consistency
     * since the equals method was overridden.
     *
     * @return Description of the Return Value
     */
    public int hashCode(  )
    {

        // Just add up 3 values - stupid?
        byte[] titleBytes = title.getBytes(  );
        long startMS = start.getTimeInMillis(  );
        byte[] channelBytes = channel.getID(  ).getBytes(  );

        int ans = 0;

        for( int i = 0; i < titleBytes.length; i++ )
        {
            ans += titleBytes[i];
        }

        ans += (int)( startMS / ( 1000 * 60 ) );

        // Time in minutes since 1970
        for( int i = 0; i < channelBytes.length; i++ )
        {
            ans += channelBytes[i];
        }

        return ans;
    }

    /**
     * DOCUMENT_ME!
     *
     * @param name DOCUMENT_ME!
     * @param attrs DOCUMENT_ME!
     */
    public void startElement( String name, Attributes attrs )
    {

        if( extraTags == null )
        {
            extraTags = new Hashtable(  );
        }

        Hashtable hashOfAttrs = new Hashtable(  );

        for( int i = 0; i < attrs.getLength(  ); i++ )
        {
            hashOfAttrs.put( attrs.getQName( i ), attrs.getValue( i ) );

        }

        extraTags.put( name, hashOfAttrs );

    }

    /**
     * DOCUMENT_ME!
     *
     * @param mainTag DOCUMENT_ME!
     * @param subTag DOCUMENT_ME!
     * @param data DOCUMENT_ME!
     */
    public void endElement( String mainTag, String subTag, String data )
    {

        if( extraTags == null )
        {
            extraTags = new Hashtable(  );
        }

        Hashtable hashOfAttrs = (Hashtable)extraTags.get( mainTag );

        if( hashOfAttrs == null )
        {
            hashOfAttrs = new Hashtable(  );
            extraTags.put( mainTag, hashOfAttrs );

        }

        String newData = (String)hashOfAttrs.get( subTag );

        if( newData == null )
        {
            newData = data;
        }
        else
        {
            newData += ( "; " + data );
        }

        hashOfAttrs.put( subTag, newData );

    }

    /**
     * DOCUMENT ME!
     *
     * @return Returns the iconURL from the cache.
     */
    public String getIconURL(  )
    {

        if( iconURL == null )
        {

            return null;
        }

        StringBuffer path = FGPreferences.getIconCacheDir(  );
        path.append( 
            iconURL.replaceAll( "[^0-9A-Za-z_-]|^http://|^ftp://", "" ) );

        // First convert the id to a suitable (and safe!!) filename
        File cache = new File( path.toString(  ) );

        // then verify if the file is in the cache
        if( !cache.canRead(  ) )
        {

            // if not, we try to fetch it from the url
            try
            {

                URL iconURL;
                iconURL = new URL( this.iconURL );

                InputStream i = iconURL.openStream(  );
                FileOutputStream o = new FileOutputStream( cache );
                byte[] buffer = new byte[4096];
                int bCount;

                while( ( bCount = i.read( buffer ) ) != -1 )
                {
                    o.write( buffer, 0, bCount );
                }

                o.close(  );
                i.close(  );
            }
            catch( MalformedURLException e )
            {

                return null;
            }
            catch( IOException e )
            {

                return null;
            }
        }

        try
        {

            return cache.toURL(  ).toString(  );
        }
        catch( MalformedURLException e )
        {

            return null;
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param iconURL The iconURL to set.
     */
    public void setIconURL( String iconURL )
    {
        this.iconURL = iconURL;
    }

    /**
     * DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public Hashtable getExtraTags(  )
    {

        return extraTags;
    }

    /**
     * DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public boolean isInGuide(  )
    {

        return inGuide;
    }

    /**
     * DOCUMENT_ME!
     *
     * @param inGuide DOCUMENT_ME!
     */
    public void setInGuide( boolean inGuide )
    {
        this.inGuide = inGuide;
    }
}