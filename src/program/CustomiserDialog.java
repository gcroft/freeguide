/*
 *  FreeGuide J2
 *
 *  Copyright (c) 2001-2003 by Andy Balaam and the FreeGuide contributors
 *
 *  freeguide-tv.sourceforge.net
 *
 *  Released under the GNU General Public License
 *  with ABSOLUTELY NO WARRANTY.
 *
 *  See the file COPYING for more information.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.*;
import javax.swing.*;

/**
 *  FreeGuideCustomiser The Colour options screen for FreeGuide
 *
 *@author     Brendan Corrigan (based on CustomiserFrame by Andy Balaam)
 *@created    22nd August 2003
 *@version    1
 */
public class CustomiserDialog extends JDialog {

    /**
     * Constructor which sets the customiser up as a JDialog...
     *
     *@param owner - the <code>JFrame</code> from which the dialog is displayed 
     *@param title - the <code>String</code> to display in the dialog's title bar
     *@param modal - true for a modal dialog, false for one that allows other windows to be active at the same time

     */
    public CustomiserDialog(JFrame owner, String title, boolean modal) {
        super(owner, title, modal);
        
        this.owner = owner;
        
        initComponents();
        initScreen();
    }


    /**
     *  This method is called from within the constructor to initialize the
     *  form. WARNING: Do NOT modify this code. The content of this method is
     *  always regenerated by the Form Editor.
     */
    private void initComponents() {
        //GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        scrScreen = new javax.swing.JScrollPane();
        panScreen = new javax.swing.JPanel();
        labChannelColour = new javax.swing.JLabel();
        txtChannelColour = new javax.swing.JTextField();
        labProgrammeMovieColour = new javax.swing.JLabel();
        txtProgrammeMovieColour = new javax.swing.JTextField();
        labProgrammeNormalColour = new javax.swing.JLabel();
        txtProgrammeNormalColour = new javax.swing.JTextField();
        labProgrammeChosenColour = new javax.swing.JLabel();
        txtProgrammeChosenColour = new javax.swing.JTextField();
        labChannelHeight = new javax.swing.JLabel();
        txtChannelHeight = new javax.swing.JTextField();
        labVerticalGap = new javax.swing.JLabel();
        txtVerticalGap = new javax.swing.JTextField();
        labHorizontalGap = new javax.swing.JLabel();
        txtHorizontalGap = new javax.swing.JTextField();
        labPanelWidth = new javax.swing.JLabel();
        txtPanelWidth = new javax.swing.JTextField();
        labChannelPanelWidth = new javax.swing.JLabel();
        txtChannelPanelWidth = new javax.swing.JTextField();
        labFont = new javax.swing.JLabel();
        butFont = new javax.swing.JButton();
        panButtons = new javax.swing.JPanel();
        butOK = new javax.swing.JButton();
        butCancel = new javax.swing.JButton();
        timebuttongroup = new javax.swing.ButtonGroup();
        time12button = new javax.swing.JRadioButton();
        time24button = new javax.swing.JRadioButton();
        timeLabel = new javax.swing.JLabel();
        timeCBLabel = new javax.swing.JLabel();
        timeCheckBox = new javax.swing.JCheckBox();
        landf = new javax.swing.JLabel();
        landfCheckBox = new javax.swing.JCheckBox();
        landfNoteLabel = new javax.swing.JLabel();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        panScreen.setLayout(new java.awt.GridBagLayout());

        labChannelColour.setText("Channel Background Colour");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panScreen.add(labChannelColour, gridBagConstraints);

        txtChannelColour.setEditable(false);
        txtChannelColour.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChannelColour.addMouseListener(
            new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    txtChannelColourMouseClicked(evt);
                }
            });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.9;
        panScreen.add(txtChannelColour, gridBagConstraints);

        labProgrammeNormalColour.setText("Programme Background Colour");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panScreen.add(labProgrammeNormalColour, gridBagConstraints);

        txtProgrammeNormalColour.setEditable(false);
        txtProgrammeNormalColour.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProgrammeNormalColour.addMouseListener(
            new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    txtProgrammeNormalColourMouseClicked(evt);
                }
            });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.9;
        panScreen.add(txtProgrammeNormalColour, gridBagConstraints);

        labProgrammeChosenColour.setText("Chosen Programme Colour");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panScreen.add(labProgrammeChosenColour, gridBagConstraints);

        txtProgrammeChosenColour.setEditable(false);
        txtProgrammeChosenColour.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProgrammeChosenColour.addMouseListener(
            new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    txtProgrammeChosenColourMouseClicked(evt);
                }
            });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.9;
        panScreen.add(txtProgrammeChosenColour, gridBagConstraints);

        labChannelHeight.setText("Channel Height");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panScreen.add(labChannelHeight, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.9;
        panScreen.add(txtChannelHeight, gridBagConstraints);

        labVerticalGap.setText("Vertical Gap");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panScreen.add(labVerticalGap, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.9;
        panScreen.add(txtVerticalGap, gridBagConstraints);

        labHorizontalGap.setText("Horizontal Gap");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panScreen.add(labHorizontalGap, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.9;
        panScreen.add(txtHorizontalGap, gridBagConstraints);

        labPanelWidth.setText("Panel Width");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panScreen.add(labPanelWidth, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.9;
        panScreen.add(txtPanelWidth, gridBagConstraints);

        labChannelPanelWidth.setText("Channel Panel Width");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panScreen.add(labChannelPanelWidth, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.9;
        panScreen.add(txtChannelPanelWidth, gridBagConstraints);

        labFont.setText("Font");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panScreen.add(labFont, gridBagConstraints);

        butFont.setText("Choose Font");
        butFont.setMinimumSize(new java.awt.Dimension(88, 26));
        butFont.setPreferredSize(new java.awt.Dimension(88, 26));
        butFont.addActionListener(
            new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    butFontActionPerformed(evt);
                }
            });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.9;
        panScreen.add(butFont, gridBagConstraints);

        labProgrammeMovieColour.setText("Movie Background Colour");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panScreen.add(labProgrammeMovieColour, gridBagConstraints);

        txtProgrammeMovieColour.setEditable(false);
        txtProgrammeMovieColour.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProgrammeMovieColour.addMouseListener(
            new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    txtProgrammeMovieColourMouseClicked(evt);
                }
            });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.9;
        panScreen.add(txtProgrammeMovieColour, gridBagConstraints);

        time12button.setText("2:30 PM");
        timebuttongroup.add(time12button);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        panScreen.add(time12button, gridBagConstraints);

        time24button.setText("14:30");
        timebuttongroup.add(time24button);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        panScreen.add(time24button, gridBagConstraints);

        timeLabel.setText("Time Display:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panScreen.add(timeLabel, gridBagConstraints);

        timeCBLabel.setText("Show time in grid:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panScreen.add(timeCBLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        panScreen.add(timeCheckBox, gridBagConstraints);

        landf.setText("Use Metal Decoration:");
        landf.setToolTipText("Use Java Metal Look and Feel Decoration, "
                + "requires restart.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panScreen.add(landf, gridBagConstraints);

        landfCheckBox.setToolTipText("Use Java Metal Look and Feel Decoration, "
                + "requires restart.");

        landfNoteLabel.setText("(Requires restart)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
        panScreen.add(landfNoteLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        panScreen.add(landfCheckBox, gridBagConstraints);

        scrScreen.setViewportView(panScreen);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.9;
        gridBagConstraints.weighty = 0.9;
        getContentPane().add(scrScreen, gridBagConstraints);

        panButtons.setLayout(new java.awt.GridBagLayout());

        butOK.setText("OK");
        butOK.setMinimumSize(new java.awt.Dimension(88, 26));
        butOK.setPreferredSize(new java.awt.Dimension(88, 26));
        butOK.addActionListener(
            new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    butOKActionPerformed(evt);
                }
            });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        panButtons.add(butOK, gridBagConstraints);

        butCancel.setText("Cancel");
        butCancel.setMinimumSize(new java.awt.Dimension(88, 26));
        butCancel.setPreferredSize(new java.awt.Dimension(88, 26));
        butCancel.addActionListener(
            new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    butCancelActionPerformed(evt);
                }
            });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panButtons.add(butCancel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(panButtons, gridBagConstraints);

        changedFont = false;

		FGPreferences scr = FreeGuide.prefs.screen;

        fontDialog = new FontChooserDialog(owner, "Choose Font", true,
                new Font(
                scr.get("font_name", "Dialog"),
                scr.getInt("font_style", Font.PLAIN),
                scr.getInt("font_size", 12)));

        fontDialog.setSize(new java.awt.Dimension(300, 200));
		
		pack();  // pack comes before the size instructions or they get ignored.
		
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new java.awt.Dimension(420, 450));
        setLocation((screenSize.width - 420) / 2, (screenSize.height - 450) / 2);

		fontDialog.setLocation((screenSize.width - 300) / 2,
                (screenSize.height - 200) / 2);
		
    }



    private Frame getParentFrame() {
	Component p = this;
	while ( (p = p.getParent()) != null && !(p instanceof Frame) );
  	return( (Frame)p );
    }


    //GEN-END:initComponents

    /**
     *  Description of the Method
     *
     *@param  evt  Description of the Parameter
     */
    private void butOKActionPerformed(java.awt.event.ActionEvent evt) {
        //GEN-FIRST:event_butOKActionPerformed
        saveScreen();
        quit();
    }


    //GEN-LAST:event_butOKActionPerformed

    /**
     *  Description of the Method
     *
     *@param  evt  Description of the Parameter
     */
    private void butCancelActionPerformed(java.awt.event.ActionEvent evt) {
        //GEN-FIRST:event_butCancelActionPerformed
        quit();
    }


    //GEN-LAST:event_butCancelActionPerformed

    /**
     *  Description of the Method
     *
     *@param  evt  Description of the Parameter
     */
    private void butFontActionPerformed(java.awt.event.ActionEvent evt) {

        changedFont = true;
        fontDialog.setVisible(true);

    }


    /**
     *  Description of the Method
     *
     *@param  evt  Description of the Parameter
     */
    private void txtProgrammeMovieColourMouseClicked(java.awt.event.MouseEvent evt) {
        //GEN-FIRST:event_txtProgrammeMovieColourMouseClicked
        doColorDialog(txtProgrammeMovieColour);
    }


    //GEN-LAST:event_txtProgrammeMovieColourMouseClicked

    /**
     *  Description of the Method
     *
     *@param  evt  Description of the Parameter
     */
    private void txtProgrammeNormalColourMouseClicked(java.awt.event.MouseEvent evt) {
        //GEN-FIRST:event_txtProgrammeNormalColourMouseClicked
        doColorDialog(txtProgrammeNormalColour);
    }


    //GEN-LAST:event_txtProgrammeNormalColourMouseClicked

    /**
     *  Description of the Method
     *
     *@param  evt  Description of the Parameter
     */
    private void txtProgrammeChosenColourMouseClicked(java.awt.event.MouseEvent evt) {
        //GEN-FIRST:event_txtProgrammeChosenColourMouseClicked
        doColorDialog(txtProgrammeChosenColour);
    }


    //GEN-LAST:event_txtProgrammeChosenColourMouseClicked

    /**
     *  Description of the Method
     *
     *@param  evt  Description of the Parameter
     */
    private void txtChannelColourMouseClicked(java.awt.event.MouseEvent evt) {
        //GEN-FIRST:event_txtChannelColourMouseClicked
        doColorDialog(txtChannelColour);
    }


    //GEN-LAST:event_txtChannelColourMouseClicked

    /**
     *  Description of the Method
     *
     *@param  evt  Description of the Parameter
     */
    private void exitForm(java.awt.event.WindowEvent evt) {
        //GEN-FIRST:event_exitForm
        quit();
    }


    //GEN-LAST:event_exitForm

    /**
     * Save the new preferences. Update the <code>changed</code> flag to indicate if
     * the user made any changes to the preferneces.
     *
     */
    private void saveScreen() {

        boolean changed = false;

        changed = FreeGuide.prefs.screen.updateColor("channel_colour", txtChannelColour.getBackground());
        if (changed) updatedFlag = true;

        changed = FreeGuide.prefs.screen.updateInt("channel_height", Integer.parseInt(txtChannelHeight.getText()));
        if (changed) updatedFlag = true;
        
        changed = FreeGuide.prefs.screen.updateInt("channel_panel_width", Integer.parseInt(txtChannelPanelWidth.getText()));
        if (changed) updatedFlag = true;
        
        changed = FreeGuide.prefs.screen.updateInt("horizontal_gap", Integer.parseInt(txtHorizontalGap.getText()));
        if (changed) updatedFlag = true;
        
        changed = FreeGuide.prefs.screen.updateInt("vertical_gap", Integer.parseInt(txtVerticalGap.getText()));
        if (changed) updatedFlag = true;
        
        changed = FreeGuide.prefs.screen.updateInt("panel_width", Integer.parseInt(txtPanelWidth.getText()));
        if (changed) updatedFlag = true;

        changed = FreeGuide.prefs.screen.updateColor("programme_chosen_colour", txtProgrammeChosenColour.getBackground());
        if (changed) updatedFlag = true;

        changed = FreeGuide.prefs.screen.updateColor("programme_normal_colour", txtProgrammeNormalColour.getBackground());
        if (changed) updatedFlag = true;

        changed = FreeGuide.prefs.screen.updateColor("programme_movie_colour", txtProgrammeMovieColour.getBackground());
        if (changed) updatedFlag = true;

        changed = FreeGuide.prefs.screen.updateBoolean("display_programme_time", timeCheckBox.isSelected());
        if (changed) updatedFlag = true;
        
        changed = FreeGuide.prefs.screen.updateBoolean("display_24hour_time", timebuttongroup.getSelection().equals(time24button.getModel()));
        if (changed) updatedFlag = true;

        changed = FreeGuide.prefs.screen.updateBoolean("use_metal_landf", landfCheckBox.isSelected());
        if (changed) updatedFlag = true;

        if (changedFont) {

            Font f = fontDialog.getSelectedFont();
            
            changed = FreeGuide.prefs.screen.update("font_name", f.getName());
            if (changed) updatedFlag = true;
            
            changed = FreeGuide.prefs.screen.updateInt("font_style", f.getStyle());
            if (changed) updatedFlag = true;

            changed = FreeGuide.prefs.screen.updateInt("font_size", f.getSize());
            if (changed) updatedFlag = true;


        }
    }


    /**
     *  Description of the Method
     *
     *@param  txt  Description of the Parameter
     */
    private void doColorDialog(JTextField txt) {

        Color col = JColorChooser.showDialog(this, "Choose a Colour", txt.getBackground());
        if (col != null) {
            fillTextAreaFromColor(txt, col);
        }
    }


    /**
     *  Description of the Method
     */
    private void initScreen() {

        Color col;

        col = FreeGuide.prefs.screen.getColor("channel_colour", FreeGuide.CHANNEL_COLOUR);
        fillTextAreaFromColor(txtChannelColour, col);

        txtChannelHeight.setText(FreeGuide.prefs.screen.get("channel_height", String.valueOf(FreeGuide.CHANNEL_HEIGHT)));
        txtChannelPanelWidth.setText(FreeGuide.prefs.screen.get("channel_panel_width", String.valueOf(FreeGuide.CHANNEL_PANEL_WIDTH)));
        txtHorizontalGap.setText(FreeGuide.prefs.screen.get("horizontal_gap", String.valueOf(FreeGuide.HORIZONTAL_GAP)));
        txtVerticalGap.setText(FreeGuide.prefs.screen.get("vertical_gap", String.valueOf(FreeGuide.VERTICAL_GAP)));
        txtPanelWidth.setText(FreeGuide.prefs.screen.get("panel_width", String.valueOf(FreeGuide.PANEL_WIDTH)));

        col = FreeGuide.prefs.screen.getColor("programme_movie_colour", FreeGuide.PROGRAMME_MOVIE_COLOUR);
        fillTextAreaFromColor(txtProgrammeMovieColour, col);
        col = FreeGuide.prefs.screen.getColor("programme_chosen_colour", FreeGuide.PROGRAMME_CHOSEN_COLOUR);
        fillTextAreaFromColor(txtProgrammeChosenColour, col);

        col = FreeGuide.prefs.screen.getColor("programme_normal_colour", FreeGuide.PROGRAMME_NORMAL_COLOUR);
        fillTextAreaFromColor(txtProgrammeNormalColour, col);
        boolean progtime = FreeGuide.prefs.screen.getBoolean("display_programme_time", true);
        timeCheckBox.setSelected(progtime);
        boolean time24 = FreeGuide.prefs.screen.getBoolean("display_24hour_time", false);
        if (time24) {
            timebuttongroup.setSelected(time24button.getModel(), true);
        } else {
            timebuttongroup.setSelected(time12button.getModel(), true);
        }

        boolean metal = FreeGuide.prefs.screen.getBoolean("use_metal_landf", false);
        landfCheckBox.setSelected(metal);

    }


    /**
     *  Description of the Method
     *
     *@param  txt  Description of the Parameter
     *@param  col  Description of the Parameter
     */
    private void fillTextAreaFromColor(JTextField txt, Color col) {

        txt.setBackground(col);
        txt.setText("(" + col.getRed() + ", " + col.getGreen() + ", " + col.getBlue() + ")");

    }


    // ------------------------------------------------------------------------
    

    /**
     * Method showDialog calls the default show method but additionally
     * returns a simple flag to indicate whether any of the preferences
     * on the customiser dialog have been updated or not.
     *
     * @returns Returns <code>true</code> if any of the preferences in the
     *                 customiser dialog have been changed, and <code>false</code> otherwise.
     */
    
    public boolean showDialog() {
        show();
        return updatedFlag;
    }


    // ------------------------------------------------------------------------

    /**
     *  Closes the form and goes back to the viewer.
     */
    private void quit() {

        hide();
        dispose();

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField txtProgrammeNormalColour;
    private javax.swing.JTextField txtProgrammeMovieColour;
    private javax.swing.JLabel labChannelHeight;
    private javax.swing.JTextField txtChannelHeight;
    private javax.swing.JLabel labChannelPanelWidth;
    private javax.swing.JTextField txtChannelPanelWidth;
    private javax.swing.JLabel labVerticalGap;
    private javax.swing.JTextField txtPanelWidth;
    private javax.swing.JTextField txtVerticalGap;
    private javax.swing.JScrollPane scrScreen;
    private javax.swing.JPanel panScreen;
    private javax.swing.JPanel panButtons;
    private javax.swing.JLabel labChannelColour;
    private javax.swing.JTextField txtChannelColour;
    private javax.swing.JButton butOK;
    private javax.swing.JButton butCancel;
    private javax.swing.JLabel labPanelWidth;
    private javax.swing.JLabel labProgrammeChosenColour;
    private javax.swing.JLabel labProgrammeMovieColour;
    private javax.swing.JTextField txtProgrammeChosenColour;
    private javax.swing.JLabel labHorizontalGap;
    private javax.swing.JTextField txtHorizontalGap;
    private javax.swing.JLabel labProgrammeNormalColour;
    private javax.swing.JButton butFont;
    private javax.swing.JLabel labFont;
    private javax.swing.JRadioButton time12button;
    private javax.swing.ButtonGroup timebuttongroup;
    private javax.swing.JRadioButton time24button;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel timeCBLabel;
    private javax.swing.JCheckBox timeCheckBox;

    private javax.swing.JLabel landf;
    private javax.swing.JCheckBox landfCheckBox;
    private javax.swing.JLabel landfNoteLabel;
//    Launcher launcher;
    FontChooserDialog fontDialog;
    boolean changedFont;
    


    /**
     * This flag indicated whether any of the parameters on the
     * CustomiserDialog have been updated during this session.
     *
     */
    private boolean updatedFlag = false;
    
    private Frame owner = null;
        

}