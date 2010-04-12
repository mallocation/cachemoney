package Utilities;

public class SymbolicReference {
	
	private String symbolicReference;
	private int baseAddress;
	private eRefType oRefType;
	private int[] arValues;
	
	/**
	 * Use this constructor to create a symbolic reference that is just
	 * used to reference a memory address.
	 * @param SymbolicReference
	 */
	SymbolicReference(String SymbolicReference) {
		this.symbolicReference = SymbolicReference;
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
	SymbolicReference(String SymbolicReference, int Value) {
		this.symbolicReference = SymbolicReference;
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
	SymbolicReference(String SymbolicReference, int[] Values) {
		this.symbolicReference = SymbolicReference;
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
	
	/**
	 * Set the base address of the symbolic reference.
	 * @param Address
	 */
	public void setBaseAddress(int Address) {
		this.baseAddress = Address;
	}
	
	/**
	 * Get the base address of the symbolic reference.
	 * @return Base address of the symbolic reference.
	 */
	public int getBaseAddress() {
		return this.baseAddress;
	}
	
	/**
	 * Get the name of the reference.
	 * @return Reference Name
	 */
	public String getReferenceName() {
		return this.symbolicReference;
	}
	
	/**
	 * This method returns the symbolic reference encoded for the .mif file.
	 * Format = address : value
	 * @return
	 */
	public String[] getEncodedReference() {
		if (!this.isDataType()) {
			return null;
		} else {
			String[] arEncodedValues = new String[this.arValues.length];
			if (this.oRefType == eRefType.INTEGER) {
				arEncodedValues[0] = Conversion.IntegerTo32BitString(this.arValues[0]);
			} else {				
				for (int i=0; i<this.arValues.length; i++) {
					arEncodedValues[i] = Conversion.IntegerTo32BitString(this.arValues[i]);
				}
			}
			return arEncodedValues;
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
