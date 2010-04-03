import Instructions.InstructionFactory;
import Utilities.AssemblyParser;

public class TestBench {
	
	private InstructionFactory oInstFactory;
	
	public TestBench() {
		oInstFactory = new InstructionFactory();	
	}
	
	public void test() {
		oInstFactory.createInstruction("add", "$1, $2, $3");
		
		String sLine = "add $1, $2, $3#Test stripping comments.";
		String sLine2 = "#Test This line is only comments.";
		String sLine3 = "Main: Testthis!";
		String sLine4 = "symbAdd	:	add $1, $2, $3";
		String sLine5 = "add $1, $2, $3";
			
		sLine = AssemblyParser.stripComments(sLine);
		sLine2 = AssemblyParser.stripComments(sLine2);
		sLine3 = AssemblyParser.getSymbolicReference(sLine3);
		sLine4 = AssemblyParser.getAssembly(sLine4);
		sLine5 = AssemblyParser.getAssembly(sLine5);
		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestBench oTestBench = new TestBench();
		oTestBench.test();
	}

}
