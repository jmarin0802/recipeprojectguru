package guru.javi.recipeprojectguru.services;

import java.util.Set;

import guru.javi.recipeprojectguru.domain.Recipe;

public interface RecipeService{

	Set<Recipe> getRecipes();
	
	Recipe findById(Long l);
}
