package ru.nspk.osks.cell;

public interface Cell {

    void putBanknotesIn(int count);

    void getBanknotesOut(int count);

    int getFullAmount();

    int getFreeSpace();

}
