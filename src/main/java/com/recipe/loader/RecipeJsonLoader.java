package com.recipe.loader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.model.Recipe;
import com.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.util.Iterator;


@Component
public class RecipeJsonLoader {

    @Autowired
    private RecipeRepository recipeRepository;

    @PostConstruct
    public void loadJsonToDatabase() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        File file = new ClassPathResource("US_recipes_null.json").getFile();

        JsonNode rootNode = mapper.readTree(file);
        Iterator<JsonNode> recipes = rootNode.elements();

        while (recipes.hasNext()) {

            JsonNode node = recipes.next();

            Recipe recipe = new Recipe();

            recipe.setTitle(node.get("title").asText());
            recipe.setCuisine(node.get("cuisine").asText());

            recipe.setRating(parseFloat(node.get("rating")));
            recipe.setPrepTime(parseInt(node.get("prep_time")));
            recipe.setCookTime(parseInt(node.get("cook_time")));
            recipe.setTotalTime(parseInt(node.get("total_time")));

            recipe.setDescription(node.get("description").asText());
            recipe.setServes(node.get("serves").asText());

            recipe.setIngredients(node.get("ingredients").toString());
            recipe.setInstructions(node.get("instructions").toString());
            recipe.setNutrients(node.get("nutrients").toString());

            recipeRepository.save(recipe);
        }

        System.out.println("JSON successfully inserted into database");
    }

    private Integer parseInt(JsonNode node) {
        if (node == null || node.isNull()
                || node.asText().equalsIgnoreCase("NaN"))
            return null;

        return node.asInt();
    }

    private Float parseFloat(JsonNode node) {
        if (node == null || node.isNull()
                || node.asText().equalsIgnoreCase("NaN"))
            return null;

        return Float.parseFloat(node.asText());
    }
}

