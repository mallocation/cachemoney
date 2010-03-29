package Instructions.Immediate;

import Instructions.cImmediateInstruction;

public class cLoadWord extends cImmediateInstruction {

	@Override
	public String getInstructionName() {
		return "lw";
	}

	@Override
	public int getOpCode() {
		return 3;
	}

	
}
