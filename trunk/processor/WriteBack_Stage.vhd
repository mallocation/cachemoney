LIBRARY ieee;
USE ieee.std_logic_1164.all;

entity WriteBack_Stage is
	port (			
			DataMemory 	:	in std_logic_vector(31 downto 0);
			DestReg		:	in std_logic_vector(4 downto 0);
			output_for_jake: out std_logic);
end WriteBack_Stage;

architecture behavior of WriteBack_Stage is
begin
	output_for_jake <= '1';
end behavior;