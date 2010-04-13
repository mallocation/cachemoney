package Instructions.Immediate;

import Instructions.ImmediateInstruction;

/**
 * This class represents a 'bneq' instruction.
 */
public class BranchNotEqual extends ImmediateInstruction {

	@Override
	public String getInstructionName() {
		return "bneq";
	}

	@Override
	public int getOpCode() {
		return 6;
	}
	
}

