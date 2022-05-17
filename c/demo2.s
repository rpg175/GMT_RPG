	.section	__TEXT,__text,regular,pure_instructions
	.build_version macos, 12, 0	sdk_version 12, 3
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
	calll	L0$pb
L0$pb:
	popl	%eax
	movl	L___stack_chk_guard$non_lazy_ptr-L0$pb(%eax), %ecx
	movl	(%ecx), %ecx
	movl	%ecx, -4(%ebp)
	movl	$0, -20(%ebp)
	movl	l___const.main.arr-L0$pb(%eax), %ecx
	movl	%ecx, -16(%ebp)
	movl	(l___const.main.arr-L0$pb)+4(%eax), %ecx
	movl	%ecx, -12(%ebp)
	movl	(l___const.main.arr-L0$pb)+8(%eax), %ecx
	movl	%ecx, -8(%ebp)
	movl	-16(%ebp), %ecx
	movl	%ecx, -24(%ebp)
	movl	-12(%ebp), %ecx
	movl	%ecx, -28(%ebp)
	movl	-8(%ebp), %ecx
	movl	%ecx, -32(%ebp)
	movl	L___stack_chk_guard$non_lazy_ptr-L0$pb(%eax), %eax
	movl	(%eax), %eax
	movl	-4(%ebp), %ecx
	cmpl	%ecx, %eax
	jne	LBB0_2
## %bb.1:
	movl	$1, %eax
	addl	$40, %esp
	popl	%ebp
	retl
LBB0_2:
	calll	___stack_chk_fail
	ud2
	.cfi_endproc
                                        ## -- End function
	.section	__TEXT,__const
	.p2align	2                               ## @__const.main.arr
l___const.main.arr:
	.long	1                               ## 0x1
	.long	2                               ## 0x2
	.long	3                               ## 0x3

	.section	__IMPORT,__pointers,non_lazy_symbol_pointers
L___stack_chk_guard$non_lazy_ptr:
	.indirect_symbol	___stack_chk_guard
	.long	0

.subsections_via_symbols
