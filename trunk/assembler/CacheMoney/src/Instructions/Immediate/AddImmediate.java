package Instructions.Immediate;

import Instructions.ImmediateInstruction;

/**
 * This class represents an 'addi' instruction.
 */
public class AddImmediate extends ImmediateInstruction {

	@Override
	public String getInstructionName() {
		return "addi";
	}

	@Override
	public int getOpCode() {
		return 1;
	}
}
