package Instructions.Arithmetic;

import Instructions.cArithmeticInstruction;

public class cMultiply extends cArithmeticInstruction {

	@Override
	public String getInstructionName() {
		return "mult";
	}

	@Override
	public int getOpCode() {
		return 2;
	}
	
}
