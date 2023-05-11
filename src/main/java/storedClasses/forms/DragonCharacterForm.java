package storedClasses.forms;

import command.*;
import exceptions.InFileModeException;
import storedClasses.enums.DragonCharacter;

import java.util.Locale;

public class DragonCharacterForm extends Form<DragonCharacter>{
    private final Printable console;
    private final UserInput scanner;
    private final String type ;

    public DragonCharacterForm(Printable console,String type) {
        this.console = (Console.isFileMode()) ? new BlankConsole() : console;
        this.type = type;
        this.scanner = (Console.isFileMode()) ? new ExecuteFileManager() : new ConsoleInput();
    }

    @Override
    public DragonCharacter build() {
        console.println("Возможные черты характера: ");
        console.println(DragonCharacter.names());
        while (true){
            console.println("Введите черту характера " + type + ": ");
            String input = scanner.nextLine().trim();
            try{
                return DragonCharacter.valueOf(input.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException exception){
                console.printException("Такого нет в списке");
                if (Console.isFileMode()) throw new InFileModeException();
            }
        }
    }
}
