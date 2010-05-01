nop
addi $3, $0, 1
addi $5, $0, -256342
addi $5, $5, 2
add $7, $3, $5
add $31, $7, $0


exit: jmp exit

	.data
length: .integer 2
array1: .array 1,2,-523281
array2: .array 15,16
array3: .integer 0