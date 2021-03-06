package sample;



import java.util.ArrayList;

/**
 The Order class defines the abstract Order type which contains the array consisting of MenuItem class objects.
 Contains constructors to generate Order objects.
 The class allows for MenuItem objects to be removed, added, the price of the items to be updated, return the
 arraylist of items, and can return the order number of an individual Order object.

 @author German Munguia, Sukhjit Singh
 */

public class Order implements Customizable{
    private int orderNumber;
    private ArrayList<MenuItem> items = new ArrayList<>();
    private double totalPrice;

    /**
     Constructor used to generate a Order object with a given order number
     @param orderNumber unique identifier for an instance of the Order class.
     */
    public Order(int orderNumber){
        this.orderNumber = orderNumber;
    }

    /**
     Add the given Object into the items arraylist.
     First checks if the given Object is an instance of MenuItem, if so it is casted and added to the items arraylist.
     Calls the updateTotal method to update the total price of the entire order.
     @param obj the Object to be added to the items arraylist
     @return true if the Object was added, false otherwise
     */
    public boolean add(Object obj){
        //Only add if its a menuItem, donut and coffee.
        if(obj instanceof MenuItem) {
            MenuItem item = (MenuItem)obj;
            items.add(item);
            //Since something was added, update the price
            updateTotal();
            return true;
        }
        return false;
    }

    /**
     Remove the given Object from the items arraylist.
     First checks if the given Object is an instance of MenuItem, if so it is casted and used to find the item to be
     removed from the items arraylist. If the arraylist contains the Object, it is removed.
     Calls the updateTotal method to update the total price of the entire order.
     @param obj the Object to be removed from the items arraylist
     @return true if the Object was removed, false otherwise
     */
    public boolean remove(Object obj){
        //ignore if its not a MenuItem
        if(obj instanceof MenuItem) {
            //Make sure the item exists.
            MenuItem item = (MenuItem) obj;
            if(items.contains(item)) {
                items.remove(item);
                //Since something was removed, update the price
                updateTotal();
                return true;
            }
        }
        return false;
    }

    /**
     Helper method which traverses the arraylist and calculates the subtotal price of the items.
     */
    private void updateTotal() {
        //reset the prize
        totalPrice = 0;
        //If there are no items, leave the price at 0.
        if(items.size() == 0) {
            return;
        }

        //traverse the list
        for(int i = 0; i < items.size(); i++) {
            totalPrice += items.get(i).getItemPrice() * items.get(i).getItemQuantity();
        }

    }

    /**
     Getter method which returns the total price of the items in the items arraylist.
     @return totalPrice of the items in the items arraylist
     */
    public double getTotal() {
        return totalPrice;
    }

    /**
     Getter method which returns the items arraylist.
     @return items arraylist containing MenuItem objects
     */
    public ArrayList<MenuItem> getItems() {
        return items;
    }

    /**
     Getter method which returns the order number of an instance Order.
     @return orderNumber unique identifier for an instance of Order.
     */
    public int getOrderNumber() {
        return orderNumber;
    }

}

