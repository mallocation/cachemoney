--------------------------------------------------------------------------------------------
--	Author: Cache Money
--	
--
-- 	This component will act as a controller to communicate with the lcd display on the
--	altera de2 board.  In it's current implementation, it will accept an integer value
-- 	on the 'data' port.  As soon as the data value changes, the lcd will display the new
--	value on the lcd display.  Otherwise, if the data value does not change, then the lcd
--	will continue to display the current value.
--
--	Pin Assignments
--	clock_50	:	PIN_N2
--	lcd_on		:	PIN_L4
--	lcd_e		:	PIN_K3
--	lcd_rs		:	PIN_K1
--	lcd_rw		:	PIN_K4
--	lcd_db[7]	:	PIN_H3
--	lcd_db[6]	:	PIN_H4
--	lcd_db[5]	:	PIN_J3
--	lcd_db[4]	:	PIN_J4
--	lcd_db[3]	:	PIN_H2
--	lcd_db[2]	:	PIN_H1
--	lcd_db[1]	:	PIN_J2
--	lcd_db[0]	:	PIN_J1
--------------------------------------------------------------------------------------------


library ieee;
use ieee.std_logic_1164.all;

entity LCD_Display is
	port
	(
		data	:	in	std_logic_vector(31 downto 0);
		clock_50:	in 	std_logic;
		lcd_on	:	out std_logic;
		lcd_e	:	out std_logic;
		lcd_rs	:	out std_logic;
		lcd_rw	:	out std_logic;
		lcd_db	:	out std_logic_vector(7 downto 0)
	);
end LCD_Display;

architecture BEHAVIOR of LCD_Display is

type CONTROLLER_STATE is (SET_MODE, DISPLAY_ON, DISPLAY_OFF, DISPLAY_CLEAR, RETURN_CURSOR);
signal state	:	CONTROLLER_STATE;

begin
	--turn on the LCD display
	lcd_on <= '1';