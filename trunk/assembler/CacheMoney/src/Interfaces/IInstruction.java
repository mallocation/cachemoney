package Interfaces;

public interface IInstruction {
	
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
