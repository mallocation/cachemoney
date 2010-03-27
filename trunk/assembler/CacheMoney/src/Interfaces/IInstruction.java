package Interfaces;

public interface IInstruction {
	
	/**
	 * Get the operation-code of the instruction.
	 * @return
	 */
	public int getOpCode();
	
	/**
	 * Get the name of the instruction.
	 * @return
	 */
	public String getInstructionName();
	
	/**
	 * Get the encoded instruction in MIF file format.
	 * @return
	 */
	public String getEncodedInstruction();
}
