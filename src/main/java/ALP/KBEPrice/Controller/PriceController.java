package ALP.KBEPrice.Controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ALP.RabbitMessage;
import ALP.KBEPrice.Model.Component;
import ALP.KBEPrice.Model.Product;
import ALP.KBEPrice.RabbitMQ.RabbitMQSender;

import static java.lang.Thread.sleep;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

@RestController
public class PriceController {
    
    private static String returnMessage;
    @Autowired
    private RabbitTemplate template;
    @Autowired
    private RabbitMQSender rabbitMQSender;

    // @PostMapping("/price")
    // public String calculatePrice(@RequestBody Product product) {
    //     returnMessage = null;
    //     System.out.println("sending message");
    //     rabbitMQSender.send(new RabbitMessage("getComponents", product));
    //     System.out.println("message sent");
    //     while (returnMessage == null) {
    //         try {
    //             System.out.println("und wir warten");
    //             sleep(10);
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     }
    //     String rMessageCopy = returnMessage;
    //     return rMessageCopy;
    // }

    public static void handle(String string) {
        returnMessage = string;
    }
    
    public void handle(Serializable serializable) {
        boolean b1 = serializable instanceof LinkedHashMap;
        boolean b2 = serializable instanceof ArrayList;
        System.out.println("lhm: " + b1);
        System.out.println("al: " + b2);
        if (serializable instanceof LinkedHashMap) {
            LinkedHashMap<?, ?> linkedHashMap = (LinkedHashMap<?, ?>) serializable;
            // deserialising:
            Product product = new Product((String) linkedHashMap.get("name"));
            if(linkedHashMap.get("components") instanceof LinkedHashMap){
                LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>> liHaMa = (LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>>) linkedHashMap
                        .get("components");
                for (String keyString : liHaMa.keySet()) {
                    ArrayList<LinkedHashMap<String, String>> components = (ArrayList<LinkedHashMap<String, String>>) linkedHashMap
                            .get(liHaMa.get(keyString));
                    List<Component> comps = new LinkedList<>();
                    for (LinkedHashMap<String, String> lhm : components) {
                        Component comp = new Component();
                        lhm.forEach((k, v) -> comp.set(k, v));
                        comps.add(comp);
                    }
                    product.setComponents(comps);
                    getPrice(product);
                    //send back
                }
            }
            else if(linkedHashMap.get("components") instanceof ArrayList){
                ArrayList<LinkedHashMap<String, String>> components = (ArrayList<LinkedHashMap<String, String>>) linkedHashMap
                            .get("components");
                    List<Component> comps = new LinkedList<>();
                    for (LinkedHashMap<String, String> lhm : components) {
                        Component comp = new Component();
                        lhm.forEach((k, v) -> comp.set(k, v));
                        comps.add(comp);
                    }
                    product.setComponents(comps);
                    getPrice(product);
                    //send back
                }
                System.out.println(product.toString());
            }

            System.out.println("done");
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
