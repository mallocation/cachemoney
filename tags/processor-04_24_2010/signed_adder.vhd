library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_arith.all;
use ieee.std_logic_signed.all;

entity signed_adder is
	generic
	(
		DATA_WIDTH : natural := 32
	);
	port 
	(
		a	:	in 	std_logic_vector((DATA_WIDTH-1) downto 0);
		b	:	in	std_logic_vector((DATA_WIDTH-1) downto 0);
		result:	out	std_logic_vector((DATA_WIDTH-1) downto 0);
		overflow:	out std_logic
	);
end entity;

architecture rtl of signed_adder is
begin	
	--process statement handles the addition
	PROCESS(a,b)
		variable operand1 : integer;
		variable operand2 : integer;
		variable sum : integer;
	BEGIN
		--calculate the sum by converting the bit vectors to integers
		operand1 := conv_integer(a);
		operand2 := conv_integer(b);		
		sum := operand1 + operand2;
		
		--convert the sum to a std logic vector for the result
		--function conv_std_logic_vector(arg: integer, size: integer) return std_logic_vector;
		result <= conv_std_logic_vector(sum, DATA_WIDTH);
		
		--Overflow detection
		if (operand1 > 0 and operand2 > 0 and sum < 0) then
			overflow <= '1';
		elsif (operand1 < 0 and operand2 < 0 and sum > 0) then
			overflow <= '1';
		else
			overflow <= '0';
		end if;
	END PROCESS;
end rtl;
