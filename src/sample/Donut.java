package sample;

public class Donut extends MenuItem implements Customizable{

    private String donutType;
    private String flavor;

    public Donut(String donutType, int donutQuantity){
        super(donutQuantity);
        this.donutType = donutType;
        super.setItemPrice(itemPrice());
        super.setItemString(donutDataString());
    }

    //Add flavors
    public boolean add(Object obj){
        if(obj instanceof String){
            //Cast object into string
            String flavor = (String) obj;

            //Check if the add in is valid
            if(isValidFlavor(flavor)){
                this.flavor = flavor;
                super.setItemString(donutDataString());
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
            if(isValidFlavor(flavor) && !flavor.equals("")){
                this.flavor = "";
                super.setItemString(donutDataString());
                return true;
            }
        }
        return false;
    }

    public double itemPrice(){
        if(donutType.equals("Yeast Donut")){
            return Constants.YEAST_DONUT_PRICE * super.getItemQuantity();
        }
        else if(donutType.equals("Cake Donut")){
            return Constants.CAKE_DONUT_PRICE * super.getItemQuantity();
        }
        else if(donutType.equals("Donut Hole")){
            return Constants.DONUT_HOLE_PRICE * super.getItemQuantity();
        }
        return 0;
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

    public String getFlavor(){
        return flavor;
    }

    public double getDonutPrice() {
        return super.getItemPrice();
    }

    public String donutDataString(){
        String donutData = flavor + " " + donutType + ", Quantity: " + super.getItemQuantity();
        return donutData;
    }



}
