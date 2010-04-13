package Instructions;
import Interfaces.IImmediateInstruction;
import Interfaces.IInstruction;
import Utilities.Conversion;

/**
 * This class serves as a base class for Immediate instructions.
 */
public abstract class ImmediateInstruction implements IInstruction, IImmediateInstruction {
	
	/**
	 * Source register
	 */
	protected int _srcReg;
	
	/**
	 * Destination register
	 */
	protected int _destReg;
	
	/**
	 * Immediate value
	 */
	protected int _immediateValue;
	
	/**
	 * Instruction address
	 */
	protected int _address;
	
	@Override
	public void setInstructionAddress(int address) {
		this._address = address;		
	}
	
	@Override
	public int getInstructionAddress() {
		return this._address;
	}
	
	@Override
	public String getEncodedInstruction() {
		String sOpCode, sDestReg, sSrcReg1, sImmValue;
		//Convert Op Code
		sOpCode = Conversion.IntegerToBinaryString(this.getOpCode(), 3);
		//Set the Destination and Source Registers
		sDestReg = Conversion.IntegerToBinaryString(this.getDestRegister(), 5);
		sSrcReg1 = Conversion.IntegerToBinaryString(this.getSourceRegister(), 5);
		//Set the immediate value
		sImmValue = Conversion.IntegerToBinaryString(this.getImmediateValue(), 19);
		return sOpCode + sDestReg + sSrcReg1 + sImmValue;
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
