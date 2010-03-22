--------------------------------------------------------------------------------------------
--	Author: Cache Money
--	
--
-- 	This component will act as a controller to communicate with the SRAM.  The SRAM will
--	act as the cache for both main memory and flash memory (1/2 of SRAM is cache for main
--	memory, and the other 1/2 of SRAM is cache for the flash memory)
--
--	Pin assignments:
-- 	
--	SRAM_ADDR[0]  	:	PIN_AE4	:	SRAM Address[0] 
--	SRAM_ADDR[1]  	:	PIN_AF4 :	SRAM Address[1] 
--	SRAM_ADDR[2]  	:	PIN_AC5 :	SRAM Address[2] 
--	SRAM_ADDR[3]  	:	PIN_AC6 :	SRAM Address[3] 
--	SRAM_ADDR[4]  	:	PIN_AD4 :	SRAM Address[4] 
--	SRAM_ADDR[5]  	:	PIN_AD5 :	SRAM Address[5] 
--	SRAM_ADDR[6]  	:	PIN_AE5 :	SRAM Address[6] 
--	SRAM_ADDR[7]  	:	PIN_AF5 :	SRAM Address[7] 
--	SRAM_ADDR[8]  	:	PIN_AD6 :	SRAM Address[8] 
--	SRAM_ADDR[9]  	:	PIN_AD7 :	SRAM Address[9] 
--	SRAM_ADDR[10]  	:	PIN_V10 :	SRAM Address[10] 
--	SRAM_ADDR[11]  	:	PIN_V9 	:	SRAM Address[11] 
--	SRAM_ADDR[12]  	:	PIN_AC7 :	SRAM Address[12] 
--	SRAM_ADDR[13]  	:	PIN_W8 	:	SRAM Address[13] 
--	SRAM_ADDR[14]  	:	PIN_W10 :	SRAM Address[14] 
--	SRAM_ADDR[15]  	:	PIN_Y10 :	SRAM Address[15] 
--	SRAM_ADDR[16]  	:	PIN_AB8 :	SRAM Address[16] 
--	SRAM_ADDR[17]  	:	PIN_AC8 :	SRAM Address[17] 
--	SRAM_DQ[0] 		:	PIN_AD8 :	SRAM Data[0] 
--	SRAM_DQ[1] 		:	PIN_AE6 :	SRAM Data[1]
--	SRAM_DQ[2] 		:	PIN_AF6 :	SRAM Data[2] 
--	SRAM_DQ[3] 		:	PIN_AA9 :	SRAM Data[3] 
--	SRAM_DQ[4] 		:	PIN_AA10 :	SRAM Data[4] 
--	SRAM_DQ[5] 		:	PIN_AB10 :	SRAM Data[5] 
--	SRAM_DQ[6] 		:	PIN_AA11 :	SRAM Data[6]
--	SRAM_DQ[7] 		:	PIN_Y11 :	SRAM Data[7] 
--	SRAM_DQ[8] 		:	PIN_AE7 :	SRAM Data[8] 
--	SRAM_DQ[9] 		:	PIN_AF7 :	SRAM Data[9] 
--	SRAM_DQ[10] 	:	PIN_AE8 :	SRAM Data[10] 
--	SRAM_DQ[11] 	:	PIN_AF8 :	SRAM Data[11] 
--	SRAM_DQ[12] 	:	PIN_W11 :	SRAM Data[12] 
--	SRAM_DQ[13] 	:	PIN_W12 :	SRAM Data[13] 
--	SRAM_DQ[14] 	:	PIN_AC9 :	SRAM Data[14] 
--	SRAM_DQ[15] 	:	PIN_AC10 :	SRAM Data[15] 
--	SRAM_WE_N 		:	PIN_AE10 :	SRAM Write Enable 
--	SRAM_OE_N 		:	PIN_AD10 :	SRAM Output Enable 
--	SRAM_UB_N 		:	PIN_AF9 :	SRAM High-byte Data Mask 
--	SRAM_LB_N 		:	PIN_AE9 :	SRAM Low-byte Data Mask 
--	SRAM_CE_N 		:	PIN_AC11 :	SRAM Chip Enable


library ieee;
use ieee.std_logic_1164.all;

entity SRAM_Controller is
	port
	(
		address	:	in	std_logic_vector(17 downto 0);
		data	:	in 	std_logic_vector(15 downto 0);
		write_enable	:	in std_logic;
		output_enable	:	in std_logic;
		high_byte_mask	:	in std_logic;
		low_byte_mask	:	in std_logic;
		chip_enable		:	in std_logic;
		SRAM_ADDR	:	out std_logic_vector(17 downto 0);
		SRAM_DQ		:	out std_logic_vector(15 downto 0);
		SRAM_WE_N	:	out std_logic;
		SRAM_OE_N	:	out std_logic;
		SRAM_UB_N	:	out std_logic;
		SRAM_LB_N	:	out std_logic;
		SRAM_CE_N	:	out std_logic;
	);
end SRAM_Controller;

architecture BEHAVIOR of SRAM_Controller is

begin



end BEHAVIOR;