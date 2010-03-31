package Interfaces;

public interface IArithmeticInstruction {
	
	public void setSourceRegister1(int nRegister);
	
	public void setSourceRegister2(int nRegister);
	
	public void setDestRegister(int nRegister);
	
	/**
	 * Get the register containing the first operand.
	 * @return The first source register.
	 */
	public int getSourceRegister1();
	
	/**
	 * Get the register containing the second operand.
	 * @return The second source register.
	 */
	public int getSourceRegister2();
	
	/**
	 * Get the register that the result is to be stored into.
	 * @return The destination register.
	 */
	public int getDestRegister();
}
