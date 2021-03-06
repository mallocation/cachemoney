package Interfaces;

/**
 * This interface creates a template for all assembly instructions.
 * ALL instructions used within the assembler must implement this interface.
 */
public interface IInstruction {
	
	/**
	 * Set the address of the instruction in memory.
	 * @param address
	 */
	public void setInstructionAddress(int address);
	
	/**
	 * Get the instruction of the address in memory.
	 * @return Address of instruction in memory
	 */
	public int getInstructionAddress();
	
	/**
	 * Get the operation-code of the instruction.
	 * @return Instruction op code.
	 */
	public int getOpCode();
	
	/**
	 * Get the name of the instruction.
	 * @return Instruction syntax name.
	 */
	public String getInstructionName();
	
	/**
	 * Get the encoded instruction in MIF file format.
	 * @return The instruction in binary format (32 bit string).
	 */
	public String getEncodedInstruction();
}
