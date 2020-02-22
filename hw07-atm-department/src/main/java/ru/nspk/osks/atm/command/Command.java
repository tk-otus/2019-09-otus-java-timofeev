package ru.nspk.osks.atm.command;

import ru.nspk.osks.atm.ATM;

public interface Command {
    void execute(ATM atm);
}
