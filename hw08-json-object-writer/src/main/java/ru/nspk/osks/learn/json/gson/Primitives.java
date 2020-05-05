package ru.nspk.osks.learn.json.gson;

import java.util.Arrays;
import java.util.Objects;

public class Primitives {
    private final int v1 = 10;
    private final String v2 = "Hello";
    private final char v3 = 'h';
    private final boolean v4 = true;
    private final long v5 = 13L;
    private final String[] v6 = {"Hello", "Java"};

    @Override
    public String toString() {
        return "Primitives{" +
                "v1=" + v1 +
                ", v2='" + v2 + '\'' +
                ", v3=" + v3 +
                ", v4=" + v4 +
                ", v5=" + v5 +
                ", v6=" + Arrays.toString(v6) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Primitives that = (Primitives) o;
        return v1 == that.v1 &&
                v3 == that.v3 &&
                v4 == that.v4 &&
                v5 == that.v5 &&
                Objects.equals(v2, that.v2) &&
                Arrays.equals(v6, that.v6);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(v1, v2, v3, v4, v5);
        result = 31 * result + Arrays.hashCode(v6);
        return result;
    }
}
