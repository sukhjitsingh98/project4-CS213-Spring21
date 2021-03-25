package sample;

import java.util.ArrayList;

public class Coffee extends MenuItem implements Customizable{
    private String coffeeType;
    private ArrayList<String> addInsList = new ArrayList<String>();
    /*Notes / Brainstorm
        Maybe create a Count variable to store number of addins and multiply by 0.2 and add to total price
        OR store each addin as a boolean and do a total price calculation based on that info
     */

    public Coffee(String coffeeType, int coffeeQuantity){
        super(coffeeQuantity);
        this.coffeeType = coffeeType;
        super.setItemPrice(itemPrice());
    }

    //Used to add add ins into an array
    public boolean add(Object obj){
        if(obj instanceof String){
            //Cast object into string
            String addIn = (String) obj;

            //Check if the add in is valid
            if(isValidAddIn(addIn)){
                addInsList.add(addIn);
                return true;
            }
        }
        return false;
    }

    //Used to remove addins
    public boolean remove(Object obj){
        return false;
    }

    //Sum of basic coffee plus add ons
    public double itemPrice(){
        double sum = coffeeSizePrice();

        //This is brainstorming and could be implemented some other way
        //sum += Constants.COFFEE_ADD_IN
        return 0;
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
