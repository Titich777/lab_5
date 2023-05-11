package storedClasses.forms;


import exceptions.InFileModeException;
import exceptions.InvalidFormException;
import command.*;
import storedClasses.Coordinates;
import java.util.Scanner;
public class CoordinatesForm extends Form<Coordinates>{
    private final Printable console;
    private final UserInput scanner;

    public CoordinatesForm(Printable console){
        this.console =(Console.isFileMode())? new BlankConsole(): console;
        this.scanner =(Console.isFileMode())? new ExecuteFileManager(): new ConsoleInput();
    }

    @Override
    public Coordinates build(){
        return new Coordinates(askX(), askY());
    }

    private Float askX(){
        while (true){
            console.println("Введите координату x");
             String input = scanner.nextLine().trim();
             try {
                 return Float.parseFloat(input);
             } catch (NumberFormatException exception){
                 console.println("X должно быть числом типа float");
             }

        }
    }
    private Double askY(){
        while (true){
            console.println("Введите координату y");
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException exception){
                console.println("Y должно быть числом типа double");
            }
        }
    }
}
