package Instructions;
import Interfaces.IInstruction;
import Interfaces.IInstructionConstants;

/**
 * This class will be used to generate instructions from the assembly code.
 * @author cjohnson
 *
 */
public class cFactory implements IInstructionConstants {

	public IInstruction createInstruction(String sInstruction, String sInstructionContents) {
		
		IInstruction oInstruction = getInstructionByName(sInstruction);
		
		return oInstruction;				
	}
	
	private IInstruction getInstructionByName(String sAsmName) {
		for (int i=0; i<arInstructions.length; i++) {
			if (arInstructions[i].getInstructionName().equalsIgnoreCase(sAsmName)) {
				return arInstructions[i];
			}
		}		
		return null;	
	}	
}
