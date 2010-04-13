package Interfaces;

/**
 * This interface lays out a template for all Arithmetic instructions.
 * Any instruction that is to be of type 'Arithmetic' must implement this interface.
 */
public interface IArithmeticInstruction {
	
	/**
	 * Set the 1st source register.
	 * @param nRegister The register number.
	 */
	public void setSourceRegister1(int nRegister);
	
	/**
	 * Set the 2nd source register.
	 * @param nRegister The register number.
	 */
	public void setSourceRegister2(int nRegister);
	
	/**
	 * Set the destination register.
	 * @param nRegister The register number.
	 */
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
