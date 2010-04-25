LIBRARY ieee;
use ieee.std_logic_1164.all;

entity ForwardingUnit is
	port
	(
		RegDest_FromMEMStage	:	in std_logic_vector(4 downto 0);
		--ExMemRd	:	in	std_logic_vector(4 downto 0);
		RegDest_FromWBStage		:	in std_logic_vector(4 downto 0);
		--MemWbRd : in std_logic_vector(4 downto 0);
		RegWrite_FromMEMStage 	:	in std_logic;
		--ExMemRegWrite : in std_logic;
		RegWrite_FromWBStage	:	in std_logic;
		--MemWbRegWrite : in std_logic;
		RegSrc1_FromEXStage		:	in std_logic_vector(4 downto 0);
		--IdExRs : in std_logic_vector(4 downto 0);
		RegSrc2_FromEXStage		:	in std_logic_vector(4 downto 0);
		--IdExRt : in std_logic_vector(4 downto 0);
		
		ForwardA : out std_logic_vector(1 downto 0);
		ForwardB : out std_logic_vector(1 downto 0));
end ForwardingUnit;

architecture STRUCTURE of ForwardingUnit is
begin
	process(RegWrite_FromMEMStage, RegWrite_FromWBStage, RegDest_FromMEMStage, RegSrc1_FromEXStage, RegSrc2_FromEXStage, RegDest_FromWBStage)
	begin
		if RegWrite_FromMEMStage = '1' and RegDest_FromMEMStage /= "00000" and RegDest_FromMEMStage = RegSrc1_FromEXStage then
			ForwardA <= "10";
		elsif RegWrite_FromWBStage = '1' and RegDest_FromWBStage /= "00000" and RegDest_FromMEMStage /= RegSrc1_FromEXStage and RegDest_FromWBStage = RegSrc1_FromEXStage then
			ForwardA <= "01";
		else
			ForwardA <= "00";
		end if;
		
		if RegWrite_FromMEMStage = '1' and RegDest_FromMEMStage /= "00000" and RegDest_FromMEMStage = RegSrc2_FromEXStage then
			ForwardB <= "10";
		elsif RegWrite_FromWBStage = '1' and RegDest_FromWBStage /= "00000" and RegDest_FromMEMStage /= RegSrc2_FromEXStage and RegDest_FromWBStage = RegSrc2_FromEXStage then
			ForwardB <= "01";
		else
			ForwardB <= "00";
		end if;
	end process;
end structure;