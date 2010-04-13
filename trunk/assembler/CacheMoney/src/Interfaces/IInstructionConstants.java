package Interfaces;

import Instructions.Arithmetic.*;
import Instructions.Immediate.*;
import Instructions.Jump.*;

/**
 * This interface contains all available instructions for use within the assembler.
 */
public interface IInstructionConstants {
	
	/** Arithmetic Instructions **/
	
	/**
	 * ADD Instruction
	 */
	public static final Add ADD = new Add();
	
	/**
	 * MULT Instruction
	 */
	public static final IInstruction MULT = new Multiply();
	
	/**
	 * NOP Instruction
	 */
	public static final IInstruction NOP = new NoOperation();
	
	/** Immediate Instructions **/
	
	/**
	 * ADDI Instruction
	 */
	public static final IInstruction ADDI = new AddImmediate();
	
	/**
	 * BEQ Instruction
	 */
	public static final IInstruction BEQ = new BranchEqual();
	
	/**
	 * BNEQ Instruction
	 */
	public static final IInstruction BNEQ = new BranchNotEqual();
	
	/**
	 * LW Instruction
	 */
	public static final IInstruction LW = new LoadWord();
	
	/**
	 * SW Instruction
	 */
	public static final IInstruction SW = new StoreWord();
	
	/** Jump Instructions **/
	
	/**
	 * JMP Instruction
	 */
	public static final IInstruction JMP = new cJump();	
	
	/**
	 * Array of all available instructions.
	 */
	public static final IInstruction[] arInstructions = new IInstruction[] { ADD, MULT, NOP, ADDI, BEQ, BNEQ, LW, SW, JMP };

}
