package sample;

public class MenuItem {
    private double itemPrice;
    private int itemQuantity;
    private String itemString;

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

    public void setItemString(String itemString){
        this.itemString = itemString;
    }

    public String getItemString() {
        return itemString;
    }
}
