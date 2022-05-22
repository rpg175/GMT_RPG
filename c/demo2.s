	.section	__TEXT,__text,regular,pure_instructions
	.build_version macos, 12, 0	sdk_version 12, 3
	.globl	_main                           ## -- Begin function main
	.p2align	4, 0x90
_main:                                  ## @main
## %bb.0:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$56, %esp
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
	leal	-16(%ebp), %ecx
	movl	-16(%ebp), %edx
	movl	%edx, -24(%ebp)
	movl	-12(%ebp), %edx
	movl	%edx, -28(%ebp)
	movl	-8(%ebp), %edx
	movl	%edx, -32(%ebp)
	movl	%ecx, -36(%ebp)
	movl	-36(%ebp), %ecx
	movl	(%ecx), %ecx
	movl	%ecx, -40(%ebp)
	movl	-36(%ebp), %ecx
	movl	4(%ecx), %ecx
	movl	%ecx, -44(%ebp)
	movl	-36(%ebp), %ecx
	movl	8(%ecx), %ecx
	movl	%ecx, -48(%ebp)
	movl	L___stack_chk_guard$non_lazy_ptr-L0$pb(%eax), %eax
	movl	(%eax), %eax
	movl	-4(%ebp), %ecx
	cmpl	%ecx, %eax
	jne	LBB0_2
## %bb.1:
	movl	$1, %eax
	addl	$56, %esp
	popl	%ebp
	retl
LBB0_2:
	calll	___stack_chk_fail
	ud2
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
