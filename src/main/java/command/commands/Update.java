package command.commands;

import exceptions.InFileModeException;
import exceptions.IllegalArgumentsException;
import exceptions.InvalidFormException;
import managers.CollectionManager;
import command.Console;
import storedClasses.Dragon;
import storedClasses.forms.DragonForm;


public class Update extends Command {
    private CollectionManager collectionManager;
    private Console console;

    public Update(Console console,CollectionManager collectionManager){
        super("update"," обновить значение элемента коллекции, id которого равен заданному ");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String args) throws IllegalArgumentsException {
        if (args.isBlank()) throw new IllegalArgumentsException();
        class NoSuchId extends RuntimeException{

        }
        try {
            int id = Integer.parseInt(args.trim());
            if (!collectionManager.checkExist(id)) throw new NoSuchId();
            console.println("Создание нового объекта Dragon");
            Dragon newDragon = new DragonForm(console).build();
            collectionManager.editById(id, newDragon);
            console.println("Создание нового объекта Dragon окончено успешно!");
        } catch (NoSuchId err) {
            console.printException("В коллекции нет элемента с таким id");
        } catch (InvalidFormException invalidFormException) {
            console.printException("Поля объекта не валидны! Объект не создан!");
        } catch (NumberFormatException exception) {
            console.printException("id должно быть числом типа int");
        } catch (InFileModeException e){
            console.printException("Поля в файле не валидны! Объект не создан");
        }
    }
    }
