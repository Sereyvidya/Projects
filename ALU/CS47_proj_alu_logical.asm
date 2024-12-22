.include "./cs47_proj_macro.asm"
.text
.globl au_logical
# TBD: Complete your project procedures
# Needed skeleton is given
#####################################################################
# Implement au_logical
# Argument:
# 	$a0: First number
#	$a1: Second number
#	$a2: operation code ('+':add, '-':sub, '*':mul, '/':div)
# Return:
#	$v0: ($a0+$a1) | ($a0-$a1) | ($a0*$a1):LO | ($a0 / $a1)
# 	$v1: ($a0 * $a1):HI | ($a0 % $a1)
# Notes:
#####################################################################

au_logical:
 	beq	$a2, '+', add_logical		# Branch if addition
	beq	$a2, '-', sub_logical		# Branch if subtraction
	beq	$a2, '*', mul_logical		# Branch if multiplication
	beq	$a2, '/', div_logical		# Branch if division

sub_logical:
	addi	$sp, $sp, -16
	sw	$fp, 16($sp)
	sw	$ra, 12($sp)
	sw	$a1, 8($sp)
	addi	$fp, $fp, -16
	
	twos_complement($a1)			# $a0 - $a1 = $a0 + (negation of $a1)
	jal	add_logical			
											
	lw	$fp, 16($sp)
	lw	$ra, 12($sp)
	lw	$a1, 8($sp)
	addi	$sp, $sp, 16
	
	jr 	$ra
	
add_logical:
	addi 	$sp, $sp, -44
	sw	$fp, 44($sp)
	sw	$ra, 40($sp)
	sw	$a0, 36($sp)
	sw	$a1, 32($sp)
	sw	$s0, 28($sp)
	sw	$s1, 24($sp)
	sw	$s2, 20($sp)
	sw	$s3, 16($sp)
	sw	$s4, 12($sp)
	sw	$s5, 8($sp)
	addi	$fp, $fp, -44
		
	or 	$s0, $zero, $zero		# $s0 is n (index) of bit (n: 0 -> 31)
	or	$s1, $zero, $zero		# $s1 is the Carry-in
	or	$v0, $zero, $zero		# $v0 will hold the sum
	
	add_loop:	
	beq 	$s0, 32, add_return 		# If n is 32, terminate loop
	extract_nth_bit($s2, $a0, $s0)		# $s2 holds the nth bit of $a0
	extract_nth_bit($s3, $a1, $s0)  	# $s3 holds the nth bit of $a1

	xor	$s4, $s2, $s3			# $s4 = $a0[n] bitwise-XOR $a1[n] 
	and	$s5, $s2, $s3			# $s5 = $a0[n] bitwise-AND $a1[n] 
	
	xor	$s2, $s4, $s1			# $s2 = $s4 bitwise-XOR $s1 (carry-in)
	and	$s3, $s4, $s1			# $s3 = $s5 bitwise-AND $s1 (carry-in)
	or	$s1, $s5, $s3			# $s1 now holds next loop iteration's carry-in
	insert_to_nth_bit($v0, $s0, $s2)	# Change $v0[n] to $s2
	addi	$s0, $s0, 1			# Increment n by 1
	j	add_loop			# Continue with loop
	
	add_return:
	move	$v0, $v0			# Stores result in $v0
	move	$v1, $s1			# Stores carry-out in $v1
	
	lw	$fp, 44($sp)
	lw	$ra, 40($sp)
	lw	$a0, 36($sp)
	lw	$a1, 32($sp)
	lw	$s0, 28($sp)
	lw	$s1, 24($sp)
	lw	$s2, 20($sp)
	lw	$s3, 16($sp)
	lw	$s4, 12($sp)
	lw	$s5, 8($sp)
	addi	$sp, $sp, 44
		
	jr	$ra

mul_logical:					# Stores Product in $v0(LO) and $v1(HI)

mul_signed:
	addi	$sp, $sp, -32
	sw	$fp, 32($sp)
	sw	$ra, 28($sp)
	sw	$a0, 24($sp)
	sw	$a1, 20($sp)
	sw	$s0, 16($sp)
	sw	$s1, 12($sp)
	sw	$s2, 8($sp)
	addi	$fp, $fp, -32
	
	check_negative($a0, $s0)		# If $a0 is negative, store MSB in $s0 and complement $a0  
	check_negative($a1, $s1)		# If $a1 is negative, store MSB in $s1 and complement $a1
	
	xor	$s2, $s0, $s1			# bitwise-XOR both MSB to get MSB of final product
	jal	mul_unsigned
	
	beq	$s2, 0, mul_signed_return
	twos_complement_64bit($v0, $v1, $s2)

	mul_signed_return:
	lw	$fp, 32($sp)
	lw	$ra, 28($sp)
	lw	$a0, 24($sp)
	lw	$a1, 20($sp)
	lw	$s0, 16($sp)
	lw	$s1, 12($sp)
	lw	$s2, 8($sp)
	addi	$sp, $sp, -32
	
	jr	$ra

