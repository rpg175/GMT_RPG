package com.rpg175.io;

public class TestEx {
    public static void main(String[] args) {
        try {
            int a = 1 / 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        } catch (ArithmeticException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
