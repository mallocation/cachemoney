import java.util.*;
import org.apache.bcel.Repository;
import org.apache.bcel.classfile.*;
import org.apache.bcel.generic.*;

/**
 * ChangeOp is a program that will perform dynamic operator mutations on a specific
 * class file.  All instances of an arithmetic operator will be changed to the new operator
 * specified at runtime.
 *
 * @author cjohnson
 * @version 1.0
 *
 */
public class ChangeOp {

	/**
    * List of usable operators within the program.
    */
	private ArrayList<String> _alValidOperators;

	/**
	 * Class to be mutated.  Specified at runtime.
	 */
	private JavaClass oClass;

	/**
	 * ClassGen object used to generate the mutated class.
	 */
	private ClassGen oClassGen;

	/**
	 * ConstantPool object of the class to modify.
	 */
	private ConstantPool oConstantPool;

	/**
	 * ConstantPoolGen used in generating the mutated class.
	 */
	private ConstantPoolGen oConstantPoolGen;

	/**
	 * Array of methods within the mutated class.
	 */
	private Method[] arMethods;

	/**
    * The operator to be mutated.
    */
	private String sOldOperator;

	/**
    * The new operator resulting from mutation.
    */
	private String sNewOperator;

	/**
	 * The instruction helper used for generating mutated instructions.
	 */
	private cInstructionHelper oInstHelper;

	/**
	 * This will create an instance of the ChangeOp program.
	 *
	 * @param args command line arguments of main method containing class file and operators
	 */
	public ChangeOp(String args[]) {
		//parse the arguments (class file, operators) and create an instruction helper
		parseArguments(args);
		oInstHelper = new cInstructionHelper();
	}

	/**
	 * This is a readonly property that contains a list of all valid operators that may be modified within a class.
	 * This readonly property provides for an easy way to quickly add/remove operators for use.
	 *
	 * @return <code>ArrayList</code> containing all operators available for modification.
	 */
	private ArrayList alValidOperators() {
		//if the list is null, initialize it
		if (_alValidOperators == null) {
			_alValidOperators = new ArrayList<String>();
			_alValidOperators.add("+");_alValidOperators.add("-");_alValidOperators.add("*");_alValidOperators.add("/");_alValidOperators.add("%");
		}
		return _alValidOperators;
	}

	/**
	 * Prints the usage specifications of the program.
	 */
	private void printUsage() {
		System.out.println("<Usage> java ChangeOp <class-file> <oldOp> <newOp>");
		System.out.println("<class-file>: without extension");
		System.out.println("<oldOp>: +, -, *, % , or /");
		System.out.println("<newOp>: +, -, *, % , or /");
	}

	/**
	 * Determines if oOperator is a valid arithmetic operator.
	 * 
	 * @param oOperator the operator to be tested
	 * @return  <code>true</code> if the operator is a valid operator
	 *          <code>false</code> otherwise
	 */
	private boolean isValidOperator(String oOperator) {
		if (alValidOperators().indexOf(oOperator) != -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Parses the arguments passed from the "main" method.
	 * These arguments should contain the class name, and the operators.
	 *
	 * @param args program execution command line arguments
	 */
	private void parseArguments(String[] args) {
		//Get the class file
		try {
			oClass = Repository.lookupClass(args[0]);
			oClassGen = new ClassGen(oClass);
		} catch (Exception e) {
			showError("Please specify a valid class file.");
		}
		
		//Get the operators
		try {
			//Store the operators, and trim any single quotes (needed to pass * as an argument)
			sOldOperator = args[1].replace("'", "");
			sNewOperator = args[2].replace("'", "");
			
			if (!isValidOperator(sOldOperator) || !isValidOperator(sNewOperator)) {
				showError("Invalid operators specified.");
			}
			if (sOldOperator.equals(sNewOperator)) {
				showError("Old and New operators cannot be the same operator.");
			}
		} catch (Exception e) {
			showError("Please specify an old operator and a new operator.");
		}
	}
	
	/**
	 * Shows an error to the user, prints the program usage, and exits.
	 * 
	 * @param sError the error to display to the user
	 */
	private void showError(String sError) {
		System.out.println(sError);
		printUsage();
		System.exit(1);
	}

	/**
	 * Changes all instances of the old operator to the new operator
	 * within a specific class file.
	 */
	public void changeOperators() {
		int nMutations = 0;
		System.out.println("Changing all instances of \"" + sOldOperator + "\" to \"" + sNewOperator + "\" in " + oClass.getClassName());
	
		oConstantPoolGen = oClassGen.getConstantPool();
		arMethods = oClassGen.getMethods();
				
		for (int i=0; i<arMethods.length; i++) {
			// mutation process
			// 1. Go through all instructions in a method
			// 2. If an instruction is an arithmetic instruction, see if it matches the old operator
			// 3. If it matches the old operator, change the instruction to match the new operator, and set the isntruction
			// 4. otherwise, don't worry about it and move on!
			
			MethodGen oMethodGen = new MethodGen(arMethods[i], oClass.getClassName(), oConstantPoolGen);
			InstructionList il = oMethodGen.getInstructionList();
			InstructionHandle ih;
			
			for (ih = il.getStart(); ih != null; ih = ih.getNext()) {
				Instruction oInstruction = ih.getInstruction();

				if (oInstHelper.isArithmeticInstruction(oInstruction)) {
					if (oInstHelper.InstructionMatchOperator(oInstruction, sOldOperator)) {
						//convert to the new instruction
						oInstruction = oInstHelper.ConvertArithmetic(oInstruction, sNewOperator);
						nMutations++;
					}					
					//set the new instruction
					ih.setInstruction(oInstruction);					
				}
			}
			il.setPositions();
			oMethodGen.setInstructionList(il);
			oClassGen.setMethodAt(oMethodGen.getMethod(), i);
			il.dispose();
		}		
		System.out.println("Mutation complete. " + nMutations + " mutation(s) were performed.");	
	}	

	/**
	 * Dumps the modified class to the program directory.
	 */
	public void dumpClass() {
		try {
			oClassGen.getJavaClass().dump(oClass.getClassName() + ".class");
      } catch (Exception e) {
			System.out.println(e);
      }
	}

	/**
	 * Offers the main method of program execution.  This is where
	 * a call to changeOperators and dumpClass should be made.
	 *
	 * @param args command line arguments
	 */
	public static void main(String args[]) {
		ChangeOp oOperatorChanger = new ChangeOp(args);
		
		oOperatorChanger.changeOperators();
		oOperatorChanger.dumpClass();
	}
}