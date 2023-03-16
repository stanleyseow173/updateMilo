package sg.nus.edu.iss.updateMilo.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import sg.nus.edu.iss.updateMilo.model.Quotation;

@Service
public class MiloService {

    
    public Optional<Quotation> getQuotation(String railwayURL)
            throws IOException {
        String finalUrl = UriComponentsBuilder
                .fromUriString(railwayURL)
                .toUriString();
        RestTemplate template = new RestTemplate();
        // ResponseEntity<String> r  = template.getForEntity(finalUrl, 
        //         String.class);
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        // headers.set("X-RapidAPI-Key", loveCalcApiKey);
        // headers.set("X-RapidAPI-Host", loveCalcApiHost);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> resp = template.exchange(finalUrl, HttpMethod.GET,
                requestEntity, String.class);
        Quotation q = Quotation.create(resp.getBody());
        // LoverResult w = LoverResult.create(resp.getBody());
        // if (w != null) {
        //     redisTemplate.opsForValue().set(w.getId(), resp.getBody().toString());
        //     return Optional.of(w);
        // }

        return Optional.of(q);
    }
}
