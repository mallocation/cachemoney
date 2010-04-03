package Instructions.Immediate;

import Instructions.ImmediateInstruction;

public class LoadWord extends ImmediateInstruction {

	@Override
	public String getInstructionName() {
		return "lw";
	}

	@Override
	public int getOpCode() {
		return 3;
	}

	
}
