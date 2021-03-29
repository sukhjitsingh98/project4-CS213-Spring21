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

    //need to check if that item already exits before adding? What happens if someone orders something and then orders that same type again? Find and increase the count?
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

    public boolean remove(Object obj){
        //ignore if its not a MenuItem
        if(obj instanceof MenuItem) {
            //Make sure it exists.
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

    //traverse the list sum the prices
    public void updateTotal() {
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

    public double getTotal() {
        return totalPrice;
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

//    //test
//    public static void main(String[] args) {
//        Donut yeastDonut = new Donut("Yeast Donut",1);
//        Order newOrder = new Order(1);
//        newOrder.add(yeastDonut);
//        System.out.println(newOrder.getTotal());
//        newOrder.remove(yeastDonut);
//        Donut donutHole = new Donut("Donut Hole",3);
//        newOrder.add(donutHole);
//        System.out.println(newOrder.getTotal());
//        newOrder.add(yeastDonut);
//        newOrder.add(yeastDonut);
//        System.out.println(newOrder.getTotal());
//    }

}

