package Utilities;

public class SymbolicReference {
	
	private String SymbolicReference;
	private int Address;
	private eRefType oRefType;
	private int[] arValues;
	
	/**
	 * Use this constructor to create a symbolic reference that is just
	 * used to reference a memory address.
	 * @param SymbolicReference
	 * @param Address
	 */
	SymbolicReference(String SymbolicReference, int Address) {
		this.SymbolicReference = SymbolicReference;
		this.Address = Address;
		this.oRefType = eRefType.ADDRESS;
		this.arValues = null;
	}
	
	/**
	 * Use this constructor to create a symbolic reference that represents
	 * an integer in memory.
	 * @param SymbolicReference Name of the reference.
	 * @param Address Address of the integer in memory.
	 * @param Value Integer value.
	 */
	SymbolicReference(String SymbolicReference, int Address, int Value) {
		this.SymbolicReference = SymbolicReference;
		this.Address = Address;
		this.oRefType = eRefType.INTEGER;
		this.arValues = new int[1];
		this.arValues[0] = Value;
	}
	
	/**
	 * Use this constructor to create a symbolic reference that represents
	 * an integer array in memory.
	 * @param SymbolicReference Name of the reference.
	 * @param Address Base address of the array in memory.
	 * @param Values Array of integer values.
	 */
	SymbolicReference(String SymbolicReference, int Address, int[] Values) {
		this.SymbolicReference = SymbolicReference;
		this.Address = Address;
		this.oRefType = eRefType.ARRAY;
		this.arValues = new int[Values.length];
		for (int i=0; i<Values.length; i++) {
			this.arValues[i] = Values[i];
		}
	}
	
	/**
	 * Call this function to determine if this symbolic reference represents a data type.
	 * @return <code>false</code> if it is simply a symbolic reference.
	 * 		   <code>true</code> if it is the address of an integer or an array of integers
	 */
	public boolean isDataType() {
		return oRefType != eRefType.ADDRESS;
	}
	
	public void setAddress(int Address) {
		this.Address = Address;
	}
	
	public int getAddress() {
		return this.Address;
	}
	
	/**
	 * Get the name of the reference.
	 * @return Reference Name
	 */
	public String getReferenceName() {
		return this.SymbolicReference;
	}
	
	/**
	 * This method returns the symbolic reference encoded for the .mif file.
	 * Format - address : value
	 * @return
	 */
	public String getEncodedReference() {
		if (!this.isDataType()) {
			return "";
		} else {
			if (this.oRefType == eRefType.INTEGER) {
				return this.Address + " : " + Conversion.IntegerTo32BitString(arValues[0]) + "\n";
			} else {
				String sEncodedReference = "";
				int nAddress = this.Address;
				for (int i=0; i<this.arValues.length; i++) {
					sEncodedReference += nAddress + " : " + Conversion.IntegerTo32BitString(arValues[i]) + "\n";
					nAddress++;
				}
				return sEncodedReference;
			}
		}
	}
	
	/**
	 * This method returns the number of elements that the symbolic reference contains.
	 * i.e. Address = 0, Integer = 1, Array = # of elements in array
	 * @return
	 */
	public int getElementCount() {
		if (!this.isDataType())
			return 0;
		else {
			if (this.oRefType == eRefType.INTEGER)
				return 1;
			else
				return this.arValues.length;
		}
	}
	
	private enum eRefType {
		ADDRESS,
		INTEGER,
		ARRAY
	}
}
