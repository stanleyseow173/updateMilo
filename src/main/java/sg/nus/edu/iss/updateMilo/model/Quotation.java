package sg.nus.edu.iss.updateMilo.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Quotation {
    private String quotation_id;
    private List<Item> items;


    public String getQuotation_id() {
        return quotation_id;
    }
    public void setQuotation_id(String quotation_id) {
        this.quotation_id = quotation_id;
    }
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static Quotation create(String json) throws IOException{
        Quotation q = new Quotation();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            q.setQuotation_id(o.getString("quotation_id"));
            q.items = o.getJsonArray("items").stream()
                .map(v-> (JsonObject)v)
                .map(v-> Item.createJson(v))
                .toList();
        }

        return q;
    }
}
