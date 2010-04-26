--------------------------------------------------------------------------------------------
--	Authors: Cache Money
--
-- 	This component will act as the on-chip cache for our processor.  It is a directly-mapped
--	cache, with 256 entries of 32 bits each.
--	256 * 32 bits = 8192 bits = 8 KB
--	8 bits for address hashing and 2 bits for byte offset => 22 bits for tag value
-- 	
--	The cache is implemented in a way that if the write_en pin is set to high, then the
-- 	'data' value will be latched into the cache block corresponding to the specific address
--	when clock_50 falls from high to low.
--------------------------------------------------------------------------------------------

library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

entity Cache_8kb is
	port
	(
		address		:	in	std_logic_vector(31 downto 0);
		data		:	in	std_logic_vector(31 downto 0);
		write_en	:	in 	std_logic;
		clock_50	:	in 	std_logic;
		cache_hit	:	out std_logic;		
		cache_data	:	out std_logic_vector(31 downto 0)
	);
end Cache_8kb;

architecture Behavior of Cache_8kb is
	
	--array of 32 bit vectors for cached data values
	type cache is array(0 to 255) of std_logic_vector(31 downto 0);
	--array of 22 bit vectors for tag values
	type tag is array(0 to 255) of std_logic_vector(21 downto 0);
	--array of bits to determine cache validity
	type valid is array(0 to 255) of bit;
	
	signal cache_8k	:	cache;
	signal tag_8k	:	tag;
	signal valid_8k	:	valid;
begin
	process(address,clock_50)
		variable index	:	integer;
	begin
		index := conv_integer(address(9 downto 2));		
		if clock_50'event and clock_50 = '0' then
			if write_en = '1' then
				cache_8k(index) <= data;
				valid_8k(index) <= '1';
				tag_8k(index) <= address(31 downto 10);
			end if;
		end if;

		if valid_8k(index) = '1' and tag_8k(index) = address(31 downto 10) then
			cache_hit <= '1';
		else
			cache_hit <= '0';
		end if;
		cache_data <= cache_8k(index);
	end process;	
end Behavior;
	
	