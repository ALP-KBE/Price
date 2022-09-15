package ALP.KBEPrice.Controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ALP.KBEPrice.Model.Component;
import ALP.KBEPrice.Model.Product;
import ALP.KBEPrice.RabbitMQ.RabbitMQSender;
import ALP.KBEPrice.RabbitMQ.RabbitMessage;

import static java.lang.Thread.sleep;

import java.io.Serializable;

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
    
    public void handle(Serializable prod) {
        Product product = (Product) prod;
        float total = getPrice(product);
        rabbitMQSender.send(new RabbitMessage("type", total));
    }

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
