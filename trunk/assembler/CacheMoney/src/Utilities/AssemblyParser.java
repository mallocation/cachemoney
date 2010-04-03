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

}
