package Instructions;

import Interfaces.IInstruction;

public class cNoOperation implements IInstruction {

	@Override
	public String getEncodedInstruction() {
		//This should return "Add $0, $0, $0" encoded in hex/bit/etc
		return null;
	}

	@Override
	public String getInstructionName() {
		return "nop";
	}

	@Override
	public int getOpCode() {
		return 0;
	}

}
