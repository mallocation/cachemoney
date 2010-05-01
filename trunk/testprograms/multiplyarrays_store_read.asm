nop
addi $1, $0, array1 #base address of array1 into register 1
addi $2, $0, array2 #base address of array2 into register 2
addi $3, $0, array3 #base address of array3 into register 3
lw $4, length	#length of array
nop

addi $30, $0, 0 #load iterator into $30
addi $29, $0, 0 #clear sum

loop:
	lw $5, $1
	nop
	lw $6, $2
	nop
	mult $7, $5, $6
	#add $29, $29, $7 running sum
	sw $7, $3
	addi $3, $3, 1
	addi $1, $1, 1
	addi $2, $2, 1
	addi $30, $30, 1
	bneq $30, $4, loop
	
addi $3, $3, -2
addi $30, $0, 0
addi $29, $0, 0
loop2:
	lw $7, $3
	nop
	add $29, $29, $7
	addi $3, $3, 1
	addi $30, $30, 1
	bneq $30, $4, loop2
	
	
addi $31, $29, 0
exit: jmp exit
	



	


	.data
length: .integer 2
array1: .array 1,2
array2: .array 15,16
array3: .integer 0