package ALP.KBEPrice.Model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Representation of a product of a guitar
 */
public class Product implements Serializable {

    private String name;
    private List<Component> components = new LinkedList<>();

    public Product(String name,
            Component... components) {
        this.name = name;
        for (Component comp : components) {
            this.components.add(comp);
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public String toString() {
        String s = "";
        for (Component component : components) {
            s = s + "<-| ";
            s = s + component.toString();
            s = s + " |->";
        }
        return s;
    }
}