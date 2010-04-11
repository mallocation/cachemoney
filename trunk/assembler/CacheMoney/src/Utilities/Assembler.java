package Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Assembler {
	
	/**
	 * The keyword that identifies the beginning of the data section.
	 */
	private static final String sDataSectionHeader = ".data";
	
	/**
	 * The list of symbolic references contained within the assembly file.
	 */
	private ArrayList<SymbolicReference> alSymbolicReferences;
	
	/**
	 * An array of assembly lines, all trimmed of their comments.
	 */
	private ArrayList<String> alFileLines;
	
	/**
	 * The file containing all assembly language for the program.
	 */
	private File assemblyFile;
	
	/**
	 * Construct a new assembler with a specific assembly file.
	 * This constructor will also parse through the file, removing any comments.
	 * @param assemblyFile
	 */
	public Assembler(File assemblyFile) {
		alSymbolicReferences = new ArrayList<SymbolicReference>();
		this.assemblyFile = assemblyFile;
		readAssemblyFile();
	}
	
	/**
	 * This method reads an assembly file into an array of strings, removing all comments
	 * from the assembly language.
	 */
	private void readAssemblyFile() {
		alFileLines = new ArrayList<String>();
		try {
			BufferedReader brFile = new BufferedReader(new FileReader(assemblyFile));
			String sFileLine;							
			while ((sFileLine = brFile.readLine()) != null) {			
				sFileLine = AssemblyParser.stripComments(sFileLine);				
				if (!sFileLine.equals(""))
					alFileLines.add(sFileLine);			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method does one pass through an assembly file, parsing any and all symbolic address,
	 * including those contained within the '.data' section, representing integers and arrays.
	 */
	public void calculateSymbolicAddresses() {
		int nAddress = 0;
		boolean bDataSectionHit = false;
		SymbolicReference oReference;
		
		String sAssemblyLine;
		
		for (int i=0; i<alFileLines.size(); i++) {
			sAssemblyLine = alFileLines.get(i);
			
			if (sAssemblyLine.equalsIgnoreCase(sDataSectionHeader)) {
				bDataSectionHit = true;
				nAddress--;
			}
			
			if (AssemblyParser.isSymbolicReference(sAssemblyLine)) {
				oReference = AssemblyParser.getSymbolicReference(sAssemblyLine, bDataSectionHit);
				oReference.setAddress(nAddress);
				if (oReference.getElementCount() > 1) {
					nAddress += oReference.getElementCount() - 1;
				}
				alSymbolicReferences.add(oReference);
			}
			
			nAddress++;
		}		
	}
	
	public String getDataSection() {
		String sDataSection = "";
		for (int i=0; i<this.alSymbolicReferences.size(); i++) {
			if (alSymbolicReferences.get(i).getEncodedReference() != "") {
				sDataSection += alSymbolicReferences.get(i).getEncodedReference();
			}
		}
		return sDataSection;
	}
}
