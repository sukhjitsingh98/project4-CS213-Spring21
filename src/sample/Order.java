package sample;

import java.awt.*;
import java.util.ArrayList;

public class Order implements Customizable{
    private int orderNumber;
    private ArrayList<MenuItem> items = new ArrayList<>();
    private double totalPrice;

    public Order(int orderNumber){
        this.orderNumber = orderNumber;
    }
    /*Notes / Brainstorming
        maybe similar to company class in previous project.
        We can create an arraylist of MenuItem instances and manipulate the array by adding or removing from it
     */

    //need to check if that item already exits before adding? What happens if someone orders something and then orders that thing again? Find and increase the count?
    public boolean add(Object obj){
        //Only add if its a menuItem, donut and coffee.
        if(obj instanceof MenuItem) {
            MenuItem item = (MenuItem)obj;
            items.add(item);
            //Since something was added, update the price
            return true;
        }

        return false;
    }

    public boolean remove(Object obj){
        //ignore if its not a MenuItem
        if(obj instanceof MenuItem) {
            //Make sure it exists.
            MenuItem item = (MenuItem) obj;
            if(items.contains(item)) {
                items.remove(item);
                //Since something was removed, update the price
                return true;
            }
        }
        return false;
    }

}

