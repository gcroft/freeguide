package freeguide.plugins.ui.horizontal.components;

import java.awt.Component;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public interface StripRenderer
{

    /**
     * Returns a Component configured according to the given parameters. The
     * component will be used to render or edit a particular Strip in a
     * StripView. This method is called frequently, thus it is recommendable
     * that this method returns a single cached instance that is just
     * reconfigured for each call.
     *
     * @param view The StripView containing the
     * @param value The value of the Strip
     * @param isSelected Whether the Strip is selected or not
     * @param hasFocus Whether the Strip is focused or not
     * @param row Row in the StripView
     * @param start Start position of the Strip
     * @param end End position of the Strip
     *
     * @return
     */
    public Component getStripRendererComponent( 
        StripView view, Object value, boolean isSelected, boolean hasFocus,
        int row, long start, long end );
}
