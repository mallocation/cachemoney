package Instructions.Immediate;

import Interfaces.IImmediateInstruction;
import Interfaces.IInstruction;

public class cBranchEqual implements IInstruction, IImmediateInstruction {
	private int _srcReg;
	private int _destReg;
	private int _branchAddress;

	@Override
	public String getEncodedInstruction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInstructionName() {
		return "beq";
	}

	@Override
	public int getOpCode() {
		return 5;
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
