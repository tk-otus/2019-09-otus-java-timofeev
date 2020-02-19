package ru.nspk.osks.atm;

public enum RUBanknote implements Banknote {
    RUB_50    (50),
    RUB_100   (100),
    RUB_200   (200),
    RUB_500   (500),
    RUB_1000  (1000),
    RUB_2000  (2000),
    RUB_5000  (5000);

    private int faceValue;

    private RUBanknote(int faceValue) {
        this.faceValue = faceValue;

    }

    @Override
    public int getValue() {
        return faceValue;
    }

    @Override
    public String getPrintableValue() {
        return faceValue + " руб.";
    }


}
