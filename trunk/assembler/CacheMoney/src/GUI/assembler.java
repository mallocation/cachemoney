/**
 * Assembler.java
 * 
 * @author cache_money
 * @version 1.0
 */

package GUI;


import java.io.*;
import javax.swing.*;

import Utilities.Assembler;

import java.awt.*;
import java.awt.event.*;

public class assembler extends JFrame implements ActionListener {

	// Set up integer constants used throughout the frame
	private static final int     FRAME_WIDTH  		 = 1000;
	private static final int     FRAME_HEIGHT 		 = 700	;
	private static final int     PANEL_WIDTH  		 = 790;
	private static final int     TITLE_PANEL_HEIGHT = 25;
	private static final int     ONE				 = 1;
	private static final int FRAME_X_ORIGIN = 10;
    private static final int FRAME_Y_ORIGIN = 10;
		
	// Set up string constants for the file menu
	private static final String FILE	 = "File";
	private static final String OPEN 	 = "Open .asm File";
	private static final String CREATEMIF = "Create .mif File";
	private static final String EXIT	 = "Exit";
	
	// set up frame variable
	JFrame mainFrame;
	
	// Panels
	JPanel mainPanel;
	JPanel titlePanel;
	JPanel assemblyPanel;
	JPanel mifPanel;
	
	// Labels
	JLabel titleLabel;
	JLabel assemblyLabel;
	JLabel mifLabel;
	
	// Text Areas
	JTextArea assemblyTextArea;
	JTextArea mifTextArea;
	
	// JMenu items
	JMenuBar menuBar;
	JMenu menuFile;
	JMenuItem menuFileOpen;
	JMenuItem menuFileCreate;
	JMenuItem menuFileSaveAs;
	JMenuItem menuFileExit;
	
	//Input and Output Files
	File inputFile, outputFile;
	
	String memoryContents [];
	
	public assembler()
	{
		Container contentPane;

        //set the frame properties
        setSize      (FRAME_WIDTH, FRAME_HEIGHT);
        setResizable (false);
        setTitle     ("Assembler");
        setLocation  (FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
        
        // create the menu
        this.createMenu();
        
        contentPane = getContentPane( );
        
        //set the layout
        contentPane.setLayout(new BorderLayout());
        
        titlePanel = new JPanel();
        titlePanel.setBounds(ONE, ONE, PANEL_WIDTH, TITLE_PANEL_HEIGHT);
        titlePanel.setLayout(new BorderLayout());
        
        assemblyLabel = new JLabel("------------- .asm File -------------");
        titlePanel.add(assemblyLabel, BorderLayout.WEST);
        mifLabel = new JLabel("------------- .mif File -------------");
        titlePanel.add(mifLabel, BorderLayout.EAST);
        
        
        contentPane.add(titlePanel, BorderLayout.NORTH);
        
        assemblyTextArea = new JTextArea();
        assemblyTextArea.setColumns(42);
        assemblyTextArea.setEditable(false);
        
        JScrollPane asmScrollText = new JScrollPane(assemblyTextArea);
        asmScrollText.setSize(200, 135);
        asmScrollText.setBorder(BorderFactory.createLineBorder(Color.black));
        contentPane.add(asmScrollText, BorderLayout.WEST);
        
        mifTextArea = new JTextArea();
        mifTextArea.setColumns(42);
        mifTextArea.setEditable(false);
        
        JScrollPane mifScrollText = new JScrollPane(mifTextArea);
        mifScrollText.setSize(200,135);
        mifScrollText.setBorder(BorderFactory.createLineBorder(Color.black));
        contentPane.add(mifScrollText, BorderLayout.EAST);
        
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        
	} //end constructor
	
	
	/**
	 * This method creates the menu bar for the GUI
	 * @return
	 */
	private void createMenu()
	{
		// create the main menu bar
		menuBar = new JMenuBar();
		
		// create the individual menu
		menuFile = new JMenu(FILE);
		
		// Create the menu items
		menuFileOpen = new JMenuItem(OPEN);
		menuFileCreate = new JMenuItem(CREATEMIF);
		menuFileExit = new JMenuItem(EXIT);
		
		menuFileOpen.addActionListener(this);
		menuFileCreate.addActionListener(this);
		menuFileExit.addActionListener(this);
		
		menuFile.add(menuFileOpen);
		menuFile.add(menuFileCreate);
		menuFile.add(menuFileExit);
		
		menuBar.add(menuFile);
		
		this.setJMenuBar(menuBar);
	} // end create menu
	
	
	/**
	 * This method handles the menu actions
	 * @param event
	 * @return
	 */
	public void actionPerformed( ActionEvent event )
	{
		// Make sure the event was done through the menu
		if (event.getSource() instanceof JMenuItem)
		{
			// Set the string the event is handling
			String eventName = ((JMenuItem) event.getSource()).getText();
			if (eventName.equals(OPEN))
			{
				// load the specified asm file.
				loadAsmFile();
				try {
					// What to do with the file, e.g. display it in a TextArea
					assemblyTextArea.read( new FileReader( inputFile.getAbsolutePath() ), null );
		        } catch (IOException ex) {
		          System.out.println("problem accessing file"+inputFile.getAbsolutePath());
		        }
			}
			else if (eventName.equals(CREATEMIF))
			{
				//create the .mif file
				createMifFile();
				
				//output the contents of the newly created .mif file to the mif text area
				try {
					// What to do with the file, e.g. display it in a TextArea
					mifTextArea.read( new FileReader( outputFile.getAbsolutePath() ), null );
		        } catch (IOException ex) {
		          System.out.println("problem accessing file"+inputFile.getAbsolutePath());
		        }
				
			}
			else if (eventName.equals(EXIT))
			{
				// The user wants to quit
				System.exit(0);
			}
		}
	}
	
	/**
	 * Function: loadAsmFile
	 * - this function loads in the .asm file specified by the user
	 * @return
	 */
	private void loadAsmFile()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Choose a .asm file");
		
		int status = fileChooser.showOpenDialog(null);
	    
	    if(status == JFileChooser.APPROVE_OPTION)
	    {
	    	inputFile = fileChooser.getSelectedFile();
	    }
	}
	
	/**
	 * Function: createMifFile()
	 * - this function allows the user to specify an out MIF file
	 * @return
	 */
	private void createMifFile()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify an output .mif file");
		
		int status = fileChooser.showSaveDialog(null);
		
		if (status == JFileChooser.APPROVE_OPTION)
		{
			outputFile = fileChooser.getSelectedFile();
		}
		
		Assembler oAssembler = new Assembler(inputFile);
		
		oAssembler.parseAssemblyFile();
		
		memoryContents =  oAssembler.getMemoryFileContents();
		int length = memoryContents.length;
		
		try{
	    // Create file 
	    FileWriter fstream = new FileWriter(outputFile);
	        BufferedWriter out = new BufferedWriter(fstream);
        for (int i = 0; i < length; i++)
		{
			out.write(memoryContents[i]);
			out.newLine();
		}
	    //Close the output stream
	    out.close();
	    }catch (Exception e){//Catch exception if any
	      System.err.println("Error: " + e.getMessage());
	    }

		
	}

	public static void main( String args[] )
	{
		// Create an instance of the test application
		assembler frame = new assembler();
        frame.setVisible(true);
	}
	
}

