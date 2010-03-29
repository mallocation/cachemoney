package Instructions.Arithmetic;

import Instructions.cArithmeticInstruction;

public class cAdd extends cArithmeticInstruction {

	@Override
	public String getInstructionName() {
		return "add";
	}

	@Override
	public int getOpCode() {
		return 0;
	}
}