mul_unsigned:
	addi 	$sp, $sp, -36
	sw	$fp, 36($sp)
	sw	$ra, 32($sp)
	sw	$s0, 28($sp)
	sw	$s1, 24($sp)
	sw	$s2, 20($sp)
	sw	$s3, 16($sp)
	sw	$s4, 12($sp)
	sw	$s5, 8($sp)
	addi	$fp, $fp, -36
	
	or 	$s0, $zero, $zero		# $s0 is n (index) of bit (n: 0 -> 31)
	or	$s1, $zero, $zero		# $s1 (HI) is initialized as 0
	or	$s2, $zero, $a1			# $s2 (LO) is initialized as multiplier
	or	$s3, $zero, $a0			# $s3 is initialized as multiplicand 
		
	mul_loop:
	beq	$s0, 32, mul_unsigned_return
	or	$s4, $zero, $zero		# $s4 is initialized as 0
	extract_nth_bit($s4, $s2, $zero)	# $s4 = LO[0]
	bit_replicator($s4)			# $s4 = {32LO[0]}
	
	or 	$s5, $zero, $zero		# $s5 is initialized as 0
	and	$s5, $s3, $s4			# $s5 = $s3 bitwise-AND $s4
	
	move	$a0, $s1			# $s1 = $s1 + $s5
	move 	$a1, $s5
	jal 	add_logical
	move	$s1, $v0
	
	srl	$s2, $s2, 1			# Shift $s2 right by 1 bit
	
	or	$a0, $zero, $zero		# Set $s2[31] = $s1[0]
	addi	$a1, $zero, 31			
	extract_nth_bit($a0, $s1, $zero)
	insert_to_nth_bit($s2, $a1, $a0)
	
	srl	$s1, $s1, 1			# Shift $s1 right by 1 bit

	addi	$s0, $s0, 1			# Increment n by 1
	j	mul_loop
	
	mul_unsigned_return:
	move 	$v0, $s2			# Store $v0 (LO)
	move	$v1, $s1			# Store $v1 (HI)
	
	lw	$fp, 36($sp)
	lw	$ra, 32($sp)
	lw	$s0, 28($sp)
	lw	$s1, 24($sp)
	lw	$s2, 20($sp)
	lw	$s3, 16($sp)
	lw	$s4, 12($sp)
	lw	$s5, 8($sp)
	addi	$sp, $sp, 36
	
	jr	$ra

div_logical:

div_signed:
	addi	$sp, $sp, -32
	sw	$fp, 32($sp)
	sw	$ra, 28($sp)
	sw	$a0, 24($sp)
	sw	$a1, 20($sp)
	sw	$s0, 16($sp)
	sw	$s1, 12($sp)
	sw	$s2, 8($sp)
	addi	$fp, $fp, -32
	
	check_negative($a0, $s0)
	check_negative($a1, $s1)
	
	xor	$s2, $s0, $s1
	jal	div_unsigned

	check_sign_Q:
	beq	$s2, 0, check_sign_R
	move	$s2, $v1			# Save $v1 because it gets override in next step
	twos_complement($v0)		
	move	$v1, $s2			# Restore $v1
	
	check_sign_R:
	beq	$s0, 0, div_signed_return
	move	$s2, $v0			# Save $v0 because it gets override in next step
	twos_complement($v1)
	move	$v0, $s2			# Restore $v0

	div_signed_return:
	lw	$fp, 32($sp)
	lw	$ra, 28($sp)
	lw	$a0, 24($sp)
	lw	$a1, 20($sp)
	lw	$s0, 16($sp)
	lw	$s1, 12($sp)
	lw	$s2, 8($sp)
	addi	$sp, $sp, 32
	
	jr	$ra

div_unsigned:
	addi	$sp, $sp, -40
	sw	$fp, 40($sp)
	sw	$ra, 36($sp)
	sw	$a0, 32($sp)
	sw	$a1, 28($sp)
	sw	$s0, 24($sp)
	sw	$s1, 20($sp)
	sw	$s2, 16($sp)
	sw	$s3, 12($sp)
	sw	$s4, 8($sp)
	addi	$fp, $fp, -40
	
	or	$s0, $zero, $zero		# (I)
	or	$s1, $zero, $a0			# (Q)
	or	$s2, $zero, $a1			# (D)
	twos_complement($s2)			# -D
	or	$s3, $zero, $zero		# (R)
	
	div_loop:
	beq	$s0, 32, div_unsigned_return
	sll	$s3, $s3, 1			# R = R << 1
	
	addi	$a0, $zero, 31			# R[0] = Q[31]
	extract_nth_bit($a1, $s1, $a0)
	or	$a0, $zero, $zero
	insert_to_nth_bit($s3, $a0, $a1)
	
	sll	$s1, $s1, 1			# Q = Q << 1
	
	move	$a0, $s3			# S = R - D
	move	$a1, $s2
	jal	add_logical
	move	$s4, $v0
	
	blt	$s4, $zero, div_increment	# S < 0 
	move	$s3, $s4			# R = S
	or 	$a0, $zero, 1			# Q[0] = 1
	insert_to_nth_bit($s1, $zero, $a0)
	
	div_increment:
	addi	$s0, $s0, 1			# I = I + 1
	j 	div_loop
	
	div_unsigned_return:
	move	$v0, $s1			# Store quotient in $v0
	move	$v1, $s3			# Store remainder in $v1
	
	lw	$fp, 40($sp)
	lw	$ra, 36($sp)
	lw	$a0, 32($sp)
	lw	$a1, 28($sp)
	lw	$s0, 24($sp)
	lw	$s1, 20($sp)
	lw	$s2, 16($sp)
	lw	$s3, 12($sp)
	lw	$s4, 8($sp)
	addi	$sp, $sp, 40
	
	jr 	$ra
