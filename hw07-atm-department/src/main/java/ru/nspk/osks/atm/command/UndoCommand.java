package ru.nspk.osks.atm.command;

import ru.nspk.osks.atm.ATM;

public class UndoCommand implements Command {
    private ATM atm;

    public UndoCommand(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void execute() {
        atm.undo();
    }
}
