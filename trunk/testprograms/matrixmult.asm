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
loop1: beq $25, $30, initSum
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

initSum:
addi $27, $0, matrix3
addi $28, $0, 0
addi $1, $0, 0
performSum: beq $1, $30, showDiagonalSum
	lw $29, $27
	nop
	add $28, $28, $29
	add $27, $27, $30
	addi $27, $27, 1
	addi $1, $1, 1
	jmp performSum


showDiagonalSum:
add $31, $28, $0
exit: jmp exit



	.data
#----------Testing....	
#dimension: .integer 4
#matrix1: .array 15,14,3,-7,7,8,-2,5,-13,0,4,2,1,1,2,3
#matrix2: .array 7,8,9,10,11,12,13,14,-1,-8,-10,-12,3,8,22,4
#----------Case 1-------------
#dimension: .integer 4
#matrix1: .array -5565,-5548,-3857,-2840,-5606,2219,-3036,-3607,142,5635,-5191,7074,1692,4625,-861,-1643
#matrix2: .array -1976,3664,-1250,-6854,1521,1231,733,-588,-7070,-4911,458,-7691,-4829,5628,-5159,-1065
#----------Case 2-------------
#dimension: .integer 4
#matrix1: .array -5878,5488,-6565,-7398,7388,-2865,7402,-938,6269,-2169,-8168,4749,-1025,4831,-3352,6775
#matrix2: .array 545,-8041,7650,1101,4982,-380,-4771,8162,1027,-4091,335,-6032,4110,-3147,-4497,7449
#Answer:44543719
#Hex:0x02A7AEE7
#----------Case 3-------------
#dimension: .integer 6
#matrix1: .array -6010,7727,-7582,364,-7246,-6666,-3359,-4616,-8137,1108,-1695,-2965,-5462,3376,-5858,-2736,4773,6340,-2997,-7553,5938,-1419,1543,2579,-6392,1905,-3669,-1403,-3120,3023,5441,2775,519,7929,6583,-427
#matrix2: .array -4948,7763,5849,-641,-1659,-6612,-5643,4336,6550,-2224,6891,-3061,-7186,-4199,-2484,-3600,186,4676,2639,2984,-224,-6943,6785,1678,-7887,-5933,2941,-907,-6686,-559,-3423,2127,3344,-5477,8078,-3307
#Answer:176719681
#Hex:0x0A888741
#----------Case 4-------------
#dimension: .integer 8
#matrix1: .array -8062,-5744,1615,-2521,5272,4591,-7956,-6362,2196,-1070,-5704,4060,1076,1922,-6969,6627,-2305,-7225,-1042,-1415,-7301,-5831,1492,2184,-6323,-1949,-7984,-7279,-3932,3541,-885,6642,669,3643,-4440,-1802,1460,-1614,6990,2139,-1369,-6634,-3872,-418,-332,-616,-6637,-7959,280,2739,187,5330,-4937,3396,-2041,-3007,6326,-3336,-4668,-3217,-4276,-1619,754,-6359
#matrix2: .array 2121,-3018,-5361,2299,-3172,5972,851,-1464,-7197,-3092,5738,6222,-3872,-2727,-1576,6569,2851,-2540,7530,-2070,6816,-5973,4098,-7270,-370,2725,4427,4370,1885,4349,-210,-926,-3186,5916,6144,-5438,-6665,-2972,-1888,620,268,4289,-7087,323,2092,-4057,-7186,-5996,3392,6158,2405,2088,-5046,-4914,-4691,671,5138,6082,-2882,3505,4538,-7061,719,5855
#Answer:-59736330
#Hex:0xFC707EF6
#----------Case 5-------------
dimension: .integer 10
matrix1: .array 7431,-6029,5416,-2521,-5207,3789,-4769,4272,-3923,4885,-5525,-5600,5216,-4102,-7120,6617,-6286,6801,-6802,-2881,7708,-7174,7179,-1850,1808,-783,2396,6570,-3307,2770,1589,3307,-8187,-1294,3302,-7034,-6416,-4682,6834,-3338,-4256,-6775,2300,2295,-6363,-4239,7922,771,-483,7044,-7040,1913,-8071,4711,-6622,3799,-4123,4665,-3777,-3572,-3276,-5345,-6448,-3768,1603,-7529,1743,-5006,4309,-5425,5137,2481,-6442,5636,5116,-1237,5189,4045,4459,4017,-6935,-21,-2177,3940,5154,659,5408,-400,-7843,-375,-2384,-3531,-4266,5343,-6727,7436,-180,1364,6226,2514
matrix2: .array 2192,-4804,-7718,-6279,-3812,-3340,8141,-7401,575,366,-7961,897,-8043,4031,7692,-610,-2311,-3009,-1664,-157,-485,6214,1580,5076,-5181,6967,2052,4644,2793,-6739,6330,948,1787,4018,-3278,-4655,-1747,7740,-974,-4082,-6324,4134,6864,-2668,-1455,-8175,-8066,1417,-6015,-859,-941,6470,3827,1382,-4317,6662,742,4555,-996,2260,2614,5601,-3258,-509,-4996,2950,149,3731,781,3432,-3362,-6048,-73,-6762,3365,245,-4149,2474,-1718,8071,7379,-5093,-3962,5386,-5234,362,-7448,2697,-1667,7081,3183,-5675,3815,3047,366,-6506,5599,7189,4118,-6681
#Answer:75401508
#Hex:0x047E8924
matrix3: .integer 0