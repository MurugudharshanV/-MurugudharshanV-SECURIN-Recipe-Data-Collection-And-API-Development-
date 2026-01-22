package com.recipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.model.Recipe;
import com.recipe.service.recipeService;

@RestController
public class recipeController {
	
	@Autowired
	recipeService recipeService;
	@GetMapping("/api/recipes")
	public List<Recipe> getAllStudents(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "1") int limit) {

	    return recipeService.getAllStudents(page, limit);
	}
	 @GetMapping("/api/recipes/search")
	    public List<Recipe> searchRecipes(
	            @RequestParam(required = false) Integer calories,
	            @RequestParam(required = false) String title,
	            @RequestParam(required = false) String cuisine,
	            @RequestParam(name = "total_time", required = false) Integer totalTime,
	            @RequestParam(required = false) Double rating,
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "10") int limit) {

	        return recipeService.searchRecipes(
	                calories,
	                title,
	                cuisine,
	                totalTime,
	                rating,
	                page,
	                limit
	        );
	    }
	}


