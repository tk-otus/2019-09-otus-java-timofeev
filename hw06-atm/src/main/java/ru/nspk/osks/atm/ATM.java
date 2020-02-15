package ru.nspk.osks.atm;

import java.util.Arrays;

public class ATM {
    public static final int MAX_FACE_VALUES = 5;

    private final String bankName;
    private final String atmSN;
    private final String supportContacts;
    private final int[] faceValuesList;
    private final FaceValue[] faceValues;

    public ATM(String bankName, String atmSN, String supportContacts, FaceValue[] faceValues) {
        this.bankName = bankName;
        this.atmSN = atmSN;
        this.supportContacts = supportContacts;

        if (faceValues.length > MAX_FACE_VALUES) {
            throw new IndexOutOfBoundsException();
        }

        Arrays.sort(faceValues);
        this.faceValues = faceValues;

        int[] fvl = new int[faceValues.length];
        for (int i = 0; i < faceValues.length; i++) {
            fvl[i] = faceValues[i].getFaceValue();
        }
        faceValuesList = fvl;

//        printWelcomeMessage();
    }

    public void putCash(int faceValue, int count) {
        FaceValue faceValueObj = null;
        for (FaceValue fv : faceValues) {
            if (fv.getFaceValue() == faceValue) {
                faceValueObj = fv;
                break;
            }
        }
        if (faceValueObj == null) {
            throw new IllegalStateException("Не удалось найти указанный номинал");
        }
        faceValueObj.putCash(count);
    }

    public void getCash(int sum) {
        if (sum <= 0) {
            throw new IllegalStateException("Нельзя использовать нулевое или отрицаиельное число");
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
            int banknotesInStock = faceValues[i].getBanknotesNumber();

            banknotes[i] = Math.min(banknotesNeeded, banknotesInStock);
            sumLeft -= banknotes[i] * faceValue;
            if (sumLeft == 0) {
                break;
            }
        }

        if (sumLeft != 0) {
            System.out.println("Извините, но мы не можем выдать такую сумму. Укажите другую");
            return;
        } else {
            for (int i = 0; i < banknotes.length; i++) {
                if (banknotes[i] != 0) {
                    faceValues[i].cassete.getCash(banknotes[i]);
                }
            }
            System.out.println("Сумма выдана полностью");
        }
    }

    public int getTotalCash() {
        int result = 0;
        for (FaceValue faceValue : faceValues) {
            result += faceValue.getTotalCash();
        }
        return result;
    }

    public FaceValue[] getFaceValues() {
        return faceValues;
    }

    private void printWelcomeMessage() {
        System.out.println("===================================================" +
                "\nЗдравствуйте и добро пожалость в " + bankName + "!" +
                "\n\nВас обслуживает банкомат под номером " + atmSN +
                "\n\nЕсли у вас возникли проблемы, вы можете обратиться " +
                "\nв поддержку по номеру " + supportContacts +
                "\n\nУдачного дня!" +
                "\n===================================================");
    }
}
