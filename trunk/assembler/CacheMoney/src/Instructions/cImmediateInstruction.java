package Instructions;
import Interfaces.IInstruction;

public abstract class cImmediateInstruction implements IInstruction {

	@Override
	public String getEncodedInstruction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public abstract String getInstructionName();	

	@Override
	public abstract int getOpCode();
	
}
