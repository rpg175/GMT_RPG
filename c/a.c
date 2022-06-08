//
// Created by ko on 2022/6/8.
//
extern  int shared;

int main() {
    int a = 100;
    swap(&a,&shared);
}