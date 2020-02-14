package ru.nspk.osks.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IoC {

    public static TestLoggingInterface getInstance() {
        InvocationHandler handler = new MyInvocationHandler(new TestLoggingImpl());
        return (TestLoggingInterface) Proxy.newProxyInstance(IoC.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class MyInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface testLogging;
        private ArrayList<String> annotatedMethods;

        public MyInvocationHandler(TestLoggingInterface testLogging) {
            this.testLogging = testLogging;
            this.annotatedMethods = setAnnotatedMethods();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (annotatedMethods.contains(getSignature(method))) {
                if (method.getParameterCount() == 0) {
                    System.out.println("Executed method: " + method.getName() + ", params: Without params");
                } else {
                    System.out.println("Executed method: " + method.getName() + ", params: " + concatenate(args));
                }
            }
            return method.invoke(testLogging, args);
        }

        private static <T> String concatenate(T[] objs) {
            return Stream.of(objs).map(Object::toString).collect(Collectors.joining(", "));
        }

        private ArrayList<String> setAnnotatedMethods() {
            ArrayList<String> annotatedMethods = new ArrayList<>();
            Method[] methods = TestLoggingImpl.class.getMethods();
            for (Method method : methods) {
                if (method.getAnnotation(Log.class) != null) {
                    annotatedMethods.add(getSignature(method));
                }
            }
            return annotatedMethods;
        }

        private static String getSignature(Method method) {
            StringBuilder result = new StringBuilder();
            result.append(method.getName());
            for (Parameter param : method.getParameters()) {
                result.append(":" + param.getType().getName());
            }
            return result.toString();
        }
    }
}
