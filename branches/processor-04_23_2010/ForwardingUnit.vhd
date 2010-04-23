LIBRARY ieee;
use ieee.std_logic_1164.all;

entity ForwardingUnit is
	port
	(
		ExMemRegWrite : in std_logic;
		MemWbRegWrite : in std_logic;
		ExMemRd	:	in	std_logic_vector(4 downto 0);
		IdExRs : in std_logic_vector(4 downto 0);
		IdExRt : in std_logic_vector(4 downto 0);
		MemWbRd : in std_logic_vector(4 downto 0);
		ForwardA : out std_logic_vector(1 downto 0);
		ForwardB : out std_logic_vector(1 downto 0));
end ForwardingUnit;

architecture STRUCTURE of ForwardingUnit is
begin
	process(ExMemRegWrite, MemWbRegWrite, ExMemRd, IdExRs, IdExRt, MemWbRd)
	begin
		if ExMemRegWrite = '1' and ExMemRd /= "00000" and ExMemRd = IdExRs then
			ForwardA <= "10";
		elsif MemWbRegWrite = '1' and MemWbRd /= "00000" and ExMemRd /= IdExRs and MemWbRd = IdExRs then
			ForwardA <= "01";
		else
			ForwardA <= "00";
		end if;
		
		if ExMemRegWrite = '1' and ExMemRd /= "00000" and ExMemRd = IdExRt then
			ForwardB <= "10";
		elsif MemWbRegWrite = '1' and MemWbRd /= "00000" and ExMemRd /= IdExRt and MemWbRd = IdExRt then
			ForwardB <= "01";
		else
			ForwardB <= "00";
		end if;
	end process;
end structure;