//
// Created by ko on 2022/6/8.
//
int shared = 1;
void swap(int* a, int* b) {
    *a ^= *b ^= *a ^= *b;
}