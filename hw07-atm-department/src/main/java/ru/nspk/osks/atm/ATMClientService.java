package ru.nspk.osks.atm;

public interface ATMClientService {
    void getBanknotesOut(int sum);
    void putBanknotesIn(Banknote banknote, int count);
}
