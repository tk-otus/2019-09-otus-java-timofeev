package ru.nspk.osks.cell;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nspk.osks.atm.RUBanknote;
import ru.nspk.osks.cell.command.Command;
import ru.nspk.osks.cell.command.GetBanknotesOutCommand;
import ru.nspk.osks.cell.command.PutBanknotesInCommand;

import static org.junit.jupiter.api.Assertions.*;

public class CassetteTest {
    Cassette cassette;
    int totalBanknotes;

    @BeforeEach
    public void createCassette() {
        cassette = new Cassette(RUBanknote.RUB_500, 20);
        totalBanknotes = cassette.getBanknotesCount();
    }

    @Test
    public void testPutCash() {
        cassette.putBanknotesIn(10);
        assertEquals(cassette.getBanknotesCount(), totalBanknotes + 10);
    }

    @Test
    public void testPutCashWithOverdraft() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cassette.putBanknotesIn(Cassette.MAX_BANKNOTES_NUMBER + 1);
        });
        assertTrue(exception.getMessage().contains("Не поддерживаемое значение количества купюр"));
    }

    @Test
    public void testPutCashWithZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cassette.putBanknotesIn(0);
        });
        assertTrue(exception.getMessage().contains("Не поддерживаемое значение количества купюр"));
    }

    @Test
    public void testPutCashWithNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cassette.putBanknotesIn(-10);
        });
        assertTrue(exception.getMessage().contains("Не поддерживаемое значение количества купюр"));
    }

    @Test
    public void testUndoRedoPutCash() {
        int count = 10;
        int banknotesCount = cassette.getBanknotesCount();
        Command command = new PutBanknotesInCommand(cassette, count);

        cassette.execute(command);

        assertTrue(cassette.undo());
        assertEquals(banknotesCount, cassette.getBanknotesCount());
        assertTrue(cassette.redo());
        assertEquals(banknotesCount + count, cassette.getBanknotesCount());
    }

    @Test
    public void testGetCash() {
        cassette.getBanknotesOut(10);
        assertEquals(cassette.getBanknotesCount(), totalBanknotes - 10);
    }

    @Test
    public void testGetCashWithOverdraft() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cassette.getBanknotesOut(Cassette.MAX_BANKNOTES_NUMBER + 1);
        });
        assertTrue(exception.getMessage().contains("Не поддерживаемое значение количества купюр"));
    }

    @Test
    public void testGetCashWithZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cassette.getBanknotesOut(0);
        });
        assertTrue(exception.getMessage().contains("Не поддерживаемое значение количества купюр"));
    }

    @Test
    public void testGetCashWithNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cassette.getBanknotesOut(-10);
        });
        assertTrue(exception.getMessage().contains("Не поддерживаемое значение количества купюр"));
    }

    @Test
    public void testUndoRedoGetCash() {
        int count = 10;
        int banknotesCount = cassette.getBanknotesCount();
        Command command = new GetBanknotesOutCommand(cassette, count);

        cassette.execute(command);

        assertTrue(cassette.undo());
        assertEquals(banknotesCount, cassette.getBanknotesCount());
        assertTrue(cassette.redo());
        assertEquals(banknotesCount - count, cassette.getBanknotesCount());
    }
}
