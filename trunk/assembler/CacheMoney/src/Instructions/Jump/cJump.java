package Instructions.Jump;

import Interfaces.IInstruction;
import Interfaces.IJumpInstruction;
import Utilities.Conversion;

public class cJump implements IInstruction, IJumpInstruction {
	
	private int _address;
	private int _jmpAddress;
	
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
		String sOpCode, sAddress;
		sOpCode = Conversion.IntegerToBinaryString(this.getOpCode(), 3);
		sAddress = Conversion.IntegerToBinaryString(this.getAddress(), 29);		
		return sOpCode + sAddress;
	}
	@Override
	public String getInstructionName() {
		return "jmp";
	}
	@Override
	public int getOpCode() {
		return 7;
	}
	@Override
	public int getAddress() {
		return _jmpAddress;
	}
	@Override
	public void setAddress(int nAddress) {
		_jmpAddress = nAddress;		
	}
}
