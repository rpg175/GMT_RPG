cmake_minimum_required(VERSION 3.22)
project(c C)

set(CMAKE_C_STANDARD 11)

add_executable(c demo5.c a.c b.c mymalloc.c)
