package ru.nspk.osks.department;

import ru.nspk.osks.atm.ATM;
import ru.nspk.osks.department.Department;

import java.util.ArrayList;
import java.util.List;

public class DepartmentImpl implements Department {
    private String name;
    private List<ATM> atms = new ArrayList<>();

    public DepartmentImpl(String name, List<ATM> atms) {
        this.name = name;
        this.atms = atms;
    }
}
