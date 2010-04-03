package Instructions.Arithmetic;

import Instructions.ArithmeticInstruction;

public class Add extends ArithmeticInstruction {

	@Override
	public String getInstructionName() {
		return "add";
	}

	@Override
	public int getOpCode() {
		return 0;
	}
}