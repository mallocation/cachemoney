package Utilities;

import Instructions.Arithmetic.*;
import Instructions.Immediate.*;
import Instructions.Jump.*;
import Interfaces.IInstruction;
import Interfaces.IInstructionConstants;

/**
 * This class will be used to generate instructions from the assembly code, and will determine certain instruction-specific
 * properties.
 */
public class InstructionFactory implements IInstructionConstants {

	/**
	 * Create an instruction from the assembly name.
	 * @param sInstructionName Name of the instruction to create. i.e. <code>add</code> or <code>mult</code>
	 * @return Template of requested instruction. <code>null</code> if requested instruction does not exist.
	 */
	public static IInstruction createInstruction(String sInstructionName) {
		return getInstructionByName(sInstructionName);
	}
	
	/**
	 * This method will search for an instruction based on the assembly name, and will return a new instance of 
	 * the requested instruction if the instruction exists.
	 * @param sAsmName Name of the instruction. i.e. <code>add</code> or <code>mult</code>
	 * @return New instruction if the requested type exists, otherwise <code>null</code>
	 */
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
	
	/**
	 * This function determines if an instruction accesses memory.
	 * @param oInstruction Instruction to check.
	 * @return <code>true</code> if the instruction accesses memory, <code>false</code> otherwise
	 */
	public static boolean InstructionAccessesMemory(IInstruction oInstruction) {
		return oInstruction.getInstructionName() == LW.getInstructionName() || oInstruction.getInstructionName() == SW.getInstructionName();
	}
	
	/**
	 * This function determines if the instruction is a branch instruction.
	 * @param oInstruction Instruction to check.
	 * @return <code>true</code> if the instruction is a branch instruction, <code> false</code> otherwise
	 */
	public static boolean IsBranchInstruction(IInstruction oInstruction) {
		return oInstruction.getInstructionName() == BEQ.getInstructionName() || oInstruction.getEncodedInstruction() == BNEQ.getInstructionName();
	}
}
