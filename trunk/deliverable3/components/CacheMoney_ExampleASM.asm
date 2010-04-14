########################################################
#	CacheMoney_ExampleASM.asm
#	Deliverable 3
########################################################
# 	This is an assembly file written to demonstrate
#	the abilities of the Cache Money assembler.
########################################################

# 	Instruction section always comes first within a file.

#	Demonstrate creating symbolic references to access within code.
main:	add $29, $2, $17	#Demonstrates an 'ADD' instruction, $29 = $2 + $17

		addi $14, $7, 1024	#Demonstrates an 'ADDI' instruction, $14 = $7 + 1024
		
		mult $1, $9, $0		#Demonstrates a 'MULT instruction, $1 = $9 * $0
		
		nop					#Demonstrates the use of 'No Operation' - This will be translated to
							#	add $0, $0, $0

load_number1:							
		lw $2, number1		#Demonstrates a 'LW' instruction, $2 = 1
		
		sw $2, number5		#Demonstrates a 'SW' instruction, memory @ number5 = $2
		
		beq $2, $1, main	#Demonstrates a 'BEQ' instruction, if $2 == $1, go to main
		
		bneq $2, $1, load_number1	#Demonstrates a 'BNEQ' instruction, if $2 != $1, go to load_number1
		
exit:	jmp exit			#Demonstrates a 'JMP' instruction, Move program execution to exit.							
		
		#This header signifies the beginning of the 'DATA' section
		.data

number1: .integer 1
number5: .integer 5
		
		
