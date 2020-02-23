package ru.nspk.osks.atm.memento;

import ru.nspk.osks.atm.Cassette;

import java.util.ArrayDeque;
import java.util.Deque;

public class CassetteOriginator {
    private final Deque<Memento> stack = new ArrayDeque<>();

    public void saveState(Cassette cassette) {
        stack.push(new Memento(cassette));
    }

    public Cassette restoreState() {
        return stack.pop().getCassette();
    }

}
