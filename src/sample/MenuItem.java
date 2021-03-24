package sample;

public class MenuItem {
    private double itemPrice;
    private int itemQuantity;

    //Constructor
    public MenuItem (int itemQuantity){
        this.itemQuantity = itemQuantity;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }
}
