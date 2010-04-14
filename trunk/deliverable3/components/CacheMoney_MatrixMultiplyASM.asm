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

#---------Beginning of program --------------#
lw #matrixDim, matrix1size	#Load the size of the first matrix

#---------Load the base address of the matrices--------#
addi #matrix1base, $0, matrix1	#Load the base address of the first matrix
addi #matrix2base, $0, matrix2  #Load the base address of the second matrix
addi #matrix3base, $0, matrix3	#Load the base address of the third matrix


add #iter1, $0, $0 #Reset the first iterator
loop1:
	add #iter2, $0, $0 #Reset the second iterator
	add #matrix3pos, $0, #matrix3base		#Set the start index of the third matrix
	loop2:	
		add #matrixValue, $0, $0			#Clear the matrixValue
		add #iter3, $0, $0 					#Reset the third iterator
		add #matrix1pos, $0, #matrix1base 	#Set the start index of the first matrix
		add #matrix2pos, $0, #matrix2base	#Set the start index of the second matrix
		loop3:
			lw #mult1, #matrix1pos	#Load the first multiplication operand
			lw #mult2, #matrix2pos	#Load the second multiplication operand
			mult #multProduct, #mult1, #mult2			#Multiply the two operands
			add #matrixValue, #matrixValue, #multProduct	#Increment the matrix value
			addi #matrix1pos, #matrix1pos, 1	#Go to the next index of the first matrix
			add #matrix2pos, #matrix2pos, #matrixDim #Go to the next index of the second index
			addi #iter3, #iter3, 1
			bneq #iter3, #matrixDim, loop3			
		sw #matrixValue, #matrix3pos	#store the new matrix value
		addi #matrix3pos, #matrix3pos, 1	#increment to the next matrix position in memory
		addi #iter2, #iter2, 1
		bneq #iter2, #matrixDim, loop2
		
	addi #iter1, #iter1, 1
	bneq #iter1, #matrixDim, loop1
end: j end
			
.data
matrixDimension: .integer 2
matrix1: .array 1,2,3,4
matrix2: .array 5,6,7,8
matrix3: .integer 0