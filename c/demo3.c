#include <stdio.h>

struct sdshdr {
	int len;
	int free;
	char buf[];
};

int main(void) {
	struct sdshdr _struct;
	int a = 1;	
	printf("%d\n",sizeof(struct sdshdr));
	printf("%d\n",sizeof(_struct));
	printf("%d\n",sizeof(a++));
	printf("%d\n",(((&a)+1)-&a));
	printf("%d\n",(((int *)0) + 1));
	return 1;
}
