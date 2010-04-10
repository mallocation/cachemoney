package Utilities;

public class Conversion {
	
	/**
	 * Convert an integer to a 32 bit binary string.
	 * @param value The integer to convert.
	 * @return Integer representation as 32 bit binary string.
	 */
	public static String IntegerTo32BitString(int value) {
		if (value < 0)
			return Integer.toBinaryString(value);
		else {
			String sInteger = Integer.toBinaryString(value);
			while (sInteger.length() != 32) {
				sInteger = "0" + sInteger;
			}
			return sInteger;
		}		
	}

}
