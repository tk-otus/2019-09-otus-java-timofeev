package ru.nspk.osks.cell;

import ru.nspk.osks.atm.Banknote;
import ru.nspk.osks.cell.command.Command;
import ru.nspk.osks.cell.memento.History;
import ru.nspk.osks.cell.memento.Memento;

public class Cassette implements Cell, Comparable<Cassette> {
    static final int MIN_BANKNOTES_NUMBER = 0;
    static final int MAX_BANKNOTES_NUMBER = 100;
    private Banknote banknote;
    private int banknotesCount;
    private History history;

    public Cassette(Banknote banknote) {
        this(banknote, MIN_BANKNOTES_NUMBER);
    }

    public Cassette(Banknote banknote, int count) {
        checkCount(count < MIN_BANKNOTES_NUMBER || count > MAX_BANKNOTES_NUMBER, "Не поддерживаемое значение количества купюр");
        this.banknote = banknote;
        this.banknotesCount = count;
        this.history = new History();
    }

    public Cassette(Cassette cassette) {
        this(cassette.banknote, cassette.banknotesCount);
    }

    public void execute(Command c) {
        history.push(c, new Memento(this));
        c.execute();
    }

    @Override
    public int getFullAmount() {
        return banknotesCount * banknote.getValue();
    }

    @Override
    public int getFreeSpace() {
        return MAX_BANKNOTES_NUMBER - banknotesCount;
    }

    public Banknote getBanknote() {
        return banknote;
    }

    public int getBanknoteFaceValue() {
        return getBanknote().getValue();
    }

    public int getBanknotesCount() {
        return banknotesCount;
    }

    public boolean undo() {
        return history.undo();
    }

    public boolean redo() {
        return history.redo();
    }

    public Cassette backup() {
        return new Cassette(this);
    }

    public void restore(Cassette backup) {
        this.banknote = backup.banknote;
        this.banknotesCount = backup.banknotesCount;
    }

    @Override
    public void putBanknotesIn(int count) {
        checkCount(count <= 0 || count > MAX_BANKNOTES_NUMBER, "Не поддерживаемое значение количества купюр");
        checkCount(banknotesCount + count > MAX_BANKNOTES_NUMBER, "В корзине не хватает места");
        banknotesCount += count;
    }

    @Override
    public void getBanknotesOut(int count) {
        checkCount(count <= 0 || count > MAX_BANKNOTES_NUMBER, "Не поддерживаемое значение количества купюр");
        checkCount(banknotesCount - count < MIN_BANKNOTES_NUMBER, "В корзине не хватает купюр");
        banknotesCount -= count;
    }


    private void checkCount(boolean b, String s) {
        if (b) {
            throw new IllegalArgumentException(s);
        }
    }

    @Override
    public int compareTo(Cassette o) {
        int compare = o.getBanknote().getValue();
        return banknote.getValue() - compare;
    }

    @Override
    public String toString() {
        return "Cassette{" +
                "banknote=" + banknote +
                ", banknotesCount=" + banknotesCount +
                '}';
    }
}
