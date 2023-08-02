package com.rpg175.interview;

/**
 * 重写，在继承关系中，子类继承父类的某个方法，但子类有其特殊行为，
 * 可以使用重写的方式对这个方法进行重写。
 * 关键点：
 * 1.发生在父子之间
 * 2.方法的方法名，参数列表，返回类型，一直。
 * 3.访问修饰符的限制一定要大于被重写方法的访问修饰符（public>protected>default>private)
 * 4.抛出的的检查异常
 */
public class OverrideDemo {
    public static void main(String[] args) {
        Father f = new Father();
        f.say();
        Sun sun = new Sun();
        sun.say();
    }
}

class Father {
    public void say() {
        System.out.println("hello");
    }
}

class Sun extends Father {
    public void say() {
        System.out.println("hi");
    }
}