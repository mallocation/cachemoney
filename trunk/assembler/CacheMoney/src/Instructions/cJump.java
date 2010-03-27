package Instructions;

import Interfaces.IInstruction;

public class cJump implements IInstruction {

	@Override
	public String getEncodedInstruction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInstructionName() {
		return "jmp";
	}

	@Override
	public int getOpCode() {
		return 7;
	}

}
