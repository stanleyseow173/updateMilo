package sg.nus.edu.iss.updateMilo.model;

import jakarta.json.JsonObject;

public class Item {
    private String name;
    private String price;
    private String username;
    private String id;
    private String dt;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDt() {
        return dt;
    }
    public void setDt(String dt) {
        this.dt = dt;
    }

    public static Item createJson(JsonObject j){
        Item c = new Item();
        c.name = j.getString("name");
        c.price = j.getJsonNumber("price").toString();
        c.username = j.getString("username");
        c.id = j.getString("id");
        c.dt = j.getString("dt");
        return c;
    }
    
}
