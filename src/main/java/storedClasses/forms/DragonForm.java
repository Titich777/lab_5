package storedClasses.forms;

import exceptions.InFileModeException;
import command.*;
import storedClasses.Coordinates;
import storedClasses.Dragon;
import storedClasses.Coordinates;
import storedClasses.DragonHead;
import storedClasses.enums.DragonCharacter;
import storedClasses.enums.DragonType;

import java.util.Date;
public class DragonForm extends Form<Dragon> {
    private final Printable console;
    private final UserInput scanner;
    public DragonForm(Printable console){
        this.console =(Console.isFileMode())? new BlankConsole() :console;
        this.scanner =(Console.isFileMode())? new ExecuteFileManager(): new ConsoleInput();
    }
    @Override
    public Dragon build(){
        return new Dragon(
                askName(),
                askCoordinates(),
                new Date(),
                askAge(),
                askWingspan(),
                askDragoneType(),
                askDragonCharacter(),
                askDragonHead()
        );

    }
    private String askName(){
        String name;
        while (true){
            console.println("Введите имя");
            name = scanner.nextLine().trim();
            if (name.isEmpty()){
                console.printException("Имя не может быть пустым");
                if (Console.isFileMode()) throw new InFileModeException();
            }
            else{
                return name;
            }
        }
    }
    private Coordinates askCoordinates(){
        return new CoordinatesForm(console).build();
    }

    private Long askAge(){
        while (true){
            console.println("Введите возраст");
            String input = scanner.nextLine().trim();
            try{
                return Long.parseLong(input);
            } catch (NumberFormatException exception){
                console.println("Неправильный возраст");
                if (Console.isFileMode()) throw new InFileModeException();
            }
        }
    }
    private long askWingspan(){
        while (true) {
            console.println("Введите количество размахов крыльев");
            String input = scanner.nextLine().trim();
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException exception) {
                console.printException("Число размахов должно быть числом типа long");
                if (Console.isFileMode()) throw new InFileModeException();
            }
        }
    }
    private DragonType askDragoneType(){
        return new DragonTypeForm(console," тип ").build();
    }
    private DragonCharacter askDragonCharacter(){
        return new DragonCharacterForm(console," стихия ").build();
    }
    private DragonHead askDragonHead(){
        return new DragonHeadForm(console).build();
    }

}
