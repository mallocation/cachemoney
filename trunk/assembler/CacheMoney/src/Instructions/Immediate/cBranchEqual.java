package Instructions.Immediate;

import Instructions.ImmediateInstruction;

public class cBranchEqual extends ImmediateInstruction {

	@Override
	public String getInstructionName() {
		return "beq";
	}

	@Override
	public int getOpCode() {
		return 5;
	}

}