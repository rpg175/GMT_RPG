#include <stdio.h>
#include <string.h>
struct sdshdr {
	int len;
	int free;
	char buf[];
};

int main(void) {
	//struct sdshdr _struct = {1,2,"123"};
	printf("%d",sizeof(struct sdshdr));
	//printf("%d",sizeof(_struct));
	return 1;
}
