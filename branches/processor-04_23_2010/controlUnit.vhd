library ieee;
use ieee.std_logic_1164.all;

entity controlUnit is
PORT (
	opCode	:	in STD_LOGIC_VECTOR(2 downto 0);
	WBControl	:	out STD_LOGIC_VECTOR(1 downto 0);
	MEMControl	:	out STD_LOGIC;
	PCMuxControl	:	out STD_LOGIC_VECTOR(1 downto 0);
	Reg2MuxSel	:	out STD_LOGIC;
	ALUControl	:	out STD_LOGIC;
	ALU_SourceBMux	:	out STD_LOGIC	
);
end controlUnit;

architecture behavioral of controlUnit is
begin
	process(opCode)
	begin
		case opCode is
			when "000" =>			-- Add
				WBControl <= "11";
				MEMControl <= '0';
				PCMuxControl <= "00";
				Reg2MuxSel <= '1';
				ALUControl <= '0';
				ALU_SourceBMux <= '0';
			when "001" =>			-- Add Immediate
				WBControl <= "11";
				MEMControl <= '0';
				PCMuxControl <= "00";
				Reg2MuxSel <= '1';
				ALUControl <= '0';
				ALU_SourceBMux <= '1';
			when "010" =>			-- Multiply
				WBControl <= "11";
				MEMControl <= '0';
				PCMuxControl <= "00";
				Reg2MuxSel <= '1';
				ALUControl <= '1';
				ALU_SourceBMux <= '0';
			when "011" =>			-- Load Word
				WBControl <= "10";
				MEMControl <= '0';
				PCMuxControl <= "00";
				Reg2MuxSel <= '0';
				ALUControl <= '0';
				ALU_SourceBMux <= '1';
			when "100" =>			-- Store Word
				WBControl <= "00";
				MEMControl <= '1';
				PCMuxControl <= "00";
				Reg2MuxSel <= '0';
				ALUControl <= '0';
				ALU_SourceBMux <= '1';
			when "101" =>			-- Branch on Equal
				WBControl <= "00";
				MEMControl <= '0';
				PCMuxControl <= "01";
				Reg2MuxSel <= '0';
				ALUControl <= '0';
				ALU_SourceBMux <= '0';
			when "110" =>			-- Branch on Not Equal
				WBControl <= "00";
				MEMControl <= '0';
				PCMuxControl <= "01";
				Reg2MuxSel <= '0';
				ALUControl <= '0';
				ALU_SourceBMux <= '0';
			when "111" =>			-- Jump
				WBControl <= "00";
				MEMControl <= '0';
				PCMuxControl <= "10";
				Reg2MuxSel <= '0';
				ALUControl <= '0';
				ALU_SourceBMux <= '0';
			when others =>
				null;
		end case;
	end process;
end behavioral;