package ru.nspk.osks.atm;

public class Cassette implements Comparable<Cassette> {
    static final int MAX_BANKNOTES_NUMBER = 100;
    private final int faceValue;
    private int banknotesCount;

    public Cassette(int faceValue) {
        this(faceValue, 0);
    }

    public Cassette(int faceValue, int banknotesNumber) {
        if (faceValue <= 0) {
            throw new IllegalArgumentException("Не поддерживаемое значение номинала купюр");
        }
        this.faceValue = faceValue;
        putCash(banknotesNumber);
    }

    public int getBanknotesCount() {
        return banknotesCount;
    }

    public int putCash(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Не поддерживаемое значение количества купюр");
        }
        if (banknotesCount + count > MAX_BANKNOTES_NUMBER) {
            throw new IllegalArgumentException("В корзине не хватает места");
        }
        banknotesCount += count;
        return banknotesCount;
    }

    public int getCash(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Не поддерживаемое значение количества купюр");
        }
        if (banknotesCount - count < 0) {
            throw new IllegalArgumentException("В корзине не хватает купюр");
        }
        banknotesCount -= count;
        System.out.println(false + ": Выдано " + count + " банкнот");
        return banknotesCount;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public int getTotalCash() {
        return banknotesCount * faceValue;
    }

    @Override
    public int compareTo(Cassette o) {
        int compare = o.getFaceValue();
        return faceValue - compare;
    }

    @Override
    public String toString() {
        return "Cassette{" +
                "faceValue=" + faceValue +
                ", banknotesCount=" + banknotesCount +
                '}';
    }
}
