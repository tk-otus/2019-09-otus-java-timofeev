package ru.nspk.osks.atm;

import java.util.*;

public class NonameBankATM implements ATM {
    public static final int MAX_FACE_VALUES = 5;

    private final String bankName;
    private final String atmSN;
    private final String supportContacts;
    private final List<Cassette> cassettes;

    public NonameBankATM(String bankName, String atmSN, String supportContacts, List<Cassette> cassettes) {
        this.bankName = bankName;
        this.atmSN = atmSN;
        this.supportContacts = supportContacts;

        if (cassettes.size() > MAX_FACE_VALUES) {
            throw new IllegalArgumentException("Банкомат не поддерживает больше " + MAX_FACE_VALUES + " ячеек для купюр");
        }

        Collections.sort(cassettes);
        this.cassettes = cassettes;
    }

    @Override
    public void putBanknotesIn(Banknote banknote, int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Нельзя использовать нулевое или отрицаиельное число");
        }

        Optional<Cassette> cassetteWithFaceValue = cassettes.stream()
                .filter(c -> c.getBanknoteFaceValue() == banknote.getValue())
                .findAny();
        if (cassetteWithFaceValue.isEmpty()) {
            throw new IllegalArgumentException("Банкомат не принимает купюры данного номинала");
        }

        Optional<Cassette> cassette = cassetteWithFaceValue.stream()
                .filter(c -> c.getFreeSpace() >= count)
                .findFirst();
        if (cassette.isEmpty()) {
            System.out.println("К сожалению мы не можем принять " + count + " купюр по " + banknote.getPrintableValue());
            return;
        }
        cassette.get().putBanknotesIn(count);
    }

    @Override
    public void getBanknotesOut(int sum) {
        if (sum <= 0) {
            throw new IllegalArgumentException("Нельзя использовать нулевое или отрицаиельное число");
        }

        System.out.println("Вы запросили " + sum + " руб. к выдаче");
        if (sum % cassettes.get(0).getBanknoteFaceValue() != 0) {
            System.out.println("К сожалению мы не можем выдать вам эту сумму. Укажите сумма кратную " +
                    cassettes.get(0).getBanknote().getPrintableValue());
            return;
        }

        int sumToLeft = sum;
        Map<Cassette, Integer> cassettesToGetOut = new HashMap<Cassette, Integer>();
        for (int i = cassettes.size() - 1; i >= 0; i--) {
            Cassette cassette = cassettes.get(i);
            int faceValue = cassette.getBanknoteFaceValue();
            int mod = sumToLeft % faceValue;
            if (mod == sumToLeft) {
                continue;
            }

            int banknotesNeeded = (sumToLeft - mod) / faceValue;
            int banknotesInStock = cassette.getBanknotesCount();
            int banknotesToGetOut = Math.min(banknotesNeeded, banknotesInStock);

            cassettesToGetOut.put(cassette, banknotesToGetOut);
            sumToLeft -= banknotesToGetOut * faceValue;
            if (sumToLeft == 0) {
                break;
            }
        }

        if (sumToLeft != 0) {
            System.out.println("Извините, но мы не можем выдать такую сумму. Укажите другую");
        } else {
            cassettesToGetOut.forEach(Cassette::getBanknotesOut);
            System.out.println("Сумма выдана полностью");
        }
    }

    public int getFullAmount() {
        int result = 0;
        for (Cassette cassette : cassettes) {
            result += cassette.getFullAmount();
        }
        return result;
    }

    public List<Cassette> getCassettes() {
        return cassettes;
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
