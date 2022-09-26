package ALP.KBEPrice.Model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Representation of a product of a guitar
 */
public class Product implements Serializable {

    private String name;
    private String price;
    private String additionalInfo;
    private List<Component> components = new LinkedList<>();

    public Product(String name,
            Component... components) {
        this.name = name;
        for (Component comp : components) {
            this.components.add(comp);
        }
    }

    public Product(String name, String price, String additionalInfo, Component... components) {
        this.name = name;
        this.price = price;
        this.additionalInfo = additionalInfo;
        for (Component comp : components) {
            this.components.add(comp);
        }
    }

    public Product() {

    }

    public List<Component> getComponents() {
        return this.components;
    }

    public String getName() {
        return this.name;
    }

    public String getAdditionalInfo() {
        return this.additionalInfo;
    }

    public void addComponent(Component component) {
        this.components.add(component);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public void setPrice(float price) {
        this.price = String.valueOf(price);
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        String string = "{\"name\":\"" + name + "\",\"additionalInfo\":\"" + additionalInfo + "\",\"price\":\"" + price + "\",\"components\":[";
        for (int i = 0; i < components.size(); i++) {
            if(i == components.size() - 1){
                string = string + components.get(i).toString();
            }
            else {
                string = string + components.get(i).toString() + ",";
            }
        }
        return string = string + "]}";
    }
}
