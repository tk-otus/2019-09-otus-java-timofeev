package ru.nspk.osks.atm;

import ru.nspk.osks.atm.command.Command;

public interface ATM extends ATMBankService, ATMClientService {
    void execute(Command command);
}
