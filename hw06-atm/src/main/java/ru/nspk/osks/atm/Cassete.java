package ru.nspk.osks.atm;

public class Cassete {
    private final String name;
    private static final int MAX_BANKNOTES_NUMBER = 100;
    private int banknotesNumber = 0;

    public Cassete(String name) {
        this.name = name;
    }

    public Cassete(String name, int banknotesNumber) {
        this.name = name;
        putCash(banknotesNumber);
    }

    public int getBanknotesNumber() {
        return banknotesNumber;
    }

    public int putCash(int count) {
        if (count <= 0) {
            throw new IllegalStateException("Нельзя использовать нулевое или отрицаиельное число");
        }
        if (banknotesNumber + count > MAX_BANKNOTES_NUMBER) {
            throw new IllegalStateException("В корзине не хватает места");
        }
        banknotesNumber += count;
        return banknotesNumber;
    }

    public int getCash(int count) {
        if (count <= 0) {
            throw new IllegalStateException("Нельзя использовать нулевое или отрицаиельное число");
        }
        if (banknotesNumber - count < 0) {
            throw new IllegalStateException("В корзине не хватает купюр");
        }
        banknotesNumber -= count;
        System.out.println(name + ": Выдано " + count + " банкнот");
        return banknotesNumber;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Cassete{" +
                "name='" + name + '\'' +
                ", banknotesNumber=" + banknotesNumber +
                '}';
    }
}
