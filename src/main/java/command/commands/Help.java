package command.commands;
import command.*;
import exceptions.IllegalArgumentsException;
import managers.CommandManager;

public class Help extends Command{

    private CommandManager commandManager;
    private Console console;
    public Help(CommandManager commandManager,Console console){
        super("help", " вывести справку по доступным командам ");
        this.commandManager = commandManager;
        this.console = console;
    }


    @Override
    public void execute(String args) throws IllegalArgumentsException{
        if (!args.isBlank()) throw new IllegalArgumentsException();
        commandManager.getCommands().forEach(command -> console.println(command.getName() + command.getDescription()));
    }
}
