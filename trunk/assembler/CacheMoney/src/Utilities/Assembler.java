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
	
	
	private ArrayList<SymbolicReference> alSymbolicReferences;
	private ArrayList<String> alFileLines;
	private File assemblyFile;
	
	public Assembler(File assemblyFile) {
		alSymbolicReferences = new ArrayList<SymbolicReference>();
		this.assemblyFile = assemblyFile;
		readAssemblyFile();
	}
	
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
				alSymbolicReferences.add(oReference);
			}
			
			nAddress++;
		}		
	}
	
	
	private void readAssemblyFile() {
		alFileLines = new ArrayList<String>();
		try {
			BufferedReader brFile = new BufferedReader(new FileReader(assemblyFile));
			String sFileLine;
							
			while ((sFileLine = brFile.readLine()) != null) {
				sFileLine = sFileLine.trim();			
				sFileLine = AssemblyParser.stripComments(sFileLine);
				
				if (sFileLine != "")
					alFileLines.add(sFileLine);			
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
