package freeguide.plugins.ui.horizontal.manylabels;

import freeguide.common.lib.general.PreferencesHelper;
import freeguide.common.lib.general.Time;

import java.awt.Color;
import java.awt.Font;

/**
 * DOCUMENT ME!
 *
 * @author Alex Bulochik (alex at zaval.org)
 */
public class HorizontalViewerConfig
{
    /** DOCUMENT ME! */
    public String currentChannelSetName;

    /** DOCUMENT ME! */
    public int positionSplitPaneVertical = 100;

    /** DOCUMENT ME! */
    public int positionSplitPaneHorizontalTop = 150;

    /** DOCUMENT ME! */
    public int positionSplitPaneHorizontalBottom = 400;

    /** DOCUMENT ME! */
    public int sizeChannelHeight = 28;

    /** DOCUMENT ME! */
    public int sizeHalfVerGap = 1;

    /** DOCUMENT ME! */
    public int sizeHalfHorGap = 1;

    /** DOCUMENT ME! */
    public int sizeProgrammePanelWidth = 8000;

    /** Default width of the channels scrolling panel */
    /** Default colour of a movie */
    public Color colorMovie = new Color( 255, 230, 230 );

    /** Default colour of a normal programme */
    public Color colorNonTicked = Color.WHITE;

    /** Default colour of the channel labels */
    public Color colorChannel = new Color( 245, 245, 255 );

    /** Default colour of a ticked programme */
    public Color colorTicked = new Color( 204, 255, 204 );
    
	/**
	 * new entries for favourite colour and guide colour
	 *
	 * @author Patrick Huber, Annetta Schaad (aschaad at hotmail.com)
	 */
    /** Default colour of a favourite and guide programme */
    public Color colorFavourite = new Color( 204, 204, 255 );
    public Color colorGuide = new Color( 255, 223, 181 );

    /** DOCUMENT ME! */
    public boolean displayAlignToLeft = true;

    /** DOCUMENT ME! */
    public String fontName = "Dialog";

    /** DOCUMENT ME! */
    public int fontStyle = Font.PLAIN;

    /** DOCUMENT ME! */
    public int fontSize = 12;

    /** DOCUMENT ME! */
    public boolean displayTime = true;

    /** DOCUMENT ME! */
    public boolean display24time = true;

    /** Not used any more but left for config compatibility. */
    public boolean displayDelta = true;

    /** DOCUMENT ME! */
    public boolean displayTooltips = false;

    /** DOCUMENT ME! */
    public Time dayStartTime = new Time( 6, 0 );

    /**
     * DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public Object clone(  )
    {
        HorizontalViewerConfig result = new HorizontalViewerConfig(  );

        PreferencesHelper.cloneObject( this, result );

        return result;

    }
}
