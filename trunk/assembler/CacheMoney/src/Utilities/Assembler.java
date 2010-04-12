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
	
	/**
	 * This function parses the assembly file and resolves instructions and symbolic references.
	 */
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
				oReference = getSymbolicReference(sAssemblyLine, bDataSectionHit);
				oReference.setBaseAddress(nAddress);
				if (oReference.getElementCount() > 1) {
					nAddress += oReference.getElementCount() - 1;
				}
				alSymbolicReferences.add(oReference);
			}
			
			nAddress++;
		}		
	}
	
	/**
	 * This function does one pass through the assembly file, parsing assembly instructions.
	 */
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
	
	/**
	 * Use this function to obtain an instruction from a line in the assembly file
	 * @param sLine Line of assembly i.e. <code>add $1, $2, $3</code>
	 * @param nLineAddress Address of the current line
	 * @return <code>IInstruction</code> if the line represents an instruction,
	 * 		   <code>null</code> if the line does not contain an instruction.
	 */
	private IInstruction getInstructionFromLine(String sLine, int nLineAddress) {
		String sInstruction = AssemblyParser.getAssembly(sLine);
		IInstruction oReturnInstruction = InstructionFactory.createInstruction(AssemblyParser.getInstructionNameFromLine(sInstruction));
		
		if (oReturnInstruction != null) {
			setInstructionProperties(sInstruction, oReturnInstruction, nLineAddress);			
		}		
		return oReturnInstruction;
	}
	
	/**
	 * Use this method to specify the properties of an instruction.
	 * @param sInstruction Assembly instruction from line i.e. <code>add $1, $2, $3</code>
	 * @param oInstruction Instruction to set.
	 * @param nCurrentInstructionAddress  Address of current line.
	 */
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
				
				if (findReferenceByName(addressReference) != null) {
					//we are dealing with reference to an address
					regSource = 0;
					immValue = findReferenceByName(addressReference).getBaseAddress();
				} else {
					addressReference = addressReference.replaceAll("\\$", "").trim();
					regSource = Integer.parseInt(addressReference);
					immValue = 0;
				}				
				
			} else if (InstructionFactory.IsBranchInstruction(oInstruction)) {
				regSource = Integer.parseInt(arInstructionProps[1].replaceAll("\\$", "").trim());
				immValue = findReferenceByName(arInstructionProps[2]).getBaseAddress() - nCurrentInstructionAddress;				
				
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
			((IJumpInstruction) oInstruction).setAddress(findReferenceByName(arInstructionProps[0]).getBaseAddress());
		}
		
	}
	/**
	 * Use this method to retrieve a symbolic reference from a line.
	 * @param sLine
	 * @param bIsDataSection
	 * @return
	 */
	private SymbolicReference getSymbolicReference(String sLine, boolean bIsDataSection) {
		String[] arSplit = sLine.split(":");
		String[] arData;
		SymbolicReference oReference = null;	
		AssemblyParser.trimArrayContents(arSplit);	
		if (bIsDataSection) {
			arData = arSplit[1].split(" ");
			AssemblyParser.trimArrayContents(arData);		
			if (arData[0].equalsIgnoreCase(".integer")) {
				oReference = new SymbolicReference(arSplit[0], Integer.parseInt(arData[1]));
			} else if (arData[0].equalsIgnoreCase(".array")) {
				String arStringValues[] = arData[1].split(",");
				AssemblyParser.trimArrayContents(arStringValues);
				int[] arValues = new int[arStringValues.length];
				for (int i=0; i<arStringValues.length; i++) {
					arValues[i] = Integer.parseInt(arStringValues[i]);
				}
				oReference = new SymbolicReference(arSplit[0], arValues);			
			}
		} else {
			oReference = new SymbolicReference(arSplit[0]);
		}		
		return oReference;		
	}
	
	/**
	 * Get a symbolic reference by name.
	 * @param ReferenceName Name of the symbolic reference in assembly.
	 * @return The reference object if found, otherwise null.
	 */
	private SymbolicReference findReferenceByName(String ReferenceName) {
		for (int i=0; i<alSymbolicReferences.size(); i++) {
			if (alSymbolicReferences.get(i).getReferenceName().equalsIgnoreCase(ReferenceName))
				return alSymbolicReferences.get(i);
		}
		return null;
	}
	
	/**
	 * Call this function to retrieve the contents of the memory file.
	 * The lines of the file are returned as a string array.
	 * @return String array containing memory file lines.
	 */
	public String[] getMemoryFileContents() {
		ArrayList<String> alMemoryContents = new ArrayList<String>();
		String[] arContents = null;
		
		printMemoryHeaderToList(alMemoryContents);
		
		for (int i=0; i<alInstructions.size(); i++) {
			IInstruction oInstruction = alInstructions.get(i);
			alMemoryContents.add(Integer.toHexString(oInstruction.getInstructionAddress()).toUpperCase() + " : " + oInstruction.getEncodedInstruction() + ";");
		}
		for (int i=0; i<alSymbolicReferences.size(); i++) {
			SymbolicReference oReference = alSymbolicReferences.get(i);
			int address = oReference.getBaseAddress();
			String[] arEncodings = oReference.getEncodedReference();
			if (arEncodings != null) {
				for (int j=0; j<arEncodings.length; j++) {					
					alMemoryContents.add(Integer.toHexString(address + j).toUpperCase() + " : " + arEncodings[j] + ";");
				}
			}
		}
		printMemoryFooterToList(alMemoryContents);
		
		arContents = new String[alMemoryContents.size()];
		alMemoryContents.toArray(arContents);
		
		return arContents;
	}
	
	/**
	 * Append the memory file header to an array list.
	 * @param alMemoryFile Array list of memory contents.
	 */
	private void printMemoryHeaderToList(ArrayList<String> alMemoryFile) {
		alMemoryFile.add("-- Cache Money MIF File.");
		alMemoryFile.add("WIDTH = 32;");
		alMemoryFile.add("DEPTH = 131072;");
		alMemoryFile.add("ADDRESS_RADIX = HEX;");
		alMemoryFile.add("DATA_RADIX = BIN;");
		alMemoryFile.add("CONTENT BEGIN");
	}
	
	private void printMemoryFooterToList(ArrayList<String> alMemoryFile) {
		alMemoryFile.add("END;");
	}
}
