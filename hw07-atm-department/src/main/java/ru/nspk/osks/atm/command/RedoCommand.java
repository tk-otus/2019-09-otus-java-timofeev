package ru.nspk.osks.atm.command;

import ru.nspk.osks.atm.ATM;

public class RedoCommand implements Command {
    private ATM atm;

    public RedoCommand(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void execute() {
        atm.redo();
    }
}
