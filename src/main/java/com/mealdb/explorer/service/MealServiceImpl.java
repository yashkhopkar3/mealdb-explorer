package com.mealdb.explorer.service;

import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MealServiceImpl implements MealService {

    private final WebClient webClient;

    public MealServiceImpl(WebClient mealDbWebClient) {
        this.webClient = mealDbWebClient;
    }

    @Override
    @Cacheable(value = "mealsByName", key = "#name")
    public Map<String, Object> getMealByName(String name) {
        return fetch("search.php?s=" + name);
    }

    @Override
    @Cacheable(value = "mealById", key = "#id")
    public Map<String, Object> getMealById(String id) {
        return fetch("lookup.php?i=" + id);
    }

    @Override
    @Cacheable("randomMeal")
    public Map<String, Object> getRandomMeal() {
        return fetch("random.php");
    }

    @Override
    @Cacheable("categories")
    public Map<String, Object> getCategories() {
        return fetch("categories.php");
    }

    @Override
    @Cacheable(value = "mealsByCategory", key = "#category")
    public Map<String, Object> getMealsByCategory(String category) {
        return fetch("filter.php?c=" + category);
    }

    /**
     * Generic fetch method using WebClient
     */
    private Map<String, Object> fetch(String endpoint) {
        try {
            return webClient.get()
                    .uri(endpoint)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .onErrorReturn(Map.of())
                    .block();
        } catch (Exception ex) {
            System.out.println("Error fetching data: " + ex.getMessage());
            return Map.of();
        }
    }
}
