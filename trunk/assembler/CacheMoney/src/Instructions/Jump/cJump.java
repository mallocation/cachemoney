package Instructions.Jump;

import Interfaces.IInstruction;
import Interfaces.IJumpInstruction;

public class cJump implements IInstruction, IJumpInstruction {
	
	private int _jmpAddress;

	@Override
	public String getEncodedInstruction() {
		// TODO Auto-generated method stub
		return null;
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

}
