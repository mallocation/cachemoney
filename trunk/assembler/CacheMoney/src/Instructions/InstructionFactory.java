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

	public IInstruction createInstruction(String sInstruction, String sInstructionContents) {
		ArrayList<Integer> alInstructionContents;
		
		IInstruction oInstruction = getInstructionByName(sInstruction);
		
		if (oInstruction instanceof IArithmeticInstruction) {
			//Create an arithmetic instruction
			alInstructionContents = parseInstructionContents(sInstructionContents, eInstructionType.ARITHMETIC);
			
			((IArithmeticInstruction) oInstruction).setDestRegister(alInstructionContents.get(0));
			((IArithmeticInstruction) oInstruction).setSourceRegister1(alInstructionContents.get(1));
			((IArithmeticInstruction) oInstruction).setSourceRegister2(alInstructionContents.get(2));
			
		} else if (oInstruction instanceof IImmediateInstruction) {
			//Create an immediate instruction
			alInstructionContents = parseInstructionContents(sInstructionContents, eInstructionType.IMMEDIATE);
			
			((IImmediateInstruction) oInstruction).setDestRegister(alInstructionContents.get(0));
			((IImmediateInstruction) oInstruction).setSourceRegister(alInstructionContents.get(1));
			((IImmediateInstruction) oInstruction).setImmediateValue(alInstructionContents.get(2));
			
		} else if (oInstruction instanceof IJumpInstruction) {
			//Create a jump instruction
			alInstructionContents = parseInstructionContents(sInstructionContents, eInstructionType.JUMP);
			
			((IJumpInstruction) oInstruction).setAddress(alInstructionContents.get(0));
		}
		return oInstruction;
	}
	
	private IInstruction getInstructionByName(String sAsmName) {
		for (int i=0; i<arInstructions.length; i++) {
			if (arInstructions[i].getInstructionName().equalsIgnoreCase(sAsmName)) {
				return arInstructions[i];
			}
		}		
		return null;
	}
	
	private ArrayList<Integer> parseInstructionContents(String sInstructionContents, eInstructionType oType) {
		ArrayList<Integer> alContents = new ArrayList<Integer>();
		
		return alContents;		
	}
	
	
	
	private enum eInstructionType {
		ARITHMETIC,
		IMMEDIATE,
		JUMP
	}
}
