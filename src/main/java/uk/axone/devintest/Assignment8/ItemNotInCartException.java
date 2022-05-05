package uk.axone.devintest.Assignment8;

public class ItemNotInCartException extends Exception{

    public ItemNotInCartException(String message){
        super(message);
    }
}
