package ru.nspk.osks.atm;

public interface ATM {

    void putBanknotesIn(Banknote banknote, int count);

    void getBanknotesOut(int sum);

    int getFullAmount();

}
