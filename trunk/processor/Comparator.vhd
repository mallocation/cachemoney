library ieee;
use ieee.std_logic_1164.all;

entity Comparator is
	port
	(a			:	in	std_logic_vector(31 downto 0);
	 b			:	in 	std_logic_vector(31 downto 0);
	 output 		:	out	std_logic);
end Comparator;

architecture STRUCTURE of Comparator is
begin
	process (a, b)
	begin
		if a = b then
			output <= '1';
		else
			output <= '0';
		end if;
	end process;
end structure;
		
	