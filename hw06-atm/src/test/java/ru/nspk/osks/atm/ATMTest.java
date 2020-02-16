package ru.nspk.osks.atm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ATMTest {
    private ATM atm;
    private int totalCash;

    @BeforeEach
    public void createATM() {
        Cassette[] cassettes = {
                new Cassette(100, 40),
                new Cassette(1000, 44),
                new Cassette(500, 80),
                new Cassette(200, 60),
                new Cassette(5000, 20),
        };
        atm = new ATM("Ноунейм Банк", "00000001", "+7 (409) 654 99 99", cassettes);
        totalCash = atm.getTotalCash();
    }

    @Test
    public void testFaceValueSorting() {
        List<Integer> list1 = Arrays.stream(atm.getFaceValuesList()).boxed().collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(new int[] {100, 200, 500, 1000, 5000}).boxed().collect(Collectors.toList());
        assertEquals(list1, list2);
    }

    @Test
    public void testTotalCash() {
        assertEquals(atm.getTotalCash(), 200000);
    }

    @Test
    public void testGetCash() {
        int cash = 100000;
        atm.getCash(cash);
        assertEquals(totalCash - cash, atm.getTotalCash());
    }

    @Test
    public void testGetCashWithZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            atm.getCash(0);
        });
        assertTrue(exception.getMessage().contains("Нельзя использовать нулевое или отрицаиельное число"));
    }

    @Test
    public void testGetCashWithNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            atm.getCash(-100);
        });
        assertTrue(exception.getMessage().contains("Нельзя использовать нулевое или отрицаиельное число"));
    }

    @Test
    public void testGetCashWithNoFaceValue() {
        atm.getCash(50);
        assertEquals(totalCash, atm.getTotalCash());
    }

    @Test
    public void testGetCashOverdraft() {
        atm.getCash(totalCash + 100);
        assertEquals(totalCash, atm.getTotalCash());
    }

    @Test
    public void testGetCashWithNotBanknotes() {
        Cassette[] cassettes = {
                new Cassette(1000, 1),
        };
        ATM atm = new ATM("Ноунейм Банк", "00000001", "+7 (409) 654 99 99", cassettes);
        int totalCache = atm.getTotalCash();
        atm.getCash(500);
        assertEquals(totalCache, atm.getTotalCash());
    }

    @Test
    public void testPutCash() {
        atm.putCash(5000, 20);
        assertEquals(totalCash + 20 * 5000, atm.getTotalCash());
    }

    @Test
    public void testPutCashWithZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            atm.putCash(5000, 0);
        });
        assertTrue(exception.getMessage().contains("Нельзя использовать нулевое или отрицаиельное число"));
    }

    @Test
    public void testPutCashWithNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            atm.putCash(5000, -100);
        });
        assertTrue(exception.getMessage().contains("Нельзя использовать нулевое или отрицаиельное число"));
    }

    @Test
    public void testPutCashWithNoFaceValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            atm.putCash(50, 10);
        });
        assertTrue(exception.getMessage().contains("Банкомат не принимает купюры данного номинала"));
    }
}
