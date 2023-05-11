package managers;

import managers.ScannerManager;
import exceptions.CommandRuntimeException;
import exceptions.ExitObligedException;
import exceptions.IllegalArgumentsException;
import  exceptions.NoSuchCommandException;
import command.*;

import java.util.*;
public class RunTimeManager {
    private final Printable console;
    private final CommandManager commandManager;

    public RunTimeManager(Printable console,CommandManager commandManager){
        this.console = console;
        this.commandManager = commandManager;
    }
    public void interactiveMode(){
        Scanner userScanner = ScannerManager.getUserScanner();
        while (true) {
            try{
                if (!userScanner.hasNext()) throw new ExitObligedException();
                String userCommand = userScanner.nextLine().trim() + " "; // прибавляем пробел, чтобы split выдал два элемента в массиве
                this.launch(userCommand.split(" ", 2));
                commandManager.addToHistory(userCommand);
            } catch (NoSuchElementException exception) {
                console.printException("Пользовательский ввод не обнаружен!");
            } catch (NoSuchCommandException noSuchCommand) {
                console.printException("Такой команды нет в списке");
            } catch (IllegalArgumentsException e) {
                console.printException("Введены неправильные аргументы команды");
            } catch (CommandRuntimeException e) {
                console.printException("Ошибка при исполнении команды");
            } catch (ExitObligedException exitObliged){
                console.println("До свидания!");
                return;
            }
        }
    }

    private void launch(String[] userCommand) throws NoSuchCommandException,ExitObligedException,IllegalArgumentsException,CommandRuntimeException {
        if (userCommand[0].equals("")) return;
        commandManager.execute(userCommand[0], userCommand[1]);
    }
}
