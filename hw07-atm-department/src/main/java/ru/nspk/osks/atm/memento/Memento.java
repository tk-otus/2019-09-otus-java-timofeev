package ru.nspk.osks.atm.memento;

import ru.nspk.osks.atm.Cassette;

public class Memento {
    private final Cassette cassette;

    Memento(Cassette cassette) {
        this.cassette = new Cassette(cassette);
    }

    public Cassette getCassette() {
        return cassette;
    }
}
