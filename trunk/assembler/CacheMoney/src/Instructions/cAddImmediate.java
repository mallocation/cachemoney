package Instructions;

import Interfaces.IInstruction;

public class cAddImmediate implements IInstruction {

	@Override
	public String getEncodedInstruction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInstructionName() {
		return "addi";
	}

	@Override
	public int getOpCode() {
		return 1;
	}

}
