package ru.nspk.osks.myjson.classes;

import java.util.Arrays;

public class AllTypesClass {
    private String v1 = "v1";
    private int v2 = 2;
    private long v3 = 3L;
    private char v4 = '4';
    private boolean v5 = true;
    private String[] v6 = {"1", "2", "3"};
    private int[] v7 = {1, 2, 3};

    @Override
    public String toString() {
        return "SimpleClass{" +
                "v1='" + v1 + '\'' +
                ", v2=" + v2 +
                ", v3=" + v3 +
                ", v4=" + v4 +
                ", v5=" + v5 +
                ", v6=" + Arrays.toString(v6) +
                ", v7=" + Arrays.toString(v7) +
                '}';
    }
}
