-------------------------------------------------------------------------
--
-- ForwardingUnit.vhd
-- 
-- Author: Cache Money
--
-- This file represents a forwarding unit that is used for forwarding
-- the most up-to-date register values to the execution stage in the
-- pipeline.
--
-------------------------------------------------------------------------

LIBRARY ieee;
use ieee.std_logic_1164.all;

entity ForwardingUnit is
	port
	(
		-- Destination Register from the Memory Stage
		RegDest_FromMEMStage	:	in std_logic_vector(4 downto 0);

		-- Destination Register from the Write back stage
		RegDest_FromWBStage		:	in std_logic_vector(4 downto 0);

		-- WB Register Write Enable From Memory stage (Specifying storing of new register value)
		RegWrite_FromMEMStage 	:	in std_logic;

		-- WB Register Write Enable From Write back stage (Specifying storing of new register value)
		RegWrite_FromWBStage	:	in std_logic;

		-- Source Register 1 from ALU
		RegSrc1_FromEXStage		:	in std_logic_vector(4 downto 0);

		-- Source Register 2 from ALU
		RegSrc2_FromEXStage		:	in std_logic_vector(4 downto 0);
		
		-- Source Register for Store Word
		RegSrc_ForStoreWord		:	in std_logic_vector(4 downto 0);

		-- Forwarding Signal to the 1st operand of the ALU
		ForwardA : out std_logic_vector(1 downto 0);
		
		-- Forwarding Signal to the 2nd operand of the ALU
		ForwardB : out std_logic_vector(1 downto 0);
		
		-- Forwarding Signal to the Store Word MUX
		ForwardC : out std_logic_vector(1 downto 0));
		
end ForwardingUnit;

architecture STRUCTURE of ForwardingUnit is
begin
	process(RegWrite_FromMEMStage, RegWrite_FromWBStage, RegDest_FromMEMStage, RegDest_FromWBStage, RegSrc1_FromEXStage, RegSrc2_FromEXStage, RegSrc_ForStoreWord)
	begin
	
		--------------------------
		-- 1st Source Register
		--------------------------
		-- If the Memory Stage has register write enabled, and the write register equals the 1st source register, then forward 
		-- source 1 from the Memory stage
		if RegWrite_FromMEMStage = '1' and RegDest_FromMEMStage /= "00000" and RegDest_FromMEMStage = RegSrc1_FromEXStage then
			ForwardA <= "10";
		--If the Write Back Stage has register write enabled, and the write register equals the 1st source register, then forward
		-- source 1 from the Write Back Stage
		elsif RegWrite_FromWBStage = '1' and RegDest_FromWBStage /= "00000" and RegDest_FromMEMStage /= RegSrc1_FromEXStage and RegDest_FromWBStage = RegSrc1_FromEXStage then
			ForwardA <= "01";
		-- Otherwise, there is no forwarding, so get the value from the register file
		else
			ForwardA <= "00";
		end if;
		
		
		--------------------------
		-- 2nd Source Register
		--------------------------
		-- If the Memory Stage has register write enabled, and the write register equals the 2nd source register, then forward 
		-- source 2 from the Memory stage
		if RegWrite_FromMEMStage = '1' and RegDest_FromMEMStage /= "00000" and RegDest_FromMEMStage = RegSrc2_FromEXStage then
			ForwardB <= "10";
		--If the Write Back Stage has register write enabled, and the write register equals the 2nd source register, then forward
		-- source 2 from the Write Back Stage
		elsif RegWrite_FromWBStage = '1' and RegDest_FromWBStage /= "00000" and RegDest_FromMEMStage /= RegSrc2_FromEXStage and RegDest_FromWBStage = RegSrc2_FromEXStage then
			ForwardB <= "01";
		-- Otherwise, there is no forwarding, so get the value from the register file
		else
			ForwardB <= "00";
		end if;
		
		--------------------------
		-- Store Word Source Register
		--------------------------
		if RegWrite_FromMEMStage = '1' and RegDest_FromMEMStage /= "00000" and RegDest_FromMEMStage = RegSrc_ForStoreWord then
			ForwardC <= "10";
		elsif RegWrite_FromWBStage = '1' and RegDest_FromWBStage /= "00000" and RegDest_FromWBStage = RegSrc_ForStoreWord then			
			ForwardC <= "01";
		else
			ForwardC <= "00";
		end if;
		
	end process;
end structure;