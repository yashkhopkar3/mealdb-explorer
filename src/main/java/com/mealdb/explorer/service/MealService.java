package com.mealdb.explorer.service;

import java.util.Map;

public interface MealService {
    Map<String, Object> getMealByName(String name);
    Map<String, Object> getMealById(String id);
    Map<String, Object> getRandomMeal();
    Map<String, Object> getCategories();
    Map<String, Object> getMealsByCategory(String category);
}
