package com.desgin.pattern.behavioral.mediator;

public class Test {
    public static void main(String[] args) {
        User tom = new User("ldc");
        User jack = new User("ghl");

        tom.sendMessage("hey!jack");
        jack.sendMessage("hey!tom");
    }
}
