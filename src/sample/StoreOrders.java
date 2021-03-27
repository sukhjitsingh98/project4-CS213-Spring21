package sample;

import java.util.ArrayList;

public class StoreOrders implements Customizable{

    private ArrayList<Order> orders = new ArrayList<>();

    //When MainMenu will open, a default first order will be generated which the user will add items to.
    //Later on the user will be ale to submit this order, after which a new order will be generated
    public StoreOrders(){
        //Order firstOrder = new Order(Constants.FIRST_ORDER);
        //orders.add(firstOrder);
    }

    public boolean add(Object obj){
        if(obj instanceof Order) {
            Order order = (Order) obj;
            orders.add(order);
            return true;
        }
        return false;
    }

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

    public Order getOrder(int i){
        return orders.get(i);
    }

    public int getNumOrders(){
        return orders.size();
    }
}
