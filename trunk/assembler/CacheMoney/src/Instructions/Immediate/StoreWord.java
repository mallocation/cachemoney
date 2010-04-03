package Instructions.Immediate;

import Instructions.ImmediateInstruction;

public class StoreWord extends ImmediateInstruction {

	@Override
	public String getInstructionName() {
		return "sw";
	}

	@Override
	public int getOpCode() {
		return 4;
	}

}
