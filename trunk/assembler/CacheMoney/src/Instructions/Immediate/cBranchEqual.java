package Instructions.Immediate;

import Instructions.cImmediateInstruction;

public class cBranchEqual extends cImmediateInstruction {

	@Override
	public String getInstructionName() {
		return "beq";
	}

	@Override
	public int getOpCode() {
		return 5;
	}

}
