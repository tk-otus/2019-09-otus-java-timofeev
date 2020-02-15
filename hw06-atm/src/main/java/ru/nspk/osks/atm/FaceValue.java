package ru.nspk.osks.atm;

import java.util.Objects;

public class FaceValue implements Comparable<FaceValue> {
    private final int faceValue;
    Cassete cassete;

    public FaceValue(int faceValue, Cassete cassete) {
        this.faceValue = faceValue;
        this.cassete = cassete;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public int getTotalCash() {
        return cassete.getBanknotesNumber() * faceValue;
    }

    public void putCash(int count) {
        cassete.putCash(count);
    }

    public int getBanknotesNumber() {
        return cassete.getBanknotesNumber();
    }

    @Override
    public int compareTo(FaceValue o) {
        int compare = o.getFaceValue();
        return getFaceValue() - compare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FaceValue faceValue1 = (FaceValue) o;
        return faceValue == faceValue1.faceValue &&
                cassete.equals(faceValue1.cassete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(faceValue, cassete);
    }

    @Override
    public String toString() {
        return "FaceValue{" +
                "faceValue=" + faceValue +
                ", cassete=" + cassete +
                '}';
    }
}
