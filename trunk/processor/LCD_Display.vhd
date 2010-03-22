--------------------------------------------------------------------------------------------
--	Authors: Cache Money
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
		--data		:	in		std_logic_vector(31 downto 0);
		data		:	in 	std_logic_vector(15 downto 0);
		clock_50	:	in	bit;
		lcd_on		:	out	std_logic;
		lcd_e		:	out	std_logic;
		lcd_rs		:	out	std_logic;
		lcd_rw		:	out	std_logic;
		lcd_db		:	out	std_logic_vector(7 downto 0)
	);
end LCD_Display;

architecture BEHAVIOR of LCD_Display is

	type CONTROLLER_STATE is (FUNCTION_SET, DISPLAY_OFF, DISPLAY_CLEAR, DISPLAY_ON, MODE_SET, WRITE_0, WRITE_X, 
							  WRITE_I7, WRITE_I6, WRITE_I5, WRITE_I4, WRITE_I3, WRITE_I2, WRITE_I1, WRITE_I0,
							  HOLD, LATCH_INFO);
							  --WRITE_CHARACTER, HOLD, LATCH_INFO);
	signal state, next_state	:	CONTROLLER_STATE;
	signal current_display : std_logic_vector(31 downto 0);	
	signal clock_20khz	:	bit;
	
	component CustomClock_20khz is
		port
		(
			clk_50mhz	:	in 	bit;
			reset		:	in 	bit;
			clk_custom	:	out	bit
		);
	end component;
	
	function HEX_TO_ASCII (hex_code : std_logic_vector; start_index : natural; end_index : natural) return std_logic_vector is	
		variable ascii_value	:	std_logic_vector(7 downto 0);
	begin
		if hex_code(start_index) = '0' then 	-- 0x0--- - this will be a number less than 8
			ascii_value := X"3" & hex_code(start_index downto end_index);
		else
			if hex_code(start_index-1 downto end_index) = "000" or hex_code(start_index-1 downto end_index) = "001" then
				ascii_value := X"3" & hex_code(start_index downto end_index);
			else
				if hex_code(start_index downto end_index) = X"A" then
					ascii_value := X"41";
				elsif hex_code(start_index downto end_index) = X"B" then
					ascii_value := X"42";
				elsif hex_code(start_index downto end_index) = X"C" then
					ascii_value := X"43";
				elsif hex_code(start_index downto end_index) = X"D" then
					ascii_value := X"44";
				elsif hex_code(start_index downto end_index) = X"E" then
					ascii_value := X"45";
				elsif hex_code(start_index downto end_index) = X"F" then
					ascii_value := X"46";
				end if;
			end if;
		end if;
		return ascii_value;
	end HEX_TO_ASCII;


begin
	clock20k	:	CustomClock_20khz
		port map (clock_50, '0', clock_20khz);	

	--turn on the LCD display
	lcd_on <= '1';
	
	process(clock_20khz)
	begin
		if clock_20khz'event and clock_20khz = '1' then
			case state is
				when FUNCTION_SET =>
					lcd_e <= '1';
					lcd_rs <= '0';
					lcd_rw <= '0';
					lcd_db <= X"38";
					state <= LATCH_INFO;
					next_state <= DISPLAY_OFF;
				when DISPLAY_OFF =>
					lcd_e <= '1';
					lcd_rs <= '0';
					lcd_rw <= '0';
					lcd_db <= X"08";
					state <= LATCH_INFO;
					next_state <= DISPLAY_CLEAR;
				when DISPLAY_CLEAR =>
					lcd_e <= '1';
					lcd_rs <= '0';
					lcd_rw <= '0';
					lcd_db <= X"01";
					state <= LATCH_INFO;
					next_state <= DISPLAY_ON;
				when DISPLAY_ON =>
					lcd_e <= '1';
					lcd_rs <= '0';
					lcd_rw <= '0';
					lcd_db <= X"0C";
					state <= LATCH_INFO;
					next_state <= MODE_SET;
				when MODE_SET =>
					lcd_e <= '1';
					lcd_rs <= '0';
					lcd_rw <= '0';
					lcd_db <= X"06";
					state <= LATCH_INFO;
					next_state <= WRITE_0;
				when WRITE_0 =>
					--This state is used to write a '0' to the display
					lcd_e <= '1';
					lcd_rs <= '1';
					lcd_rw <= '0';
					lcd_db <= X"30";
					state <= LATCH_INFO;
					next_state <= WRITE_X;
				when WRITE_X =>
					--This state is used to write a 'x' to the display, in addition with the previous '0' to 
					-- represent HEX notation (0x)
					lcd_e <= '1';
					lcd_rs <= '1';
					lcd_rw <= '0';
					lcd_db <= X"78";
					state <= LATCH_INFO;
					next_state <= WRITE_I7;
				when WRITE_I7 =>
					lcd_e <= '1';
					lcd_rs <= '1';
					lcd_rw <= '0';
					lcd_db <= HEX_TO_ASCII(current_display, 31, 28);
					state <= LATCH_INFO;
					next_state <= WRITE_I6;
				when WRITE_I6 =>
					lcd_e <= '1';
					lcd_rs <= '1';
					lcd_rw <= '0';
					lcd_db <= HEX_TO_ASCII(current_display, 27, 24);
					state <= LATCH_INFO;
					next_state <= WRITE_I5;
				when WRITE_I5 =>
					lcd_e <= '1';
					lcd_rs <= '1';
					lcd_rw <= '0';
					lcd_db <= HEX_TO_ASCII(current_display, 23, 20);
					state <= LATCH_INFO;
					next_state <= WRITE_I4;
				when WRITE_I4 =>
					lcd_e <= '1';
					lcd_rs <= '1';
					lcd_rw <= '0';
					lcd_db <= HEX_TO_ASCII(current_display, 19, 16);
					state <= LATCH_INFO;
					next_state <= WRITE_I3;
				when WRITE_I3 =>
					lcd_e <= '1';
					lcd_rs <= '1';
					lcd_rw <= '0';
					lcd_db <= HEX_TO_ASCII(current_display, 15, 12);
					state <= LATCH_INFO;
					next_state <= WRITE_I2;
				when WRITE_I2 =>
					lcd_e <= '1';
					lcd_rs <= '1';
					lcd_rw <= '0';
					lcd_db <= HEX_TO_ASCII(current_display, 11, 8);
					state <= LATCH_INFO;
					next_state <= WRITE_I1;
				when WRITE_I1 =>
					lcd_e <= '1';
					lcd_rs <= '1';
					lcd_rw <= '0';
					lcd_db <= HEX_TO_ASCII(current_display, 7, 4);
					state <= LATCH_INFO;
					next_state <= WRITE_I0;
				when WRITE_I0 =>
					lcd_e <= '1';
					lcd_rs <= '1';
					lcd_rw <= '0';
					lcd_db <= HEX_TO_ASCII(current_display, 3, 0);
					state <= LATCH_INFO;
					next_state <= HOLD;				
				when LATCH_INFO =>
					lcd_e <= '0'; -- toggle lcd_e, lcd latches info on falling edge
					state <= next_state;					
				when HOLD =>
					state <= HOLD;
					--TODO : Change this if statement for 32 bit operation
					if not (current_display = X"0000" & data) then
						current_display <= X"0000" & data;
						state <= FUNCTION_SET;
					end if;
				when OTHERS =>
					state <= FUNCTION_SET;
			end case;
		end if;
	end process;
end BEHAVIOR;