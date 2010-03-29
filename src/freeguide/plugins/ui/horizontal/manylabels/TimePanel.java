/*
 *  FreeGuide J2
 *
 *  Copyright (c) 2001-2004 by Andy Balaam and the FreeGuide contributors
 *
 *  Released under the GNU General Public License
 *  with ABSOLUTELY NO WARRANTY.
 *
 *  See the file COPYING for more information.
 */
package freeguide.plugins.ui.horizontal.manylabels;

import freeguide.common.lib.fgspecific.Application;
import freeguide.common.lib.general.Time;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * A panel that displays a ruler-like time line.
 *
 * @author Andy Balaam
 * @version 5
 */
public class TimePanel extends JPanel implements ActionListener
{
    // The time on the left hand side of the panel
    private long startTime;

    // The time on the right hand side of the panel
    private long endTime;

    // The no. millisecs over the no. pixels
    private double multiplier;

    private boolean display = false;
    private final SimpleDateFormat time24format =
        new SimpleDateFormat( "HH:mm" );
    private final SimpleDateFormat timeformat =
        new SimpleDateFormat( "h:mm aa" );

    /**
     * A timer running 3 times every minute to refresh the display
     * so that the time indicator stays up-to-date.
     */
    private Timer timer;

    // Save the parent viewer
    private HorizontalViewer parent;

    /**
     * Constructor for the TimePanel object
     */
    public TimePanel( HorizontalViewer parent )
    {
        this.parent = parent;

        display = false;

        setLayout( new java.awt.BorderLayout(  ) );

        // Run the timer 3 times every minute
        timer = new Timer( (int)( Time.MINUTE / 3 ), this );
        timer.start();
    }

    /**
     * Called every 20 secs: redraws the panel so that the time
     * indicator stays correct.
     */
    public void actionPerformed( ActionEvent e )
    {
        repaint();
    }

    /**
     * Description of the Method
     *
     * @param g Description of the Parameter
     */
    public void paintComponent( Graphics g )
    {
        //FreeGuide.log.info("Painting Time Panel.");
        if( display )
        {
            super.paintComponent( g );

            Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON );

            // FIXME: This prevents a divide by zero but does extra work!!
            int wid = this.getPreferredSize(  ).width;

            if( wid > 0 )
            {
                SimpleDateFormat fmt;

                //DMT use preferences for 24 hour or 12 hour display
                if( parent.config.display24time )
                {
                    fmt = time24format;
                }

                else
                {
                    fmt = timeformat;
                }

                fmt.setTimeZone( Application.getInstance(  ).getTimeZone(  ) );

                Rectangle drawHere = g.getClipBounds(  );

                //Rectangle viewable = new Rectangle();
                //computeVisibleRect(viewable);
                multiplier = (double)( endTime - startTime ) / (double)( wid );

                // FIXME: Instance variable?  Inconsistent locale setting
                Calendar tmpTime =
                    GregorianCalendar.getInstance(
                        Application.getInstance(  ).getTimeZone(  ),
                        Locale.ENGLISH );

                // FIXME: Why do this every paint?  Modify start/end
                //        in setTimes()?
                tmpTime.setTimeInMillis( startTime );

                // Forget about seconds and milliseconds
                tmpTime.set( Calendar.SECOND, 0 );

                tmpTime.set( Calendar.MILLISECOND, 0 );

                // Round to the nearest 5 mins
                tmpTime.set(
                    Calendar.MINUTE,
                    ( (int)( tmpTime.get( Calendar.MINUTE ) / 5 ) ) * 5 );

                // FIXME: -- END --

                // Step through each 5 mins
                while( tmpTime.getTimeInMillis(  ) < endTime )
                {
                    int xPos =
                        (int)( ( tmpTime.getTimeInMillis(  ) - startTime ) / multiplier );

                    // If this time is on screen, draw a mark
                    if(
                        ( ( xPos + 50 ) >= drawHere.x )
                            && ( ( xPos - 50 ) <= ( drawHere.x
                            + drawHere.width ) ) )
                    {
                        // Make a mark
                        if( tmpTime.get( Calendar.MINUTE ) == 0 )
                        {
                            // Hours
                            g.drawLine( xPos, 0, xPos, 10 );
                            g.drawLine( xPos + 1, 0, xPos + 1, 10 );
                            g.drawString(
                                fmt.format( tmpTime.getTime(  ) ), xPos - 17,
                                21 );

                        }

                        else if( tmpTime.get( Calendar.MINUTE ) == 30 )
                        {
                            // Half hours
                            g.drawLine( xPos, 0, xPos, 7 );

                            g.drawString(
                                fmt.format( tmpTime.getTime(  ) ), xPos - 17,
                                21 );
                        }

                        else if( ( tmpTime.get( Calendar.MINUTE ) % 10 ) == 0 )
                        {
                            // 10 mins
                            g.drawLine( xPos, 0, xPos, 4 );
                        }

                        else
                        {
                            g.drawLine( xPos, 0, xPos, 1 );
                        }
                    }

                    // Add another 5 mins
                    tmpTime.add( Calendar.MINUTE, 5 );
                } //while

                // Draw the "now" line
                int xPos = getNowScroll(  );
                drawNowLine( g, xPos );
            } //if
        } //if
    } //paintComponent

    /**
     * Draws the "now line" on this panel as a black triangle.
     *
     * @param g The graphics context to draw on.
     * @param xPos The x coordinate
     */
    protected void drawNowLine( final Graphics g, final int xPos )
    {
        int[] xPoints = { xPos - 5, xPos + 5, xPos };
        int[] yPoints = { 0, 0, 25 };
        int   nPoints = 3;

        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        g2.fillPolygon( xPoints, yPoints, nPoints );
    }

    /**
     * Gets the nowScroll attribute of the TimePanel object
     *
     * @return The nowScroll value
     */
    public int getNowScroll(  )
    {
        return getScrollValue( GregorianCalendar.getInstance(  ) );
    }

    /**
     * DOCUMENT_ME!
     *
     * @param showMillis DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public int getScrollValue( long showMillis )
    {
        if( display )
        {
            if( ( showMillis >= startTime ) && ( showMillis <= endTime ) )
            {
                return (int)( ( showMillis - startTime ) / multiplier );
            }
        }

        return 0;
    }

    /**
     * Gets the value to use to scroll the to a specified time.
     *
     * @param showTime DOCUMENT ME!
     *
     * @return The value to use to scroll to showTime
     */
    public int getScrollValue( Calendar showTime )
    {
        return getScrollValue( showTime.getTimeInMillis(  ) );
    }

    /**
     * Sets the times attribute of the TimePanel object
     *
     * @param newStartTime The new times value
     * @param newEndTime The new times value
     */
    public void setTimes( long newStartTime, long newEndTime )
    {
        // FIXME: divide by zero possible!!
        int wid = this.getPreferredSize(  ).width;
        multiplier = (double)( newEndTime - newStartTime ) / (double)( wid );

        startTime = newStartTime;
        endTime = newEndTime;
        display = true;
        repaint(  );
    }
}
