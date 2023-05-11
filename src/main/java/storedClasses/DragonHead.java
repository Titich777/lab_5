package storedClasses;

import java.util.Objects;
public class DragonHead implements Validator{
    private int eyesCount;
    private Float toothCount;

    public DragonHead(int eyesCount,Float toothCount){
        this.eyesCount = eyesCount;
        this.toothCount = toothCount;
    }

    public Integer getEyesCount(){
        return eyesCount;
    }

    public void setEyesCount(int eyesCount){
        this.eyesCount = eyesCount;
    }

    public Float getToothCount(){
        return toothCount;
    }

    public void setToothCount(Float toothCount){
        this.toothCount = toothCount;
    }


    @Override
    public boolean validate() {
        if (this.toothCount == null ) return false;
        return this.eyesCount >0;
    }

    @Override
    public boolean equals(Objects o) {
        if (o == null ) return false;

        if (eyesCount != this.eyesCount) return false;
        return Objects.equals(toothCount,this.toothCount);
    }
    @Override
    public int hashCode() {
        int result = toothCount.hashCode();
        result = 31 * result + eyesCount;
        return result;
    }
    @Override
    public String toString() {
        return "DragonHead {" + "\n" +
                "eyesCount=" + eyesCount  + "\n" +
                "toothCount=" + toothCount + "\n" +
                '}';
    }
}
