package com.mealdb.explorer.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.mealdb.explorer.service.MealService;

@Controller
@RequestMapping("/")
public class MealController {

    @Autowired
    private MealService mealService;

    // Home page: search, random meal, categories
    @GetMapping
    public String home(Model model, @RequestParam(required = false) String search,
                       @RequestParam(required = false) Boolean random) {

        // Load categories once
        Map<String, Object> categories = mealService.getCategories();
        model.addAttribute("categories", categories.getOrDefault("categories", Collections.emptyList()));

        // Random meal if requested
        if (random != null && random) {
            Map<String, Object> randomMeal = mealService.getRandomMeal();
            model.addAttribute("randomMeal", randomMeal.getOrDefault("meals", Collections.emptyList()));
        }

        // Search meals
        if (search != null && !search.isEmpty()) {
            Map<String, Object> result = mealService.getMealByName(search);
            List<Map<String,Object>> meals = (List<Map<String,Object>>) result.getOrDefault("meals", Collections.emptyList());
            model.addAttribute("meals", meals);

            if (meals.isEmpty()) {
                model.addAttribute("errorMessage", "No meals found for your search.");
            }
        }

        return "index";
    }

    @GetMapping("/random")
    public String randomMeal(Model model) {
        Map<String, Object> random = mealService.getRandomMeal();
        model.addAttribute("randomMeal", random.getOrDefault("meals", Collections.emptyList()));

        // load categories also for navigation
        Map<String, Object> categories = mealService.getCategories();
        model.addAttribute("categories", categories.getOrDefault("categories", Collections.emptyList()));

        return "index";
    }

    // Meal details page
    @GetMapping("/meal/{id}")
    public String mealDetails(@PathVariable String id, Model model) {
        Map<String, Object> result = mealService.getMealById(id);
        List<Map<String, Object>> meals = (List<Map<String, Object>>) result.getOrDefault("meals", Collections.emptyList());
        if (!meals.isEmpty()) {
            model.addAttribute("meal", meals.get(0));
        }
        return "mealDetails";
    }

    // Meals by category page
    @GetMapping("/category/{category}")
    public String mealsByCategory(@PathVariable String category, Model model) {
        Map<String, Object> result = mealService.getMealsByCategory(category);
        model.addAttribute("meals", result.getOrDefault("meals", Collections.emptyList()));
        model.addAttribute("categoryName", category);

        // Also pass categories for navigation
        Map<String, Object> categories = mealService.getCategories();
        model.addAttribute("categories", categories.getOrDefault("categories", Collections.emptyList()));

        return "index";
    }
}
