package sample;

import java.util.ArrayList;

public class Coffee extends MenuItem implements Customizable{

    private String coffeeType;
    private ArrayList<String> addInsList = new ArrayList<String>();

    public Coffee(String coffeeType, int coffeeQuantity){
        super(coffeeQuantity);
        this.coffeeType = coffeeType;
        super.setItemPrice(itemPrice());
    }

    //Used to add an add in into the arrayList. Assume only one of each type of add in is allowed.
    public boolean add(Object obj){
        if(obj instanceof String){
            //Cast object into string
            String addIn = (String) obj;

            //Check if the add in is valid
            if(isValidAddIn(addIn)){
                addInsList.add(addIn);
                super.setItemPrice((itemPrice()));
                return true;
            }
        }
        return false;
    }

    //Used to remove an add in from the arrayList. Assume only one of each type of add in is allowed.
    public boolean remove(Object obj){
        if(obj instanceof String){
            //Cast object into string
            String addIn = (String) obj;

            //Check if the add in is valid
            if(isValidAddIn(addIn)){
                addInsList.remove(addIn);
                super.setItemPrice((itemPrice()));
                return true;
            }
        }
        return false;
    }

    //Sum of basic coffee plus add ins. Include tax?
    public double itemPrice(){
        double sum = coffeeSizePrice()*super.getItemQuantity() + Constants.COFFEE_ADD_IN * addInsList.size();
        return sum;
    }

    private double coffeeSizePrice(){
        if(coffeeType.equals("Short")){
            return Constants.SHORT_BLACK_COFFEE;
        }
        else if(coffeeType.equals("Tall")){
            return Constants.TALL_BLACK_COFFEE;
        }
        else if(coffeeType.equals("Grande")){
            return Constants.GRANDE_BLACK_COFFEE;
        }
        else if(coffeeType.equals("Venti")){
            return Constants.VENTI_BLACK_COFFEE;
        }
        else return 0;
    }

    private boolean isValidAddIn(String addIn){
        if(addIn.equals("cream") || addIn.equals("syrup") || addIn.equals("milk") || addIn.equals("caramel") || addIn.equals("whipped cream")){
            return true;
        }
        return false;
    }

    public double getCoffeePrice(){
        return super.getItemPrice();
    }
}
