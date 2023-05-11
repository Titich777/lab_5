package command.commands;

import exceptions.IllegalArgumentsException;
import managers.CollectionManager;
import command.*;


public class Info extends Command {

    private CollectionManager collectionManager;
    private Console console;


    public Info(CollectionManager collectionManager, Console console) {
        super("info"," вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.) ");
        this.collectionManager = collectionManager;
        this.console = console;
    }


    @Override
    public void execute(String args) throws IllegalArgumentsException {
        if (!args.isBlank()) throw new IllegalArgumentsException();
        String lastIniTime = (collectionManager.getLastInitTime() == null)?"В сессии коллекция не инициализирована": collectionManager.getLastInitTime().toString();
        String lastSaveTime = (collectionManager.getLastSaveTime() == null)?"В сессии коллекция не инициализирована": collectionManager.getLastSaveTime().toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Сведегия о коллекции: \n")
                .append("Тип: " + collectionManager.collectionType() + "\n")
                .append("Количество элементов: " + collectionManager.collectionSize() + "\n")
                .append("Дата последней инициализации: " + lastIniTime + "\n")
                .append("Дата последнего изменения: " + lastSaveTime + "\n");
        console.print(stringBuilder.toString());
    }
}
