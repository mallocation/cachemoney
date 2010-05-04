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

entity Cache_1KB is
	port
	(
		address		:	in	std_logic_vector(31 downto 0);
		data		:	in	std_logic_vector(31 downto 0);
		write_en	:	in 	std_logic;
		clock_50	:	in 	std_logic;
		cache_hit	:	out std_logic;		
		cache_data	:	out std_logic_vector(31 downto 0)
	);
end Cache_1KB;

architecture Behavior of Cache_1KB is
	
	--array of 32 bit vectors for cached data values
	--type cache is array(0 to 255) of std_logic_vector(31 downto 0);
	type cache is array(0 to 31) of std_logic_vector(31 downto 0);
	
	--array of 22 bit vectors for tag values
	--type tag is array(0 to 255) of std_logic_vector(23 downto 0);
	type tag is array(0 to 31) of std_logic_vector(26 downto 0);
	
	--array of bits to determine cache validity
	--type valid is array(0 to 255) of bit;
	type valid is array(0 to 31) of bit;
	
	signal cache_1k	:	cache;
	signal tag_1k	:	tag;
	signal valid_1k	:	valid;
begin
	process(address,clock_50,write_en,data)
		variable index	:	integer;
	begin
		index := conv_integer(address(4 downto 0));		
		if (clock_50'event and clock_50 = '1' and write_en = '1') then
			cache_1k(index) <= data;
			valid_1k(index) <= '1';
			tag_1k(index) <= address(31 downto 5);
		end if;
	end process;
	
	process(address,cache_1k,tag_1k,valid_1k)
		variable index	:	integer;
	begin
		index := conv_integer(address(4 downto 0));
		if valid_1k(index) = '1' and tag_1k(index) = address(31 downto 5) then
			cache_hit <= '1';
		else
			cache_hit <= '0';
		end if;
		cache_data <= cache_1k(index);	
	end process;
	
end Behavior;
	
	