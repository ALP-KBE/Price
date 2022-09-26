package ALP.KBEPrice.RabbitMQ;

import java.util.LinkedList;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ALP.RabbitMessage;
import ALP.KBEPrice.Controller.PriceController;
import ALP.KBEPrice.Model.Product;

@Component
@RabbitListener(queues = "price-queue", id = "listener")
public class RabbitMQReceiver {
    @Autowired
    PriceController priceController;
    @Autowired
    RabbitMQSender sender;
    ObjectMapper objectMapper = new ObjectMapper();

    @RabbitHandler
    public void receiver(RabbitMessage message) throws JsonMappingException, JsonProcessingException {
        if (message.getType().equals("getPrice")) {
            Product product = objectMapper.readValue((String) message.getValue(),
                    new TypeReference<Product>() {
                    });
            product = PriceController.setPrice(product);
            RabbitMessage rabbitMessage = new RabbitMessage("product", objectMapper.writeValueAsString(product));
            sender.send(rabbitMessage);
        } else if (message.getType().equals("getPrices")) {
            System.out.println((String) message.getValue());
            List<Product> products = objectMapper.readValue((String) message.getValue(),
                    new TypeReference<List<Product>>() {
                    });
            List<Product> returnProducts = new LinkedList<>();
            for (Product product : products) {
                Product prod = PriceController.setPrice(product);
                returnProducts.add(prod);
            }
            RabbitMessage rabbitMessage = new RabbitMessage("product", "");
            if(returnProducts.size() == 1){
                rabbitMessage.setValue(returnProducts.get(0));
            }
            else {
                String returnString = "[";
                for (int i = 0; i < returnProducts.size(); i++) {
                    if(i == returnProducts.size() - 1){
                        returnString = returnString + returnProducts.get(i).toString() + "]";
                    }
                    else {
                        returnString = returnString + returnProducts.get(i).toString() + ",";
                    }
                }
                rabbitMessage.setValue(returnString);
            }
            sender.send(rabbitMessage);
    }
    }
}
