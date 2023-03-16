package sg.edu.nus.iss.app.Batch1SSF.model;

import java.io.Serializable;

import jakarta.json.JsonObject;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Delivery implements Serializable {
    
    @NotNull(message="Name cannot be empty")
    @Size(min=3, message="Name has to be minimally 3 characters")
    private String name;

    @NotNull(message="Address cannot be empty")
    @NotEmpty(message="Address cannot be empty")
    private String address;

    @NotNull(message="Phone cannot be empty")
    @Pattern(regexp="^[0-9]{8,}$", message="Must be a valid phone number")
    private String phoneNum;

    private boolean rush = false;

    private String comments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public boolean isRush() {
        return rush;
    }

    public void setRush(boolean rush) {
        this.rush = rush;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Delivery [name=" + name + ", address=" + address + ", phoneNum=" + phoneNum + ", rush=" + rush
                + ", comments=" + comments + "]";
    }

    public static Delivery create(JsonObject o) {
        Delivery d = new Delivery();
        d.setName(o.getString("name"));
        d.setAddress(o.getString("address"));
        d.setPhoneNum(o.getString("phoneNum"));
        d.setRush(o.getBoolean("rush"));
        d.setComments(o.getString("comments"));
        return d;
    }
    
}
