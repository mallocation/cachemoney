package Instructions.Immediate;

import Instructions.ImmediateInstruction;

public class cBranchNotEqual extends ImmediateInstruction {

	@Override
	public String getInstructionName() {
		return "bneq";
	}

	@Override
	public int getOpCode() {
		return 6;
	}
	
}

