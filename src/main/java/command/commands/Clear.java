package command.commands;

import exceptions.IllegalArgumentsException;
import managers.CollectionManager;
import command.Console;

/**
 * Команда 'clear'
 * Очищает коллекцию
 */
public class Clear extends Command{
    private CollectionManager collectionManager;
    private Console console;

    public Clear(CollectionManager collectionManager,Console console){
        super("Clear"," очистить коллекцию ");
        this.collectionManager =collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String args) throws IllegalArgumentsException {
            if (!args.isBlank()) throw new IllegalArgumentsException();
            collectionManager.clear();
            console.println("Элементы удалены");
    }
}
