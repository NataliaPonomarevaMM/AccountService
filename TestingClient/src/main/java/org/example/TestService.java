package org.example;

import org.example.config.TestConfig;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.Random;

@Service
public class TestService {
    private static final String getAccountUrl = "http://localhost:8080/accounts/{id}";
    private static final String addAmountUrl = "http://localhost:8080/accounts";
    private final RestTemplate restTemplate = new RestTemplate();
    private final Random random = new Random();
    @Autowired
    private TestConfig config;

    @Async
    public void postRequest() {
        restTemplate.postForObject(addAmountUrl, createPostRequest(), String.class);
        System.out.println("POST Execute method asynchronously. " + Thread.currentThread().getName());
    }

    @Async
    public void getRequest() {
        restTemplate.getForEntity(getAccountUrl, String.class, Map.of("id", getRandomId()));
        System.out.println("GET Execute method asynchronously. " + Thread.currentThread().getName());
    }

    private HttpEntity<String> createPostRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject accountJsonObject = new JSONObject();
        try {
            accountJsonObject.put("id", getRandomId());
            accountJsonObject.put("amount", 5);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new HttpEntity<>(accountJsonObject.toString(), headers);
    }

    private int getRandomId() {
        return config.getIdMinBound() + random.nextInt(config.getIdMaxBound() - config.getIdMinBound());
    }
}
