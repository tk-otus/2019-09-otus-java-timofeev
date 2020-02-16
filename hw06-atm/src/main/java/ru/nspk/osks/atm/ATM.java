package ru.nspk.osks.atm;

import java.util.Arrays;

public class ATM {
    public static final int MAX_FACE_VALUES = 5;

    private final String bankName;
    private final String atmSN;
    private final String supportContacts;

    private final int[] faceValuesList;
    private final Cassette[] cassettes;

    public ATM(String bankName, String atmSN, String supportContacts, Cassette[] cassettes) {
        this.bankName = bankName;
        this.atmSN = atmSN;
        this.supportContacts = supportContacts;

        if (cassettes.length > MAX_FACE_VALUES) {
            throw new IllegalArgumentException("Банкомат не поддерживает больше " + MAX_FACE_VALUES + " ячеек для купюр");
        }

        Arrays.sort(cassettes);
        this.cassettes = cassettes;

        int[] fvl = new int[cassettes.length];
        for (int i = 0; i < cassettes.length; i++) {
            fvl[i] = cassettes[i].getFaceValue();
        }
        faceValuesList = fvl;
    }

    public void putCash(int fv, int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Нельзя использовать нулевое или отрицаиельное число");
        }
        Cassette cassette = null;
        for (Cassette c : cassettes) {
            if (c.getFaceValue() == fv) {
                cassette = c;
                break;
            }
        }
        if (cassette == null) {
            throw new IllegalArgumentException("Банкомат не принимает купюры данного номинала");
        }
        cassette.putCash(count);
    }

    public void getCash(int sum) {
        if (sum <= 0) {
            throw new IllegalArgumentException("Нельзя использовать нулевое или отрицаиельное число");
        }
        System.out.println("Вы запросили " + sum + " руб. к выдаче");
        if (sum % faceValuesList[0] != 0) {
            System.out.println("К сожалению мы не можем выдать вам эту сумму. Укажите сумма кратную " +
                    faceValuesList[0] + " руб.");
            return;
        }

        int sumLeft = sum;
        int[] banknotes = new int[faceValuesList.length];
        for (int i = faceValuesList.length - 1; i >= 0; i--) {
            int faceValue = faceValuesList[i];
            int mod = sumLeft % faceValue;
            if (mod == sumLeft) {
                continue;
            }

            int diff = sumLeft - mod;
            int banknotesNeeded = diff / faceValue;
            int banknotesInStock = cassettes[i].getBanknotesCount();

            banknotes[i] = Math.min(banknotesNeeded, banknotesInStock);
            sumLeft -= banknotes[i] * faceValue;
            if (sumLeft == 0) {
                break;
            }
        }

        if (sumLeft != 0) {
            System.out.println("Извините, но мы не можем выдать такую сумму. Укажите другую");
        } else {
            for (int i = 0; i < banknotes.length; i++) {
                if (banknotes[i] != 0) {
                    cassettes[i].getCash(banknotes[i]);
                }
            }
            System.out.println("Сумма выдана полностью");
        }
    }

    public int getTotalCash() {
        int result = 0;
        for (Cassette cassette : cassettes) {
            result += cassette.getTotalCash();
        }
        return result;
    }

    int[] getFaceValuesList() {
        return faceValuesList;
    }

    public void printWelcomeMessage() {
        System.out.println("===================================================" +
                "\nЗдравствуйте и добро пожалость в " + bankName + "!" +
                "\n\nВас обслуживает банкомат под номером " + atmSN +
                "\n\nЕсли у вас возникли проблемы, вы можете обратиться " +
                "\nв поддержку по номеру " + supportContacts +
                "\n\nУдачного дня!" +
                "\n===================================================");
    }
}
