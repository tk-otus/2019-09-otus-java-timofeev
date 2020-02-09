package ru.nspk.osks.proxy;

public class TestLoggingImpl implements TestloggingInterface {

    @Override
    public void calc(int param) {
        System.out.println("Param: " + param);
    }
}
