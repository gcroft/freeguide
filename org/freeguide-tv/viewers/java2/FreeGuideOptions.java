/*
 * FreeGuide J2
 *
 * Copyright (c) 2001 by Andy Balaam
 *
 * Released under the GNU General Public License
 * with ABSOLUTELY NO WARRANTY.
 *
 * See the file COPYING for more information.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JTextArea;
import javax.swing.JList;

/**
 * The options screen in FreeGuide with tabs for Download Options,
 * View Options and Advanced Options.
 *
 * @author  Andy Balaam
 * @version 2
 */
public class FreeGuideOptions extends javax.swing.JFrame {

	/** Creates new form FreeGuideOptions */
    public FreeGuideOptions() {
		channels = new DefaultListModel();
		
        initComponents();
		initMyComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
	private void initComponents() {//GEN-BEGIN:initComponents
		grpDLAmount = new javax.swing.ButtonGroup();
		tabPane = new javax.swing.JTabbedPane();
		panDownload = new javax.swing.JPanel();
		labChannels = new javax.swing.JLabel();
		labAmount = new javax.swing.JLabel();
		radDay = new javax.swing.JRadioButton();
		radWeek = new javax.swing.JRadioButton();
		radAll = new javax.swing.JRadioButton();
		jScrollPane3 = new javax.swing.JScrollPane();
		lisChannels = new javax.swing.JList(channels);
		butRefresh = new javax.swing.JButton();
		labNote = new javax.swing.JLabel();
		panView = new javax.swing.JPanel();
		labChannelHeight = new javax.swing.JLabel();
		txtChannelHeight = new javax.swing.JTextField();
		labVerGap = new javax.swing.JLabel();
		txtVerGap = new javax.swing.JTextField();
		labHorGap = new javax.swing.JLabel();
		txtHorGap = new javax.swing.JTextField();
		labPanelWidth = new javax.swing.JLabel();
		txtPanelWidth = new javax.swing.JTextField();
		labPixels = new javax.swing.JLabel();
		panAdvanced = new javax.swing.JPanel();
		labDLCmdLine = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		txaDLCmdLine = new javax.swing.JTextArea();
		jScrollPane2 = new javax.swing.JScrollPane();
		txaChCmdLine = new javax.swing.JTextArea();
		labProgHTML = new javax.swing.JLabel();
		jScrollPane4 = new javax.swing.JScrollPane();
		texProgHTML = new javax.swing.JTextArea();
		labChFile = new javax.swing.JLabel();
		txtChFile = new javax.swing.JTextField();
		labFGHomeDir = new javax.swing.JLabel();
		txtFGHomeDir = new javax.swing.JTextField();
		labLogFile = new javax.swing.JLabel();
		txtLogFile = new javax.swing.JTextField();
		labBrowserCmdLine = new javax.swing.JLabel();
		txtBrowserCmdLine = new javax.swing.JTextField();
		labCSSFile = new javax.swing.JLabel();
		txtCSSFile = new javax.swing.JTextField();
		labMaxFilenameLength = new javax.swing.JLabel();
		txtMaxFilenameLength = new javax.swing.JTextField();
		labChCmdLine = new javax.swing.JLabel();
		panBottom = new javax.swing.JPanel();
		butOK = new javax.swing.JButton();
		butCancel = new javax.swing.JButton();
		
		grpDLAmount.add(radDay);
		grpDLAmount.add(radWeek);
		grpDLAmount.add(radAll);
		
		getContentPane().setLayout(new java.awt.GridBagLayout());
		java.awt.GridBagConstraints gridBagConstraints1;
		
		setTitle("FreeGuide Options");
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});
		
		panDownload.setLayout(new java.awt.GridBagLayout());
		java.awt.GridBagConstraints gridBagConstraints2;
		
		labChannels.setText("Channels to download:");
		gridBagConstraints2 = new java.awt.GridBagConstraints();
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.gridy = 0;
		gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
		panDownload.add(labChannels, gridBagConstraints2);
		
		labAmount.setText("Amount to download:");
		gridBagConstraints2 = new java.awt.GridBagConstraints();
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.gridy = 5;
		gridBagConstraints2.insets = new java.awt.Insets(20, 5, 5, 5);
		panDownload.add(labAmount, gridBagConstraints2);
		
