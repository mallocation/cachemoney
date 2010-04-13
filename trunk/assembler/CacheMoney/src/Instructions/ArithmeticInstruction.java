package Instructions;
import Interfaces.IInstruction;
import Interfaces.IArithmeticInstruction;
import Utilities.Conversion;

/**
 * This class is a base class for all Arithmetic Instructions.
 */
public abstract class ArithmeticInstruction implements IInstruction, IArithmeticInstruction {
	
	/**
	 * 1st source register
	 */
	protected int _srcReg1;
	
	/**
	 * 2nd source register
	 */
	protected int _srcReg2;
	
	/**
	 * Destination register
	 */
	protected int _destReg;
	
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
		String sOpCode, sDestReg, sSrcReg1, sSrcReg2;
		//Convert Op Code
		sOpCode = Conversion.IntegerToBinaryString(this.getOpCode(), 3);
		//Convert Destination and Source Registers
		sDestReg = Conversion.IntegerToBinaryString(this.getDestRegister(), 5);
		sSrcReg1 = Conversion.IntegerToBinaryString(this.getSourceRegister1(), 5);
		sSrcReg2 = Conversion.IntegerToBinaryString(this.getSourceRegister2(), 5);
		return sOpCode + sDestReg + sSrcReg1 + sSrcReg2 + Conversion.IntegerToBinaryString(0, 14);
	}
	
	@Override
	public abstract String getInstructionName();

	@Override
	public abstract int getOpCode();	
	
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

}
