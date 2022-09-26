package ALP.KBEPrice.Controller;

import org.springframework.web.bind.annotation.*;

import ALP.KBEPrice.Model.Component;
import ALP.KBEPrice.Model.Product;

@RestController
public class PriceController {

    public static Product setPrice(Product product) {
        Product p = new Product();
        p.setAdditionalInfo(product.getAdditionalInfo());
        p.setName(product.getName());
        p.setComponents(product.getComponents());
        p.setPrice(getPrice(product));
        return p;
    }

    public static float getPrice(Product product) {
        float price = 0.0f;
        for (Component component : product.getComponents()) {
            price += Float.parseFloat(component.getPreis());
        }
        price = Math.round(price * 100);
        price = price / 100;
        return price;
    }
}
