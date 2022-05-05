package uk.axone.devintest.Assignment8;

import java.util.Map;

public class ShoppingCartDemo {
    public static void main(String[] args) throws InvalidDataException {
        ShoppingCart sc = new ShoppingCart();
        Map<Item, Integer> getStock = sc.inv.getStock();
        for (Map.Entry<Item,Integer> i : getStock.entrySet()){
            System.out.println("Key: " + i.getKey() + " Value: " + i.getValue());
        }
    }
}
