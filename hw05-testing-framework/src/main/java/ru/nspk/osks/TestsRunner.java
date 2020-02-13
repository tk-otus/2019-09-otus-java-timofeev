package ru.nspk.osks;

import ru.nspk.osks.testing.After;
import ru.nspk.osks.testing.Before;
import ru.nspk.osks.testing.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestsRunner {
    private final Class<?> klass;
    private ArrayList<Method> beforeMethods = new ArrayList<>();
    private ArrayList<Method> testMethods = new ArrayList<>();
    private ArrayList<Method> afterMethods = new ArrayList<>();

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
            printTestMethodInfo(testMethod);
            try {
                Object classInstance = klass.getDeclaredConstructor().newInstance();

                // Выполняем методы с аннотацией Before
                for (Method beforeMethod : beforeMethods) {
                    beforeMethod.invoke(classInstance);
                }

                // Выполняем метод самого теста
                try {
                    testMethod.invoke(classInstance);
                    succeedTests += 1;
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                }

                // Выполняем методы с аннотацией After
                for (Method afterMethod : afterMethods) {
                    afterMethod.invoke(classInstance);
                }
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
}
