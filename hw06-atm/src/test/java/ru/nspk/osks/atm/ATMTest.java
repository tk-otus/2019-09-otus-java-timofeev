package ru.nspk.osks.atm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ATMTest {
    private NonameBankATM atm;
    private int totalCash;

    @BeforeEach
    public void createATM() {
        List<Cassette> cassettes = new ArrayList<>() {
            {
                add(new Cassette(RUBanknote.RUB_1000, 38));
                add(new Cassette(RUBanknote.RUB_500, 80));
                add(new Cassette(RUBanknote.RUB_200, 60));
                add(new Cassette(RUBanknote.RUB_5000, 20));
                add(new Cassette(RUBanknote.RUB_5000, 2));
            }
        };
        atm = new NonameBankATM("Ноунейм Банк", "00000001", "+7 (409) 654 99 99", cassettes);
        totalCash = atm.getFullAmount();
    }

    @Test
    public void testCassettesSorting() {
        List<Integer> list1 = atm.getCassettes().stream().map(c -> c.getBanknote().getValue()).collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(new int[] {200, 500, 1000, 5000, 5000}).boxed().collect(Collectors.toList());
        assertEquals(list1, list2);
    }

    @Test
    public void testTotalCash() {
        assertEquals(200000, atm.getFullAmount());
    }

    @Test
    public void testGetCash() {
        int cash = 100_000;
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
        List<Cassette> cassettes = new ArrayList<>();
        cassettes.add(new Cassette(RUBanknote.RUB_1000, 44));
        NonameBankATM atm = new NonameBankATM("Ноунейм Банк", "00000001", "+7 (409) 654 99 99", cassettes);
        int totalCache = atm.getFullAmount();
        atm.getBanknotesOut(500);
        assertEquals(totalCache, atm.getFullAmount());
    }

    @Test
    public void testGetCashFromTwoSameCassettes() {
        atm.getBanknotesOut(110_000);
        List<Cassette> cassettes = atm.getCassettes();
        assertEquals(0, cassettes.get(cassettes.size() - 1).getBanknotesCount());
        assertEquals(0, cassettes.get(cassettes.size() - 2).getBanknotesCount());
    }

    @Test
    public void testGetCashWithEmptyCassettes() {
        List<Cassette> cassettes = new ArrayList<>();
        cassettes.add(new Cassette(RUBanknote.RUB_500, 50));
        cassettes.add(new Cassette(RUBanknote.RUB_1000, 0));
        NonameBankATM atm = new NonameBankATM("Ноунейм Банк", "00000001", "+7 (409) 654 99 99", cassettes);
        int totalCache = atm.getFullAmount();
        atm.getBanknotesOut(5000);
        assertEquals(totalCache - 5000, atm.getFullAmount());
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
    @Test
    public void testPutCashInTwoSameCassettes() {
        atm.putBanknotesIn(RUBanknote.RUB_5000, 100);
        List<Cassette> cassettes = atm.getCassettes();
        assertEquals(22, cassettes.get(cassettes.size() - 1).getBanknotesCount());
        assertEquals(100, cassettes.get(cassettes.size() - 2).getBanknotesCount());
    }
}
