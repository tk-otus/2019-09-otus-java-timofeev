package ru.nspk.osks;

import ru.nspk.osks.atm.Cassette;
import ru.nspk.osks.atm.NonameBankATM;
import ru.nspk.osks.atm.RUBanknote;
import ru.nspk.osks.department.Department;
import ru.nspk.osks.department.DepartmentImpl;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<Cassette> cassettes = new ArrayList<>() {
            {
                add(new Cassette(RUBanknote.RUB_100, 10));
                add(new Cassette(RUBanknote.RUB_200, 10));
                add(new Cassette(RUBanknote.RUB_500, 10));
                add(new Cassette(RUBanknote.RUB_1000, 10));
                add(new Cassette(RUBanknote.RUB_5000, 10));
            }
        };
        NonameBankATM atm = new NonameBankATM("Ноунейм Банк", "00000001", "+7 (409) 654 99 99", cassettes);

        Department department = new DepartmentImpl("СВАО");
        department.addATM(atm);

        System.out.println("В отделении '" + department.getName() + "' осталось " + department.getAllAmounts() + " руб.");

        System.out.println("Добавляем 10000 руб. и сохраняем состояние");
        atm.putBanknotesIn(RUBanknote.RUB_5000, 2);

        System.out.println("В отделении '" + department.getName() + "' осталось " + department.getAllAmounts() + " руб.");

        System.out.println("Возвращаем предыдущее состояние");

        atm.restoreState();

        System.out.println("В отделении '" + department.getName() + "' осталось " + department.getAllAmounts() + " руб.");
    }
}
