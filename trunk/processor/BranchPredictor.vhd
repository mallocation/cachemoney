library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

entity BranchPredictor is
	port
	(
		pc_address	:	in 	std_logic_vector(31 downto 0);
		cache_hit	:	out std_logic;
	);
end BranchPredictor;

architecture Behavior of BranchPredictor is
	type pc_address_cache is array(0 to 7) of std_logic_vector(31 downto 3);
	type predicted_pc is array(0 to 7) of std_logic_vector(31 downto 0);
	type valid is array(0 to 7) of std_logic;
	type branch_taken is array(0 to 7) of std_logic;
	
	signal ar_pc_tag	:	pc_address_cache;
	signal ar_pred_pc	:	predicted_pc;
	signal ar_valid		:	valid;
	signal ar_taken		:	branch_taken;
begin

	process(pc_address)
		variable index	:	integer;
	begin
		index := conv_integer(pc_address(2 downto 0));
		
		if ar_valid(index) = '1' and ar_pc_tag(index) = pc_address(31 downto 3) then
			cache_hit <= '1';
		else
			cache_hit <= '0';
		end if;

end Behavior;