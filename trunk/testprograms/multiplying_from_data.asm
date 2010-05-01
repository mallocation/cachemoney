#register 8 holds teh value

lw $1, dimension #load the dimension
nop
addi $2, $0, matrix1 #load base of matrix1
addi $3, $0, matrix2 #load base of matrix2
addi $10, $0, matrix3 #load base of matrix3

loop:
beq $6, $1, loopthrough
lw $4, $2 #load value at base of matrix1 into r4
nop
lw $5, $3 #load value at base of matrix2 into r5
nop
mult $7, $4, $5
sw $7, $10
addi $2, $2, 1
addi $3, $3, 1  #loop incrementations used as nops
addi $6, $6, 1
addi $10, $10, 1  #loop incrementations used as nops
jmp loop

loopthrough: addi $10, $0, matrix3 #load the base of matrix3
loop2:	beq $11, $1, showresults
	lw $12, $10
	nop
	add $13, $13, $12
	addi $10, $10, 1
	addi $11, $11, 1  
	jmp loop2


showresults:
addi $31, $13, 0
exit: jmp exit

.data
dimension: .integer 4
matrix1: .array 1,2,3,4
matrix2: .array 15,16,17,18
matrix3: .array 0,0,0,0