package sample;

//This class is not listed in the project description but might be needed since its use is mentioned
public class Donut extends MenuItem implements Customizable{
    private String donutType;

    //Maybe include flavors as well
    public Donut(String donutType){
        this.donutType = donutType;
        super.setItemPrice(itemPrice());
    }

    //Not sure how this will be used
    public boolean add(Object obj){
        return false;
    }
    //Not sure how this will be used
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
