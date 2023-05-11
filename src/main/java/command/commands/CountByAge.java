package command.commands;

import command.Console;
import exceptions.CommandRuntimeException;
import exceptions.ExitObligedException;
import exceptions.InvalidFormException;
import exceptions.IllegalArgumentsException;
import managers.CollectionManager;
import storedClasses.Dragon;
import storedClasses.forms.DragonForm;

import java.util.Objects;

public class CountByAge extends Command{
    private CollectionManager collectionManager;
    private Console console;

    public CountByAge(Console console,CollectionManager collectionManager){
        super("CountByAge"," вывести количество элементов, значение поля age которых равно заданному ");
        this.collectionManager = collectionManager;
        this.console = console;
    }


    @Override
    public void execute(String args) throws IllegalArgumentsException, ExitObligedException, CommandRuntimeException {
        if (!args.isBlank()) throw new IllegalArgumentsException();
        try {
            long Age = Long.parseLong(args.trim());
            console.print("Колличество элементов,с меньшим значением поля Age");
            console.println(String.valueOf(CollectionManager.getCollection().stream().filter(Objects::nonNull).filter(s -> s.getAge() == Age).map(Objects::toString).count()));
        } catch (NumberFormatException exception){
            console.printException("Age должно быть числом типа long");
        }
    }
}
