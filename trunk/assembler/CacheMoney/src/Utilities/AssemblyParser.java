package Utilities;

/**
 * This class is used primarily for parsing through an assembly file.
 * @author Cache Money
 */
public class AssemblyParser {
	
	/**
	 * Use this method to strip a line of any comments.
	 * i.e. <code>add $1, $2, $3 #This is a comment</code> => <code>add $1, $2, $3</code>
	 * @param <code>sLine</code> The line to remove comments from. 
	 * @return A line without comments.  If a line is strictly a comment, then an empty string is returned.
	 */
	public static String stripComments(String sLine) {
		return sLine.replaceAll("[#]+.*", "").trim();
	}
	
	/**
	 * Use this method to strip a line of its reference.
	 * i.e. <code>fcnAdd: add $1, $2, $3</code> => <code>add $1, $2, $3</code>
	 * @param sLine
	 * @return
	 */
	public static String stripReference(String sLine) {
		return sLine.replaceAll(".*:+", "").trim();
	}
	
	/**
	 * Use this method to get the instruction name from a line.
	 * i.e. <code>mult $1, $2, $3</code> => <code>mult</code> 
	 * @param sLine
	 * @return
	 */
	public static String getInstructionNameFromLine(String sLine) {
		return stripReference(sLine).replaceAll("[\\W]+.*", "").trim();
	}
	
	/**
	 * Use this method to get an instruction's properties from a line.
	 * i.e. <code> mult $1, $2, $3</code> => <code>$1, $2, $3</code> 
	 * @param sLine
	 * @return
	 */	
	public static String[] getInstructionPropertiesFromLine(String sLine) {
		String sProps = stripReference(sLine).replaceFirst("[\\w]+[\\s\\t]*", "").trim();
		if (sProps.equalsIgnoreCase(""))
			return null;
		String[] arProps = sProps.split(",");
		for (int i=0; i<arProps.length; i++) {
			arProps[i] = arProps[i].trim();
		}
		return arProps;
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
		return sLine.trim();
	}
	
	/**
	 * This will trim values in an array.
	 * @param array
	 */
	public static void trimArrayContents(String[] array) {
		for (int i=0; i<array.length; i++) {
			array[i] = array[i].trim();
		}
	}

}
