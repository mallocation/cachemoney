import java.util.Arrays;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.ArithmeticInstruction;
import org.apache.bcel.generic.InstructionFactory;

/**
 * cInstructionHelper is a helper class for the ChangeOp program.
 * This class handles all operator/instruction verification.
 *
 * @author cjohnson
 * @version 1.0
 */
public class cInstructionHelper {

	/**
	 * Valid arithmetic instructions of type DOUBLE.
	 */
	private ArithmeticInstruction[] arDoubleInstructions = {InstructionFactory.DADD, InstructionFactory.DSUB, InstructionFactory.DMUL, InstructionFactory.DDIV, InstructionFactory.DREM};

	/**
	 * Valid arithmetic instructions of type FLOAT.
	 */
	private ArithmeticInstruction[] arFloatInstructions = {InstructionFactory.FADD, InstructionFactory.FSUB, InstructionFactory.FMUL, InstructionFactory.FDIV, InstructionFactory.FREM};

	/**
	 * Valid arithmetic instructions of type INT.
	 */
	private ArithmeticInstruction[] arIntInstructions = {InstructionFactory.IADD, InstructionFactory.ISUB, InstructionFactory.IMUL, InstructionFactory.IDIV, InstructionFactory.IREM};

	/**
	 * Valid arithmetic instructions of type LONG.
	 */
 	private ArithmeticInstruction[] arLongInstructions = {InstructionFactory.LADD, InstructionFactory.LSUB, InstructionFactory.LMUL, InstructionFactory.LDIV, InstructionFactory.LREM};

	/**
	 * Checks if a specific Instruction in the Java virtual machine is an Arithmetic Instruction.
	 *
	 * @param oInstruction the instruction to check
	 * 
	 * @return <code>true</code> if the instruction is an arithmetic instruction,
	 *         <code>false</code> otherwise
	 */
	public boolean isArithmeticInstruction(Instruction oInstruction) {
		if (oInstruction instanceof ArithmeticInstruction) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if a specific instruction is that of the operator type.
	 * i.e. IADD corresponds to the "+" operator, and would therefore return true
	 *
	 * @param oInstruction the instruction to examine
	 * @param sOperator the desired operator to match
	 *
	 * @return <code>true</code> if the instructions is representative of the given operator,
	 *         <code>false</code> otherwise
	 */
	public boolean InstructionMatchOperator(Instruction oInstruction, String sOperator) {
		return oInstruction.getName().contains(OperatorToInstName(sOperator));
	}

	/**
	 * Converts an Arithmetic Instruction to that of the new operator.
	 *
	 * @param oInstruction the instruction to convert
	 * @param sNewOperator operator representative of the new operation
	 *
	 * @return the new arithmetic instruction corresponding to the new operation type
	 */
	public Instruction ConvertArithmetic(Instruction oInstruction, String sNewOperator) {
		//First get the arithmetic type to know what instruction type to convert to
		eArithmeticType oArithType = InstructionToArithmeticType(oInstruction);
		Instruction oNewInstruction = null; //The converted instruction
		
		//Now, we have the arithmetic type, get the corresponding opcode.
		switch (oArithType) {
			case DOUBLE:	oNewInstruction = findInstructionByName(arDoubleInstructions, OperatorToInstName(sNewOperator));break;
			case FLOAT:		oNewInstruction = findInstructionByName(arFloatInstructions, OperatorToInstName(sNewOperator));break;
			case INT:		oNewInstruction = findInstructionByName(arIntInstructions, OperatorToInstName(sNewOperator));break;
			case LONG:		oNewInstruction = findInstructionByName(arLongInstructions, OperatorToInstName(sNewOperator));break;
		}		
		return oNewInstruction;
	}

	/**
	 * Determines an arithmetic operator's corresponding instruction name.
	 *
	 * @param sOperator the arithmetic operator
	 *
	 * @return the arithmetic operation name of the operator
	 */
	private String OperatorToInstName(String sOperator) {
		if (sOperator.equals("+")) { return "add"; }
		else if (sOperator.equals("-")) { return "sub"; }
		else if (sOperator.equals("*")) { return "mul"; }
		else if (sOperator.equals("/")) { return "div"; }
		else if (sOperator.equals("%")) { return "rem"; }
		else { return ""; }
	}

	/**
	 * Searches for an instruction in the specified array by a subset of the
	 * instruction's name.
	 *
	 * @param arInstructions array of instructions
	 * @param sSubName subset of the instruction name
	 *
	 * @return <code>Instruction</code> if an instruction containing sSubName in the name is found,
	 *         <code>null</code> otherwise
	 */
	private Instruction findInstructionByName(Instruction arInstructions[], String sSubName) {
		for (int i=0; i<arInstructions.length; i++) {
			if (arInstructions[i].getName().toLowerCase().contains(sSubName.toLowerCase())) {
				//If the instruction name contains sSubName, return it
				return arInstructions[i];
			}
		}
		return null;
	}

	/**
	 * Determines the arithmetic type of a specific instruction. (Double, Int, etc.)
	 * isArithmeticInstruction should be called prior to this
	 *
	 * @param oInstruction the instruction to check
	 * @return <code>eArithmeticType</code> if a valid arithmetic type is found,
	 *         <code>null</code> otherwise
	 */
	private eArithmeticType InstructionToArithmeticType(Instruction oInstruction) {
		if (Arrays.asList(arDoubleInstructions).indexOf(oInstruction) != -1) {
			return eArithmeticType.DOUBLE;
		} else if (Arrays.asList(arFloatInstructions).indexOf(oInstruction) != -1) {
			return eArithmeticType.FLOAT;
		} else if (Arrays.asList(arIntInstructions).indexOf(oInstruction) != -1) {
			return eArithmeticType.INT;
		} else if (Arrays.asList(arLongInstructions).indexOf(oInstruction) != -1) {
			return eArithmeticType.LONG;
		} else {
			return null; //should never get here
		}
	}		

	/**
	 * Enumeration for valid arithmetic operation types.
	 */
	private enum eArithmeticType {
		DOUBLE,
		FLOAT,
		INT,
		LONG
	}
}