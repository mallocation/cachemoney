package Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class Assembler {
	
	private ArrayList<SymbolicReference> alSymbolicReferences;
	private ArrayList<String> alFileLines;
	private File assemblyFile;
	
	Assembler(File assemblyFile) {
		alSymbolicReferences = new ArrayList<SymbolicReference>();
		this.assemblyFile = assemblyFile;
		
		 
		try {
			readAssemblyFileLines();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private void calculateSymbolicAddresses() {
		int nAddress = 0;
		String sAssemblyLine, sReference;
		
		for (int i=0; i<alFileLines.size(); i++) {
			sAssemblyLine = alFileLines.get(i);
			
			sReference = AssemblyParser.getSymbolicReference(sAssemblyLine);
			
			if (sReference != "") {
				//There is a symbolic reference here!
				alSymbolicReferences.add(new SymbolicReference(sReference, nAddress));
			}
			nAddress += 1;
		}		
	}
	
	
	private void readAssemblyFileLines() throws IOException, FileNotFoundException {
		alFileLines = new ArrayList<String>();
		BufferedReader brFile = new BufferedReader(new FileReader(assemblyFile));
		String sFileLine;
						
		while ((sFileLine = brFile.readLine()) != null) {
			sFileLine = sFileLine.trim();			
			sFileLine = AssemblyParser.stripComments(sFileLine);
			
			if (sFileLine != "")
				alFileLines.add(sFileLine);			
		}
	}
	

}
