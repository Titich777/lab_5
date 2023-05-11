package command.commands;

import command.Console;
import command.ExecuteFileManager;
import exceptions.CommandRuntimeException;
import exceptions.ExitObligedException;
import exceptions.IllegalArgumentsException;
import exceptions.NoSuchCommandException;
import managers.CommandManager;
import managers.FileManager;

import java.io.*;
import java.util.NoSuchElementException;


public class Execute extends Command{
    private final CommandManager commandManager;
    private final Console console;
    private FileManager fileManager;

    public Execute (CommandManager commandManager,Console console,FileManager fileManager){
        super("execute_script"," считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме. ");
        this.console =console;
        this.fileManager = fileManager;
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String args) throws IllegalArgumentsException,ExitObligedException ,CommandRuntimeException{
        if (args == null || args.isEmpty()) {
            console.printException("Путь не распознан");
            return;
        }
        else console.println("Путь получен успешно");

        try {
            Console.setFileMode(true);
            ExecuteFileManager.pushFile(args);
            for (String line = ExecuteFileManager.readLine(); line != null; line = ExecuteFileManager.readLine()) {
                try{
                    commandManager.addToHistory(line);
                    String[] cmd = (line + " ").split(" ", 2);
                    if (cmd[0].isBlank()) return;
                    if (cmd[0].equals("execute_script")){
                        if(ExecuteFileManager.fileRepeat(cmd[1])){
                            console.printException("Найдена рекурсия по пути " + new File(cmd[1]).getAbsolutePath());
                            continue;
                        }
                    }
                    console.println("Выполнение команды " + cmd[0]);
                    commandManager.execute(cmd[0], cmd[1]);
                    if (cmd[0].equals("execute_script")){
                        ExecuteFileManager.popFile();
                    }
                } catch (NoSuchElementException exception) {
                    console.printException("Пользовательский ввод не обнаружен!");
                } catch (NoSuchCommandException noSuchCommandException) {
                    console.printException("Такой команды нет в списке");
                } catch (IllegalArgumentsException e) {
                    console.printException("Введены неправильные аргументы команды");
                } catch (CommandRuntimeException e) {
                    console.printException("Ошибка при исполнении команды");
                }
            }
            ExecuteFileManager.popFile();
        }  catch (NoSuchCommandException noSuchCommand){
            console.printException("Такой команды не существует");
        } catch (FileNotFoundException fileNotFoundException){
            console.printException("Такого файла не существует");
        } catch (IOException e) {
            console.printException("Ошибка ввода вывода");
        }
        Console.setFileMode(false);
    }
    }
