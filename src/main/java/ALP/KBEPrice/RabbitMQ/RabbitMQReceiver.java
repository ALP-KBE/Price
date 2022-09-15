package ALP.KBEPrice.RabbitMQ;

import java.io.Serializable;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ALP.KBEPrice.Controller.PriceController;
import ALP.KBEPrice.Model.Product;

@Component
@RabbitListener(queues = "price-queue", id = "listener")
public class RabbitMQReceiver {
    @Autowired
    PriceController priceController;

    @RabbitHandler
    public void receiver(String string) {
        System.out.println("message angekommen " + string);
        PriceController.handle(string);
    }
    
    @RabbitHandler
    public void receiver(Serializable product) {
        System.out.println("message angekommen " + product.toString());
        priceController.handle(product);

    }
}
