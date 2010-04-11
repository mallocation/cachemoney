package Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import Interfaces.IArithmeticInstruction;
import Interfaces.IImmediateInstruction;
import Interfaces.IInstruction;
import Interfaces.IJumpInstruction;

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
	 * The list of instructions contained within the assembly file.
	 */
	private ArrayList<IInstruction> alInstructions;
	
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
		alInstructions = new ArrayList<IInstruction>();
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
	
	public void parseAssemblyFile() {
		alSymbolicReferences = new ArrayList<SymbolicReference>();
		alInstructions = new ArrayList<IInstruction>();
		this.calculateSymbolicAddresses();
		this.parseInstructions();
	}
	
	/**
	 * This method does one pass through an assembly file, parsing any and all symbolic address,
	 * including those contained within the '.data' section, representing integers and arrays.
	 */
	private void calculateSymbolicAddresses() {
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
	
	private void parseInstructions() {
		int i = 0;
		int nAddress = 0;		
		while (i < alFileLines.size()) {
			String sLine = alFileLines.get(i);			
			if (!(sLine == sDataSectionHeader)) {
				IInstruction oLineInstruction = getInstructionFromLine(sLine, nAddress);
				if (oLineInstruction != null) {
					oLineInstruction.setInstructionAddress(nAddress);
					alInstructions.add(oLineInstruction);					
					nAddress++;
				}
			} else {
				i = alFileLines.size();
			}			
			i++;
		}		
	}
	
	private IInstruction getInstructionFromLine(String sLine, int nLineAddress) {
		String sInstruction = AssemblyParser.getAssembly(sLine);
		IInstruction oReturnInstruction = InstructionFactory.createInstruction(AssemblyParser.getInstructionNameFromLine(sInstruction));
		
		if (oReturnInstruction != null) {
			setInstructionProperties(sInstruction, oReturnInstruction, nLineAddress);			
		}		
		return oReturnInstruction;
	}
	
	private void setInstructionProperties(String sInstruction, IInstruction oInstruction, int nCurrentInstructionAddress) {
		String[] arInstructionProps = AssemblyParser.getInstructionPropertiesFromLine(sInstruction);
		if (arInstructionProps == null)
			return;
		if (oInstruction instanceof IArithmeticInstruction) {
			int regSource1, regSource2, regDest;
			regSource1 = Integer.parseInt(arInstructionProps[1].replaceAll("\\$", "").trim());
			regSource2 = Integer.parseInt(arInstructionProps[2].replaceAll("\\$", "").trim());
			regDest = Integer.parseInt(arInstructionProps[0].replaceAll("\\$", "").trim());
			
			((IArithmeticInstruction) oInstruction).setSourceRegister1(regSource1);
			((IArithmeticInstruction) oInstruction).setSourceRegister2(regSource2);
			((IArithmeticInstruction) oInstruction).setDestRegister(regDest);
		} else if (oInstruction instanceof IImmediateInstruction) {
			int regSource, regDest, immValue;
			regDest = Integer.parseInt(arInstructionProps[0].replaceAll("\\$", "").trim());
			
			if (InstructionFactory.InstructionAccessesMemory(oInstruction)) {
				String addressReference = arInstructionProps[1].replaceAll(".*[(]+", "").replaceAll("[)]+.*", "").trim();
				
				if (getSymbolicReference(addressReference) != null) {
					//we are dealing with reference to an address
					regSource = 0;
					immValue = getSymbolicReference(addressReference).getAddress();
				} else {
					addressReference = addressReference.replaceAll("\\$", "").trim();
					regSource = Integer.parseInt(addressReference);
					immValue = 0;
				}				
				
			} else if (InstructionFactory.IsBranchInstruction(oInstruction)) {
				regSource = Integer.parseInt(arInstructionProps[1].replaceAll("\\$", "").trim());
				immValue = getSymbolicReference(arInstructionProps[2]).getAddress() - nCurrentInstructionAddress;				
				
				((IImmediateInstruction) oInstruction).setSourceRegister(regSource);
				((IImmediateInstruction) oInstruction).setDestRegister(regDest);
				((IImmediateInstruction) oInstruction).setImmediateValue(immValue);				
			} else {
				//immediate arithmetic instruction....
				regSource = Integer.parseInt(arInstructionProps[1].replaceAll("\\$", "").trim());
				immValue = Integer.parseInt(arInstructionProps[2]);
			}
			((IImmediateInstruction) oInstruction).setSourceRegister(regSource);
			((IImmediateInstruction) oInstruction).setImmediateValue(immValue);
			((IImmediateInstruction) oInstruction).setDestRegister(regDest);			
		} else if (oInstruction instanceof IJumpInstruction) {
			((IJumpInstruction) oInstruction).setAddress(getSymbolicReference(arInstructionProps[0]).getAddress());
		}
		
	}
	
	public String[] getMemoryFileContents() {
		ArrayList<String> alMemoryContents = new ArrayList<String>();
		String[] arContents = null;
		for (int i=0; i<alInstructions.size(); i++) {
			IInstruction oInstruction = alInstructions.get(i);
			alMemoryContents.add(Integer.toHexString(oInstruction.getInstructionAddress()) + " : " + oInstruction.getEncodedInstruction());
		}
		for (int i=0; i<alSymbolicReferences.size(); i++) {
			SymbolicReference oReference = alSymbolicReferences.get(i);
			int address = oReference.getAddress();
			String[] arEncodings = oReference.getEncodedReference();
			if (arEncodings != null) {
				for (int j=0; j<arEncodings.length; j++) {					
					alMemoryContents.add(Integer.toHexString(address + j) + " : " + arEncodings[j]);
				}
			}
		}
		arContents = new String[alMemoryContents.size()];
		alMemoryContents.toArray(arContents);
		
		return arContents;
	}
	
	private SymbolicReference getSymbolicReference(String RefName) {
		for (int i=0; i<alSymbolicReferences.size(); i++) {
			if (alSymbolicReferences.get(i).getReferenceName().equalsIgnoreCase(RefName)) {
				return alSymbolicReferences.get(i);
			}
		}
		return null;
	}
	
}
