package ru.nspk.osks;

import ru.nspk.osks.testing.After;
import ru.nspk.osks.testing.Before;
import ru.nspk.osks.testing.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestsRunner {
    private final Class<?> klass;
    private final List<Method> beforeMethods = new ArrayList<>();
    private final List<Method> testMethods = new ArrayList<>();
    private final List<Method> afterMethods = new ArrayList<>();

    private int succeedTests = 0;


    TestsRunner(Class<?> klass) {
        this.klass = klass;
        for (Method method : klass.getMethods()) {
            if (method.isAnnotationPresent(Before.class)) {
                beforeMethods.add(method);
            } else if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            } else if (method.isAnnotationPresent(After.class)) {
                afterMethods.add(method);
            }
        }
    }

    public void run() {
        for (Method testMethod : testMethods) {
            boolean testPassed;
            printTestMethodInfo(testMethod);
            try {
                Object classInstance = klass.getDeclaredConstructor().newInstance();

                testPassed = invokeMethods(beforeMethods, classInstance) && invokeMethod(testMethod, classInstance);
                testPassed = invokeMethods(afterMethods, classInstance) && testPassed;
                succeedTests = testPassed ? ++succeedTests : succeedTests;

            } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        printStat();
    }

    private static void printTestMethodInfo(Method method) {
        System.out.println("====================================");
        System.out.println("Running method: " + method.getName());
        System.out.println("====================================");
    }

    private void printStat() {
        System.out.println("======== TEST RESULTS ========");
        System.out.println("Total tests: " + testMethods.size());
        System.out.println("Passed: " + succeedTests);
        System.out.println("Failed: " + (testMethods.size() - succeedTests));
    }


    private boolean invokeMethods(List<Method> methods, Object classInstance) {
        boolean result = true;
        for (Method method : methods) {
            try {
                method.invoke(classInstance);
            } catch (Exception e) {
                e.printStackTrace(System.out);
                result = false;
            }
        }
        return result;
    }

    private boolean invokeMethod(Method method, Object classInstance) {
        return invokeMethods(Collections.singletonList(method), classInstance);
    }
}
