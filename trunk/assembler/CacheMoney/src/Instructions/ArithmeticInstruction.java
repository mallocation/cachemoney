package Instructions;
import Interfaces.IInstruction;
import Interfaces.IArithmeticInstruction;
import Utilities.Conversion;

public abstract class ArithmeticInstruction implements IInstruction, IArithmeticInstruction {
	
	protected int _srcReg1;
	protected int _srcReg2;
	protected int _destReg;
	protected int _address;

/**	IInstruction Methods **/
	
	@Override
	public void setInstructionAddress(int address) {
		this._address = address;		
	}
	
	@Override
	public String getEncodedInstruction() {
		String sOpCode, sDestReg, sSrcReg1, sSrcReg2;
		sOpCode = Conversion.IntegerToBinaryString(this.getOpCode(), 3);
		sDestReg = Conversion.IntegerToBinaryString(this.getDestRegister(), 5);
		sSrcReg1 = Conversion.IntegerToBinaryString(this.getSourceRegister1(), 5);
		sSrcReg2 = Conversion.IntegerToBinaryString(this.getSourceRegister2(), 5);
		return this._address + " : " + sOpCode + sDestReg + sSrcReg1 + sSrcReg2 + Conversion.IntegerToBinaryString(0, 14);
	}
	
	@Override
	public abstract String getInstructionName();

	@Override
	public abstract int getOpCode();	
/** End IInstruction Methods **/
	
/** IArithmeticInstruction Methods **/
	@Override
	public void setDestRegister(int nRegister) {
		this._destReg = nRegister;
	}

	@Override
	public void setSourceRegister1(int nRegister) {
		this._srcReg1 = nRegister;
	}

	@Override
	public void setSourceRegister2(int nRegister) {
		this._srcReg2 = nRegister;
	}
	
	@Override
	public int getDestRegister() {
		return this._destReg;
	}
	@Override
	public int getSourceRegister1() {
		return this._srcReg1;
	}
	@Override
	public int getSourceRegister2() {
		return this._srcReg2;
	}
/** End IArithmeticInstruction Methods **/
}
