package ru.nspk.osks.department;

import ru.nspk.osks.atm.ATM;
import ru.nspk.osks.atm.command.Executor;
import ru.nspk.osks.atm.command.GetFullAmountCommand;
import ru.nspk.osks.atm.command.ResetToInitialStateCommand;

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
        for (ATM atm : atms) {
            Executor executor = new Executor();
            GetFullAmountCommand getFullAmountCommand = new GetFullAmountCommand(atm);
            executor.addCommand(getFullAmountCommand);
            executor.executeCommands();
            result += getFullAmountCommand.getResult();
        }
        return result;
    }

    @Override
    public void resetToInitialState() {
        Executor executor = new Executor();
        for (ATM atm : atms) {
            executor.addCommand(new ResetToInitialStateCommand(atm));
        }
        executor.executeCommands();
    }

    public String getName() {
        return name;
    }
}
