package command.commands;

import com.thoughtworks.xstream.mapper.Mapper;
import command.Console;
import exceptions.InFileModeException;
import exceptions.IllegalArgumentsException;
import exceptions.InvalidFormException;
import managers.CollectionManager;
import storedClasses.Dragon;
import storedClasses.forms.DragonForm;

import java.util.Objects;

/**
 * Команда 'add_if_max'
 * Добавляет элемент в коллекцию если он больше максмального
 */
public class AddIfMax extends Command{
    private CollectionManager collectionManager;
    private Console console;

    public AddIfMax(Console console, CollectionManager collectionManager) {
        super("add_if_max", " {element}: добавить элемент в коллекцию если он больше максмального");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /**
     * Исполнить команду
     * @param args аргументы команды
     * @throws exceptions.IllegalArgumentsException неверные аргументы команды
     */
    @Override
    public void execute(String args) throws IllegalArgumentsException {
        if (!args.isBlank()) throw new IllegalArgumentsException();
        try {
            console.println("Создание объекта StudyGroup");
            Dragon newElement = new DragonForm(console).build();
            console.println("Создание объекта StudyGroup окончено успешно!");
            if (newElement.compareTo(collectionManager.getCollection().stream()
                    .filter(Objects::nonNull)
                    .max(Dragon::compareTo)
                    .orElse(null)) >= 1)
            {
                collectionManager.addElement(newElement);
                console.println("Объект успешно добавлен");
            } else {
                console.println("Элемент меньше максимального");
            }
        } catch (InvalidFormException invalidForm) {
            console.printException("Поля объекта не валидны! Объект не создан!");
        }  catch (InFileModeException e){
            console.printException("Поля в файле не валидны! Объект не создан");
        }
    }
}
