package command.commands;

import exceptions.CommandRuntimeException;
import exceptions.ExitObligedException;
import exceptions.IllegalArgumentsException;
import command.Console;
import exceptions.InFileModeException;
import managers.CollectionManager;
import storedClasses.Dragon;
import storedClasses.forms.DragonForm;

import java.util.Collection;
import java.util.Objects;

public class RemoveLower extends Command{

    private CollectionManager collectionManager;
    private Console console;

    public RemoveLower(CollectionManager collectionManager,Console console){
        super("RemoveLower"," удалить из коллекции все элементы, меньшие, чем заданный ");
        this.collectionManager = collectionManager;
        this.console = console;
    }
    @Override
    public void execute(String args) throws IllegalArgumentsException, ExitObligedException, CommandRuntimeException {
    if (!args.isBlank()) throw new IllegalArgumentsException();
    class NoElements extends RuntimeException{

    }
    try {
        console.println("Создание объекта StudyGroup");
        Dragon newElement = new DragonForm(console).build();
        console.println("Создание объекта StudyGroup окончено успешно!");
        Collection<Dragon> toRemove = CollectionManager.getCollection().stream().filter(Objects::nonNull).filter(studyGroup -> studyGroup.compareTo(newElement) <= 1).toList();
        collectionManager.removeElements(toRemove);
        console.println("Удалены элементы большие чем заданный");
    }catch (NoElements e){
        console.printException("В коллекции нет элементов");
    }catch (InFileModeException e){
        console.printException("Поля в файле не валидны! Объект не создан");
    }
    }


}
