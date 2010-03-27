package Instructions;

import Interfaces.IInstruction;

public class cBranchEqual implements IInstruction {

	@Override
	public String getEncodedInstruction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInstructionName() {
		return "beq";
	}

	@Override
	public int getOpCode() {
		return 5;
	}

}
