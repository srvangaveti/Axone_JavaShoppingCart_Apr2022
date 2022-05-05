/**
 * Java Shopping Cart Assignment - Class ShoppingCart

 @author - Sri.Vangaveti
 */

package uk.axone.devintest.Assignment8;

import java.util.*;

/*
* This class stores the items added to the shopping cart as a HashMap
* with the keys as Item and values as the quantity of items added to cart
 */
public class ShoppingCart {

    private Map<Item, Integer> shoppingCart = new HashMap<>();
    public Inventory inv;

    //Getter and setter methods for the private variables
    public Map<Item, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Map<Item,Integer> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    //Constructor initialises the Inventory class and loads the stock
    public ShoppingCart() throws InvalidDataException {
        inv = new Inventory();
    }

    //Public method adds a single item to the cart
    //First checks if the item is valid and in stock
    //Returns true if item added to cart successfully
    public boolean addToCart(Item it) throws ItemNotFoundException, ItemOutOfStockException {
        if(inv.validateItem(it)){
            if (inv.getItemStock(it)>0){
                shoppingCart.put(it,1);
                return true;
            }
            else{
                throw new ItemOutOfStockException("Sorry item is out of stock");
            }
        }
        return false;
    }

    //Method to add multiple items to the cart
    //after checking if item is valid and if it is in stock
    public boolean addToCart(Item it, int quantity) throws ItemNotFoundException, ItemOutOfStockException {
        if (inv.validateItem(it)){
            if (inv.getItemStock(it) > 0 && inv.getItemStock(it) >= quantity){
                shoppingCart.put(it,quantity);
                return true;
            }
            else{
                throw new ItemOutOfStockException("Sorry item is out of stock");
            }
        }
        return false;
    }

    //Method to remove a single item from the cart
    //returns true if operation is successful
    public boolean removeFromCart(Item it) throws ItemNotInCartException {
        if(shoppingCart.containsKey(it)){
            shoppingCart.remove(it);
            return true;
        }
        else{
            throw new ItemNotInCartException("The item you are trying to remove is not in the shopping cart");
        }
    }

    //Method to calculate the total cost of items in the cart at any point
    public int calculateTotalCost(){
        int totalCost = 0;
        for (Map.Entry<Item,Integer> item : shoppingCart.entrySet()){
            totalCost = totalCost + (item.getKey().getPrice())* item.getValue();
        }
        return totalCost;
    }

    //Method that returns the items in the cart as an array
    public Item[] getCartContents(){
        Item[] cartContents = shoppingCart.keySet().toArray(new Item[0]);
        return cartContents;
    }

    //Method to check out the cart items,
    //reducing the stock levels for the items being checked out
    public void checkout() throws ItemNotFoundException {
        if(shoppingCart != null){
            for (Map.Entry<Item,Integer> item : shoppingCart.entrySet()){
                inv.reduceStock(item.getKey(), item.getValue());
            }
        }
    }

}
