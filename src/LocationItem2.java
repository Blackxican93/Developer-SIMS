public class LocationItem2 {
    private String name;

    public LocationItem2(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "LocationItem2{" +
                "name='" + name + '\'' +
                '}';
    }
}