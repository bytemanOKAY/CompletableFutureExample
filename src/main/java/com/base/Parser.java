package com.base;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static Map<String, Object> getMapForId(int id){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts/" + id, HttpMethod.GET, entity, String.class);
        Map<String, Object> result;
        try{
            if(id == 1) {
                Thread.sleep(2000);
            }
            return new ObjectMapper().readValue(response.getBody(), HashMap.class);
        } catch (IOException e){
            return null;
        } catch (InterruptedException e) {
            return null;
        }
    }

}
