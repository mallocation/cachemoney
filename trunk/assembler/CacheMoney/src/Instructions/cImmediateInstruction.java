package Instructions;
import Interfaces.IImmediateInstruction;
import Interfaces.IInstruction;

public abstract class cImmediateInstruction implements IInstruction, IImmediateInstruction {
	
	protected int _srcReg;
	protected int _destReg;
	protected int _immediateValue;
	
	@Override
	public String getEncodedInstruction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public abstract String getInstructionName();	

	@Override
	public abstract int getOpCode();

	@Override
	public int getDestRegister() {	
		return _destReg;
	}

	@Override
	public int getImmediateValue() {
		return _immediateValue;
	}

	@Override
	public int getSourceRegister() {
		return _srcReg;
	}
	
}
