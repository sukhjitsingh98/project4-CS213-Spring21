package sample;

import java.util.ArrayList;

/**
 The Order class defines the abstract StoreOrders type which contains the array consisting of Order class objects.
 Contains constructors to generate StoreOrders objects.
 The class allows for Order objects to be removed, added, return the order number of an individual Order object, and
 return the number of orders in the orders arraylist.]

 @author German Munguia, Sukhjit Singh
 */
public class StoreOrders implements Customizable{

    private ArrayList<Order> orders = new ArrayList<>();

    /**
     Default constructor used to generate a StoreOrders object
     */
    public StoreOrders(){
    }

    /**
     Add the given Object into the orders arraylist.
     First checks if the given Object is an instance of Order, if so it is casted and added to the orders arraylist.
     @param obj the Object to be added to the orders arraylist
     @return true if the Object was added, false otherwise
     */
    public boolean add(Object obj){
        if(obj instanceof Order) {
            Order order = (Order) obj;
            orders.add(order);
            return true;
        }
        return false;
    }

    /**
     Remove the given Object from the orders arraylist.
     First checks if the given Object is an instance of Order, if so it is casted and used to find the item to be
     removed from the orders arraylist. If the arraylist contains the Object, it is removed.
     @param obj the Object to be removed from the orders arraylist
     @return true if the Object was removed, false otherwise
     */
    public boolean remove(Object obj){
        if(obj instanceof Order) {
            //Make sure it exists.
            Order order = (Order) obj;
            if(orders.contains(order)) {
                orders.remove(order);
                return true;
            }
        }
        return false;
    }

    /**
     Getter method which returns an individual Order from the orders arraylist.
     @return orders.get(i) an individual order from the orders arraylist
     @param i the order number that is asked for.
     */
    public Order getOrder(int i){
        return orders.get(i);
    }

    /**
     Getter method which returns the number of Order objects in the orders arraylist.
     @return orders.size() the number of items in the orders arraylist
     */
    public int getNumOrders(){
        return orders.size();
    }

}
