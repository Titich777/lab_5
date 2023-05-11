package storedClasses.forms;

import command.*;
import exceptions.InFileModeException;
import storedClasses.enums.DragonType;

import java.util.Locale;
import java.util.Scanner;


public class DragonTypeForm extends Form<DragonType> {
    private final Printable console;
    private final UserInput scanner;
    private final String type ;

    public DragonTypeForm(Printable console,String type) {
        this.console = (Console.isFileMode()) ? new BlankConsole() : console;
        this.type = type;
        this.scanner = (Console.isFileMode()) ? new ExecuteFileManager() : new ConsoleInput();
    }
    @Override
    public DragonType build() {
        console.println("Возможный тип: ");
        console.println(DragonType.names());
        while (true){
            console.println("Введите тип " + type + ": ");
            String input = scanner.nextLine().trim();
            try{
                return DragonType.valueOf(input.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException exception){
                console.printException("Такого типа нет в списке");
                if (Console.isFileMode()) throw new InFileModeException();
            }
        }
    }
}
