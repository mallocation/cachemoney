package Interfaces;

public interface IArithmeticInstruction {
	
	/**
	 * Get the register containing the first operand.
	 * @return
	 */
	public int getSourceRegister1();
	
	/**
	 * Get the register containing the second operand.
	 * @return
	 */
	public int getSourceRegister2();
	
	/**
	 * Get the register that the result is to be stored into.
	 * @return
	 */
	public int getDestRegister();
}
