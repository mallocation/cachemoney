package Instructions.Arithmetic;

import Instructions.ArithmeticInstruction;

/**
 * This class represents an 'add' instruction.
 */
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