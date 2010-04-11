package Instructions;
import Interfaces.IImmediateInstruction;
import Interfaces.IInstruction;
import Utilities.Conversion;

public abstract class ImmediateInstruction implements IInstruction, IImmediateInstruction {
	
	protected int _srcReg;
	protected int _destReg;
	protected int _immediateValue;
	protected int _address;
	
	@Override
	public void setInstructionAddress(int address) {
		this._address = address;		
	}
	
	@Override
	public String getEncodedInstruction() {
		String sOpCode, sDestReg, sSrcReg1, sImmValue;
		sOpCode = Conversion.IntegerToBinaryString(this.getOpCode(), 3);
		sDestReg = Conversion.IntegerToBinaryString(this.getDestRegister(), 5);
		sSrcReg1 = Conversion.IntegerToBinaryString(this.getSourceRegister(), 5);
		sImmValue = Conversion.IntegerToBinaryString(this.getImmediateValue(), 19);
		return this._address + " : " + sOpCode + sDestReg + sSrcReg1 + sImmValue;
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

	@Override
	public void setDestRegister(int nRegister) {
		_destReg = nRegister;
	}

	@Override
	public void setImmediateValue(int nValue) {
		_immediateValue = nValue;
	}

	@Override
	public void setSourceRegister(int nRegister) {
		_srcReg = nRegister;
	}
	
}
