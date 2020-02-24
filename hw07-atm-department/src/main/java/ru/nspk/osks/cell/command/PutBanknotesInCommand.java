package ru.nspk.osks.cell.command;

import ru.nspk.osks.cell.Cassette;

public class PutBanknotesInCommand implements Command {
    private Cassette cassette;
    private int count;

    public PutBanknotesInCommand(Cassette cassette, int count) {
        this.cassette = cassette;
        this.count = count;
    }

    @Override
    public void execute() {
        cassette.putBanknotesIn(count);
    }
}
