package Utilities;

/**
 * This class is a platform for performing any necessary conversions for the memory file.
 */
public class Conversion {
	
	/**
	 * Convert an integer to a 32 bit binary string.
	 * @param value The integer to convert.
	 * @return Integer representation as 32 bit binary string.
	 */
	public static String IntegerTo32BitString(int value) {
		return IntegerToBinaryString(value, 32);
	}
	
	/**
	 * Convert an integer to a binary string.
	 * @param value Integer value to convert.
	 * @param length Length of binary string.
	 * @return Integer encoded as binary string.
	 */
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
