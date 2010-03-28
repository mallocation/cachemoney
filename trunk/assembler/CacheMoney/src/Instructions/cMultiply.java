package Instructions;

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
