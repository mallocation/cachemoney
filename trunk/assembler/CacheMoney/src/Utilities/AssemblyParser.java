package Utilities;

public class AssemblyParser {
	
	/**
	 * Use this method to strip a line of any comments.
	 * i.e. <code>$add $1, $2, $3 #This is a comment</code> => <code>$add $1, $2, $3</code>
	 * @param <code>sLine</code> The line to remove comments from. 
	 * @return A line without comments.  If a line is strictly a comment, then an empty string is returned.
	 */
	public static String stripComments(String sLine) {
		int nCommentIndex;		
		// '#' signifies the beginning of a comment 
		if (sLine.contains("#")) {
			nCommentIndex = sLine.indexOf("#");			
			if (nCommentIndex == 0)
				return "";	
			sLine = sLine.substring(0, nCommentIndex);			
			return sLine.trim();			
		} else {
			return sLine;
		}
	}
	
	/**
	 * Determines if a line contains a symbolic reference.
	 * @param sLine Line from assembly file.
	 * @return <code>true</code> if the line contains a symbolic reference
	 * 		   <code>false</code> if the line does not contain a symbolic reference.
	 */
	public static boolean isSymbolicReference(String sLine) {
		String[] arSplit = sLine.split(":");
		return arSplit.length > 1;
	}
	
	/**
	 * Use this method to retrieve a symbolic Reference from a line.
	 * i.e. <code>symbAdd: add $1, $2, $3</code> => <code>symbAdd</code> 
	 * @param <code>sLine</code> The line to obtain a symbolic address from. 
	 * @return The symbolic address, if a line contains one.  If not, an empty string is returned.
	 */
	public static String getSymbolicReference(String sLine) {
		String[] arSplit = sLine.split(":");		
		if (arSplit.length > 0) {
			return arSplit[0].trim();
		}		
		return "";
	}
	
	/**
	 * Use this method to retrieve a symbolic reference from a line.
	 * @param sLine
	 * @param bIsDataSection
	 * @return
	 */
	public static SymbolicReference getSymbolicReference(String sLine, boolean bIsDataSection) {
		String[] arSplit = sLine.split(":");
		String[] arData;
		SymbolicReference oReference = null;		
		trimArrayContents(arSplit);		
		if (bIsDataSection) {
			arData = arSplit[1].split(" ");
			trimArrayContents(arData);						
			if (arData[0].equalsIgnoreCase(".integer")) {
				oReference = new SymbolicReference(arSplit[0], 0, Integer.parseInt(arData[1]));
			} else if (arData[0].equalsIgnoreCase(".array")) {
				String arStringValues[] = arData[1].split(",");
				int[] arValues = new int[arStringValues.length];
				for (int i=0; i<arStringValues.length; i++) {
					arValues[i] = Integer.parseInt(arStringValues[i]);
				}
				oReference = new SymbolicReference(arSplit[0], 0, arValues);			
			}
		} else {
			oReference = new SymbolicReference(arSplit[0], 0);
		}		
		return oReference;		
	}
	
	/**
	 * Use this method to retrieve the assembly from a line.
	 * i.e. symbAdd: add $1, $2, $3 => add $1, $2, $3
	 * @param sLine The line to obtain the assembly from.
	 * @return The assembly contents.
	 */
	public static String getAssembly(String sLine) {
		String[] arSplit = sLine.split(":");
		if (sLine.contains(":")) {
			return arSplit[1].trim();
		}
		return sLine;
	}
	
	/**
	 * This will trim values in an array.
	 * @param array
	 */
	private static void trimArrayContents(String[] array) {
		for (int i=0; i<array.length; i++) {
			array[i] = array[i].trim();
		}
	}

}
