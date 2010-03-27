package Instructions;

import Interfaces.IInstruction;

public class cAdd implements IInstruction {

	@Override
	public String getEncodedInstruction() {
		return null;
	}

	@Override
	public String getInstructionName() {
		return "add";
	}

	@Override
	public int getOpCode() {
		return 0;
	}
}
