package storedClasses.forms;
import exceptions.InFileModeException;
import command.*;
import storedClasses.DragonHead;

public class DragonHeadForm extends Form<DragonHead> {
    private final Printable console;
    private final UserInput scanner;

    public DragonHeadForm(Printable console){
        this.console = (Console.isFileMode()) ? new BlankConsole() : console;
        this.scanner = (Console.isFileMode()) ? new ExecuteFileManager() : new ConsoleInput();
    }

    @Override
    public DragonHead build() {
        console.println("Создание объекта админа");
        DragonHead dragonHead = new DragonHead(
                askEyesCount(),
                askToothCount()

        );
        console.println("Создание объекта админа окончено успешно");
        return dragonHead;
    }
    private int askEyesCount(){
        while (true) {
            console.println("Введите количество глаз");
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                console.println("Число глаз должно быть числом типа long");
                if (Console.isFileMode()) throw new InFileModeException();
            }
        }
    }

    private Float askToothCount(){
        while (true) {
            console.println("Введите количество зубов");
            String input = scanner.nextLine().trim();
            try {
                return Float.parseFloat(input);
            } catch (NumberFormatException exception) {
                console.println("Число зубов должно быть числом типа long");
                if (Console.isFileMode()) throw new InFileModeException();
            }
        }
    }

}
