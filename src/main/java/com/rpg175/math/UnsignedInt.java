package com.rpg175.math;

public class UnsignedInt {
    public static void main(String[] args) {
        byte b = -1;
        int x = Byte.toUnsignedInt(b);
        int x2 = b;  //int接byte -1
        System.out.println("Signed value in byte   = " + b);
        System.out.println("Unsigned value in  byte   = " + x);
        System.out.println("Byte to int value in  byte   = " + x2);
    }
}
