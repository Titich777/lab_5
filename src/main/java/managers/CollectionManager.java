package managers;

import exceptions.InvalidFormException;
import storedClasses.Dragon;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Collection;

public class CollectionManager {
    private static final ArrayDeque<Dragon> collection = new ArrayDeque<>();

    private LocalDateTime lastInitTime;
    /**
     * Дата последнего изменения коллекции
     */
    private LocalDateTime lastSaveTime;

    public CollectionManager() {
        this.lastInitTime = LocalDateTime.now();
        this.lastSaveTime = null;
    }

    public static ArrayDeque<Dragon> getCollection(){
        return collection;
    }
    /**
     * Метод скрывающий дату, если она сегодняшняя
     * @param localDateTime объект {@link LocalDateTime}
     * @return вывод даты
     */
    public static String timeFormatter(LocalDateTime localDateTime){
        if (localDateTime == null) return null;
        if (localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .equals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))){
            return localDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        }
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * Метод скрывающий дату, если она сегодняшняя
     * @return вывод даты
     */
    public static String timeFormatter(Date dateToConvert){
        LocalDateTime localDateTime = dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        if (localDateTime == null) return null;
        if (localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .equals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))){
            return localDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        }
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    public String getLastInitTime() {
        return timeFormatter(lastInitTime);
    }

    public String getLastSaveTime() {
        return timeFormatter(lastSaveTime);
    }
    /**
     * @return Имя типа коллекции.
     */
    public String collectionType(){
        return collection.getClass().getName();
    }

    public int collectionSize(){
        return collection.size();
    }
    public void clear(){
        this.collection.clear();
        lastInitTime = LocalDateTime.now();
    }

    public Dragon getLast() {
        return collection.getLast();
    }
    /**
     * @param id ID элемента.
     * @return Элемент по его ID или null, если не найдено.
     */

    public Dragon getById(int id) {
        for (Dragon element : collection) {
            if (element.getId() == id) return element;
        }
        return null;
    }

    /**
     * Изменить элемент коллекции с таким id
     * @param id id
     * @param newElement новый элемент
     */
    public void editById(int id, Dragon newElement) throws InvalidFormException{
        Dragon pastElement = this.getById(id);
        this.removeElement(pastElement);
        newElement.setId(id);
        this.addElement(newElement);
        Dragon.updateId(this.getCollection());
    }
    /**
     * @param id ID элемента.
     * @return Проверяет, существует ли элемент с таким ID.
     */
    public boolean checkExist(int id) {
        return collection.stream()
                .anyMatch((x) -> x.getId() == id);
    }
    public void addElement(Dragon dragon) throws InvalidFormException{
        this.lastSaveTime = LocalDateTime.now();
        if (!dragon.validate()) throw new InvalidFormException();
        collection.add(dragon);
    }

    public void addElements(Collection<Dragon> collection) throws InvalidFormException{
        if (collection == null) return;
        for (Dragon dragon:collection){
            this.addElement(dragon);
        }
    }

    public void removeElement(Dragon dragon){
        collection.remove(dragon);
    }

    public void removeElements(Collection<Dragon> collection){
        this.collection.removeAll(collection);
    }

    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пуста!";
        var last = getLast();

        StringBuilder info = new StringBuilder();
        for (Dragon dragon : collection) {
            info.append(dragon);
            if (dragon != last) info.append("\n\n");
        }
        return info.toString();
    }

}

