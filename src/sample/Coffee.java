package sample;

public class Coffee extends MenuItem implements Customizable{
    private String coffeeType;

    /*Notes / Brainstorm
        Maybe create a Count variable to store number of addins and multiply by 0.2 and add to total price
        OR store each addin as a boolean and do a total price calculation based on that info
     */

    public Coffee(String coffeeType){
        this.coffeeType = coffeeType;
        super.setItemPrice(itemPrice());
    }

    //Maybe used to add addins
    public boolean add(Object obj){
        return false;
    }
    //Maybe used to remove addins
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

    public double getCoffeePrice(){
        return super.getItemPrice();
    }
}
