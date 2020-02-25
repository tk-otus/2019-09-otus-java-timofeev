package ru.nspk.osks.atm;

public interface ATMBankService {
    void undo();
    void redo();
    int getFullAmount();
    void resetToInitialState();
}
