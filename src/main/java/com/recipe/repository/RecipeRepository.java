package com.recipe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.recipe.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	  @Query("""
		        SELECT r FROM Recipe r
		        WHERE (:title IS NULL OR LOWER(r.title) LIKE LOWER(CONCAT('%', :title, '%')))
		          AND (:cuisine IS NULL OR LOWER(r.cuisine) = LOWER(:cuisine))
		          AND (:calories IS NULL OR r.calories = :calories)
		          AND (:totalTime IS NULL OR r.totalTime <= :totalTime)
		          AND (:rating IS NULL OR r.rating >= :rating)
		    """)
		    Page<Recipe> searchRecipes(
		            @Param("calories") Integer calories,
		            @Param("title") String title,
		            @Param("cuisine") String cuisine,
		            @Param("totalTime") Integer totalTime,
		            @Param("rating") Double rating,
		            Pageable pageable
		    );
}

