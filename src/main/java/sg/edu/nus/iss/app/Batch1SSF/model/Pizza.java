package sg.edu.nus.iss.app.Batch1SSF.model;

import java.io.Serializable;

import jakarta.json.JsonObject;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Pizza implements Serializable {
    
    @NotNull(message="Size cannot be empty")
    private String size;

    @Min(value=1, message="Must order at least 1")
    @Max(value=10, message="Can only order up to 10")
    private int quantity;

    @NotNull(message="Please select a pizza type")
    private String selection;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    @Override
    public String toString() {
        return "Pizza [size=" + size + ", quantity=" + quantity + ", selection=" + selection + "]";
    }

    public static Pizza create(JsonObject o) {
        Pizza p = new Pizza();
        p.setSelection(o.getString("selection"));
        p.setSize(o.getString("size"));
        p.setQuantity(o.getInt("quantity"));
        return p;
    }
}
