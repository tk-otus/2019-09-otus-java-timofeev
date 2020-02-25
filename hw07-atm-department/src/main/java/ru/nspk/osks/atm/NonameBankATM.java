package ru.nspk.osks.atm;

import ru.nspk.osks.atm.command.Command;
import ru.nspk.osks.cell.Cassette;
import ru.nspk.osks.cell.command.GetBanknotesOutCommand;
import ru.nspk.osks.cell.command.PutBanknotesInCommand;

import java.util.*;
import java.util.stream.Collectors;

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

        throwExIfTrue(cassettes.size() > MAX_FACE_VALUES,
                "Банкомат не поддерживает больше " + MAX_FACE_VALUES + " ячеек для купюр");

        Collections.sort(cassettes);
        this.cassettes = cassettes;
    }

    @Override
    public void putBanknotesIn(Banknote banknote, int count) {
        checkCanPutBanknotesIn(banknote, count);
        List<Cassette> cassettes = getCassettesByBanknote(banknote);
        for (Cassette cassette : cassettes) {
            int banknotesToPut = Math.min(cassette.getFreeSpace(), count);
            cassette.execute(new PutBanknotesInCommand(cassette, banknotesToPut));
            count -= banknotesToPut;
            if (count == 0) {
                break;
            }
        }
    }

    @Override
    public boolean canPutBanknotesIn(Banknote banknote, int count) {
        try {
            checkCanPutBanknotesIn(banknote, count);
        } catch (IllegalArgumentException ex) {
            return false;
        }
        return true;
    }

    private void checkCanPutBanknotesIn(Banknote banknote, int count) {
        List<Cassette> cassettes = getCassettesByBanknote(banknote);
        int totalFreeSpace = cassettes.stream().mapToInt(Cassette::getFreeSpace).sum();

        throwExIfTrue(count <= 0, "Нельзя использовать нулевое или отрицаиельное число");
        throwExIfTrue(cassettes.isEmpty(), "Банкомат не принимает купюры данного номинала");
        throwExIfTrue(totalFreeSpace < count, "В банкомате не хватает места");
    }

    private List<Cassette> getCassettesByBanknote(Banknote banknote) {
        return cassettes.stream()
                .filter(c -> c.getBanknoteFaceValue() == banknote.getValue())
                .collect(Collectors.toList());
    }

    @Override
    public void getBanknotesOut(int sum) {
        checkCanGetBanknotesOut(sum);
        Map<Cassette, Integer> cassettesToGetOut = getCassettesToGetOut(sum);
        for (Map.Entry<Cassette, Integer> entry : cassettesToGetOut.entrySet()) {
            Cassette cassette = entry.getKey();
            cassette.execute(new GetBanknotesOutCommand(cassette, entry.getValue()));
        }
    }

    private Map<Cassette, Integer> getCassettesToGetOut(int sum) {
        int sumToLeft = sum;
        Map<Cassette, Integer> result = new HashMap<>();
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
            if (banknotesToGetOut == 0) {
                continue;
            }

            result.put(cassette, banknotesToGetOut);
            sumToLeft -= banknotesToGetOut * faceValue;
            if (sumToLeft == 0) {
                break;
            }
        }
        return result;
    }

    @Override
    public boolean canGetBanknotesOut(int sum) {
        try {
            checkCanGetBanknotesOut(sum);
        } catch (IllegalArgumentException ex) {
            return false;
        }
        return true;
    }

    private void checkCanGetBanknotesOut(int sum) {
        throwExIfTrue(sum <= 0, "Нельзя использовать нулевое или отрицаиельное число");
        throwExIfTrue(sum % cassettes.get(0).getBanknoteFaceValue() != 0, "Нет подходящего номинала");

        Map<Cassette, Integer> cassettesToGetOut = getCassettesToGetOut(sum);
        throwExIfTrue(cassettesToGetOut.entrySet()
                        .stream()
                        .mapToInt(entry -> entry.getKey().getBanknoteFaceValue() * entry.getValue())
                        .sum() != sum,
                "Нет нужного количества купюр");
    }


    @Override
    public void execute(Command command) {
        command.execute();
    }

    @Override
    public int getFullAmount() {
        int result = 0;
        for (Cassette cassette : cassettes) {
            result += cassette.getFullAmount();
        }
        return result;
    }

    @Override
    public void undo() {
        for (Cassette cassette : cassettes) cassette.undo();
    }

    @Override
    public void redo() {
        for (Cassette cassette : cassettes) cassette.redo();
    }

    public void resetToInitialState() {
        for (Cassette cassette : cassettes) {
            while (true) {
                if (!cassette.undo()) {
                    break;
                }
            }
        }
    }

    public List<Cassette> getCassettes() {
        return cassettes;
    }

    private void throwExIfTrue(boolean b, String s) {
        if (b) {
            throw new IllegalArgumentException(s);
        }
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
