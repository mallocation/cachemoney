package Utilities;

import Instructions.Arithmetic.*;
import Instructions.Immediate.*;
import Instructions.Jump.*;
import Interfaces.IInstruction;
import Interfaces.IInstructionConstants;

/**
 * This class will be used to generate instructions from the assembly code.
 */
public class InstructionFactory implements IInstructionConstants {

	public static IInstruction createInstruction(String sInstructionName) {
		return getInstructionByName(sInstructionName);
	}
	
	private static IInstruction getInstructionByName(String sAsmName) {
		IInstruction oInstruction = null;
		for (int i=0; i<arInstructions.length; i++) {
			if (arInstructions[i].getInstructionName().equalsIgnoreCase(sAsmName)) {
				oInstruction = arInstructions[i];
			}
		}		
		if (oInstruction == ADD) return new Add();		
		else if (oInstruction == ADDI) return new AddImmediate();
		else if (oInstruction == BEQ) return new BranchEqual();
		else if (oInstruction == BNEQ) return new BranchNotEqual();
		else if (oInstruction == JMP) return new cJump();
		else if (oInstruction == LW) return new LoadWord();
		else if (oInstruction == MULT) return new Multiply();
		else if (oInstruction == NOP) return new NoOperation();
		else if (oInstruction == SW) return new StoreWord();
		else return null;
	}
	
	public static boolean InstructionAccessesMemory(IInstruction oInstruction) {
		return oInstruction.getInstructionName() == LW.getInstructionName() || oInstruction.getInstructionName() == SW.getInstructionName();
	}
	
	public static boolean IsBranchInstruction(IInstruction oInstruction) {
		return oInstruction.getInstructionName() == BEQ.getInstructionName() || oInstruction.getEncodedInstruction() == BNEQ.getInstructionName();
	}
	
	public static boolean IsJumpInstruction(IInstruction oInstruction) {
		return oInstruction.getInstructionName() == JMP.getInstructionName();
	}
}
