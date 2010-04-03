package Instructions.Immediate;

import Instructions.ImmediateInstruction;

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
