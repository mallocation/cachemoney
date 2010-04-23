LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY SignExtend32bit IS
	generic
	(
		DATA_WIDTH : natural := 28
	);
	PORT (		data_in		:	IN	STD_LOGIC_VECTOR(DATA_WIDTH-1 DOWNTO 0);
					data_out	:	OUT	STD_LOGIC_VECTOR(31 DOWNTO 0));
END SignExtend32bit;

ARCHITECTURE Behavior OF SignExtend32bit IS
BEGIN	
	data_out(DATA_WIDTH-1 downto 0) <= data_in;
	data_out(31 downto DATA_WIDTH) <= (31 downto DATA_WIDTH => data_in(DATA_WIDTH-1));
END Behavior;