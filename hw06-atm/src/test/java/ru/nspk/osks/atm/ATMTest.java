package ru.nspk.osks.atm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ATMTest {
    private NonameBankATM atm;
    private int totalCash;

    @BeforeEach
    public void createATM() {
        Cassette[] cassettes = {
                new Cassette(RUBanknote.RUB_100, 40),
                new Cassette(RUBanknote.RUB_1000, 44),
                new Cassette(RUBanknote.RUB_500, 80),
                new Cassette(RUBanknote.RUB_200, 60),
                new Cassette(RUBanknote.RUB_5000, 20),
        };
        atm = new NonameBankATM("Ноунейм Банк", "00000001", "+7 (409) 654 99 99", cassettes);
        totalCash = atm.getFullAmount();
    }

    @Test
    public void testFaceValueSorting() {
        List<Integer> list1 = Arrays.stream(atm.getFaceValuesList()).boxed().collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(new int[] {100, 200, 500, 1000, 5000}).boxed().collect(Collectors.toList());
        assertEquals(list1, list2);
    }

    @Test
    public void testTotalCash() {
        assertEquals(atm.getFullAmount(), 200000);
    }

    @Test
    public void testGetCash() {
        int cash = 100000;
        atm.getBanknotesOut(cash);
        assertEquals(totalCash - cash, atm.getFullAmount());
    }

    @Test
    public void testGetCashWithZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            atm.getBanknotesOut(0);
        });
        assertTrue(exception.getMessage().contains("Нельзя использовать нулевое или отрицаиельное число"));
    }

    @Test
    public void testGetCashWithNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            atm.getBanknotesOut(-100);
        });
        assertTrue(exception.getMessage().contains("Нельзя использовать нулевое или отрицаиельное число"));
    }

    @Test
    public void testGetCashWithNoFaceValue() {
        atm.getBanknotesOut(50);
        assertEquals(totalCash, atm.getFullAmount());
    }

    @Test
    public void testGetCashOverdraft() {
        atm.getBanknotesOut(totalCash + 100);
        assertEquals(totalCash, atm.getFullAmount());
    }

    @Test
    public void testGetCashWithNotBanknotes() {
        Cassette[] cassettes = {
                new Cassette(RUBanknote.RUB_1000, 1),
        };
        NonameBankATM atm = new NonameBankATM("Ноунейм Банк", "00000001", "+7 (409) 654 99 99", cassettes);
        int totalCache = atm.getFullAmount();
        atm.getBanknotesOut(500);
        assertEquals(totalCache, atm.getFullAmount());
    }

    @Test
    public void testPutCash() {
        atm.putBanknotesIn(RUBanknote.RUB_5000, 20);
        assertEquals(totalCash + 20 * 5000, atm.getFullAmount());
    }

    @Test
    public void testPutCashWithZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            atm.putBanknotesIn(RUBanknote.RUB_5000, 0);
        });
        assertTrue(exception.getMessage().contains("Нельзя использовать нулевое или отрицаиельное число"));
    }

    @Test
    public void testPutCashWithNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            atm.putBanknotesIn(RUBanknote.RUB_5000, -100);
        });
        assertTrue(exception.getMessage().contains("Нельзя использовать нулевое или отрицаиельное число"));
    }

    @Test
    public void testPutCashWithNoFaceValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            atm.putBanknotesIn(RUBanknote.RUB_50, 10);
        });
        assertTrue(exception.getMessage().contains("Банкомат не принимает купюры данного номинала"));
    }
}
