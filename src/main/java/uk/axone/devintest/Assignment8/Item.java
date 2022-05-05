/**
 * Java Shopping Cart Assignment - Class Item
 * Criteria: Represents a single product or item.
             Key attributes are item code, item name, item description, price.
             Public methods should be getters and setters for each attribute.
             Constructor takes all four attributes as input

 * Overriding equals() and hashcode() methods
 * Whenever we use entities (like Item here) ALWAYS override these two methods in your entity class,
 * helps to compare two objects of type Item. Objects of type Item will be reference datatypes.
 * Comparing them using equals() method will compare EVERY attribute of the two objects. See equals() method code below.
 * Professional programmers always override these 2 methods
 * THIS IS AN INTERVIEW QUESTION ALSO
 * The IDE usually has an easy way to override
 * In this case Right-click inside the class -> Generate -> equals() and hashcode()

@author - Sri.Vangaveti
 */

package uk.axone.devintest.Assignment8;

import java.util.Objects;

public class Item {
    private int itemCode;
    private String itemName;
    private String itemDescription;
    private int price;

    //Default constructor - required in Inventory class
    public Item(){}

    //Constructor with parameters
    public Item(int itemCode, String itemName, String itemDescription, int price){
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.price = price;
    }

    //Getter and setter methods for each private attribute
    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return itemCode == item.itemCode && price == item.price && itemName.equals(item.itemName) && itemDescription.equals(item.itemDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemCode, itemName, itemDescription, price);
    }

    //ALSO override toString() if you want your data to be displayed in a nice format
    @Override
    public String toString() {
        return "Item{" +
                "itemCode=" + itemCode +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", price=" + price +
                '}';
    }
}
