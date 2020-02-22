package ru.nspk.osks.atm.command;

import ru.nspk.osks.atm.ATM;

public class GetFullAmountCommand implements Command {
    private int result;

    @Override
    public void execute(ATM atm) {
        result = atm.getFullAmount();
    }

    public int getResult() {
        return result;
    }
}
