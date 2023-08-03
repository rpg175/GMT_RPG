package com.rpg175.interview;

public class OverloadDemo {
    public static void main(String[] args) {
        Father2 f = new Father2();
        f.say();
        f.say("li");
    }
}

class Father2 {
    public void say() {
        System.out.println("hi");
    }

    public void say(String name) {
        System.out.println("hi " + name);
    }
}
