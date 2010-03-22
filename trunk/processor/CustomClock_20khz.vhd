ENTITY CustomClock_20khz IS
	GENERIC
	(
		count_value :	natural := 625000	--50mhz / 20khz = 2500000 / 4 = 625000
	);
	PORT
	(
		clk_50mhz	:	IN		BIT;
		reset				:	IN		BIT;
		clk_custom	:	OUT	BIT
	);
END CustomClock_20khz;

ARCHITECTURE Behavior OF CustomClock_20khz IS
	SIGNAL clk_custom_hz : BIT;
BEGIN
	PROCESS (reset, clk_50mhz)
		VARIABLE cnt : INTEGER RANGE 0 TO count_value;
	BEGIN
		IF (reset = '1') THEN
			clk_custom_hz <= '0';
			cnt := 0;
		ELSIF (clk_50mhz'EVENT AND clk_50mhz = '1') THEN
			IF (cnt = count_value) THEN
				cnt := 0;
				clk_custom_hz <= NOT clk_custom_hz;
			ELSE
				cnt := cnt + 1;
			END IF;
		END IF;
	END PROCESS;
	
	clk_custom <= clk_custom_hz;
END Behavior;