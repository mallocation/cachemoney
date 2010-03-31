package Instructions;
import Interfaces.IInstruction;
import Interfaces.IArithmeticInstruction;

public abstract class cArithmeticInstruction implements IInstruction, IArithmeticInstruction {
	
	protected int _srcReg1;
	protected int _srcReg2;
	protected int _destReg;

/**	IInstruction Methods **/
	@Override
	public String getEncodedInstruction() {
		return null;
	}
	
	@Override
	public abstract String getInstructionName();

	@Override
	public abstract int getOpCode();	
/** End IInstruction Methods **/
	
/** IArithmeticInstruction Methods **/
	@Override
	public void setDestRegister(int nRegister) {
		_destReg = nRegister;
	}

	@Override
	public void setSourceRegister1(int nRegister) {
		_srcReg1 = nRegister;
	}

	@Override
	public void setSourceRegister2(int nRegister) {
		_srcReg2 = nRegister;
	}
	
	@Override
	public int getDestRegister() {
		return _destReg;
	}
	@Override
	public int getSourceRegister1() {
		return _srcReg1;
	}
	@Override
	public int getSourceRegister2() {
		return _srcReg2;
	}
/** End IArithmeticInstruction Methods **/
}
