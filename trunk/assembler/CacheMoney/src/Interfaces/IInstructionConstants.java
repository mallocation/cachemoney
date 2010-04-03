package Interfaces;

import Instructions.Arithmetic.*;
import Instructions.Immediate.*;
import Instructions.Jump.*;

public interface IInstructionConstants {
	
	/** Arithmetic Instructions **/
	public static final IInstruction ADD = new Add();
	public static final IInstruction MULT = new Multiply();
	public static final IInstruction NOP = new NoOperation();
	
	/** Immediate Instructions **/
	public static final IInstruction ADDI = new AddImmediate();
	public static final IInstruction BEQ = new BranchEqual();
	public static final IInstruction BNEQ = new BranchNotEqual();
	public static final IInstruction LW = new LoadWord();
	public static final IInstruction SW = new StoreWord();
	
	/** Jump Instructions **/
	public static final IInstruction JMP = new cJump();

	
	public static final IInstruction[] arInstructions = new IInstruction[] { ADD, MULT, NOP, ADDI, BEQ, BNEQ, LW, SW, JMP };

}
