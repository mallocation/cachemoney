package Instructions;

import Interfaces.IInstruction;

public class cStoreWord implements IInstruction {

	@Override
	public String getEncodedInstruction() {
		return null;
	}

	@Override
	public String getInstructionName() {
		return "sw";
	}

	@Override
	public int getOpCode() {
		return 4;
	}

}
