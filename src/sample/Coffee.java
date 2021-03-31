package sample;

import java.util.ArrayList;

public class Coffee extends MenuItem implements Customizable{

    private String coffeeType;
    private ArrayList<String> addInsList = new ArrayList<String>();

    public Coffee(String coffeeType, int coffeeQuantity){
        super(coffeeQuantity);
        this.coffeeType = coffeeType;
        super.setItemPrice(itemPrice());
        super.setItemString(coffeeDataString());
    }

    //Used to add an add in into the arrayList. Assume only one of each type of add in is allowed.
    public boolean add(Object obj){
        if(obj instanceof String){
            //Cast object into string
            String addIn = (String) obj;

            //Check if the add in is valid
            if(isValidAddIn(addIn) && !addInsList.contains(addIn)){
                addInsList.add(addIn);
                super.setItemPrice((itemPrice()));
                super.setItemString(coffeeDataString());
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
            if(isValidAddIn(addIn) && addInsList.contains(addIn)){
                addInsList.remove(addIn);
                super.setItemPrice((itemPrice()));
                super.setItemString(coffeeDataString());
                return true;
            }
        }
        return false;
    }

    //Sum of basic coffee plus add ins. Include tax?
    public double itemPrice(){
        double sum = coffeeSizePrice() * super.getItemQuantity() + Constants.COFFEE_ADD_IN * addInsList.size();
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

    public void setCoffeeType(String coffeeType){
        this.coffeeType = coffeeType;
        super.setItemPrice(itemPrice());
        super.setItemString(coffeeDataString());
    }

    public void setCoffeeQuantity(int coffeeQuantity){
        super.setItemQuantity(coffeeQuantity);
        super.setItemPrice(itemPrice());
        super.setItemString(coffeeDataString());
    }

    //Having a string will be easier to display the data
    public String coffeeDataString(){
        String coffeeData =
                coffeeType + " Coffee, " + "Quantity: " + super.getItemQuantity() + ", Addins: " + addInsToString();
        return coffeeData;
    }

    private String addInsToString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : addInsList) {
            //Only add commas if there is more than one add in
            if(addInsList.size() > 1 && !s.equals(addInsList.get(0))){
                stringBuilder.append(", ");
            }
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }
}
