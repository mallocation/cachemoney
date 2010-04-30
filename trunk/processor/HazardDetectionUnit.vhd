library ieee;
use ieee.std_logic_1164.all;

entity HazardDetectionUnit is
	port (
		Instruction			:	in	std_logic_vector(31 downto 0);
		RegDestFromEXStage	:	in std_logic_vector(4 downto 0);
		MEMRd_WBWr_From_EX	:	in std_logic;
		RegDestFromMEMStage	:	in 	std_logic_vector(4 downto 0);
		MEMRd_WBWr_From_MEM	:	in 	std_logic;
		RegDestFromWBStage	:	in	std_logic_vector(4 downto 0);
		WBWr_From_WB		:	in 	std_logic;
		MemReadFromEXStage  :   in std_logic;
		FlushIDStage		:	out std_logic;
		EnableIDStage		:	out std_logic;
		EnableIFStage		:	out std_logic;
		FlushEXStage		:	out std_logic
	);	
end HazardDetectionUnit;

architecture behavior of HazardDetectionUnit is
	signal opcode	:	std_logic_vector(2 downto 0);
	signal InstructionRD	:	std_logic_vector(4 downto 0);
	signal InstructionRS	:	std_logic_vector(4 downto 0);
	signal InstructionRT    :   std_logic_vector(4 downto 0);
begin
	opcode <= Instruction(31 downto 29);
	InstructionRD <= Instruction(28 downto 24);
	InstructionRS <= Instruction(23 downto 19);
	InstructionRT <= Instruction(18 downto 14);
	
	process(opcode, InstructionRD, InstructionRS, InstructionRT, RegDestFromEXStage, RegDestFromMEMStage, RegDestFromWBStage, MemReadFromEXStage, MEMRd_WBWr_From_EX,MEMRd_WBWr_From_MEM,WBWr_From_WB)
	begin
		
		if MemReadFromEXStage = '1' and ((RegDestFromEXStage = InstructionRS) or (RegDestFromEXStage = InstructionRT)) then
			--flush ex stage, stall IF and ID stages
			FlushEXStage <= '1';
			EnableIDStage <= '0';
			EnableIFStage <= '0';
		else
			if opcode = "101" or opcode = "110" then -- Branch on equal, branch on not equal
				EnableIDStage <= '0';	-- don't allow writing to the decode stage

				if (((InstructionRD = RegDestFromEXStage or InstructionRS = RegDestFromEXStage) and MemRD_WBWr_From_EX = '1') or
					((InstructionRD = RegDestFromMEMStage or InstructionRS = RegDestFromMEMStage) and MemRD_WBWr_From_MEM = '1') or 
					((InstructionRD = RegDestFromWBStage or InstructionRS = RegDestFromWBStage) and WBWr_From_WB = '1')) then
					--wait for other instructions to complete
					FlushIDStage <= '0';
					EnableIFStage <= '0';
					FlushEXStage <= '1';
				else
					--analyze the branch, flush the ID stage, and re-enable the IF stage
					FlushIDStage <= '1';
					EnableIFStage <= '1';
					FlushEXStage <= '0';
				end if;
			else
				EnableIDStage <= '1';
				FlushIDStage <= '0';
				EnableIFStage <= '1';
				FlushExStage <= '0';	
			end if;
		end if;
	end process;
end behavior;