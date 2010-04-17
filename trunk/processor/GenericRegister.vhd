LIBRARY ieee;
USE ieee.std_logic_1164.all;

entity GenericRegister is
	generic
	(
		DATA_WIDTH : natural := 32
	);
	port (
			D		:		IN	STD_LOGIC_VECTOR(31 DOWNTO 0);
			Clock	:		IN	STD_LOGIC;
			Enable	:		IN	STD_LOGIC;
			Reset	:		IN	STD_LOGIC;
			Q		:		OUT	STD_LOGIC_VECTOR(31 DOWNTO 0));
end GenericRegister;

architecture Behavior of GenericRegister is
begin
	process (Reset, Clock)
	begin
		if Reset = '1' then
			Q <= (OTHERS => '0');
		elsif Clock'EVENT and Clock = '1' and Enable= '1' then
			Q <= D;
		end if;
	end process;
end Behavior;