package Interfaces;

public interface IImmediateInstruction {
	
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
