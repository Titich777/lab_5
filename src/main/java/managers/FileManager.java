package managers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.security.AnyTypePermission;
import command.Console;
import command.Printable;
import exceptions.ExitObligedException;
import exceptions.InvalidFormException;
import storedClasses.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Date;

/**
 * Класс реализующий работу с файлами
 */
public class FileManager {
    private String text;
    private final Printable console;
    private final CollectionManager collectionManager;
    private final XStream xStream = new XStream();


    public FileManager(Console console, CollectionManager collectionManager){
        this.console = console;
        this.collectionManager = collectionManager;

        this.xStream.alias("Dragon", Dragon.class);
        this.xStream.alias("Linked", CollectionManager.class);
        this.xStream.addPermission(AnyTypePermission.ANY);
        this.xStream.addImplicitCollection(CollectionManager.class, "collection");
    }
    public void findFile() throws ExitObligedException{
        String file_path = System.getenv("file_path");
        if (file_path == null || file_path.isEmpty()) {
            console.printException("Путь должен быть в переменных окружения в перменной 'file_path'");
            throw new ExitObligedException();
        }
        else console.println("Путь получен успешно");

        File file = new File(file_path);
        BufferedInputStream bis;
        FileInputStream fis;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            while (bis.available() > 0) {
                stringBuilder.append((char) bis.read());
            }
            fis.close();
            bis.close();
            if (stringBuilder.isEmpty()) {
                console.printException("Файл пустой");
                this.text = "</Linked>";
                return;
            }
            this.text = stringBuilder.toString();
        } catch (FileNotFoundException fnfe) {
            console.printException("Такого файла не найдено");
            throw new ExitObligedException();
        } catch (IOException ioe) {
            console.printException("Ошибка ввода/вывода" + ioe);
            throw new ExitObligedException();
        }
    }
    public void createObjects() throws ExitObligedException{
        try{
            XStream xstream = new XStream();
            xstream.alias("Dragon", Dragon.class);
            xstream.alias("Array", CollectionManager.class);
            xstream.addPermission(AnyTypePermission.ANY);
            xstream.addImplicitCollection(CollectionManager.class, "collection");
            CollectionManager collectionManagerWithObjects = (CollectionManager) xstream.fromXML(this.text);

            for(Dragon s : collectionManagerWithObjects.getCollection()){
                if (this.collectionManager.checkExist(s.getId())){
                    console.printException("В файле повторяются айди!");
                    throw new ExitObligedException();
                }
                this.collectionManager.addElement(s);
            }
            this.collectionManager.addElements(collectionManagerWithObjects.getCollection());
        } catch (InvalidFormException | StreamException e) {
            console.printException("Объекты в файле не валидны");
        }
        Dragon.updateId(collectionManager.getCollection());
    }
    public void saveObjects(){
        String file_path = System.getenv("file_path");
        if (file_path == null || file_path.isEmpty())
            console.printException("Путь должен быть в переменных окружения в перменной 'file_path'");
        else console.println("Путь получен успешно");
        try{
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file_path));
            out.write(this.xStream.toXML(collectionManager)
                    .getBytes(StandardCharsets.UTF_8));
            out.close();
        } catch (FileNotFoundException e) {
            console.printException("Файл не существует");
        }catch (IOException e){
            console.printException("Ошибка ввода вывода");
        }
    }

}
