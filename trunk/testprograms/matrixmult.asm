#Variables
#matrix1base $1
#matrix2base $2
#matrix3base $3
#dimension $30 - dimension of matrices
#----Iterators----
#Iterator for 1st loop: $25
#Iterator for 2nd loop: $26
#Iterator for 3rd loop: $27
#----Operands----
# $15, multiplication operand 1
# $16, multiplication operand 2
# $17, multiplication result
# $18, running sum for matrix index



#---------Beginning of program --------------
lw $30, dimension #Load the dimension of the matrix
nop

#---------Load the base address of the matrices--------
addi $1, $0, matrix1	#Load the base address of the first matrix
addi $2, $0, matrix2  	#Load the base address of the second matrix
addi $3, $0, matrix3	#Load the base address of the third matrix

#$4 - address of matrix 1 copied into 2nd loop
#$5 - address of matrix 1 copied into 3rd loop
#$6 - address of matrix 2 copied into 2nd loop
#$7 - address of matrix 2 copied into 3rd loop

addi $25, $0, 0	#Reset the iterator for the 1st loop
loop1: beq $25, $30, performSum
	#
	# Loop1 does nothing more than incrementing
	# the address of matrix 1 by dimension, and 
	# resets the address of matrix 2 to the base address
	#
	
	addi $26, $0, 0 #Reset the iterator for the 2nd loop
	add $4, $1, $0 #Copy base address of row for 1st matrix
	add $6, $2, $0	#Copy base address of column for 2nd matrix
	loop2: beq $26, $30, loop1iter
		#
		# Loop2 has a responsibility of copying the address of the first matrix
		# from loop1 into a usable register for loop2 and loop3.
		# At the end of loop 2, increment address of 2nd matrix by 1
		#
	
		addi $27, $0, 0 #Reset the iterator for the 3rd loop
		add $5, $4, $0 	#Copy the base address of row for 1st matrix
		add $7, $6, $0	#Copy the base address of column for 2nd matrix
		add $18, $0, $0
		loop3: beq $27, $30, storeNewValue
			#
			#	Loop3 iterates through the 1st and 2nd matrix, multiplying the
			# ith value of matrix 1 by the jth value of matrix 2, summing that value
			# with the running sum of the index value, and then
			# increments the address of matrix 1 by 1, and the address of matrix 2 by dimension
			#
			lw $15, $5
			nop
			lw $16, $7
			nop
			mult $17, $15, $16
			add $18, $18, $17			
			
			addi $27, $27, 1	#Increment the iterator for loop3
			addi $5, $5, 1		#Increment base address of row for matrix1
			add $7, $7, $30		#Increment base address of column for matrix2
			jmp loop3			#Jump back to loop3
	
		storeNewValue:
			sw $18, $3
			addi $3, $3, 1
		loop2iter:
		addi $26, $26, 1	#Increment the iterator for loop2
		addi $6, $6, 1		#Increment the base address of the column for matrix2
		jmp loop2			#Jump back to loop2
	
	
	loop1iter:
	add $1, $1, $30			#Increment the accessible address of matrix1 by the dimension
	addi $2, $0, matrix2	#Reset the accessible address of matrix2 to the base address of matrix2
	addi $25, $25, 1		#Increment the iterator for loop1
	jmp loop1				#Jump back to loop1


performSum:
addi $27, $0, matrix3
addi $27, $27, 0
lw $31, $27
nop
exit: jmp exit



	.data
dimension: .integer 2
matrix1: .array 1,2,3,4
matrix2: .array 5,6,7,8
matrix3: .integer 0