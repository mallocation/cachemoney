package Instructions.Immediate;

import Instructions.ImmediateInstruction;

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

