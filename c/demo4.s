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
	movl	12(%ebp), %eax
	movl	8(%ebp), %eax
	movl	8(%ebp), %eax
	addl	12(%ebp), %eax
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
	subl	$24, %esp
	calll	L1$pb
L1$pb:
	popl	%eax
	movl	%eax, -8(%ebp)                  ## 4-byte Spill
	movl	$0, -4(%ebp)
	movl	$1, (%esp)
	movl	$2, 4(%esp)
	calll	_sum
	movl	%eax, %edx
	movl	-8(%ebp), %eax                  ## 4-byte Reload
	leal	L_.str-L1$pb(%eax), %ecx
	movl	L_value_sum$non_lazy_ptr-L1$pb(%eax), %eax
	movl	%edx, (%eax)
	movl	(%eax), %eax
	movl	%ecx, (%esp)
	movl	%eax, 4(%esp)
	calll	_printf
	movl	$1, %eax
	addl	$24, %esp
	popl	%ebp
	retl
	.cfi_endproc
                                        ## -- End function
	.section	__TEXT,__cstring,cstring_literals
L_.str:                                 ## @.str
	.asciz	"%d\n"

	.section	__IMPORT,__pointers,non_lazy_symbol_pointers
L_value_sum$non_lazy_ptr:
	.indirect_symbol	_value_sum
	.long	0

.subsections_via_symbols