		radDay.setText("Today");
		radDay.setPreferredSize(new java.awt.Dimension(116, 21));
		radDay.setMaximumSize(new java.awt.Dimension(116, 21));
		radDay.setMinimumSize(new java.awt.Dimension(116, 21));
		radDay.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				radDayActionPerformed(evt);
			}
		});
		
		gridBagConstraints2 = new java.awt.GridBagConstraints();
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.gridy = 6;
		gridBagConstraints2.insets = new java.awt.Insets(5, 0, 0, 0);
		panDownload.add(radDay, gridBagConstraints2);
		
		radWeek.setText("This Week");
		radWeek.setPreferredSize(new java.awt.Dimension(116, 21));
		radWeek.setMaximumSize(new java.awt.Dimension(116, 21));
		radWeek.setMinimumSize(new java.awt.Dimension(116, 21));
		radWeek.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				radWeekActionPerformed(evt);
			}
		});
		
		gridBagConstraints2 = new java.awt.GridBagConstraints();
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.gridy = 7;
		gridBagConstraints2.insets = new java.awt.Insets(5, 0, 0, 0);
		panDownload.add(radWeek, gridBagConstraints2);
		
		radAll.setText("All Available");
		radAll.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				radAllActionPerformed(evt);
			}
		});
		
		gridBagConstraints2 = new java.awt.GridBagConstraints();
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.gridy = 8;
		gridBagConstraints2.insets = new java.awt.Insets(5, 0, 5, 0);
		panDownload.add(radAll, gridBagConstraints2);
		
		lisChannels.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				lisChannelsFocusLost(evt);
			}
		});
		
		jScrollPane3.setViewportView(lisChannels);
		
		gridBagConstraints2 = new java.awt.GridBagConstraints();
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.gridy = 1;
		gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints2.weightx = 0.1;
		gridBagConstraints2.weighty = 0.9;
		panDownload.add(jScrollPane3, gridBagConstraints2);
		
		butRefresh.setText("Refresh Channels List");
		butRefresh.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				butRefreshActionPerformed(evt);
			}
		});
		
		gridBagConstraints2 = new java.awt.GridBagConstraints();
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.gridy = 3;
		gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHEAST;
		panDownload.add(butRefresh, gridBagConstraints2);
		
		labNote.setText("(Hold CTRL to select required channels)");
		labNote.setFont(new java.awt.Font("Dialog", 0, 12));
		gridBagConstraints2 = new java.awt.GridBagConstraints();
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.gridy = 2;
		panDownload.add(labNote, gridBagConstraints2);
		
		tabPane.addTab("Download", panDownload);
		
		panView.setLayout(new java.awt.GridBagLayout());
		java.awt.GridBagConstraints gridBagConstraints9;
		
		labChannelHeight.setText("Channel height:");
		gridBagConstraints9 = new java.awt.GridBagConstraints();
		gridBagConstraints9.gridx = 0;
		gridBagConstraints9.gridy = 1;
		gridBagConstraints9.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints9.anchor = java.awt.GridBagConstraints.EAST;
		panView.add(labChannelHeight, gridBagConstraints9);
		
		txtChannelHeight.setPreferredSize(new java.awt.Dimension(10000, 20));
		txtChannelHeight.setMaximumSize(new java.awt.Dimension(10000, 20));
		txtChannelHeight.setMinimumSize(new java.awt.Dimension(200, 20));
		txtChannelHeight.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				txtChannelHeightFocusLost(evt);
			}
		});
		
		gridBagConstraints9 = new java.awt.GridBagConstraints();
		gridBagConstraints9.gridx = 1;
		gridBagConstraints9.gridy = 1;
		gridBagConstraints9.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints9.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints9.weightx = 0.9;
		panView.add(txtChannelHeight, gridBagConstraints9);
		
		labVerGap.setText("Ver. gap between channels:");
		gridBagConstraints9 = new java.awt.GridBagConstraints();
		gridBagConstraints9.gridx = 0;
		gridBagConstraints9.gridy = 2;
		gridBagConstraints9.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints9.anchor = java.awt.GridBagConstraints.EAST;
		panView.add(labVerGap, gridBagConstraints9);
		
		txtVerGap.setPreferredSize(new java.awt.Dimension(10000, 20));
		txtVerGap.setMaximumSize(new java.awt.Dimension(10000, 20));
		txtVerGap.setMinimumSize(new java.awt.Dimension(200, 20));
		txtVerGap.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				txtVerGapFocusLost(evt);
			}
		});
		
		gridBagConstraints9 = new java.awt.GridBagConstraints();
		gridBagConstraints9.gridx = 1;
		gridBagConstraints9.gridy = 2;
		gridBagConstraints9.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints9.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints9.weightx = 0.9;
		panView.add(txtVerGap, gridBagConstraints9);
		
		labHorGap.setText("Hor. gap betweeen programmes:");
		gridBagConstraints9 = new java.awt.GridBagConstraints();
		gridBagConstraints9.gridx = 0;
		gridBagConstraints9.gridy = 3;
		gridBagConstraints9.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints9.anchor = java.awt.GridBagConstraints.EAST;
		panView.add(labHorGap, gridBagConstraints9);
		
		txtHorGap.setPreferredSize(new java.awt.Dimension(10000, 20));
		txtHorGap.setMaximumSize(new java.awt.Dimension(10000, 20));
		txtHorGap.setMinimumSize(new java.awt.Dimension(200, 20));
		txtHorGap.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				txtHorGapFocusLost(evt);
			}
		});
		
		gridBagConstraints9 = new java.awt.GridBagConstraints();
		gridBagConstraints9.gridx = 1;
		gridBagConstraints9.gridy = 3;
		gridBagConstraints9.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints9.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints9.weightx = 0.9;
		panView.add(txtHorGap, gridBagConstraints9);
		
		labPanelWidth.setText("Width of full listings:");
		gridBagConstraints9 = new java.awt.GridBagConstraints();
		gridBagConstraints9.gridx = 0;
		gridBagConstraints9.gridy = 4;
		gridBagConstraints9.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints9.anchor = java.awt.GridBagConstraints.EAST;
		panView.add(labPanelWidth, gridBagConstraints9);
		
		txtPanelWidth.setPreferredSize(new java.awt.Dimension(10000, 20));
		txtPanelWidth.setMaximumSize(new java.awt.Dimension(10000, 20));
		txtPanelWidth.setMinimumSize(new java.awt.Dimension(200, 20));
		txtPanelWidth.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				txtPanelWidthFocusLost(evt);
			}
		});
		
		gridBagConstraints9 = new java.awt.GridBagConstraints();
		gridBagConstraints9.gridx = 1;
		gridBagConstraints9.gridy = 4;
		gridBagConstraints9.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints9.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints9.weightx = 0.9;
		panView.add(txtPanelWidth, gridBagConstraints9);
		
		labPixels.setText("(pixels)");
		gridBagConstraints9 = new java.awt.GridBagConstraints();
		gridBagConstraints9.gridx = 1;
		gridBagConstraints9.gridy = 0;
		panView.add(labPixels, gridBagConstraints9);
		
		tabPane.addTab("View", panView);
		
		panAdvanced.setLayout(new java.awt.GridBagLayout());
		java.awt.GridBagConstraints gridBagConstraints8;
		
		panAdvanced.setPreferredSize(new java.awt.Dimension(100, 20));
		panAdvanced.setMinimumSize(new java.awt.Dimension(100, 20));
		panAdvanced.setMaximumSize(new java.awt.Dimension(100, 20));
		labDLCmdLine.setText("Downloading command line:");
		gridBagConstraints8 = new java.awt.GridBagConstraints();
		gridBagConstraints8.gridx = 0;
		gridBagConstraints8.gridy = 0;
		gridBagConstraints8.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints8.anchor = java.awt.GridBagConstraints.NORTHEAST;
		panAdvanced.add(labDLCmdLine, gridBagConstraints8);
		
		txaDLCmdLine.setRows(3);
		txaDLCmdLine.setPreferredSize(new java.awt.Dimension(5000, 39));
		txaDLCmdLine.setMaximumSize(new java.awt.Dimension(5000, 200));
		txaDLCmdLine.setMinimumSize(new java.awt.Dimension(5000, 200));
		txaDLCmdLine.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				txaDLCmdLineFocusLost(evt);
			}
		});
		
		jScrollPane1.setViewportView(txaDLCmdLine);
		
		gridBagConstraints8 = new java.awt.GridBagConstraints();
		gridBagConstraints8.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints8.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints8.weightx = 0.9;
		gridBagConstraints8.weighty = 0.1;
		panAdvanced.add(jScrollPane1, gridBagConstraints8);
		
		txaChCmdLine.setRows(3);
		txaChCmdLine.setPreferredSize(new java.awt.Dimension(5000, 39));
		txaChCmdLine.setMaximumSize(new java.awt.Dimension(5000, 200));
		txaChCmdLine.setMinimumSize(new java.awt.Dimension(5000, 200));
		txaChCmdLine.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				txaChCmdLineFocusLost(evt);
			}
		});
		
		jScrollPane2.setViewportView(txaChCmdLine);
		
		gridBagConstraints8 = new java.awt.GridBagConstraints();
		gridBagConstraints8.gridx = 1;
		gridBagConstraints8.gridy = 1;
		gridBagConstraints8.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints8.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints8.weightx = 0.9;
		gridBagConstraints8.weighty = 0.1;
		panAdvanced.add(jScrollPane2, gridBagConstraints8);
		
		labProgHTML.setText("Programme HTML:");
		gridBagConstraints8 = new java.awt.GridBagConstraints();
		gridBagConstraints8.gridx = 0;
		gridBagConstraints8.gridy = 2;
		gridBagConstraints8.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints8.anchor = java.awt.GridBagConstraints.NORTHEAST;
		panAdvanced.add(labProgHTML, gridBagConstraints8);
		
		texProgHTML.setRows(3);
		texProgHTML.setPreferredSize(new java.awt.Dimension(5000, 39));
		texProgHTML.setMaximumSize(new java.awt.Dimension(5000, 200));
		texProgHTML.setMinimumSize(new java.awt.Dimension(5000, 200));
		texProgHTML.setEnabled(false);
		jScrollPane4.setViewportView(texProgHTML);
		
		gridBagConstraints8 = new java.awt.GridBagConstraints();
		gridBagConstraints8.gridx = 1;
		gridBagConstraints8.gridy = 2;
		gridBagConstraints8.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints8.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints8.weightx = 0.9;
		gridBagConstraints8.weighty = 0.1;
		panAdvanced.add(jScrollPane4, gridBagConstraints8);
		
		labChFile.setText("Channels file name:");
		gridBagConstraints8 = new java.awt.GridBagConstraints();
		gridBagConstraints8.gridx = 0;
		gridBagConstraints8.gridy = 3;
		gridBagConstraints8.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints8.anchor = java.awt.GridBagConstraints.EAST;
		panAdvanced.add(labChFile, gridBagConstraints8);
		
		txtChFile.setPreferredSize(new java.awt.Dimension(10000, 20));
		txtChFile.setMaximumSize(new java.awt.Dimension(10000, 20));
		txtChFile.setMinimumSize(new java.awt.Dimension(200, 20));
		txtChFile.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				txtChFileFocusLost(evt);
			}
		});
		
		gridBagConstraints8 = new java.awt.GridBagConstraints();
		gridBagConstraints8.gridx = 1;
		gridBagConstraints8.gridy = 3;
		gridBagConstraints8.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints8.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints8.weightx = 0.9;
		panAdvanced.add(txtChFile, gridBagConstraints8);
		
		labFGHomeDir.setText("FreeGuide home directory:");
		gridBagConstraints8 = new java.awt.GridBagConstraints();
		gridBagConstraints8.gridx = 0;
		gridBagConstraints8.gridy = 4;
		gridBagConstraints8.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints8.anchor = java.awt.GridBagConstraints.EAST;
		panAdvanced.add(labFGHomeDir, gridBagConstraints8);
		
		txtFGHomeDir.setPreferredSize(new java.awt.Dimension(10000, 20));
		txtFGHomeDir.setMaximumSize(new java.awt.Dimension(10000, 20));
		txtFGHomeDir.setMinimumSize(new java.awt.Dimension(200, 20));
		txtFGHomeDir.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				txtFGHomeDirFocusLost(evt);
			}
		});
		
		gridBagConstraints8 = new java.awt.GridBagConstraints();
		gridBagConstraints8.gridx = 1;
		gridBagConstraints8.gridy = 4;
		gridBagConstraints8.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints8.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints8.weightx = 0.9;
		panAdvanced.add(txtFGHomeDir, gridBagConstraints8);
		
		labLogFile.setText("Log file:");
		gridBagConstraints8 = new java.awt.GridBagConstraints();
		gridBagConstraints8.gridx = 0;
		gridBagConstraints8.gridy = 5;
		gridBagConstraints8.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints8.anchor = java.awt.GridBagConstraints.EAST;
		panAdvanced.add(labLogFile, gridBagConstraints8);
		
		txtLogFile.setPreferredSize(new java.awt.Dimension(10000, 20));
		txtLogFile.setMaximumSize(new java.awt.Dimension(10000, 20));
		txtLogFile.setMinimumSize(new java.awt.Dimension(200, 20));
		txtLogFile.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				txtLogFileFocusLost(evt);
			}
		});
		
		gridBagConstraints8 = new java.awt.GridBagConstraints();
		gridBagConstraints8.gridx = 1;
		gridBagConstraints8.gridy = 5;
		gridBagConstraints8.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints8.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints8.weightx = 0.9;
		panAdvanced.add(txtLogFile, gridBagConstraints8);
		
		labBrowserCmdLine.setText("Browser command:");
		gridBagConstraints8 = new java.awt.GridBagConstraints();
		gridBagConstraints8.gridx = 0;
		gridBagConstraints8.gridy = 6;
		gridBagConstraints8.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints8.anchor = java.awt.GridBagConstraints.EAST;
		panAdvanced.add(labBrowserCmdLine, gridBagConstraints8);
		
		txtBrowserCmdLine.setPreferredSize(new java.awt.Dimension(10000, 20));
		txtBrowserCmdLine.setMaximumSize(new java.awt.Dimension(10000, 20));
		txtBrowserCmdLine.setMinimumSize(new java.awt.Dimension(200, 20));
		txtBrowserCmdLine.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				txtBrowserCmdLineFocusLost(evt);
			}
		});
		
		gridBagConstraints8 = new java.awt.GridBagConstraints();
		gridBagConstraints8.gridx = 1;
		gridBagConstraints8.gridy = 6;
		gridBagConstraints8.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints8.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints8.weightx = 0.9;
		panAdvanced.add(txtBrowserCmdLine, gridBagConstraints8);
		
		labCSSFile.setText("CSS File:");
		gridBagConstraints8 = new java.awt.GridBagConstraints();
		gridBagConstraints8.gridx = 0;
		gridBagConstraints8.gridy = 7;
		gridBagConstraints8.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints8.anchor = java.awt.GridBagConstraints.EAST;
		panAdvanced.add(labCSSFile, gridBagConstraints8);
		
		txtCSSFile.setPreferredSize(new java.awt.Dimension(10000, 20));
		txtCSSFile.setMaximumSize(new java.awt.Dimension(10000, 20));
		txtCSSFile.setMinimumSize(new java.awt.Dimension(200, 20));
		txtCSSFile.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				txtCSSFileFocusLost(evt);
			}
		});
		
		gridBagConstraints8 = new java.awt.GridBagConstraints();
		gridBagConstraints8.gridx = 1;
		gridBagConstraints8.gridy = 7;
		gridBagConstraints8.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints8.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints8.weightx = 0.9;
		panAdvanced.add(txtCSSFile, gridBagConstraints8);
		
		labMaxFilenameLength.setText("Max. filename length:");
		gridBagConstraints8 = new java.awt.GridBagConstraints();
		gridBagConstraints8.gridx = 0;
		gridBagConstraints8.gridy = 8;
		gridBagConstraints8.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints8.anchor = java.awt.GridBagConstraints.EAST;
		panAdvanced.add(labMaxFilenameLength, gridBagConstraints8);
		
		txtMaxFilenameLength.setPreferredSize(new java.awt.Dimension(10000, 20));
		txtMaxFilenameLength.setMaximumSize(new java.awt.Dimension(10000, 20));
		txtMaxFilenameLength.setMinimumSize(new java.awt.Dimension(200, 20));
		txtMaxFilenameLength.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				txtMaxFilenameLengthFocusLost(evt);
			}
		});
		
		gridBagConstraints8 = new java.awt.GridBagConstraints();
		gridBagConstraints8.gridx = 1;
		gridBagConstraints8.gridy = 8;
		gridBagConstraints8.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints8.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints8.weightx = 0.9;
		panAdvanced.add(txtMaxFilenameLength, gridBagConstraints8);
		
		labChCmdLine.setText("Channels command line:");
		gridBagConstraints8 = new java.awt.GridBagConstraints();
		gridBagConstraints8.gridx = 0;
		gridBagConstraints8.gridy = 1;
		gridBagConstraints8.insets = new java.awt.Insets(5, 5, 5, 5);
		gridBagConstraints8.anchor = java.awt.GridBagConstraints.NORTHEAST;
		panAdvanced.add(labChCmdLine, gridBagConstraints8);
		
		tabPane.addTab("Advanced", panAdvanced);
		
		gridBagConstraints1 = new java.awt.GridBagConstraints();
		gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints1.weightx = 0.9;
		gridBagConstraints1.weighty = 0.9;
		getContentPane().add(tabPane, gridBagConstraints1);
		
		butOK.setText("OK");
		butOK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				butOKActionPerformed(evt);
			}
		});
		
		panBottom.add(butOK);
		
		butCancel.setText("Cancel");
		butCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				butCancelActionPerformed(evt);
			}
		});
		
		panBottom.add(butCancel);
		
		gridBagConstraints1 = new java.awt.GridBagConstraints();
		gridBagConstraints1.gridx = 0;
		gridBagConstraints1.gridy = 1;
		gridBagConstraints1.anchor = java.awt.GridBagConstraints.SOUTHEAST;
		getContentPane().add(panBottom, gridBagConstraints1);
		
		pack();
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setSize(new java.awt.Dimension(450, 400));
		setLocation((screenSize.width-450)/2,(screenSize.height-400)/2);
	}//GEN-END:initComponents

	private void txtPanelWidthFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPanelWidthFocusLost
		
		FreeGuide.config.setValue("panelWidth", String.valueOf(Integer.parseInt(txtPanelWidth.getText())));
		
	}//GEN-LAST:event_txtPanelWidthFocusLost

	private void txtHorGapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHorGapFocusLost
		
		FreeGuide.config.setValue("horizontalGap", String.valueOf(Integer.parseInt(txtHorGap.getText())));
		
	}//GEN-LAST:event_txtHorGapFocusLost

	private void txtVerGapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtVerGapFocusLost
		
		FreeGuide.config.setValue("verticalGap", String.valueOf(Integer.parseInt(txtVerGap.getText())));
		
	}//GEN-LAST:event_txtVerGapFocusLost

	private void txtChannelHeightFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChannelHeightFocusLost
		
		FreeGuide.config.setValue("channelHeight", String.valueOf(Integer.parseInt(txtChannelHeight.getText())));
		
	}//GEN-LAST:event_txtChannelHeightFocusLost

	private void txtMaxFilenameLengthFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaxFilenameLengthFocusLost
		
		FreeGuide.config.setValue("maxFilenameLength", String.valueOf(Integer.parseInt(txtMaxFilenameLength.getText())));
		
	}//GEN-LAST:event_txtMaxFilenameLengthFocusLost

	private void txtCSSFileFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCSSFileFocusLost

		FreeGuide.config.setValue("cssFile", txtCSSFile.getText());
		
	}//GEN-LAST:event_txtCSSFileFocusLost

	private void txtBrowserCmdLineFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBrowserCmdLineFocusLost
		
		FreeGuide.config.setValue("browserCommandLine", txtBrowserCmdLine.getText());
		
	}//GEN-LAST:event_txtBrowserCmdLineFocusLost

	private void radDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDayActionPerformed
		
		FreeGuide.config.setValue("downloadAmount", "Day");
		
	}//GEN-LAST:event_radDayActionPerformed

	private void radAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radAllActionPerformed
		
		FreeGuide.config.setValue("downloadAmount", "All");
		
	}//GEN-LAST:event_radAllActionPerformed

	private void radWeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radWeekActionPerformed
		
		FreeGuide.config.setValue("downloadAmount", "Week");
		
	}//GEN-LAST:event_radWeekActionPerformed

	private void txtLogFileFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLogFileFocusLost
		
		FreeGuide.config.setValue("logFile", txtLogFile.getText());
		
	}//GEN-LAST:event_txtLogFileFocusLost

	private void txtFGHomeDirFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFGHomeDirFocusLost
		
		FreeGuide.config.setValue("freeguideDir", txtFGHomeDir.getText());
		
	}//GEN-LAST:event_txtFGHomeDirFocusLost

	private void txtChFileFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChFileFocusLost
		
		FreeGuide.config.setValue("channelsFile", txtChFile.getText());
		
	}//GEN-LAST:event_txtChFileFocusLost

	private void txaChCmdLineFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txaChCmdLineFocusLost
		
		FreeGuide.config.setListValue("channelsCommandLine", getVectorFromJTextArea(txaChCmdLine));
		
	}//GEN-LAST:event_txaChCmdLineFocusLost

	private void txaDLCmdLineFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txaDLCmdLineFocusLost
		
		FreeGuide.config.setListValue("downloadCommandLine", getVectorFromJTextArea(txaDLCmdLine));
		
	}//GEN-LAST:event_txaDLCmdLineFocusLost

	private void lisChannelsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lisChannelsFocusLost
		
		FreeGuide.config.setListValue("channels", getVectorFromJList(lisChannels));
		
	}//GEN-LAST:event_lisChannelsFocusLost

	private void butRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butRefreshActionPerformed

		downloadChannels();
		
	}//GEN-LAST:event_butRefreshActionPerformed

	private void butCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butCancelActionPerformed
		
		if(FreeGuide.config.isChanged()) {
			
			FreeGuide.config.load();
			
		}
		
		quit();
		
	}//GEN-LAST:event_butCancelActionPerformed

	private void butOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butOKActionPerformed
		
		if(FreeGuide.config.isChanged()) {
			
			FreeGuide.config.save();
			
		}
		
		quit();
		
	}//GEN-LAST:event_butOKActionPerformed

	//------------------------------------------------------------------------
	
	/**
	 * Gets the values from the config file and places them in the controls
	 * on the screen.
	 */
	private void initMyComponents() {
		
		showAdvanced();
		
		showView();
		
		showDownload();
		
	}
	
	/**
	 * Gets the values for the Advanced tab from the config file.
	 */
	private void showAdvanced() {
		
		// Get the system line break character
		String lineBreak = System.getProperty("line.separator");
		
		// Do the download command line
		Vector tmp = FreeGuide.config.getListValue("downloadCommandLine");
		
		if(tmp!=null) {
		
			for(int i=0;i<tmp.size();i++) {
				txaDLCmdLine.append((String)tmp.get(i)+lineBreak);
			}
			
		}
		
		// Do the channels command line
		tmp = FreeGuide.config.getListValue("channelsCommandLine");
		
		if(tmp!=null) {
		
			for(int i=0;i<tmp.size();i++) {
				txaChCmdLine.append((String)tmp.get(i)+lineBreak);
			}
			
		}
		
		// Do the channels file, FreeGuide home directory and log boxes, etc
		txtChFile.setText(FreeGuide.config.getValue("channelsFile"));
		txtFGHomeDir.setText(FreeGuide.config.getValue("freeguideDir"));
		txtLogFile.setText(FreeGuide.config.getValue("logFile"));
		txtBrowserCmdLine.setText(FreeGuide.config.getValue("browserCommandLine"));
		txtCSSFile.setText(FreeGuide.config.getValue("cssFile"));
		txtMaxFilenameLength.setText(FreeGuide.config.getValue("maxFilenameLength"));
		
	}//showAdvanced
	
	/**
	 * Gets the values for the View tab from the config file.
	 */
	private void showView() {
		
		txtChannelHeight.setText(FreeGuide.config.getValue("channelHeight"));
		txtVerGap.setText(FreeGuide.config.getValue("verticalGap"));
		txtHorGap.setText(FreeGuide.config.getValue("horizontalGap"));
		txtPanelWidth.setText(FreeGuide.config.getValue("panelWidth"));
		
	}//showView
	
	/**
	 * Gets the values for the Download tab from the config file.
	 */
	private void showDownload() {
		
		// Do the channels box
		String strChannelsFile = FreeGuide.config.getValue("channelsFile");
		
		if(strChannelsFile!=null) {
			File channelsFile = new File(strChannelsFile);
				
			Vector tmpChannels = new Vector();
		
			try {
		
				BufferedReader buffy = new BufferedReader(new FileReader(channelsFile));

				String line = buffy.readLine();

				while(line!=null) {
				
					tmpChannels.addElement(line.trim());
				
					line = buffy.readLine();
				
					if(line==null) {
						break;
					}
				
					line = buffy.readLine();
				
				}
			
				buffy.close();

			} catch(IOException e) {
			
				e.printStackTrace();
			
			}

			Collections.sort(tmpChannels);
		
			for(int i=0;i<tmpChannels.size();i++) {
				channels.addElement(tmpChannels.get(i));
			}
		
			Vector tmpSelChans = FreeGuide.config.getListValue("channels");
		
			if(tmpSelChans!=null) {
			
				int[] tmpSelInts = new int[tmpSelChans.size()];
		
				for(int i=0;i<tmpSelInts.length;i++) {
			
					tmpSelInts[i] = tmpChannels.indexOf(tmpSelChans.get(i));
			
				}//for
		
				lisChannels.setSelectedIndices(tmpSelInts);
				
			}
			
		}//if
		
		//---------------
		
		// Do the download amount radio buttons
		String tmpDLAmt = FreeGuide.config.getValue("downloadAmount");
		
		if(tmpDLAmt==null || tmpDLAmt.equals("All")) {
			grpDLAmount.setSelected(radAll.getModel(), true);
		} else if(tmpDLAmt.equals("Week")) {
			grpDLAmount.setSelected(radWeek.getModel(), true);
		} else {
			grpDLAmount.setSelected(radDay.getModel(), true);
		}

	}//showDownload

	/**
	 * Download the list of channels from the web.
	 */
	private void downloadChannels() {
		
		// Get the name of the channels file
		String channelsFile = FreeGuide.config.getValue("channelsFile");
		
		// Get the commands to download the channels file
		Vector channelCmds = FreeGuide.config.getListValue("channelsCommandLine");
		
		// Execute the commands to download channels list
		for(int i=0;i<channelCmds.size();i++) {
		
			FreeGuide.execExternal(((String)channelCmds.get(i))+" "+channelsFile);
			
		}
		
	}
	
	/** 
	 * Puts each line of a JTestArea into a vector.
	 *
	 * @param txtArea the 
	 * @return        the Vector composed of individual Strings for each 
	 *		          line of txtArea
	 */
	private Vector getVectorFromJTextArea(JTextArea txtArea) {
		
		// Get the system line break character
		String lineBreak = System.getProperty("line.separator");
		
		// Get the text out of the text box
		String txt = txtArea.getText();
		
		// Initialise the vector we'll return
		Vector ans = new Vector();
		
		// Find the first line break
		int i = txt.indexOf(lineBreak);
		
		// Loop until no more line breaks were found
		while(i>-1) {
			
			// Add the first line in the string to the vector
			ans.add(txt.substring(0, i));
			
			// Cut this line out of the vector
			txt = txt.substring(i+lineBreak.length());
			
			// Find the next line break
			i = txt.indexOf(lineBreak);
			
		}//while
		
		ans.add(txt);
		
		return ans;
		
	}//getVectorFromJTextArea
	
	/** 
	 * Puts each line of a JList into a vector.
	 *
	 * @param list the list to process
	 * @return     the Vector composed of individual Strings for each 
	 *		       line of list
	 */
	
	private Vector getVectorFromJList(JList list) {
		
		// Initialise the vector we'll return
		Vector ans = new Vector();
		
		// Get the list values
		Object[] selectedItems = list.getSelectedValues();
		
		for(int i=0;i<selectedItems.length;i++) {
			
			ans.add(selectedItems[i]);
			
		}
		
		return ans;
		
	}//getVectorFromJList
	
    /** 
	 * Calls the quit method to exit.
	 */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        
		quit();
		
    }//GEN-LAST:event_exitForm

	/** 
	 * Closes the form and goes back to the viewer.
	 */
	private void quit() {
		
		hide();
		new FreeGuideViewer().show();
		dispose();
		
	}
	
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.ButtonGroup grpDLAmount;
	private javax.swing.JTabbedPane tabPane;
	private javax.swing.JPanel panDownload;
	private javax.swing.JLabel labChannels;
	private javax.swing.JLabel labAmount;
	private javax.swing.JRadioButton radDay;
	private javax.swing.JRadioButton radWeek;
	private javax.swing.JRadioButton radAll;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JList lisChannels;
	private javax.swing.JButton butRefresh;
	private javax.swing.JLabel labNote;
	private javax.swing.JPanel panView;
	private javax.swing.JLabel labChannelHeight;
	private javax.swing.JTextField txtChannelHeight;
	private javax.swing.JLabel labVerGap;
	private javax.swing.JTextField txtVerGap;
	private javax.swing.JLabel labHorGap;
	private javax.swing.JTextField txtHorGap;
	private javax.swing.JLabel labPanelWidth;
	private javax.swing.JTextField txtPanelWidth;
	private javax.swing.JLabel labPixels;
	private javax.swing.JPanel panAdvanced;
	private javax.swing.JLabel labDLCmdLine;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea txaDLCmdLine;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextArea txaChCmdLine;
	private javax.swing.JLabel labProgHTML;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JTextArea texProgHTML;
	private javax.swing.JLabel labChFile;
	private javax.swing.JTextField txtChFile;
	private javax.swing.JLabel labFGHomeDir;
	private javax.swing.JTextField txtFGHomeDir;
	private javax.swing.JLabel labLogFile;
	private javax.swing.JTextField txtLogFile;
	private javax.swing.JLabel labBrowserCmdLine;
	private javax.swing.JTextField txtBrowserCmdLine;
	private javax.swing.JLabel labCSSFile;
	private javax.swing.JTextField txtCSSFile;
	private javax.swing.JLabel labMaxFilenameLength;
	private javax.swing.JTextField txtMaxFilenameLength;
	private javax.swing.JLabel labChCmdLine;
	private javax.swing.JPanel panBottom;
	private javax.swing.JButton butOK;
	private javax.swing.JButton butCancel;
	// End of variables declaration//GEN-END:variables

	DefaultListModel channels;
		// The ListModel holding the names of channels in the listbox
	
}
