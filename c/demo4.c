//
// Created by ko on 2022/5/23.
//
#include <stdio.h>

extern int value_sum;
int sum(int a,int b) {
    return a + b;
}

int main(void) {
    value_sum = sum(1,2);
    printf("%d\n",value_sum);
    return 1;
}
