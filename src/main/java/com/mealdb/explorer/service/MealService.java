package com.mealdb.explorer.service;

import java.util.Collections;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MealService {

    private final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private final RestTemplate restTemplate;

    public MealService() {
        // Configure RestTemplate with timeouts
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000); // 3 seconds
        factory.setReadTimeout(5000);    // 5 seconds
        this.restTemplate = new RestTemplate(factory);
    }

    @Cacheable(value = "mealsByName", key = "#name")
    public Map<String, Object> getMealByName(String name) {
        String url = BASE_URL + "search.php?s=" + name;
        return fetch(url, "meal by name");
    }

    @Cacheable(value = "mealById", key = "#id")
    public Map<String, Object> getMealById(String id) {
        String url = BASE_URL + "lookup.php?i=" + id;
        return fetch(url, "meal by ID");
    }

    @Cacheable("randomMeal")
    public Map<String, Object> getRandomMeal() {
        String url = BASE_URL + "random.php";
        return fetch(url, "random meal");
    }

    @Cacheable("categories")
    public Map<String, Object> getCategories() {
        String url = BASE_URL + "categories.php";
        return fetch(url, "categories");
    }

    @Cacheable(value = "mealsByCategory", key = "#category")
    public Map<String, Object> getMealsByCategory(String category) {
        String url = BASE_URL + "filter.php?c=" + category;
        return fetch(url, "meals by category");
    }

    private Map<String, Object> fetch(String url, String context) {
        try {
            Map<String, Object> result = restTemplate.getForObject(url, Map.class);
            return result != null ? result : Collections.emptyMap();
        } catch (Exception e) {
            System.out.println("Error fetching " + context + ": " + e.getMessage());
            return Collections.emptyMap();
        }
    }
}
