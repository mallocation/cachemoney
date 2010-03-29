package Instructions.Immediate;

import Instructions.cImmediateInstruction;

public class cAddImmediate extends cImmediateInstruction {

	@Override
	public String getInstructionName() {
		return "addi";
	}

	@Override
	public int getOpCode() {
		return 1;
	}
}
