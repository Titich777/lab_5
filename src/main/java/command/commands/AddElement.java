package command.commands;
import exceptions.InFileModeException;
import exceptions.IllegalArgumentsException;
import managers.CollectionManager;
import command.Console;
import exceptions.InvalidFormException;
import storedClasses.forms.DragonForm;


public class AddElement extends Command{

    private CollectionManager collectionManager;
    private Console console;
    public AddElement(CollectionManager collectionManager, Console console) {
        super("addElement", " добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.console = console;
    }


    @Override
    public void execute(String args) throws IllegalArgumentsException {
        if (!args.isBlank()) throw new IllegalArgumentsException();
        try {
            console.println("Создание объекта Dragon");
            collectionManager.addElement(new DragonForm(console).build());
            console.println("Создание объекта Dragon окончено успешно!");
        } catch (InvalidFormException invalidForm) {
            console.printException("Поля объекта не валидны! Объект не создан!");
        } catch (InFileModeException e){
            console.printException("Поля в файле не валидны! Объект не создан");
        }
    }
}
