package Interfaces;

/**
 * This interface lays out a template for all Jump instructions.
 * Any instruction that is to be of type 'Jump' must implement this interface.
 */
public interface IJumpInstruction {
	
	/**
	 * Set the address to move program execution to.
	 * @param nAddress Address in memory to jump to.
	 */
	public void setAddress(int nAddress);
	
	/**
	 * Get the address to jump to in memory.	
	 * @return Address in memory to jump to.
	 */
	public int getAddress();
	
}
