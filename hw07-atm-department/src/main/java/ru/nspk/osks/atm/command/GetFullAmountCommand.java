package ru.nspk.osks.atm.command;

import ru.nspk.osks.atm.ATM;

public class GetFullAmountCommand implements Command {
    private int result;
    private ATM atm;

    public GetFullAmountCommand(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void execute() {
        result = atm.getFullAmount();
    }

    public int getResult() {
        return result;
    }
}
