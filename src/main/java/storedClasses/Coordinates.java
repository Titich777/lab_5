package storedClasses;

import java.util.Objects;

public class Coordinates implements Validator{

    private float x; // значение поля должно быть больше -288
    private Double y; // значение поля должно быть больше -676

    public Coordinates(float x, Double y){
        this.x=x;
        this.y=y;
    }

    public float getX(){
        return x;
    }

    public void setX(float x){
        this.x = x;
    }
    public Double getY(){
        return y;
    }
    public void setY(Double y){
        this.y = y;
    }

    @Override
    public boolean validate() {
        if(this.x <=-288) return false;
        return !(this.y <=676);
    }

    @Override
    public boolean equals(Objects o) {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Float.compare(that.x, x) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
