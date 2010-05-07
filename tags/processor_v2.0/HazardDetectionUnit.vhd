-------------------------------------------------------------------------
--
-- HazardDetectionUnit.vhd
-- 
-- Author: Cache Money
--
-- This file represents the Hazard Detection Unit that is used in the
-- Instruction Decode stage.  It detects two types of hazards.
-- 1. Load Word Hazards
--		If a load word is in the EX Stage, then stall the if/id stages
--		and let the lw instruction get past the memory stage.
-- 2. Branch Hazards
--    	If a branch instruction is in the Instruction Decode stage, wait
--	 	until all previous instructions that write to one of the
--		registers involved in the branch instruction are done.
--		This removes the need to place any forwarding logic into the 
--		decode stage.
--
-------------------------------------------------------------------------

library ieee;
use ieee.std_logic_1164.all;

entity HazardDetectionUnit is
	port (
		Instruction			:	in	std_logic_vector(31 downto 0);
		RegDestFromEXStage	:	in std_logic_vector(4 downto 0);
		MEMRd_WBWr_From_EX	:	in std_logic;
		RegDestFromMEMStage	:	in 	std_logic_vector(4 downto 0);
		MEMRd_WBWr_From_MEM	:	in 	std_logic;
		RegDestFromWBStage	:	in	std_logic_vector(4 downto 0);
		WBWr_From_WB		:	in 	std_logic;
		MemReadFromEXStage  :   in std_logic;		
		HDUBranchHazards	:	out std_logic;
		HDULoadWordHazards	:	out std_logic
	);	
end HazardDetectionUnit;

architecture behavior of HazardDetectionUnit is
	signal opcode	:	std_logic_vector(2 downto 0);
	signal InstructionRD	:	std_logic_vector(4 downto 0);
	signal InstructionRS	:	std_logic_vector(4 downto 0);
	signal InstructionRT    :   std_logic_vector(4 downto 0);
begin
	opcode <= Instruction(31 downto 29);
	InstructionRD <= Instruction(28 downto 24);
	InstructionRS <= Instruction(23 downto 19);
	InstructionRT <= Instruction(18 downto 14);
	
	process(opcode, InstructionRD, InstructionRS, InstructionRT, RegDestFromEXStage, RegDestFromMEMStage, RegDestFromWBStage, MemReadFromEXStage, MEMRd_WBWr_From_EX, MEMRd_WBWr_From_MEM,WBWr_From_WB)
	begin
		
		--Initialize Signals
		HDUBranchHazards <= '0';
		HDULoadWordHazards <= '0';
		
		--Check for Load Word hazards
		if MemReadFromEXStage = '1' and ((RegDestFromEXStage = InstructionRS) or (RegDestFromEXStage = InstructionRT)) then
			HDULoadWordHazards <= '1';
		end if;

		--Check for Branch Hazards
		if opcode = "101" or opcode = "110" then -- Branch on equal, branch on not equal
			if (((InstructionRD = RegDestFromEXStage or InstructionRS = RegDestFromEXStage) and MemRD_WBWr_From_EX = '1') or
				((InstructionRD = RegDestFromMEMStage or InstructionRS = RegDestFromMEMStage) and MemRD_WBWr_From_MEM = '1') or 
				((InstructionRD = RegDestFromWBStage or InstructionRS = RegDestFromWBStage) and WBWr_From_WB = '1')) then
				HDUBranchHazards <= '1';
			else
				HDUBranchHazards <= '0';					
			end if;
		end if;
	end process;
end behavior;