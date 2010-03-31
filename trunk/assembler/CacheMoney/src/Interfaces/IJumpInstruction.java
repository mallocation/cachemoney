package Interfaces;

public interface IJumpInstruction {
	
	public void setAddress(int nAddress);
	
	/**
	 * Get the address to jump to in memory.	
	 * @return Address in memory to jump to.
	 */
	public int getAddress();
	
}
