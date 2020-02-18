package ru.nspk.osks;

import ru.nspk.osks.atm.Cassette;
import ru.nspk.osks.atm.RUBanknote;
import ru.nspk.osks.atm.NonameBankATM;


public class Demo {
    public static void main(String[] args) {
        Cassette[] cassettes = {
                new Cassette(RUBanknote.RUB_100, 40),
                new Cassette(RUBanknote.RUB_1000, 44),
                new Cassette(RUBanknote.RUB_500, 80),
                new Cassette(RUBanknote.RUB_200, 60),
                new Cassette(RUBanknote.RUB_5000, 20),
        };
        NonameBankATM atm = new NonameBankATM("Ноунейм Банк", "00000001", "+7 (409) 654 99 99", cassettes);
        atm.printWelcomeMessage();

        System.out.println("Всего налички: " + atm.getFullAmount());
        System.out.println("=====================");

        atm.getBanknotesOut(100500);
        atm.getBanknotesOut(9000);
        atm.getBanknotesOut(900);
        atm.getBanknotesOut(1000);

        atm.putBanknotesIn(RUBanknote.RUB_500, 5);

        System.out.println("=====================");
        System.out.println("Всего налички: " + atm.getFullAmount());
    }
}
