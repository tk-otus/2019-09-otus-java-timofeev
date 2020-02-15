package ru.nspk.osks;

import ru.nspk.osks.atm.ATM;
import ru.nspk.osks.atm.Cassete;
import ru.nspk.osks.atm.FaceValue;

import java.util.ArrayList;
import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        FaceValue[] faceValues = {
                new FaceValue(100, new Cassete("100 руб.", 2)),
                new FaceValue(1000, new Cassete("1000 руб.", 20)),
                new FaceValue(500, new Cassete("500 руб.", 80)),
                new FaceValue(200, new Cassete("200 руб.", 1)),
                new FaceValue(5000, new Cassete("5000 руб.", 10))
        };
        ATM atm = new ATM("Ноунейм Банк", "999111333", "+7 (409) 654 99 99", faceValues);

        System.out.println("Всего налички: " + atm.getTotalCash());
        System.out.println("=====================");

        atm.getCash(500);

        System.out.println("=====================");
        System.out.println("Всего налички: " + atm.getTotalCash());
    }
}
