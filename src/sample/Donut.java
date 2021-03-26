package sample;

import java.util.ArrayList;

//This class is not listed in the project description but might be needed since its use is mentioned
public class Donut extends MenuItem implements Customizable{
    private String donutType;
    private ArrayList<String> flavors = new ArrayList<String>();

    public Donut(String donutType, int donutQuantity){
        super(donutQuantity);
        this.donutType = donutType;
        super.setItemPrice(itemPrice());
    }

    //Add flavors
    public boolean add(Object obj){
        if(obj instanceof String){
            //Cast object into string
            String flavor = (String) obj;

            //Check if the add in is valid
            if(isValidFlavor(flavor) && ! flavors.contains(flavor)){
                flavors.add(flavor);
                return true;
            }
        }
        return false;
    }

    //Remove flavors
    public boolean remove(Object obj){
        if(obj instanceof String){
            //Cast object into string
            String flavor = (String) obj;

            //Check if the add in is valid
            if(isValidFlavor(flavor) && flavors.contains(flavor)){
                flavors.remove(flavor);
                return true;
            }
        }
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

    public void setDonutQuanitity(int donutQuantity){
        super.setItemQuantity(donutQuantity);
    }

    private boolean isValidFlavor(String flavor) {
        if(donutType.equals("Yeast Donut")){
            if(flavor.equals("Vanilla") || flavor.equals("Strawberry") || flavor.equals("Chocolate")){
                return true;
            }
        }
        else if(donutType.equals("Cake Donut")){
            if(flavor.equals("Jelly") || flavor.equals("Old Fashioned") || flavor.equals("Chocolate")){
                return true;
            }
        }
        else if(donutType.equals("Donut Hole")){
            if(flavor.equals("Jelly Holes") || flavor.equals("Cinnamon Sugar Holes") || flavor.equals("Glazed Holes")){
                return true;
            }
        }
        return false;
    }
}
