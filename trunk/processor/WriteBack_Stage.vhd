library ieee, work;
use ieee.std_logic_1164.all;

entity WriteBack_Stage is
	port (
			-- Enable writing to the latches of the write back stage
			WriteBackStage_Enable: in std_logic;
			
			-- WB lines from control unit
			WBControlLine	:	in std_logic_vector(1 downto 0);
			
			-- Data from off chip memory to write to register
			DataFromMemory	:	in std_logic_vector(31 downto 0);
			
			-- Data from EX stage to write to register
			DataFromALU		:	in std_logic_vector(31 downto 0);			
			
			-- Destination register (register to write to)
			DestRegister	:	in std_logic_vector(4 downto 0);
			
			-- Board clock input
			clock_50		:	in std_logic;
			
			-- Write Register Enable : determines if write to a register
			WriteRegisterEnable: out std_logic;
			
			-- Register to write to in the register file
			WriteRegister: out std_logic_vector(4 downto 0);
			
			-- Data to write to the register
			WriteRegisterData: out std_logic_vector(31 downto 0)
	);
			
end WriteBack_Stage;

architecture Behavior of WriteBack_Stage is
	-- Output of WBControlLine latch
	signal wbcontrolline_reg	: 	std_logic_vector(1 downto 0);
	
	-- Output of DataFromMemory latch
	signal datafrommemory_reg	:	std_logic_vector(31 downto 0);
	
	-- Output of DataFromALU latch
	signal datafromalu_reg		:	std_logic_vector(31 downto 0);
	
	-- Output of DestRegist
	signal writeregister_reg		:	std_logic_vector(4 downto 0);	
begin

	-- Register for WB from control unit
	wbcontrolreg: work.GenericRegister generic map (DATA_WIDTH=>2)
					port map (WBControlLine,clock_50,WriteBackStage_Enable,'0',wbcontrolline_reg);

	-- Register for Data to write loaded from Memory
	datafrommemoryreg: work.GenericRegister generic map(DATA_WIDTH=>32)
					port map (DataFromMemory,clock_50,WriteBackStage_Enable,'0',datafrommemory_reg);
	
	-- Register for Data to write passing through from ALU
	datafromalureg: work.GenericRegister generic map(DATA_WIDTH=>32)
					port map (DataFromALU,clock_50,WriteBackStage_Enable,'0',datafromalu_reg);

	-- Register for Destination Register (register to write to)
	writeregisterreg: work.GenericRegister generic map (DATA_WIDTH=>5)
					port map (DestRegister,clock_50,WriteBackStage_Enable,'0',writeregister_reg);	
	
	--wbcontrolline_reg structure
	--MSB - write enable register
	--LSB - data source (0 for memory, 1 for alu passthrough)	
	
	process (wbcontrolline_reg,datafrommemory_reg,datafromalu_reg)
	begin
		if wbcontrolline_reg(0) = '0' then
			WriteRegisterData <= datafrommemory_reg;
		else
			WriteRegisterData <= datafromalu_reg;
		end if;	
	end process;
	
	WriteRegisterEnable <= wbcontrolline_reg(1);
	WriteRegister <= writeregister_reg;
	
end Behavior;