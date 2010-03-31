import Instructions.cInstructionFactory;
import Utilities.cAssemblyParser;

public class TestBench {
	
	private cInstructionFactory oInstFactory;
	
	public TestBench() {
		oInstFactory = new cInstructionFactory();	
	}
	
	public void test() {
		//oInstFactory.createInstruction("add", "$1, $2, $3");
		
		String sLine = "add $1, $2, $3#Test stripping comments.";
		String sLine2 = "#Test This line is only comments.";
		String sLine3 = "Main: Testthis!";
		String sLine4 = "symbAdd	:	add $1, $2, $3";
			
		sLine = cAssemblyParser.stripComments(sLine);
		sLine2 = cAssemblyParser.stripComments(sLine2);
		sLine3 = cAssemblyParser.getSymbolicAddress(sLine3);
		sLine4 = cAssemblyParser.getSymbolicAddress(sLine4);
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestBench oTestBench = new TestBench();
		oTestBench.test();
	}

}
