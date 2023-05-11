package command.commands;

import exceptions.IllegalArgumentsException;
import managers.CollectionManager;
import command.Console;
import storedClasses.Dragon;

import java.util.Collection;

public class Show extends Command{

    private CollectionManager collectionManager;
    private Console console;


    public Show(CollectionManager collectionManager , Console console ) {
        super("show"," вывести в стандартный поток вывода все элементы коллекции в строковом представлении ");
        this.collectionManager = collectionManager;
        this.console = console;
    }


    @Override
    public void execute(String args) throws IllegalArgumentsException {
        if (!args.isBlank()) throw new IllegalArgumentsException();
        Collection<Dragon> collection = collectionManager.getCollection();
        if (collection == null || collection.isEmpty()) {
            console.printException("Коллекция еще не инициализирована");
            return;
        }
        console.println(collection.toString());
    }
    }
