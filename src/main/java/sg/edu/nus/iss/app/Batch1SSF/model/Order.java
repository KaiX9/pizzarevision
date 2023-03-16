package sg.edu.nus.iss.app.Batch1SSF.model;

import java.io.Serializable;
import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Order implements Serializable {
    
    private float totalCost = -1;
    private String orderId;
    private Pizza pizza;
    private Delivery delivery;

    public Order (Pizza pizza, Delivery d) {
        this.pizza = pizza;
        this.delivery = d;
    }
    
    public float getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Pizza getPizza() {
        return pizza;
    }
    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
    public Delivery getDelivery() {
        return delivery;
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public float getPizzaCost() {
        return this.getDelivery().isRush() ? this.getTotalCost() - 2 : this.getTotalCost();
    }

    @Override
    public String toString() {
        return "Order [totalCost=" + totalCost + ", orderId=" + orderId + ", pizza=" + pizza + ", delivery=" + delivery
                + "]";
    }

    public static JsonObject toJSON(String json) {
        JsonReader r = Json.createReader(new StringReader(json));
        return r.readObject();
    }

    public static Order create(String jsonStr) {
        JsonObject o = toJSON(jsonStr);
        Pizza p = Pizza.create(o);
        Delivery d = Delivery.create(o);
        Order ord = new Order(p, d);
        ord.setOrderId(o.getString("orderId"));
        ord.setTotalCost((float)o.getJsonNumber("total").doubleValue());
        return ord;
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
            .add("orderId", this.getOrderId())
            .add("name", this.getDelivery().getName())
            .add("address", this.getDelivery().getAddress())
            .add("phoneNum", this.getDelivery().getPhoneNum())
            .add("rush", this.getDelivery().isRush())
            .add("comments", this.getDelivery().getComments())
            .add("selection", this.getPizza().getSelection())
            .add("size", this.getPizza().getSize())
            .add("quantity", this.getPizza().getQuantity())
            .add("total", this.getTotalCost())
            .build();
    }
    
}
