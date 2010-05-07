-------------------------------------------------------------------------
--
-- GenericRegister.vhd
-- 
-- Author: Cache Money
--
-- This file represents a generic register that is used to hold specific
-- values.  It is fired on each clock rise.  If Reset is high, then
-- the register will be reset to '0'.  If Enable is high, then the value
-- at D will be latched into the register.
--
-- Specify DATA_WIDTH for a custom width register.
--
-------------------------------------------------------------------------

LIBRARY ieee;
USE ieee.std_logic_1164.all;

entity GenericRegister is
	generic
	(
		DATA_WIDTH : natural := 32
	);
	port (
			D		:		IN	STD_LOGIC_VECTOR((DATA_WIDTH-1) DOWNTO 0);
			Clock	:		IN	STD_LOGIC;
			Enable	:		IN	STD_LOGIC;
			Reset	:		IN	STD_LOGIC;
			Q		:		OUT	STD_LOGIC_VECTOR((DATA_WIDTH-1) DOWNTO 0));
end GenericRegister;

architecture Behavior of GenericRegister is
begin
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
end Behavior;