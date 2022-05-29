package ALP.KBEPrice;

public class Calculator {
    public static float getPrice(Product product) {
        float price = 0.0f;
        for(Component component : product.getComponents().values()){
            if(component != null){
                price += Float.parseFloat(component.getPreis());
            }
        }
        price = Math.round(price * 100);
        price = price / 100;
        return price;

    }
}
