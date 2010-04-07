/**
 * Assembler.java
 * 
 * @author cache_money
 * @version 1.0
 */



package GUI;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;

public class assembler implements ActionListener {

	// Set up integer contants used throughout the frame
	private static final int     FRAME_WIDTH  		 = 800;
	private static final int     FRAME_HEIGHT 		 = 600	;
	private static final int     FRAME_X      		 = 100;
	private static final int     FRAME_Y	   		 = 100;
	private static final int     PANEL_WIDTH  		 = 798;
	private static final int     PANEL_HEIGHT       = 500;
	private static final int     TITLE_PANEL_HEIGHT = 25;
	private static final int     ONE				 = 1;
	private static final int[][] LIST_DIMENSIONS	 = { { 6, 26, 362, 237 },
	 												 { 6, 15, 350, 15 },
	 												 { 6, 31, 350, 200 }
														};
	private static final int[]   STAT_DIMENSIONS    = { 371, 26, 150, 200 };
	
	// Title constant string
	private static final String TITLE = "Assembler";
	
	// Set up string constants for the file menu
	private static final String FILE	 = "File";
	private static final String OPEN 	 = "Open .asm File";
	private static final String CREATEMIF = "Create .mif File";
	private static final String SAVE_AS	 = "Save .mif as...";
	private static final String EXIT	 = "Exit";
	
	// set up frame variable
	JFrame mainFrame;
	
	//--- Panels ---
	JPanel mainPanel;
	JPanel titlePanel;
	JPanel assemblyPanel;
	JPanel mifPanel;
	
	//--- Labels ---
	JLabel titleLabel;
	JLabel assemblyLabel;
	JLabel mifLabel;
	
	//--- Text Areas ---
	JTextArea assemblyTextArea;
	JTextArea mifTextArea;
	
	JMenuBar menuBar;
	
	JMenu menuFile;
	
	JMenuItem menuFileOpen;
	JMenuItem menuFileCreate;
	JMenuItem menuFileSaveAs;
	JMenuItem menuFileExit;
	
	public assembler()
	{
		mainFrame = new JFrame(TITLE);
		mainFrame.setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
		
		// Set up panel
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setSize(PANEL_WIDTH, PANEL_HEIGHT);
		mainFrame.getContentPane().add(mainPanel);
		
		// Set up title label
		titleLabel = new JLabel(TITLE, JLabel.CENTER);
		titleLabel.setBounds(ONE, ONE, PANEL_WIDTH, TITLE_PANEL_HEIGHT);
		mainPanel.add(titleLabel);
		
		// Create the menu
		createMenu();
		
		// create other panels
		
		
		
		
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} //end constructor
	
	private void createMenu()
	{
		// create the main menu bar
		menuBar = new JMenuBar();
		
		// create the individual menu
		menuFile = new JMenu(FILE);
		
		// Create the menu items
		menuFileOpen = new JMenuItem(OPEN);
		menuFileCreate = new JMenuItem(CREATEMIF);
		menuFileSaveAs = new JMenuItem(SAVE_AS);
		menuFileExit = new JMenuItem(EXIT);
		
		menuFileOpen.addActionListener(this);
		menuFileCreate.addActionListener(this);
		menuFileSaveAs.addActionListener(this);
		menuFileExit.addActionListener(this);
		
		menuFile.add(menuFileOpen);
		menuFile.add(menuFileCreate);
		menuFile.add(menuFileSaveAs);
		menuFile.add(menuFileExit);
		
		mainFrame.setJMenuBar(menuBar);
	} // end create menu
	
	public void actionPerformed( ActionEvent event )
	{
		
	}
	
	public static void main( String args[] )
	{
		// Create an instance of the test application
		new assembler();
	}
	
}

