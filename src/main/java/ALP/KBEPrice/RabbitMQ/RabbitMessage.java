package ALP.KBEPrice.RabbitMQ;

import java.io.Serializable;

public class RabbitMessage implements Serializable {
    private String type;
    private String value;
    private Serializable serializableValue;

    public RabbitMessage(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public RabbitMessage(String type, Serializable value) {
        this.type = type;
        this.serializableValue = value;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
    
    public Serializable getSerializableValue() {
        return serializableValue;
    }

}
