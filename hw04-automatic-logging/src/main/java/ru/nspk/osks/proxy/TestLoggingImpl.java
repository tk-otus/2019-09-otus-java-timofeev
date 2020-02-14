package ru.nspk.osks.proxy;

public class TestLoggingImpl implements TestLoggingInterface {

    TestLoggingImpl() {
    }

    @Log
    @Override
    public void calc() {
    }

    @Log
    @Override
    public void calc(int param) {
    }

    @Log
    @Override
    public void calc(int param1, int param2) {
    }

    @Override
    public void calc(int param1, int param2, int param3) {
        System.out.println(
                "Oops, this method don't have the @Log annotation. So, we can't show you what params it contains");
    }
}
