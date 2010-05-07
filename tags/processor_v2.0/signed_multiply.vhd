-------------------------------------------------------------------------
--
-- signed_multiply.vhd
-- 
-- Author: Cache Money
--
-- This file represents a multiplier that can perform multiplication on
-- two 16-bit numbers.
--
-- Specify DATA_WIDTH to have a custom width multiplier.
--
-------------------------------------------------------------------------

library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_arith.all;
use ieee.std_logic_signed.all;

entity signed_multiply is
	generic
	(
		DATA_WIDTH : natural := 16
	);
	port 
	(
		a		:	in	std_logic_vector((DATA_WIDTH-1) downto 0);
		b		:	in	std_logic_vector((DATA_WIDTH-1) downto 0);
		result	:	out	std_logic_vector((2*DATA_WIDTH-1) downto 0)
	);
end entity;

architecture rtl of signed_multiply is
begin
	PROCESS(a,b)
		variable operand1 : integer;
		variable operand2 : integer;
		variable product : integer;
	BEGIN
	
		--Convert the two bit vectors to integers
		operand1 := conv_integer(a);
		operand2 := conv_integer(b);
		
		--Multiply the two integers
		product := operand1 * operand2;
		
		--Output the result as a bit vector
		result <= conv_std_logic_vector(product,2*DATA_WIDTH);
	END PROCESS;
end rtl;