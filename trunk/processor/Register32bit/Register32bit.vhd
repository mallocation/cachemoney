LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY Register32bit IS
	PORT (
			D			:		IN		STD_LOGIC_VECTOR(31 DOWNTO 0);
			Clock	:		IN		STD_LOGIC;
			Enable	:		IN		STD_LOGIC;
			Reset	:		IN		STD_LOGIC;
			Q			:		OUT	STD_LOGIC_VECTOR(31 DOWNTO 0));
END Register32bit;

ARCHITECTURE Behavior OF Register32bit IS
BEGIN
	PROCESS (Reset, Clock)
	BEGIN	
		IF Reset = '1' THEN
			Q <= (OTHERS => '0');
		ELSIF Clock'EVENT and Clock = '1' THEN
			Q <= D;
		END IF;
	END PROCESS;
END Behavior;