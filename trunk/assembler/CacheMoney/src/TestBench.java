import java.io.File;

//import Instructions.InstructionFactory;
import Utilities.Assembler;
import Utilities.AssemblyParser;

public class TestBench {
	
	private final String sFilePath = "C:\\TEMP\\test.asm";
	
	public TestBench() {
	}
	
	public void test() {
		//oInstFactory.createInstruction("add", "$1, $2, $3");
		
		String sLine = "add $1, $2, $3  #Test stripping comments.";
		String sLine2 = "#Test This line is only comments.";
		String sLine3 = "Main: Test this!";
		String sLine4 = "symbAdd	:	add $1, $2, $3#Comments Oh Yeah!!";
		String sLine5 = "add $1, $2, $3#commentcommentcomment";
			
		Assembler oAssembler = new Assembler(new File(sFilePath));
		//oAssembler.calculateSymbolicAddresses();
		//oAssembler.parseInstructions();
		oAssembler.parseAssemblyFile();
		String[] mifContents = oAssembler.getMemoryFileContents();
		for (int i=0; i<mifContents.length; i++) {
			System.out.println(mifContents[i]);
		}
//		System.out.println(oAssembler.getInstructionSection());
//		System.out.println(oAssembler.getDataSection());
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestBench oTestBench = new TestBench();
		oTestBench.test();
	}

}
