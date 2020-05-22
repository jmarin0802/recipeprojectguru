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
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

	private final NotesCommandToNotes notesCommandToNotes;
	private final CategoryCommandToCategory categoryCommandToCategory;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;
	
	public RecipeCommandToRecipe(NotesCommandToNotes notesCommandToNotes,
			CategoryCommandToCategory categoryCommandToCategory,
			IngredientCommandToIngredient ingredientCommandToIngredient) {
		this.notesCommandToNotes = notesCommandToNotes;
		this.categoryCommandToCategory = categoryCommandToCategory;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
	}
	
	@Synchronized
	@Nullable
	@Override
	public Recipe convert(RecipeCommand source) {
		if (source == null) return null;
		
		Recipe recipe = new Recipe();
		recipe.setId(source.getId());
		recipe.setDescription(source.getDescription());
		recipe.setCookTime(source.getCookTime());
		recipe.setDifficulty(source.getDifficulty());
		recipe.setDirections(source.getDirections());
		recipe.setImage(source.getImage());
		recipe.setPrepTime(source.getPrepTime());
		recipe.setServings(source.getServings());
		recipe.setSource(source.getSource());
		recipe.setUrl(source.getUrl());
        recipe.setNotes(notesCommandToNotes.convert(source.getNotes()));

        if (source.getCategories() != null && source.getCategories().size() > 0){
            source.getCategories()
                    .forEach( category -> recipe.getCategories().add(categoryCommandToCategory.convert(category)));
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0){
            source.getIngredients()
                    .forEach(ingredient -> recipe.getIngredients().add(ingredientCommandToIngredient.convert(ingredient)));
        }		
        return recipe;
	}

}
