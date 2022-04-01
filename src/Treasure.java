public class Treasure extends Thing implements java.io.Serializable{


    private int value;

    public Treasure(String aName, String aDescription, int aValue) {
        super(aName, aDescription);
        this.value = aValue;
    }

    public int getValue() {
        return value;
    }

}
