package Instructions.Immediate;

import Interfaces.IImmediateInstruction;
import Interfaces.IInstruction;

public class cBranchNotEqual implements IInstruction, IImmediateInstruction {

	private int _srcReg;
	private int _destReg;
	private int _branchAddress;
	
	@Override
	public String getEncodedInstruction() {
		return null;
	}

	@Override
	public String getInstructionName() {
		return "bneq";
	}

	@Override
	public int getOpCode() {
		return 6;
	}

	@Override
	public int getDestRegister() {
		return _destReg;
	}

	@Override
	public int getImmediateValue() {
		return _branchAddress;
	}

	@Override
	public int getSourceRegister() {
		return _srcReg;
	}

}

