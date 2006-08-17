package freeguide.common.plugininterfaces;

import freeguide.common.lib.fgspecific.data.TVProgramme;
import freeguide.common.lib.fgspecific.selection.Favourite;

import javax.swing.JMenu;
import javax.swing.JPopupMenu;

/**
 * Reminder support module for alarm or VCR recording.  There are favourites
 * and selections. Favourite describes programme parameters, like channel and
 * title. Selection describes only one TV programme for scheduled date and
 * time. New grabbed programmes can have favourites, but can't have
 * selections. Selections can change favourites settings only for own
 * programme. For example, programme can equals to favourite, but there is
 * selection where flag  'selected'==false. In this case programme should be
 * deselected.
 *
 * @author Alex Buloichik(alex73 at zaval.org)
 */
public interface IModuleReminder extends IModule
{
    /**
     * Read programmes and schedule again.
     */
    public void reschedule(  );

    /**
     * Start scheduler for wait new schedule.
     */
    public void start(  );

    /**
     * Stop scheduler.
     */
    public void stop(  );

    /**
     * Check if programme is highlighted.
     *
     * @author Patrick Huber, Annetta Schaad (aschaad at hotmail.com)
     *
     * @param programme programme
     *
     * @return true if highlighted
     */
    public boolean isHighlighted( TVProgramme programme );


    /**
     * Check if programme is selected.
     *
     * @param programme programme
     *
     * @return true if selected
     */
    public boolean isSelected( TVProgramme programme );

    /**
     * Select/deselect programme.
     *
     * @param programme programme
     * @param newSelection DOCUMENT ME!
     *
     * @author Patrick Huber, Annetta Schaad (aschaad at hotmail.com)
     * add parameter for highlight
     */
    public void setProgrammeSelection( 
        final TVProgramme programme, final boolean newSelection, final boolean newHighlight);

    /**
     * Add items to main frame menu.
     *
     * @param menu main frame menu
     */
    public void addItemsToMenu( final JMenu menu );

    /**
     * Add items to popup menu for programme.
     *
     * @param programme programme
     * @param menu popup menu
     */
    public void addItemsToPopupMenu( 
        final TVProgramme programme, final JPopupMenu menu );

    Favourite getFavourite( TVProgramme programme );

    void addFavourite( final Favourite favourite );

    void removeFavourite( final Favourite favourite );
}
