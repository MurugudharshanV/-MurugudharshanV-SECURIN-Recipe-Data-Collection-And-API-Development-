package com.recipe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.recipe.model.Recipe;
import com.recipe.repository.RecipeRepository;

@Service
public class recipeService {
	
	@Autowired
	RecipeRepository recipeRepository;

	public List<Recipe> getAllStudents(int page, int limit) {
		// TODO Auto-generated method stub
		  Pageable pageable = PageRequest.of(page, limit);

		    Page<Recipe> recipePage = recipeRepository.findAll(pageable);

		    return recipePage.getContent(); // returns List<Recipe>
	}

			 public List<Recipe> searchRecipes(
			            Integer calories,
			            String title,
			            String cuisine,
			            Integer totalTime,
			            Double rating,
			            int page,
			            int limit) {

			        Pageable pageable = PageRequest.of(page, limit);
			        return recipeRepository
			                .searchRecipes(calories, title, cuisine, totalTime, rating, pageable)
			                .getContent();
			    }

}

