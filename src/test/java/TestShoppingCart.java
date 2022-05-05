/**
 * Main Test class to test four scenarios
 * @author - Sri.Vangaveti
 */

import org.junit.Assert;
import org.junit.Test;
import uk.axone.devintest.Assignment8.*;


public class TestShoppingCart {

    ShoppingCart sc = new ShoppingCart();

    public TestShoppingCart() throws InvalidDataException {
    }

    /* TEST SCENARIO 1
    Add valid in stock items to the cart and check if price is calculated properly
     */
    @Test
    public void calculateTotalCost_Test() throws ItemOutOfStockException, ItemNotFoundException {
        //Adding Items to cart
        sc.addToCart(new Item(1001, "iPhone 12", "A great all-round phone from Apple", 1000));
        sc.addToCart(new Item(1010, "Google Pixel 6", "The best phone for pure Android", 550));
        sc.addToCart(new Item(1002, "Samsung S10", "Samsung Galaxy flagship model", 500), 2);
        int actualResult = sc.calculateTotalCost();
        int expectedResult = 2550;
        Assert.assertEquals(expectedResult, actualResult);
    }

    /* TEST SCENARIO 2
    Add items and perform a checkout and ensure stock levels have reduced
    */
    @Test
    public void checkout_Test() throws ItemOutOfStockException, ItemNotFoundException {
        Item item1 = new Item(1001, "iPhone 12", "A great all-round phone from Apple", 1000);
        Item item2 = new Item(1010, "Google Pixel 6", "The best phone for pure Android", 550);
        Item item3 = new Item(1002, "Samsung S10", "Samsung Galaxy flagship model", 500);

        sc.addToCart(item1);
        sc.addToCart(item2);
        sc.addToCart(item3, 2);

        sc.checkout();
        int item1NewStock = sc.inv.getItemStock(item1);
        int item2NewStock = sc.inv.getItemStock(item2);
        int item3NewStock = sc.inv.getItemStock(item3);

        int[] actualResult = {item1NewStock, item2NewStock, item3NewStock};
        int[] expectedResult = {19, 44, 38};

        Assert.assertArrayEquals(expectedResult, actualResult);
    }

    /* TEST SCENARIO 3
    Try to add an invalid item and ensure appropriate exception is generated
    */
    @Test (expected = ItemNotFoundException.class)
    public void invalidItemTest() throws ItemOutOfStockException, ItemNotFoundException {
        //Adding an invalid item (item code 1003 is not Samsung)
        sc.addToCart(new Item(1003, "Samsung S10", "Samsung Galaxy flagship model", 500), 2);
    }

    /* TEST SCENARIO 4
    Try to add an item not in stock and ensure appropriate exception is generated
    */
    @Test (expected = ItemOutOfStockException.class)
    public void outOfStockTest() throws ItemOutOfStockException, ItemNotFoundException {
        Item item1 = new Item(1001, "iPhone 12", "A great all-round phone from Apple", 1000);
        //Adding 50 of iPhone12 when the stock is only of 20
        sc.addToCart(item1,50);
    }

}


