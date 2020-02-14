package ru.nspk.osks;

import ru.nspk.osks.proxy.IoC;
import ru.nspk.osks.proxy.TestLoggingInterface;

public class DemoProxy {
    public static void main(String[] args) {
        TestLoggingInterface tl = IoC.getInstance();

        tl.calc();
        tl.calc(144);
        tl.calc(150, 15);
        tl.calc(333, 666, 999);
    }
}
