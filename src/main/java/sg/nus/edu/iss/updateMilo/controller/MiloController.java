package sg.nus.edu.iss.updateMilo.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.nus.edu.iss.updateMilo.model.Item;
import sg.nus.edu.iss.updateMilo.model.Quotation;
import sg.nus.edu.iss.updateMilo.service.MiloService;

@Controller
public class MiloController {

    private static final String railwayURL = "https://quotation-production.up.railway.app/quotation";

    // @GetMapping(path="/posted")
    // public String getPosted(){
    //     return "index";
    // }
    @Autowired
    private MiloService miloSvc;

    @GetMapping(path={"/","/index.html"})
    public String getIndex(Model model) throws IOException{
        Item i = new Item();
        model.addAttribute("item", i);
        return "index";
    }

    @PostMapping(path={"/post"})
    public String updateMilo(String name, String price, Model model) throws IOException{
        JsonObject json = Json.createObjectBuilder()
            .add("name", name)
            .add("price", price)
            .add("username","stanley").build();
        RequestEntity<String> req = RequestEntity
            .post(railwayURL)
            .contentType(MediaType.APPLICATION_JSON)
            .body(json.toString(), String.class);
            //.headers("Accept", MediaType.APPLICATION_JSON)

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);
        Optional<Quotation> q = miloSvc.getQuotation(railwayURL);
        model.addAttribute("quotation", q.get());
        return "posted";
    }   

    @GetMapping(path={"/post"})
    public String getQuotation(Model model) throws IOException{
        Optional<Quotation> q = miloSvc.getQuotation(railwayURL);
        model.addAttribute("quotation", q.get());
        return "posted";
    }
}
