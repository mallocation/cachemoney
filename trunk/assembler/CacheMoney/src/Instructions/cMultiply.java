package Instructions;

import Interfaces.IInstruction;

public class cMultiply implements IInstruction {

	@Override
	public String getEncodedInstruction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInstructionName() {
		return "mult";
	}

	@Override
	public int getOpCode() {
		return 2;
	}	
}
