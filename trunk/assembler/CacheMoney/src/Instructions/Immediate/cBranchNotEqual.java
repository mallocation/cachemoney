package Instructions.Immediate;

import Instructions.cImmediateInstruction;

public class cBranchNotEqual extends cImmediateInstruction {

	@Override
	public String getInstructionName() {
		return "bneq";
	}

	@Override
	public int getOpCode() {
		return 6;
	}
	
}

