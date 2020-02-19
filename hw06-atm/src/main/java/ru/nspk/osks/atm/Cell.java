package ru.nspk.osks.atm;

public interface Cell {

    void putBanknotesIn(int count);

    void getBanknotesOut(int count);

    int getFullAmount();

    int getFreeSpace();
    
}
