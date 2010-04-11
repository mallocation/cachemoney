package Instructions;
import java.util.ArrayList;

import Interfaces.IArithmeticInstruction;
import Interfaces.IImmediateInstruction;
import Interfaces.IInstruction;
import Interfaces.IInstructionConstants;
import Interfaces.IJumpInstruction;

/**
 * This class will be used to generate instructions from the assembly code.
 * @author cjohnson
 *
 */
public class InstructionFactory implements IInstructionConstants {

	public static IInstruction createInstruction(String sInstruction, String sInstructionContents) {
		IInstruction oInstruction = getInstructionByName(sInstruction);
		String[] arInstructionContents = parseInstructionContents(sInstructionContents);
		int regSource1, regSource2, regDest, immValue;
		
		if (oInstruction instanceof IArithmeticInstruction) {
			try {
				regSource1 = Integer.parseInt(arInstructionContents[1]);
				regSource2 = Integer.parseInt(arInstructionContents[2]);
				regDest = Integer.parseInt(arInstructionContents[0]);
				((IArithmeticInstruction) oInstruction).setDestRegister(regDest);
				((IArithmeticInstruction) oInstruction).setSourceRegister1(regSource1);
				((IArithmeticInstruction) oInstruction).setSourceRegister2(regSource2);				
			} catch (Exception e) {
				System.out.println("There was a problem creating instruction for " + sInstruction + " " + sInstructionContents);
			}
			
		} else if (oInstruction instanceof IImmediateInstruction) {
			//Create an immediate instruction
			try {
				regSource1 = Integer.parseInt(arInstructionContents[1]);
				immValue = Integer.parseInt(arInstructionContents[2]);
				regDest = Integer.parseInt(arInstructionContents[0]);
				((IImmediateInstruction) oInstruction).setDestRegister(regDest);
				((IImmediateInstruction) oInstruction).setSourceRegister(regSource1);
				((IImmediateInstruction) oInstruction).setImmediateValue(immValue);				
			} catch (Exception e) {
				System.out.println("There was a problem creating instruction for " + sInstruction + " " + sInstructionContents);
			}			
		} else if (oInstruction instanceof IJumpInstruction) {
			//Create a jump instruction
			alInstructionContents = parseInstructionContents(sInstructionContents, eInstructionType.JUMP);
			
			((IJumpInstruction) oInstruction).setAddress(alInstructionContents.get(0));
		}
		return oInstruction;
	}
	
	private static IInstruction getInstructionByName(String sAsmName) {
		for (int i=0; i<arInstructions.length; i++) {
			if (arInstructions[i].getInstructionName().equalsIgnoreCase(sAsmName)) {
				return arInstructions[i];
			}
		}		
		return null;
	}
	
	private static String[] parseInstructionContents(String sInstructionContents) {
		String[] arContents = sInstructionContents.split(",");		
		for (int i=0; i<arContents.length; i++) {
			arContents[i] = arContents[i].trim().replace("$", "");
		}
		return arContents;	
	}
	
	
	
	private enum eInstructionType {
		ARITHMETIC,
		IMMEDIATE,
		JUMP
	}
}
