library ieee,work;
use ieee.std_logic_1164.all;
use work.all;

entity ALU32bit is
	port
	(
		a	:	in	std_logic_vector(31 downto 0);
		b	:	in 	std_logic_vector(31 downto 0);
		op	:	in	std_logic;
		result:	out	std_logic_vector(31 downto 0);
		overflow:out std_logic
	);
end ALU32bit;

architecture STRUCTURE of ALU32bit is
	signal add_overflow : std_logic;
	signal add_result, mult_result : std_logic_vector(31 downto 0);
begin
	--instantiate a signed_adder
	adder1: work.signed_adder generic map (DATA_WIDTH=>32) port map (a, b, add_result, add_overflow);
	
	--instantiate a signed_multiply
	multiply1: work.signed_multiply generic map (DATA_WIDTH=>16) port map (a(15 downto 0), b(15 downto 0), mult_result);

	process(add_result, mult_result, add_overflow, op)
	begin
		if op = '0' then --op 0 is an add operation
			result <= add_result;
			overflow <= add_overflow;
		else -- op 1 is a multiply operation
			result <= mult_result;
			overflow <= '0'; -- overflow isn't possible in the multipler
		end if;
	end process;
end STRUCTURE;