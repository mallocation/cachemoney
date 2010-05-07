--------------------------------------------------------------------------------------------
--	Authors: Cache Money	
--
-- 	This component describes the behavior of a 32 bit register.
--  The register's value can be changed to the value on the 'D' port
--  on the rising edge of the clock if the write enable is set to high.
--	
--	Reset is synchronous on high.
--------------------------------------------------------------------------------------------

LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY Register32bit IS
	PORT (
			D		:		IN	STD_LOGIC_VECTOR(31 DOWNTO 0);
			Clock	:		IN	STD_LOGIC;
			Enable	:		IN	STD_LOGIC;
			Reset	:		IN	STD_LOGIC;
			Q		:		OUT	STD_LOGIC_VECTOR(31 DOWNTO 0));
END Register32bit;		

ARCHITECTURE Behavior OF Register32bit IS
BEGIN
	process (Clock)
	begin
		if Clock'EVENT and Clock='1' then
			if Reset = '1' then
				Q <= (OTHERS => '0');
			elsif Enable='1' then
				Q <= D;
			end if;
		end if;
	end process;
END Behavior;