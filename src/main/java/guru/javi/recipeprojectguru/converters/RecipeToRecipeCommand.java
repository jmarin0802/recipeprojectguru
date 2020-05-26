package guru.javi.recipeprojectguru.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sun.istack.Nullable;

import guru.javi.recipeprojectguru.commands.RecipeCommand;
import guru.javi.recipeprojectguru.domain.Recipe;
import lombok.Synchronized;

/*
 *Created for jalemaov on 21-05-2020
 */
@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

	private final NotesToNotesCommand notesToNotesCommand;
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final CategoryToCategoryCommand categoryCommandToCategory;
	
	public RecipeToRecipeCommand(NotesToNotesCommand notesToNotesCommand,
			IngredientToIngredientCommand ingredientToIngredientCommand,
			CategoryToCategoryCommand categoryCommandToCategory) {
		this.notesToNotesCommand = notesToNotesCommand;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.categoryCommandToCategory = categoryCommandToCategory;
	}

	@Synchronized
	@Nullable
	@Override
	public RecipeCommand convert(Recipe source) {
		if (source == null) return null;
		
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(source.getId());
		recipeCommand.setCookTime(source.getCookTime());
		recipeCommand.setDescription(source.getDescription());
		recipeCommand.setDifficulty(source.getDifficulty());
		recipeCommand.setDirections(source.getDirections());
		recipeCommand.setImage(source.getImage());
		recipeCommand.setPrepTime(source.getPrepTime());
		recipeCommand.setServings(source.getServings());
		recipeCommand.setSource(source.getSource());
		recipeCommand.setUrl(source.getUrl());
		recipeCommand.setNotes(notesToNotesCommand.convert(source.getNotes()));
		
		if (source.getCategories() != null && source.getCategories().size() > 0) {
			source.getCategories()
			.forEach(category -> recipeCommand.getCategories().add(categoryCommandToCategory.convert(category)));
		}
		
		if (source.getIngredients() != null && source.getIngredients().size() > 0) {
			source.getIngredients()
			.forEach(ingredient -> recipeCommand.getIngredients().add(ingredientToIngredientCommand.convert(ingredient)));
		}
		
		return recipeCommand;
	}

}
