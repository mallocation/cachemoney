LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY WriteBack_Stage IS
	PORT (
			DataMemory 	:	in std_logic_vector(31 downto 0);
			DestReg		:	in std_logic_vector(4 downto 0);
			
END Register32bit;