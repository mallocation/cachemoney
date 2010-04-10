package Utilities;

public class SymbolicReference {
	
	public String SymbolicReference;
	public int Address;
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
	
	private enum eRefType {
		ADDRESS,
		INTEGER,
		ARRAY
	}
}
