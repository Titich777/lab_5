package command.commands;

import exceptions.CommandRuntimeException;
import exceptions.ExitObligedException;
import exceptions.IllegalArgumentsException;
import command.Console;
import managers.CommandManager;

import java.util.List;

public class History extends Command{
    private CommandManager commandManager;
    private Console console;

    public History(CommandManager commandManager,Console console){
        super("History"," вывести последние 5 команд (без их аргументов)");
        this.commandManager = commandManager;
        this.console = console;
    }


    @Override
    public void execute(String args) throws IllegalArgumentsException, ExitObligedException, CommandRuntimeException {
        if (!args.isBlank()) throw new IllegalArgumentsException();
        List<String> history = commandManager.getCommandHistory();
        for (String command:history.subList(Math.max(history.size() -5,0), history.size())){
            console.println(command);
        }
    }
}
