package ru.nspk.osks.department;

import ru.nspk.osks.atm.ATM;

public interface Department {

    void addATM(ATM atm);

    void removeATM(ATM atm);

    int getAllAmounts();

    void resetToInitialState();

    String getName();
}
