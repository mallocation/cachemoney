library ieee;
use ieee.std_logic_1164.all;

entity Comparator is
	port
	(a			:	in	std_logic_vector(31 downto 0);
	 b			:	in 	std_logic_vector(31 downto 0);
	CheckEqual	:	in	std_logic;
	output 		:	out	std_logic);
end Comparator;

architecture STRUCTURE of Comparator is
begin
	process (a, b, CheckEqual)
	begin
		if CheckEqual = '1' then
			if a = b then
				output <= '1';
			else
				output <= '0';
			end if;
		else
			if a /= b then
				output <= '1';
			else
				output <= '0';
			end if;
		end if;
	end process;
end structure;
		
	