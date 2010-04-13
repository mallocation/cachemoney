package Instructions.Arithmetic;

import Instructions.ArithmeticInstruction;

/**
 * This class represents a 'mult' instruction.
 */
public class Multiply extends ArithmeticInstruction {

	@Override
	public String getInstructionName() {
		return "mult";
	}

	@Override
	public int getOpCode() {
		return 2;
	}
	
}
