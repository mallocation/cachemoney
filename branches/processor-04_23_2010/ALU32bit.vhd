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
	--signed adder in work library
	component signed_adder
		port(a,b: in std_logic_vector(31 downto 0);
			 result: out std_logic_vector(31 downto 0);
			 overflow: out std_logic);
	end component;
	--signed_multiply in work library
	component signed_multiply
		port(a,b: in std_logic_vector(15 downto 0);
			 result: out std_logic_vector(31 downto 0));
	end component;
	signal add_overflow : std_logic;
	signal add_result, mult_result : std_logic_vector(31 downto 0);
begin
	--instantiate a signed_adder
	adder1: signed_adder
		port map (a, b, add_result, add_overflow);
	--instantiate a signed_multiply
	mult1: signed_multiply
		port map (a(15 downto 0), b(15 downto 0), mult_result);

	--process fires on both operands, as well as the operation type
	process(a,b,op)
	begin
		if op = '0' then --op 0 is an add operation
			result <= add_result;
			overflow <= add_overflow;
		elsif op = '1' then	-- op 1 is a multiply operation
			result <= mult_result;
			overflow <= '0'; -- overflow isn't possible in the multipler
		end if;
	end process;
end STRUCTURE;