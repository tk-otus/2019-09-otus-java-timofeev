package ru.nspk.osks.proxy;

public class Demo {
    public static void main(String[] args) {
        TestloggingInterface tl = IoC.create();
        tl.calc(12);
    }
}
