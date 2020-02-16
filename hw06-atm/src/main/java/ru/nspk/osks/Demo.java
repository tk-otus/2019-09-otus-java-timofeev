package ru.nspk.osks;

import ru.nspk.osks.atm.ATM;
import ru.nspk.osks.atm.Cassette;


public class Demo {
    public static void main(String[] args) {
        Cassette[] cassettes = {
                new Cassette(100, 40),
                new Cassette(1000, 44),
                new Cassette(500, 80),
                new Cassette(2000, 60),
                new Cassette(5000, 20),
        };
        ATM atm = new ATM("Ноунейм Банк", "00000001", "+7 (409) 654 99 99", cassettes);
        atm.printWelcomeMessage();

        System.out.println("Всего налички: " + atm.getTotalCash());
        System.out.println("=====================");

        atm.getCash(100500);
        atm.getCash(9000);
        atm.getCash(900);
        atm.getCash(1000);

        atm.putCash(500, 5);

        System.out.println("=====================");
        System.out.println("Всего налички: " + atm.getTotalCash());
    }
}
