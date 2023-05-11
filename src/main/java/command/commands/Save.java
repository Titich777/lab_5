package command.commands;

import exceptions.IllegalArgumentsException;
import command.Console;
import managers.FileManager;

public class Save extends Command{
    private FileManager fileManager;
    private Console console;

    public Save(FileManager fileManager,Console console){
        super("Save"," сохранить коллекцию в файл");
        this.fileManager = fileManager;
        this.console =console;
    }

    @Override
    public void execute(String args) throws IllegalArgumentsException {
        if (!args.isBlank()) throw new IllegalArgumentsException();
        fileManager.saveObjects();
        console.println("Объекты сохранены успешно");
    }
    }
