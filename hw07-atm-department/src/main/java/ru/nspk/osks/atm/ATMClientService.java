package ru.nspk.osks.atm;

public interface ATMClientService {

    void putBanknotesIn(Banknote banknote, int count);

    void getBanknotesOut(int sum);

}
