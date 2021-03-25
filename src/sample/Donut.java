package sample;

import java.util.ArrayList;

//This class is not listed in the project description but might be needed since its use is mentioned
public class Donut extends MenuItem implements Customizable{
    private String donutType;
    private ArrayList<String> flavors = new ArrayList<String>();

    //Maybe include flavors as well
    public Donut(String donutType, int donutQuantity){
        super(donutQuantity);
        this.donutType = donutType;
        super.setItemPrice(itemPrice());
    }

    //Add flavors
    public boolean add(Object obj){
        //check for donut type
        //based on that check if the flavor matches that donut
        return false;
    }
    //Remove flavors
    public boolean remove(Object obj){
        return false;
    }

    //Maybe calculate with Tax?
    public double itemPrice(){
        if(donutType.equals("Yeast Donut")){
            return Constants.YEAST_DONUT_PRICE;
        }
        else if(donutType.equals("Cake Donut")){
            return Constants.CAKE_DONUT_PRICE;
        }
        else if(donutType.equals("Donut Hole")){
            return Constants.DONUT_HOLE_PRICE;
        }
        return 0;
    }

    public double getDonutPrice(){
        return super.getItemPrice();
    }
}
