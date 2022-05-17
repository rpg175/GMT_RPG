	.section	__TEXT,__text,regular,pure_instructions
	.build_version macos, 12, 0	sdk_version 12, 3
	.globl	_sum                            ## -- Begin function sum
	.p2align	4, 0x90
_sum:                                   ## @sum
	.cfi_startproc
## %bb.0:
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset %ebp, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register %ebp
	movl	28(%ebp), %eax
	movl	24(%ebp), %eax
	movl	20(%ebp), %eax
	movl	16(%ebp), %eax
	movl	12(%ebp), %eax
	movl	8(%ebp), %eax
	movl	8(%ebp), %eax
	addl	12(%ebp), %eax
	addl	16(%ebp), %eax
	addl	20(%ebp), %eax
	addl	24(%ebp), %eax
	addl	28(%ebp), %eax
	popl	%ebp
	retl
	.cfi_endproc
                                        ## -- End function
	.globl	_main                           ## -- Begin function main
	.p2align	4, 0x90
_main:                                  ## @main
	.cfi_startproc
## %bb.0:
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset %ebp, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register %ebp
	subl	$40, %esp
	movl	$0, -4(%ebp)
	movl	$1, (%esp)
	movl	$2, 4(%esp)
	movl	$3, 8(%esp)
	movl	$4, 12(%esp)
	movl	$5, 16(%esp)
	movl	$6, 20(%esp)
	calll	_sum
	xorl	%eax, %eax
	addl	$40, %esp
	popl	%ebp
	retl
	.cfi_endproc
                                        ## -- End function
.subsections_via_symbols
