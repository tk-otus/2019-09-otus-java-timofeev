package ru.nspk.osks.atm;

public class Cassette implements Cell, Comparable<Cassette> {
    static final int MAX_BANKNOTES_NUMBER = 100;
    private final Banknote banknote;
    private int banknotesCount;

    public Cassette(Banknote banknote) {
        this(banknote, 0);
    }

    public Cassette(Banknote banknote, int banknotesCount) {
        this.banknote = banknote;
        if (banknotesCount < 0 || banknotesCount > MAX_BANKNOTES_NUMBER) {
            throw new IllegalArgumentException("Не поддерживаемое значение количества купюр");
        }
        this.banknotesCount = banknotesCount;
    }

    public Cassette(Cassette cassette) {
        this(cassette.banknote, cassette.banknotesCount);
    }

    @Override
    public void putBanknotesIn(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Не поддерживаемое значение количества купюр");
        }
        if (banknotesCount + count > MAX_BANKNOTES_NUMBER) {
            throw new IllegalArgumentException("В корзине не хватает места");
        }
        banknotesCount += count;
    }

    @Override
    public void getBanknotesOut(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Не поддерживаемое значение количества купюр");
        }
        if (banknotesCount - count < 0) {
            throw new IllegalArgumentException("В корзине не хватает купюр");
        }
        banknotesCount -= count;
        System.out.println(banknote + ": Выдано " + count + " банкнот");
    }

    @Override
    public int getFullAmount() {
        return banknotesCount * banknote.getValue();
    }

    @Override
    public int getFreeSpace() {
        return MAX_BANKNOTES_NUMBER - banknotesCount;
    }

    public Banknote getBanknote() {
        return banknote;
    }

    public int getBanknoteFaceValue() {
        return getBanknote().getValue();
    }

    public int getBanknotesCount() {
        return banknotesCount;
    }

    @Override
    public int compareTo(Cassette o) {
        int compare = o.getBanknote().getValue();
        return banknote.getValue() - compare;
    }

    @Override
    public String toString() {
        return "Cassette{" +
                "banknote=" + banknote +
                ", banknotesCount=" + banknotesCount +
                '}';
    }
}
