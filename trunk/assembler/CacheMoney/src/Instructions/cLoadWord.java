package Instructions;

import Interfaces.IInstruction;

public class cLoadWord implements IInstruction {

	@Override
	public String getEncodedInstruction() {
		return null;
	}

	@Override
	public String getInstructionName() {
		return "lw";
	}

	@Override
	public int getOpCode() {
		return 3;
	}

}
