package Instructions.Immediate;

import Instructions.ImmediateInstruction;

/**
 * This class represents a 'beq' instruction.
 */
public class BranchEqual extends ImmediateInstruction {

	@Override
	public String getInstructionName() {
		return "beq";
	}

	@Override
	public int getOpCode() {
		return 5;
	}

}
