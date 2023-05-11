package command.commands;

import exceptions.IllegalArgumentsException;
import managers.CollectionManager;
import command.Console;


public class RemoveById extends Command{
    private Console console;
    private CollectionManager collectionManager;
    public RemoveById(CollectionManager collectionManager,Console console){
        super("RemoveById"," удалить элемент из коллекции по его id");
        this.collectionManager =collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String args) throws IllegalArgumentsException {
        if (args.isBlank()) throw new IllegalArgumentsException();
        class NoSuchId extends RuntimeException {
        }
        try {
            int id = Integer.parseInt(args.trim());
            if (!collectionManager.checkExist(id)) throw new NoSuchId();
            collectionManager.removeElement(collectionManager.getById(id));
            console.println("Объект удален успешно");
        } catch (NoSuchId err) {
            console.printException("В коллекции нет элемента с таким id");
        } catch (NumberFormatException exception) {
            console.printException("id должно быть числом типа int");
        }
    }
}
