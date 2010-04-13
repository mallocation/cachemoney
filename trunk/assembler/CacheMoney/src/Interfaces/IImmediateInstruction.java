package Interfaces;

/**
 * This interface lays out a template for all Immediate instructions.
 * Any instruction that is to be of type 'Immediate' must implement this interface.
 */
public interface IImmediateInstruction {
	
	/**
	 * Set the source register for the operation.
	 * @param nRegister The source register.
	 */
	public void setSourceRegister(int nRegister);
	
	/**
	 * Set the destination register for the operation.
	 * @param nRegister The destination register.
	 */
	public void setDestRegister(int nRegister);
	
	/**
	 * Set the immediate value for the operation.
	 * @param nValue The immediate value.
	 */
	public void setImmediateValue(int nValue);
	
	/**
	 * Get the source register involved in the immediate instruction
	 * @return The source register.
	 */
	public int getSourceRegister();
	
	/**
	 * Get the destination register to save the result to.
	 * @return The destination register.
	 */
	public int getDestRegister();
	
	/**
	 * Get the immediate value for the operation.
	 * @return The immediate value.
	 */
	public int getImmediateValue();

}
