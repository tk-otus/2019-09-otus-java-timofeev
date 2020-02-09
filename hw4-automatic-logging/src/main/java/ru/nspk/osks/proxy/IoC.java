package ru.nspk.osks.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class IoC {

    public static TestloggingInterface create() {
        InvocationHandler handler = new MyInvocationHandler(new TestLoggingImpl());
        return (TestloggingInterface) Proxy.newProxyInstance(IoC.class.getClassLoader(),
                new Class<?>[]{TestloggingInterface.class}, handler);

    }

    static class MyInvocationHandler implements InvocationHandler {
        private final TestloggingInterface testLogging;

        public MyInvocationHandler(TestloggingInterface testLogging) {
            this.testLogging = testLogging;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("executed method: " + method.getName() + ", param: " + args[0]);
            return method.invoke(testLogging, args);
        }
    }
}
