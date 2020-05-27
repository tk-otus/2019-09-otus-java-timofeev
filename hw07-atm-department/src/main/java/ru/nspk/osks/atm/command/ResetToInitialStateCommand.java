package ru.nspk.osks.atm.command;

import ru.nspk.osks.atm.ATM;

public class ResetToInitialStateCommand implements Command {
    private ATM atm;

    public ResetToInitialStateCommand(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void execute() {
        atm.resetToInitialState();
    }
}
