package storedClasses.enums;

public enum DragonCharacter {
    CUNNING,
    WISE,
    GOOD,
    CHAOTIC_EVIL;
    public static String names(){
        StringBuilder nameList = new StringBuilder();
        for (var forms: values()){
            nameList.append(forms.name()).append("\n");
        }
        return nameList.substring(0, nameList.length()-1);
    }
}
