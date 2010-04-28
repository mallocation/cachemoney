main:	nop
	nop
	lw $31, test
	addi $31, $31, 15
exit: jmp exit

.data

test: .integer 13