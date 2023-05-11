import command.Console;
import command.commands.*;
import managers.*;
import exceptions.ExitObligedException;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Console console = new Console();
        CollectionManager collectionManager = new CollectionManager();
        FileManager fileManager = new FileManager(console,collectionManager);
        CommandManager commandManager = new CommandManager();
        try{
            fileManager.findFile();
            fileManager.createObjects();
        }catch (ExitObligedException e){
            console.println("До свидания!");
            return;
        }

        commandManager.addCommand(List.of(
                new Help(commandManager, console),
                new Info(collectionManager, console),
                new Show(collectionManager, console),
                new AddElement(collectionManager, console),
                new Update(console, collectionManager),
                new RemoveById(collectionManager, console),
                new Clear(collectionManager, console),
                new Save(fileManager, console),
                new Execute(commandManager, console, fileManager),
                new Exit(),
                new AddIfMax(console, collectionManager),
                new RemoveLower(collectionManager, console),
                new History(commandManager, console),
                new CountByAge(console, collectionManager)

        ));
        new RunTimeManager(console,commandManager).interactiveMode();
    }
}
