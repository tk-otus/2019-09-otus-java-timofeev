package ru.nspk.osks.cell.memento;

import ru.nspk.osks.cell.Cassette;

public class Memento {
    private final Cassette cassette;
    private final Cassette backup;

    public Memento(Cassette cassette) {
        this.cassette = cassette;
        this.backup = cassette.backup();
    }

    public void restore() {
        cassette.restore(backup);
    }
}
