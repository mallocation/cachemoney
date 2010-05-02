#Cache Money - Matrix Muliplication
#PLEASE NOTE: This is a PRELIMINARY matrix multiplication program.
#			  This is NOT our final matrix multiplication program.
#Variables
#matrix1base $29
#matrix2base $30
#matrix3base $31
#iter1 - iterator for loop 1 $1
#iter2 - iterator for loop 2 $2
#iter3 - iterator for loop 3 $3
#mult1 $4
#mult2 $5
#multProduct $6
#matrixValue $7
#matrixDim $8
#matrix1pos $9
#matrix2pos $10
#matrix3pos $11

#---------Beginning of program --------------
lw $8, matrixDimension #Load the dimension of the matrix
nop

#---------Load the base address of the matrices--------
addi $29, $0, matrix1	#Load the base address of the first matrix
addi $30, $0, matrix2  #Load the base address of the second matrix
addi $31, $0, matrix3	#Load the base address of the third matrix


add $1, $0, $0 #Reset the first iterator
loop1:
	add $2, $0, $0 		#Reset the second iterator
	add $11, $0, $31	#Set the start index of the third matrix
	loop2:	
		add $7, $0, $0	#Clear the matrixValue
		add $3, $0, $0 	#Reset the third iterator
		add $9, $0, $29 	#Set the start index of the first matrix
		add $10, $0, $30	#Set the start index of the second matrix
		loop3:
			lw $4, $9	#Load the first multiplication operand
			nop
			lw $5, $10	#Load the second multiplication operand
			nop
			mult $6, $4, $5		#Multiply the two operands
			add $7, $7, $6		#Increment the matrix value
			addi $9, $9, 1		#Go to the next index of the first matrix
			add $10, $10, $8 	#Go to the next index of the second index
			addi $3, $3, 1
			bneq $3, $8, loop3			
		sw $7, $11	#store the new matrix value
		addi $11, $11, 1	#increment to the next matrix position in memory
		addi $2, $2, 1
		bneq $2, $8, loop2		
	addi $1, $1, 1
	bneq $1, $8, loop1
	
lw $31, matrix2
nop

end: jmp end
			
.data
matrixDimension: .integer 2	#Dimension of the two arrays (2 rows x 2 columns)
matrix1: .array 1,2,3,4	  	#Matrix 1, with elements stored sequentially in memory
matrix2: .array 5,6,7,8		#Matrix 2
matrix3: .integer 0			#Define Matrix 3 to have an addressable space for storing
							#	the third matrix