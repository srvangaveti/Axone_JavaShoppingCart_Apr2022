/**
 * Java Shopping Cart Assignment - Class Inventory
 * Criteria: This class represents stock levels for each item.
             The stock can be stored as a map with Item and corresponding quantities.

@author - Sri.Vangaveti
 */

package uk.axone.devintest.Assignment8;

import java.io.*;
import java.util.*;

/*
 * Inventory class represents the stock levels for each item.
 */
public class Inventory {

    //stock stored as a map with item as key and quantity as values
    private Map<Item, Integer> stock;

    //Getter and setter methods for the private variables
    public Map<Item, Integer> getStock() {
        return stock;
    }

    public void setStock(Map<Item, Integer> stock) {
        this.stock = stock;
    }

    /*Constructor:
    * calls a private method called load() which will read a csv file (which holds the stock levels) and populate 'stock'
    * throws InvalidDataException when the load() method fails due to improper data in the file
     */
    public Inventory() throws InvalidDataException {
        stock = this.load();
    }

    /* Private method load()
    * This method reads the csv file from the 'resources', obtains all the data, and puts into the stock map
    * The method returns a map, with Item as key and int stock levels as values
    * I used think link for reference: https://www.geeksforgeeks.org/reading-text-file-into-java-hashmap/
     */
    private Map<Item, Integer> load() throws InvalidDataException {

        Map<Item, Integer> map = new HashMap<>();

        // Creating an object of File that contains the path where csv file is located
        File f1 = new File("src/main/resources/Stock_levels.csv");

        /*Using the try-with-resources statement to ensure that all opened resources are closed at the end of try stmt
        * By using this, you do not need to close the resources manually in the finally block.
        * Refer: https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
        * Resources used here are BufferedReader and FileReader.
        * FileReader object reads the above file
        * BufferedReader object takes FileReader as parameter
         */
        try (FileReader fr = new FileReader(f1); BufferedReader br = new BufferedReader(fr)) {

            //Creating an empty String that will be used to read the file line by line
            String line = null;

            //Loop to read the file line by line, using String.split() to split the rows into tokens by the given delimiter
            while((line = br.readLine()) != null){
                String[] tokens = line.split(",");
                Item it = new Item();
                int quantity;

                //Adding the individual tokens to the Item object
                //using the trim() method here to remove any whitespace from both ends of the string token
                //Since each element in the String array tokens is of String type, where relevant parsing it to Integer
                it.setItemCode(Integer.parseInt(tokens[0].trim()));
                it.setItemName(tokens[1].trim());
                it.setItemDescription(tokens[2].trim());
                it.setPrice(Integer.parseInt(tokens[3]));
                //Adding the last token to quantity variable
                quantity = Integer.parseInt(tokens[4].trim());

                //putting the key and values to the HashMap
                map.putIfAbsent(it,quantity);
            }

        } catch (IOException e) {
            throw new InvalidDataException("Improper or invalid data is in the file");
        }
        return map;
    }

    //Public method to check if the Item passed matches a product in the stock file
    public boolean validateItem(Item it) throws ItemNotFoundException{
        if(stock.containsKey(it)) {
            return true;
        }
        else{
            throw new ItemNotFoundException("Item not found in stock");
        }
    }

    //Public method that reduces stock and returns true if successful
    public boolean reduceStock(Item it, Integer quantity) throws ItemNotFoundException{
        if(validateItem(it)){
            if(stock.get(it) > quantity){
                stock.computeIfPresent(it,(key,val) -> val - quantity);
                return true;
            }
        }
        return false;
    }

    // Public method that returns the number of items in stock for the Item passed
    public int getItemStock(Item it) throws ItemNotFoundException {
        int itemStock = 0;
        try {
            itemStock = stock.get(it);
        }
        catch (Exception e){
            e = new ItemNotFoundException("Item passed not found in stock");
        }
        return itemStock;
    }

    //Public method returns itemCodes and itemNames as a map
    //This catalogue can be used to identify what items are available to shop
    public Map<Integer,String> getItemCatalogue(){
        Map<Integer,String> map = new HashMap();
        for (Map.Entry<Item,Integer> i : stock.entrySet()){
            map.put(i.getKey().getItemCode(),i.getKey().getItemName());
        }
        return map;
    }
}
