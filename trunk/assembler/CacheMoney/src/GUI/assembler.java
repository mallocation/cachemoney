package GUI;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;

public class assembler extends JFrame implements ActionListener {

	File inputFile;		//input file (.asm)
	File outputFile;	//output file (.mif)
	
	//--- Panels ---
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
	
	public void assemblerGUI()
	{
		Container contentPane;
		
		setTitle( "Assembler" );
		setSize( 515, 375 );
		
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		titlePanel = new JPanel(new BorderLayout());
		titlePanel.add(titleLabel = new JLabel("Assembler"));
		contentPane.add(titlePanel, BorderLayout.NORTH);
		
		assemblyPanel = new JPanel(new BorderLayout());
		assemblyPanel.add(assemblyLabel = new JLabel("Assembly C0d3"));
		contentPane.add(assemblyPanel, BorderLayout.WEST);
		mifPanel = new JPanel(new BorderLayout());
		mifPanel.add(mifLabel = new JLabel("MIF C0d3"));
		contentPane.add(mifPanel, BorderLayout.EAST);
		
		assemblyTextArea = new JTextArea();
		assemblyTextArea.setSize(250, 180);
		assemblyTextArea.setEditable(false);
		JScrollPane assemblyScrollText = new JScrollPane(assemblyTextArea);
		assemblyScrollText.setSize(250,180);
		assemblyScrollText.setBorder(BorderFactory.createLineBorder(Color.black));
		assemblyPanel.add(assemblyScrollText);
		
		getContentPane().add(assemblyScrollText, BorderLayout.WEST);
		
		mifTextArea = new JTextArea();
		mifTextArea.setSize(250, 180);
		mifTextArea.setEditable(false);
		JScrollPane mifScrollText = new JScrollPane(mifTextArea);
		mifScrollText.setSize(250,180);
		mifScrollText.setBorder(BorderFactory.createLineBorder(Color.black));
		mifPanel.add(mifScrollText);
		
		getContentPane().add(mifScrollText, BorderLayout.EAST);
		
		menuBar = new JMenuBar();
		
		setJMenuBar( menuBar );
		
		menuFile = new JMenu( "File" );
		menuFile.setMnemonic( 'F' );
		menuBar.add(menuFile);
		
		menuFileOpen = CreatMenuItem( menuFile, ITEM_PLAIN, "Open .asm File", null, 'O', "Open an assembly file");
		menuFileCreate = CreateMenuItem( menuFile, ITEM_PLAIN, "Create mif File", null, 'C', "Create a mif file from opened assembly file");
		menuFileSaveAs = CreateMenuItem( menuFile, ITEM_PLAIN, "Save mif as...", null, 'S', "Save the generated mif file");
		menuFileExit = CreateMenuItem( menuFile, ITEM_PLAIN, "Exit", null, 'E', "Exit the program");
		
		
		
		
		
		
		assemblyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	}
	
}

