.include "./cs47_proj_macro.asm"
.text
.globl au_normal
# TBD: Complete your project procedures
# Needed skeleton is given
#####################################################################
# Implement au_normal
# Argument:
# 	$a0: First number
#	$a1: Second number
#	$a2: operation code ('+':add, '-':sub, '*':mul, '/':div)
# Return:
#	$v0: ($a0+$a1) | ($a0-$a1) | ($a0*$a1):LO | ($a0 / $a1)
# 	$v1: ($a0 * $a1):HI | ($a0 % $a1)
# Notes:
#####################################################################
au_normal:
# TBD: Complete it
	beq	$a2, '+', add
	beq	$a2, '-', sub
	beq	$a2, '*', mul
	beq	$a2, '/', div
add:
	add 	$v0, $a0, $a1
	b	end
sub:
	sub	$v0, $a0, $a1
	b	end
mul:
	mult 	$a0, $a1
	mflo	$v0
	mfhi	$v1
	b	end
div:	
	div  	$a0, $a1
	mflo	$v0
	mfhi	$v1
end:
	jr 	$ra
