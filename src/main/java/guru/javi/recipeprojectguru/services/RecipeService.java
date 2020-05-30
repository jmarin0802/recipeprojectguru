package guru.javi.recipeprojectguru.services;

import java.util.Set;

import guru.javi.recipeprojectguru.commands.RecipeCommand;
import guru.javi.recipeprojectguru.domain.Recipe;

public interface RecipeService{

	Set<Recipe> getRecipes();
	
	Recipe findById(Long l);
	
	RecipeCommand savedRecipe(RecipeCommand recipeCommand);
	
	RecipeCommand findByCommandId(Long id);
	
	void deleteById(Long id);
}
