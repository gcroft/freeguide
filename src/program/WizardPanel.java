
import java.awt.Component;
import java.io.File;
import java.lang.reflect.Method;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *  A JPanel to go on a FreeGuideWizard. It is subclassed by
 *  FreeGuideTextWizardPanel, FreeGuideFileWizardPanel,
 *  FreeGuideDirectoryWizardPanel, FreeGuideCommandsWizardPanel, or
 *  FreeGuideChoiceWizardPanel. The above classes may be used to link to a
 *  config entry by calling the setConfig method. If this is done, the onExit
 *  and onEnter methods are useful as they save and load the value respectively.
 *  You can also provide a custom onEnter and/or onExit method to be executed
 *  using the setOnEnter and setOnExist methods. These will be executed on enter
 *  and exit.
 *
 *@author     Andy Balaam
 *@created    28 June 2003
 *@version    1
 */
public class WizardPanel extends javax.swing.JPanel {

    /**
     *  Constructor for the FreeGuideWizardPanel superclass. This panel can be
     *  linked to a config entry, or it can have an onExit Method, or both.
     */
    public WizardPanel() { }


    /**
     *  Set up the messages that will appear above and below the box on the
     *  panel.
     *
     *@param  topMessage     The new messages value
     *@param  bottomMessage  The new messages value
     */
    public void setMessages(String topMessage, String bottomMessage) {
        this.topMessage = topMessage;
        this.bottomMessage = bottomMessage;
    }


    /**
     *  Set a configuration option that is linked to this panel.
     *
     *@param  configGroup  the String for group e.g. 'misc'
     *@param  configEntry  the String for entry e.g. 'working_directory'
     */
    public void setConfig(String configGroup, String configEntry) {
        this.configGroup = configGroup;
        this.configEntry = configEntry;
    }


    /**
     *  Sets up a method to be executed when we exit this panel. It must take an
     *  single argument: the object that is being got from the user by this
     *  panel e.g. a File object if this panel asks for a directory. Its return
     *  value will be ignored if there is one.
     *
     *@param  onExitObject  The new onExit value
     *@param  onExitMethod  The new onExit value
     */
    public void setOnExit(Object onExitObject, Method onExitMethod) {
        this.onExitMethod = onExitMethod;
        this.onExitObject = onExitObject;
    }


    /**
     *  Sets up a method to be executed when we enter this panel. It must take
     *  no args and return value to be put in this panel's box.
     *
     *@param  onEnterObject  The new onEnter value
     *@param  onEnterMethod  The new onEnter value
     */
    public void setOnEnter(Object onEnterObject, Method onEnterMethod) {
        this.onEnterObject = onEnterObject;
        this.onEnterMethod = onEnterMethod;
    }


    // -------------------------------------

    /**
     *  Construct the GUI of this Wizard Panel.
     */
    public void construct() {
        // Will be overridden
    }


    // ---------------------------------------------

    /**
     *  Prepare as we enter this panel.
     */
    public void onEnter() {

        // Save the config entry if there is one
        if (configEntry != null) {

            try {
                FGPreferences pref = (FGPreferences) (PreferencesGroup.class.getField(configGroup).get(new PreferencesGroup()));
                loadFromPrefs(pref);

            } catch (java.lang.NoSuchFieldException e) {
                e.printStackTrace();
            } catch (java.lang.IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (onEnterMethod != null) {

            try {

                Object[] args = new Object[0];
                setBoxValue(onEnterMethod.invoke(onEnterObject, args));

            } catch (java.lang.IllegalAccessException e) {
                e.printStackTrace();
            } catch (java.lang.reflect.InvocationTargetException e) {
                e.printStackTrace();
            }

        }
    }


    /**
     *  Clear up as we leave this panel. Return false if it's not ok to leave,
     *  otherwise leave.
     *
     *@return    Description of the Return Value
     */
    public boolean onExit() {

        // Save the config entry if there is one
        if (configEntry != null) {

            try {

                String error = checkValue();

                if (error != null) {
                    // If we have an error, ask the user if they want to continue

                    String lb = System.getProperty("line.separator");
                    int ignore = JOptionPane.showConfirmDialog(this, error + lb + "Do you want to continue?", "Error", JOptionPane.YES_NO_OPTION);

                    if (ignore == JOptionPane.NO_OPTION) {
                        // If not, go back
                        return false;
                    }
                    // Otherwise, go on with saving the value
                }
                FGPreferences pref = (FGPreferences) (PreferencesGroup.class.getField(configGroup).get(new PreferencesGroup()));
                saveToPrefs(pref);

            } catch (java.lang.NoSuchFieldException e) {
                e.printStackTrace();
            } catch (java.lang.IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // Execute an onExit method if there is one
        if (onExitMethod != null) {

            Object[] args = new Object[1];
            args[0] = getBoxValue();

            try {

                onExitMethod.invoke(onExitObject, args);

            } catch (java.lang.reflect.InvocationTargetException e) {
                e.printStackTrace();
            } catch (java.lang.IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return true;
    }


    // -----------------------------------------

    /**
     *  Returns an error if this value if faulty, or null it's ok
     *
     *@return    Description of the Return Value
     */
    private String checkValue() {

        return ConfigGuesser.checkValue(configGroup, configEntry,
                this.getBoxValue());
    }


    /**
     *  Files the box with a guess at the right answer for this panel's config
     *  entry.
     */
    protected void guess() {
        this.setBoxValue( ConfigGuesser.guess( configGroup, configEntry) );
    }


    // -------------------------------

    /**
     *  Description of the Method
     *
     *@param  pref  Description of the Parameter
     */
    protected void saveToPrefs(FGPreferences pref) {
        // Should never get here.
    }


    /**
     *  Description of the Method
     *
     *@param  pref  Description of the Parameter
     */
    protected void loadFromPrefs(FGPreferences pref) {
        // Should never get here.
    }


    // -----------------------------------------------

    /**
     *  Gets the value that's in this panel's box. The box just means the
     *  textfield or whatever that the user is typing into or choosing items in.
     *
     *@return    The boxValue value
     */
    protected Object getBoxValue() {
        return null;
        // Should never get here
    }


    /**
     *  Sets the value that's in this panel's box. The box just means the
     *  textfield or whatever that the user is typing into or choosing items in.
     *
     *@param  val  The new boxValue value
     */
    protected void setBoxValue(Object val) {
        // Should never get here
    }


    // -------------------------------------------

    /**
     *  Description of the Field
     */
    protected String topMessage;
    // The config group if there is to be a guess
    /**
     *  Description of the Field
     */
    protected String bottomMessage;
    // The config entry if there is to be a guess

    /**
     *  Description of the Field
     */
    protected String configGroup;
    // The config group if there is to be a guess
    /**
     *  Description of the Field
     */
    protected String configEntry;
    // The config entry if there is to be a guess

    /**
     *  Description of the Field
     */
    protected Object onExitObject;
    // Object containing method to execute on exit
    /**
     *  Description of the Field
     */
    protected Method onExitMethod;
    // Method to execute on exit

    /**
     *  Description of the Field
     */
    protected Object onEnterObject;
    // Object containing method to execute on entry
    /**
     *  Description of the Field
     */
    protected Method onEnterMethod;
    // Method to execute on entry

}
