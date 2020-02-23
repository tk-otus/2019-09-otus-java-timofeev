package ru.nspk.osks.department;

import ru.nspk.osks.atm.ATM;
import ru.nspk.osks.atm.command.Executor;
import ru.nspk.osks.atm.command.GetFullAmountCommand;

import java.util.ArrayList;
import java.util.List;

public class DepartmentImpl implements Department {
    private String name;
    private List<ATM> atms = new ArrayList<>();

    public DepartmentImpl(String name) {
        this.name = name;
    }

    @Override
    public void addATM(ATM atm) {
        atms.add(atm);
    }

    @Override
    public void removeATM(ATM atm) {
        atms.remove(atm);
    }

    @Override
    public int getAllAmounts() {
        int result = 0;
        GetFullAmountCommand getFullAmountCommand = new GetFullAmountCommand();
        Executor executor = new Executor();
        executor.addCommand(getFullAmountCommand);
        for (ATM atm : atms) {
            executor.executeCommands(atm);
            result += getFullAmountCommand.getResult();
        }
        return result;
    }

    public String getName() {
        return name;
    }
}
