# Add you macro definition here - do not touch cs47_common_macro.asm"
#<------------------ MACRO DEFINITIONS ---------------------->#

       	# Macro: extract_nth_bit
        # Usage: extract_nth_bit(<register to store nth bit>, <register containing bit pattern>, 
        #			 <register containing bit position n>)
	.macro extract_nth_bit($regD, $regS, $regT)
		addi	$sp, $sp, -16
		sw	$fp, 16($sp)
		sw	$ra, 12($sp)
		sw	$s6, 8($sp)
		addi	$fp, $fp, -16
	
		or 	$s6, $zero, 1			# Set $s6 to 1
		sllv	$s6, $s6, $regT			# Shift $s6 to the left by n to create a mask, and store the mask in $s6
		and	$s6, $regS, $s6			# Bitwise-AND the mask and the bit pattern to extract the nth bit, and store the bit in $s6
		srlv	$regD, $s6, $regT		# Shift $s6 to the right by to put the nth bit in lsb (0 or 1)
		
		lw	$fp, 16($sp)
		lw	$ra, 12($sp)
		lw	$s6, 8($sp)
		addi	$sp, $sp, 16
	.end_macro
	
	# Macro: insert_to_nth_bit
	# Usage: insert_to_nth_bit(<register containing bit pattern>, <register containing bit position n>, 
	#			   <register containing 0 or 1 to change to>)
	.macro insert_to_nth_bit($regD, $regS, $regT)
		addi	$sp, $sp, -20
		sw	$fp, 20($sp)
		sw	$ra, 16($sp)
		sw	$s6, 12($sp)
		sw	$s7, 8($sp)
		addi	$fp, $fp, -20
	
		or	$s6, $zero, 1			# Set $s6 to 1
		sllv	$s6, $s6, $regS			# Shift $s6 to the left by n to create a mask, and store the mask in $s6
		not	$s6, $s6			# Invert the mask
		and 	$s6, $s6, $regD			# Changing the nth bit to 0, while keeping everything else the same and storing it in $s6
		
		or	$s7, $zero, $regT		# Set $s7 to insert value
		sllv	$s7, $s7, $regS			# Shift $s7 to the left by n
		or 	$regD, $s6,$s7			# Inserting the insert value to the nth position 
		
		lw	$fp, 20($sp)
		lw	$ra, 16($sp)
		lw	$s6, 12($sp)
		lw	$s7, 8($sp)
		addi	$sp, $sp, 20
	.end_macro
	
	# Macro: twos_complement
	# Usage: twos_complement(<register containing bit pattern>)
	.macro twos_complement($regD)
		addi 	$sp, $sp, -20		
		sw	$fp, 20($sp)
		sw	$ra, 16($sp)
		sw	$a0, 12($sp)
		sw	$a1, 8($sp)
		addi	$fp, $fp, -20
	
		not	$regD, $regD			# bitwise-NOT $regD
		or 	$a0, $zero, 1			# Set $a0 to 1
		move 	$a1, $regD			# Move $regD to $a1
		jal 	add_logical			# Add $a0 (which has the value of 1 now) to $a1
	
		lw	$fp, 20($sp)
		lw	$ra, 16($sp)
		lw	$a0, 12($sp)
		lw	$a1, 8($sp)
		addi	$sp, $sp, 20
		
		move	$regD, $v0			# Set $regD to $v0 (its negation)
	.end_macro

	# Macro: bit_replicator
	# Usage: bit_replicator(<register containing bit value to replicate>)
	.macro bit_replicator($regD)
		addi 	$sp, $sp, -20		
		sw	$fp, 20($sp)
		sw	$ra, 16($sp)
		sw	$a0, 12($sp)
		sw	$a1, 8($sp)
		addi	$fp, $fp, -20
		
		move	$a1, $regD
		or 	$a0, $zero, $regD		# Index to start (0 or 1)
		bit_replicator_loop:
		beq	$a0, 32, bit_replicator_return	# While index is not 32
		insert_to_nth_bit($a1, $a0, $regD)	# Insert value 
		addi	$a0, $a0, 1			# Increment index
		j 	bit_replicator_loop		# Repeat loop
		bit_replicator_return:
		move	$regD, $a1			# Store result in register passed in
		
		lw	$fp, 20($sp)
		lw	$ra, 16($sp)
		lw	$a0, 12($sp)
		lw	$a1, 8($sp)
		addi	$sp, $sp, 20
	.end_macro
	
	# Macro: check_negative
	# Usage: check_negative(<Register containing bit pattern>, <Register containing MSB>)
	.macro check_negative($regD, $regS)
		addi	$sp, $sp, -24
		sw	$fp, 24($sp)
		sw	$ra, 20($sp)
		sw	$s3, 16($sp)
		sw	$s4, 12($sp)
		sw	$s5, 8($sp)
		addi	$fp, $fp, -24
		
		addi	$s4, $zero, 31
		or	$s5, $zero, $zero

		extract_nth_bit($s5, $regD, $s4)	# Extract most significant bit
		
		beq 	$s5, 0, check_negative_return	# If MSB is 0, do nothing, else complement it
		move	$s3, $regD
		twos_complement($s3)		
		move	$regD, $s3
		
		check_negative_return:
		move	$regS, $s5
	
		lw	$fp, 24($sp)
		lw	$ra, 20($sp)
		lw	$s3, 16($sp)
		lw	$s4, 12($sp)
		lw	$s5, 8($sp)
		addi	$sp, $sp, 24
	.end_macro
	
	# Macro: twos_complement_64bit
	# Usage: twos_complement_64bit(<Register containing lower 32 bits>, <Register containing higher 32 bits>,
	#			       <Temporary reigister>)
	.macro twos_complement_64bit($regD, $regS, $regT)
		addi	$sp, $sp, -28
		sw	$fp, 28($sp)
		sw	$ra, 24($sp)
		sw	$a0, 20($sp)
		sw	$a1, 16($sp)
		sw	$s6, 12($sp)
		sw	$s7, 8($sp)
		addi	$fp, $fp, -28
		
		not	$regD, $regD						# bitwise-NOT $regD (lower 32 bits)
		move	$a0, $regD		
		move	$a1, $regT		
		
		jal	add_logical						# Add $regD and $regT (1)
		move	$s6, $v0						# Store summation in $s6
		move	$a1, $v1
		
		bne 	$regS, $zero, upper_bits_can_be_complemented
		bit_replicator($regT)
		j	twos_complement_64bit_return
		
		upper_bits_can_be_complemented:
		not 	$regS, $regS
		move	$a0, $regS						# bit pattern
		move	$a1, $a1						# Carry-in
		jal	add_logical
		move	$regT, $v0
		
		twos_complement_64bit_return:
		move	$v0, $s6
		move	$v1, $regT
	
		lw	$fp, 28($sp)
		lw	$ra, 24($sp)
		lw	$a0, 20($sp)
		lw	$a1, 16($sp)
		lw	$s6, 12($sp)
		lw	$s7, 8($sp)
		addi	$sp, $sp, 28
	.end_macro 
