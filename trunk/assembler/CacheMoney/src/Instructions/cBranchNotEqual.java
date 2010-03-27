package Instructions;

import Interfaces.IInstruction;

public class cBranchNotEqual implements IInstruction {

	@Override
	public String getEncodedInstruction() {
		return null;
	}

	@Override
	public String getInstructionName() {
		return "bneq";
	}

	@Override
	public int getOpCode() {
		return 6;
	}

}

