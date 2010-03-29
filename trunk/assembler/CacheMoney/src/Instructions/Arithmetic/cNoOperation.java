package Instructions.Arithmetic;


public class cNoOperation extends cAdd {
	
	@Override
	public String getInstructionName() {
		return "nop";
	}
	
	/**
	 *	NOP has a destination register of $0.
	 */
	@Override
	public int getDestRegister() {
		return 0;
	}
	
	/**
	 * NOP has source registers of $0.
	 */
	@Override
	public int getSourceRegister1() {
		return 0;
	}
	
	/**
	 * NOP has source registers of $0.
	 */
	@Override
	public int getSourceRegister2() {
		return 0;
	}
}
