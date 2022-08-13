//
// Created by ko on 2022/6/12.
//

#include <stdlib.h>

int main(void) {
    void *p = malloc(0);
    void *q = realloc(p,128*1024);

    return 1;
}