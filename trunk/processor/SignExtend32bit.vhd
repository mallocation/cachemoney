LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY SignExtend32bit IS
	PORT (		data_in		:	IN	STD_LOGIC_VECTOR(15 DOWNTO 0);
					data_out	:	OUT	STD_LOGIC_VECTOR(31 DOWNTO 0));
END SignExtend32bit;

ARCHITECTURE Behavior OF SignExtend32bit IS
BEGIN	
	data_out(15 downto 0) <= data_in;
	data_out(31 downto 16) <= (31 downto 16 => data_in(15));
END Behavior;