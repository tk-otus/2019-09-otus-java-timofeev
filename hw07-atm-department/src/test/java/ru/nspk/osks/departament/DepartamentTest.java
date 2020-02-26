package ru.nspk.osks.departament;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nspk.osks.atm.ATM;
import ru.nspk.osks.atm.NonameBankATM;
import ru.nspk.osks.atm.RUBanknote;
import ru.nspk.osks.cell.Cassette;
import ru.nspk.osks.department.Department;
import ru.nspk.osks.department.DepartmentImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DepartamentTest {
    private ATM atm1;
    private ATM atm2;
    private Department department;

    @BeforeEach
    public void createDepartmentWithATMs() {
        List<Cassette> cassettes1 = new ArrayList<>();
        cassettes1.add(new Cassette(RUBanknote.RUB_5000, 10));
        atm1 = new NonameBankATM("Ноунейм Банк", "00000001", "+7 (409) 654 99 99", cassettes1);

        List<Cassette> cassettes2 = new ArrayList<>();
        cassettes2.add(new Cassette(RUBanknote.RUB_5000, 5));
        atm2 = new NonameBankATM("Ноунейм Банк", "00000002", "+7 (409) 654 99 99", cassettes2);

        department = new DepartmentImpl("СВАО");
        department.addATM(atm1);
        department.addATM(atm2);
    }

    @Test
    public void testGetAllAmounts() {
        assertEquals(75_000, department.getAllAmounts());
    }

    @Test
    public void testResetToInitState() {
        atm1.putBanknotesIn(RUBanknote.RUB_5000, 30);
        atm1.putBanknotesIn(RUBanknote.RUB_5000, 30);
        atm2.putBanknotesIn(RUBanknote.RUB_5000, 30);
        atm2.putBanknotesIn(RUBanknote.RUB_5000, 30);
        department.resetToInitialState();
        assertEquals(75_000, department.getAllAmounts());
    }
}
