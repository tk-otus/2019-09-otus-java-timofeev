package ru.nspk.osks.cell.command;

import ru.nspk.osks.cell.Cassette;

public class GetBanknotesOutCommand implements Command {
    private Cassette cassette;
    private int count;

    public GetBanknotesOutCommand(Cassette cassette, int count) {
        this.cassette = cassette;
        this.count = count;
    }

    @Override
    public void execute() {
        cassette.getBanknotesOut(count);
    }
}
