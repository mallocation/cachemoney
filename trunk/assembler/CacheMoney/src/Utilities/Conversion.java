package Utilities;

public class Conversion {
	
	public static String IntegerTo32BitString(int value) {
		String sEncodedValue = Integer.toString(value, 2);
		return SignExtendBinaryString(sEncodedValue);		
	}
	
	private static String SignExtendBinaryString(String sString) {
		while (sString.length() != 32) {
			sString = sString.substring(0,1) + sString;
		}
		return sString;
	}

}
