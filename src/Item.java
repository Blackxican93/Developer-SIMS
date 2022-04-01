public class Item {

    private String itemName;
    private String itemDescription;
//    private String itemAttribute;
//    private String itemLocation;

    @SuppressWarnings("unchecked")
    public Item(String itemName, String itemDescription, String itemAttribute, String itemLocation) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
//        this.itemAttribute = itemAttribute;
//        this.itemLocation = itemLocation;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

//    public String getItemAttribute() {
//        return itemAttribute;
//    }

//    public void setItemAttribute(String itemAttribute) {
//        this.itemAttribute = itemAttribute;
//    }

//    public String getItemLocation() {
//        return itemLocation;
//    }

//    public void setItemLocation(String itemLocation) {
//        this.itemLocation = itemLocation;
//    }

    @Override
    public String toString() {
        return  "Name='" + itemName + '\'' +
                ", Description='" + itemDescription + '\'';
//                ", Attribute='" + itemAttribute + '\'' +
//                ", Location='" + itemLocation + '\'';
    }
}