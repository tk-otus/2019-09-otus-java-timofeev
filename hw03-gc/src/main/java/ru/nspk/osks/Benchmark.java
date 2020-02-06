package ru.nspk.osks;


import java.util.ArrayList;

public class Benchmark implements BenchmarkMBean {
    private final int loopCounter;
    private volatile int size = 0;
    private int arraySize = 0;

    Benchmark(int loopCounter) {
        this.loopCounter = loopCounter;
    }

    void run() throws InterruptedException {
        ArrayList<String> arr = new ArrayList(size);
        for (int i = 0; i < loopCounter; i++) {
            for (int c = 0; c < size; c++) {
                arr.add(new String(new char[0]));
            }
            for (int c = 0; c < size / 2; c++) {
                arr.remove(c);
            }
            arraySize = arr.size();
            Thread.sleep(100);
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        System.out.println("New size: " + size);
        this.size = size;
    }

    public int getArraySize() {
        return arraySize;
    }
}
