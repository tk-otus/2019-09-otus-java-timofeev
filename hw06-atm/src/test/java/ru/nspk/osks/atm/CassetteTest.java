package ru.nspk.osks.atm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CassetteTest {
    Cassette cassette;
    int totalBanknotes;

    @BeforeEach
    public void createCassette() {
        cassette = new Cassette(500, 20);
        totalBanknotes = cassette.getBanknotesCount();
    }

    @Test
    public void testPutCash() {
        cassette.putCash(10);
        assertEquals(cassette.getBanknotesCount(), totalBanknotes + 10);
    }

    @Test
    public void testPutCashWithOverdraft() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cassette.putCash(Cassette.MAX_BANKNOTES_NUMBER + 1);
        });
        assertTrue(exception.getMessage().contains("В корзине не хватает места"));
    }

    @Test
    public void testPutCashWithZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cassette.putCash(0);
        });
        assertTrue(exception.getMessage().contains("Не поддерживаемое значение количества купюр"));
    }

    @Test
    public void testPutCashWithNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cassette.putCash(-10);
        });
        assertTrue(exception.getMessage().contains("Не поддерживаемое значение количества купюр"));
    }

    @Test
    public void testGetCash() {
        cassette.getCash(10);
        assertEquals(cassette.getBanknotesCount(), totalBanknotes - 10);
    }

    @Test
    public void testGetCashWithOverdraft() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cassette.getCash(Cassette.MAX_BANKNOTES_NUMBER + 1);
        });
        assertTrue(exception.getMessage().contains("В корзине не хватает купюр"));
    }

    @Test
    public void testGetCashWithZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cassette.putCash(0);
        });
        assertTrue(exception.getMessage().contains("Не поддерживаемое значение количества купюр"));
    }

    @Test
    public void testGetCashWithNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cassette.putCash(-10);
        });
        assertTrue(exception.getMessage().contains("Не поддерживаемое значение количества купюр"));
    }
}
