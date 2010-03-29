package Instructions.Immediate;

import Instructions.cImmediateInstruction;
import Interfaces.IImmediateInstruction;
import Interfaces.IInstruction;

public class cBranchEqual extends cImmediateInstruction {

	@Override
	public String getInstructionName() {
		return "beq";
	}

	@Override
	public int getOpCode() {
		return 5;
	}

}
