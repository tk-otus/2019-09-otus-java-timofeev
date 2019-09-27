package ru.nspk.osks;


import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HelloOtus {
    private static final int MEASURE_COUNT = 1;

    public static void main(String... args) {
        calcTime("TotalForAllProgram", HelloOtus::run);
    }

    private static void run() {
        long totalTimeNs = 0;
        List<Integer> example = new ArrayList<>();
        int min = 0;
        int max = 999_999;

        totalTimeNs += calcTime("CycleFor", () -> {
            for (int i = min; i < max + 1; i++) {
                example.add(i);
            }
        });

        List<Integer> result = new ArrayList<>();
        totalTimeNs += calcTime("Collections.shuffle", () -> Collections.shuffle(example));
        totalTimeNs += calcTime("result.addAll", () -> result.addAll(example));
        totalTimeNs += calcTime("Lists.reverse", () -> Lists.reverse(result));
        System.out.println("==================");
        System.out.println("[TotalCalcTime] Time spent: " + totalTimeNs + "ns (" + totalTimeNs / 1_000_000 + "ms)");
    }

    private static long calcTime(String title, Runnable runnable) {
        long startTime = System.nanoTime();
        for (int i = 0; i < MEASURE_COUNT; i++) {
            runnable.run();
        }
        long finishTime = System.nanoTime();
        long timeNs = (finishTime - startTime) / MEASURE_COUNT;
        System.out.println("[" + title + "] Time spent: " + timeNs + "ns (" + timeNs / 1_000_000 + "ms)");
        return timeNs;
    }
}
