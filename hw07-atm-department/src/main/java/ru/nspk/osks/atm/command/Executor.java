package ru.nspk.osks.atm.command;

import java.util.ArrayList;
import java.util.List;

public class Executor {
    private final List<Command> commands = new ArrayList<>();

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void executeCommands() {
        for (Command command : commands) {
            command.execute();
        }
    }
}
