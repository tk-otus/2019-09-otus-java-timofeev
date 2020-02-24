package ru.nspk.osks.atm;

public interface ATMBankService {

    int getFullAmount();

    void undo();

    void redo();

    void resetToInitialState();
}
