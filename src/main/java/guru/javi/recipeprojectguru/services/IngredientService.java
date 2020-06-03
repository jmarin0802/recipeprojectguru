package guru.javi.recipeprojectguru.services;

import guru.javi.recipeprojectguru.commands.IngredientCommand;

public interface IngredientService {

	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
	
	IngredientCommand savedIngredientCommand(IngredientCommand ingredientCommand);
	
	void deleteById(Long recipeId, Long ingredientId);
}
