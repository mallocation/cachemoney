package Instructions.Immediate;

import Instructions.cImmediateInstruction;

public class cStoreWord extends cImmediateInstruction {

	@Override
	public String getInstructionName() {
		return "sw";
	}

	@Override
	public int getOpCode() {
		return 4;
	}

}
