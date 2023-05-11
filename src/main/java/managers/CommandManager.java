package managers;

import exceptions.CommandRuntimeException;
import exceptions.ExitObligedException;
import exceptions.IllegalArgumentsException;
import exceptions.NoSuchCommandException;
import command.commands.Command;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Командный менеджер.
 * Реализует паттерн программирования Command
 */

public class CommandManager {
    /**
     * Поле для хранения комманд в виде Имя-Комманда
     */
    private final HashMap<String, Command> commands = new HashMap<>();
    /**
     * Поле для истории команд
     */
    private final List<String> commandHistory = new ArrayList<>();

    public void addCommand(Command command){
        this.commands.put(command.getName(),command);
    }

    public void addCommand(Collection<Command> commands){
        this.commands.putAll(commands.stream().collect(Collectors.toMap(Command::getName, s -> s)));
    }
    public Collection<Command> getCommands(){
        return commands.values();
    }
    public void addToHistory(String line){
        if (line.isBlank())return;
        this.commandHistory.add(line);
    }

    public List<String> getCommandHistory(){
        return commandHistory;
    }
    /**
     * Выполняет команду
     * @param name название команды
     * @param args аргументы команды
     */

    public void execute (String name,String args) throws NoSuchCommandException,IllegalArgumentsException,CommandRuntimeException,ExitObligedException{
        Command command = commands.get(name);
        if (command == null) throw new NoSuchCommandException();
        command.execute(args);
    }

}