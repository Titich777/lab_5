package storedClasses;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import managers.CollectionManager;
import storedClasses.enums.DragonCharacter;
import storedClasses.enums.DragonType;

import java.util.Date;
import java.util.ArrayDeque;
import java.util.Objects;


interface CoordinatesRange<T, S>{
    float getDistanceFromCentre(T x, S y);
}
@XStreamAlias("Dragon")
public class Dragon implements Validator,Comparable<Dragon> {

    private Integer id; // Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; // Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; // Поле не может быть null
    protected Date creationDate; // Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int age; // Значение поля должно быть больше 0
    private long wingspan; // Значение поля должно быть больше 0
    private DragonType type; // Поле не может быть null
    private DragonCharacter character; // Поле не может быть null
    private DragonHead head; //

    private static int nextId = 0;

    public Dragon(String name, Coordinates coordinates, Date creationDate, Long age, float wingspan, DragonType type, DragonCharacter character, DragonHead head){
        this.id = incNextId();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.age = Math.toIntExact(age);
        this.wingspan = (long) wingspan;
        this.type = type;
        this.character = character;
        this.head = head;

    }


    private static int incNextId(){
        return nextId++;
    }

    /**
     * Обновляет указатель на следующий id
     * <p>
     * Требуется, так как xStream с помощью reflection обходит приватность id
     * @param collection коллекция, в которой получить id.
     */

    public static void updateId(ArrayDeque<Dragon> collection){
        nextId = collection.
                stream().filter(Objects::nonNull)
                .map(Dragon::getId)
                .mapToInt(Integer::intValue)
                .max().orElse(0)+1;
    }

    public Integer getId() {
        return Math.toIntExact(id);
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public Coordinates getCoordinates(){
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }
    public Date getCreationDate(){
        return creationDate;
    }

    public void setCreationDate(Date creationDate){
        this.creationDate = creationDate;
    }

    public int getAge(){
        return age;
    }
    public void setAge(Integer age){
        this.age = age;
    }

    public float getWingspan(){
        return wingspan;
    }

    public void setWingspan(long wingspan){
        this.wingspan = wingspan;
    }

    public DragonType getType(){
        return type;
    }

    public void setType(DragonType type){
        this.type = type;
    }

    public DragonCharacter getCharacter(){
        return character;
    }

    public void setCharacter(DragonCharacter character){
        this.character = character;
    }

    public DragonHead getHead(){
        return head;
    }

    public void setHead(DragonHead head){
        this.head = head;
    }


    /**
     * Метод валидирующие поля по условию
     * @return true если поля валидные,false иначе
     */
    @Override
    public boolean validate() {
        if (this.id == null || this.id <=0) return false;
        if (this.name == null || this.name.isEmpty()) return false;
        if (this.coordinates == null ) return false;
        if (this.creationDate == null ) return false;
        if (this.age <=0) return false;
        if (this.wingspan <=0) return false;
        if (this.type == null) return false;
        if (this.character == null) return false;
        return this.head != null;
    }

    @Override
    public boolean equals(Objects o){
        if (o == null) return false;

        if (age != this.age) return false;
        if (wingspan != this.wingspan) return false;
        if (!id.equals(this.id))return false;
        if (!name.equals(this.name)) return false;
        if (!coordinates.equals(this.coordinates)) return false;
        if (!creationDate.equals(this.creationDate)) return false;
        if (!type.equals(this.type)) return false;
        if (!character.equals(this.character)) return false;
        if (!head.equals(this.head)) return false;
        return head.equals(this.head);
    }
    /**
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Dragon o) {
        if (Objects.isNull(o)) return 1;
        CoordinatesRange<Float, Double> calc = (x, y) -> (float) Math.sqrt(x * x + y * y);
        return Float.compare(
                calc.getDistanceFromCentre(this.getCoordinates().getX(), (double) this.getCoordinates().getY()),
                calc.getDistanceFromCentre(o.getCoordinates().getX(), (double) o.getCoordinates().getY()));
    }
    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + coordinates.hashCode();
        result = 31 * result + creationDate.hashCode();
        result = 31 * result + (int) (age ^ (age >>> 32));
        result = 31 * result + (int) (wingspan ^ (wingspan >>> 32));
        result = 31 * result + type.hashCode();
        result = 31 * result + character.hashCode();
        result = 31 * result + head.hashCode();

        return result;
    }
    @Override
    public String toString() {
        return "Dragon{" + '\n' +
                "id = " + id + '\n' +
                "name = " + name + '\n' +
                "coordinates = " + coordinates + '\n' +
                "creationDate = " + CollectionManager.timeFormatter(creationDate) + '\n' +
                "age = " + age + '\n' +
                "wingspan = " + wingspan + '\n' +
                "type = " + type + '\n' +
                "character = " + character + '\n' +
                "head = " + head + '\n' +
                '}';
    }

}
