package com.rpg175.jnidemo;

public class JniDemo {

    static {
        System.load("/Users/ko/Documents/L_learn_workspace/JavaGameMakerTools/src/main/java/com/rpg175/jnidemo/libSayHello.so");
    }

    //添加native表示他使用c语言来实现
    static native void sayHello();

    public static void main(String args[]) {
        sayHello();
    }
}