package Utilities;

public class Conversion {
	
	/**
	 * Convert an integer to a 32 bit binary string.
	 * @param value The integer to convert.
	 * @return Integer representation as 32 bit binary string.
	 */
	public static String IntegerTo32BitString(int value) {
		return IntegerToBinaryString(value, 32);
	}
	
	public static String IntegerToBinaryString(int value, int length) {
		if (value < 0)
			return Integer.toBinaryString(value).substring(32-length);
		else {
			String sInteger = Integer.toBinaryString(value);
			while (sInteger.length() != length) {
				sInteger = "0" + sInteger;
			}
			return sInteger;
		}
	}

}
