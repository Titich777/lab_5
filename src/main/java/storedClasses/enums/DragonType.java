package storedClasses.enums;

public enum DragonType {
    WATER,
    UNDERGROUND,
    AIR,
    FIRE;
    public static String names(){
        StringBuilder nameList = new StringBuilder();
        for (var forms: values()){
            nameList.append(forms.name()).append("\n");
        }
        return nameList.substring(0, nameList.length()-1);
    }
}
