-------------------------------------------------------------------------
--
-- controlUnit.vhd
-- 
-- Author: Cache Money
--
-- This file represents the control unit for the five stage pipelined
-- processor.  It is to be contained within the Instruction Decode stage.
--
-------------------------------------------------------------------------

library ieee;
use ieee.std_logic_1164.all;

entity controlUnit is
PORT (
	opCode				:	in 	std_logic_vector(2 downto 0);
	comparator			:	in 	std_logic;
	HDUBranchHazards	:	in 	std_logic;
	HDULoadWordHazards	:	in	std_logic;
	WBControl			:	out std_logic_vector(1 downto 0);
	MEMControl			:	out std_logic_vector(1 downto 0);
	PCMuxControl		:	out std_logic_vector(1 downto 0);
	Reg2MuxSel			:	out std_logic;
	ALUControl			:	out std_logic;
	ALU_SourceBMux		:	out std_logic;
	FlushIDStage		:	out std_logic;
	EnableIDStage		:	out std_logic;
	EnableIFStage		:	out std_logic;
	FlushEXStage		:	out std_logic
);
end controlUnit;

architecture behavioral of controlUnit is
begin
	
	-- Process fires on opcode changes, comparator results, and hazard detection lines
	process(opCode, comparator, HDUBranchHazards, HDULoadWordHazards)
	begin
	
		--Initialize certain output lines
		FlushIDStage <= '0';
		EnableIDStage <= '1';
		EnableIFStage <= '1';
		FlushEXStage <= '0';		
		PCMuxControl <= "00";
		
		if HDULoadWordHazards = '1' then 
			--Flush the EX stage, and stall the IF and ID stages
			EnableIFStage <= '0';
			EnableIDStage <= '0';
			FlushEXStage <= '1';			
		else
			EnableIFStage <= '1';
			EnableIDStage <= '1';
			FlushEXStage <= '0';			
		end if;
		
		if HDUBranchHazards = '1' then
			--Flush the EX stage, and stall the IF and ID stages
			EnableIFStage <= '0';
			EnableIDStage <= '0';
			FlushEXStage <= '1';
		end if;
		
		
		case opCode is
			when "000" =>			-- Add
				WBControl <= "11";
				MEMControl <= "00";
				Reg2MuxSel <= '1';
				ALUControl <= '0';
				ALU_SourceBMux <= '0';
			when "001" =>			-- Add Immediate
				WBControl <= "11";
				MEMControl <= "00";
				Reg2MuxSel <= '1';
				ALUControl <= '0';
				ALU_SourceBMux <= '1';
			when "010" =>			-- Multiply
				WBControl <= "11";
				MEMControl <= "00";
				Reg2MuxSel <= '1';
				ALUControl <= '1';
				ALU_SourceBMux <= '0';
			when "011" =>			-- Load Word
				WBControl <= "10";
				MEMControl <= "01";
				Reg2MuxSel <= '0';
				ALUControl <= '0';
				ALU_SourceBMux <= '1';
			when "100" =>			-- Store Word
				WBControl <= "00";
				MEMControl <= "10";
				Reg2MuxSel <= '0';
				ALUControl <= '0';
				ALU_SourceBMux <= '1';
			when "101" =>			-- Branch on Equal
				WBControl <= "00";
				MEMControl <= "00";
				if HDUBranchHazards = '0' then
					if comparator = '1' then --comparator output is '1' when equal
						PCMuxControl <= "01";
						EnableIDStage <= '0';
						FlushIDStage <= '1';
					else
						PCMuxControl <= "00";
						EnableIDStage <= '1';
						FlushIDStage <= '0';
					end if;
				end if;
				Reg2MuxSel <= '0';
				ALUControl <= '0';
				ALU_SourceBMux <= '0';
			when "110" =>			-- Branch on Not Equal
				WBControl <= "00";
				MEMControl <= "00";
				if HDUBranchHazards = '0' then
					if comparator = '0' then --comparator output is '0' when not equal
						PCMuxControl <= "01";
						EnableIDStage <= '0';
						FlushIDStage <= '1';
					else
						PCMuxControl <= "00";
						EnableIDStage <= '1';
						FlushIDStage <= '0';
					end if;
				end if;
				Reg2MuxSel <= '0';
				ALUControl <= '0';
				ALU_SourceBMux <= '0';
			when "111" =>			-- Jump
				WBControl <= "00";
				MEMControl <= "00";
				PCMuxControl <= "10";
				Reg2MuxSel <= '0';
				ALUControl <= '0';
				ALU_SourceBMux <= '0';
				EnableIDStage <= '0';
				FlushIDStage <= '1';
			when others =>
				null;
		end case;
	end process;
end behavioral;