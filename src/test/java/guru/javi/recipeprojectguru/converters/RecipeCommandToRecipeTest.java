package guru.javi.recipeprojectguru.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.javi.recipeprojectguru.commands.CategoryCommand;
import guru.javi.recipeprojectguru.commands.IngredientCommand;
import guru.javi.recipeprojectguru.commands.NotesCommand;
import guru.javi.recipeprojectguru.commands.RecipeCommand;
import guru.javi.recipeprojectguru.domain.Recipe;
import guru.javi.recipeprojectguru.enump.Difficulty;

class RecipeCommandToRecipeTest {

	RecipeCommandToRecipe convert;
	
	@BeforeEach
	void setUp() throws Exception {
		convert = new RecipeCommandToRecipe(new NotesCommandToNotes(), 
				new CategoryCommandToCategory(), 
				new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()));
	}
	
	@Test
	void isNull() {
		RecipeCommand recipeCommand = null;
		convert.convert(recipeCommand);
		assertNull(recipeCommand);
	}

	@Test
	void testConvert() {
		RecipeCommand recipeCommand = new RecipeCommand();
		
		recipeCommand.setId(ValueTest.id);
		recipeCommand.setCookTime(ValueTest.cookTime);
		recipeCommand.setDescription(ValueTest.description);
		recipeCommand.setDifficulty(Difficulty.EASY);
		recipeCommand.setDirections(ValueTest.directions);
		recipeCommand.setPrepTime(ValueTest.prepTime);
		recipeCommand.setServings(ValueTest.servings);
		recipeCommand.setSource(ValueTest.source);
		recipeCommand.setUrl(ValueTest.url);
		
		NotesCommand notesCommand = new NotesCommand();
		notesCommand.setId(ValueTest.id);
		recipeCommand.setNotes(notesCommand);
		
		CategoryCommand categoryCommand1 = new CategoryCommand();
		categoryCommand1.setId(ValueTest.id);
		CategoryCommand categoryCommand2 = new CategoryCommand();
		categoryCommand2.setId(ValueTest.id2);
		recipeCommand.getCategories().add(categoryCommand1);
		recipeCommand.getCategories().add(categoryCommand2);
		
		IngredientCommand ingredientCommand1 = new IngredientCommand();
		ingredientCommand1.setId(ValueTest.id);
		IngredientCommand ingredientCommand2 = new IngredientCommand();
		ingredientCommand2.setId(ValueTest.id2);
		recipeCommand.getIngredients().add(ingredientCommand1);
		recipeCommand.getIngredients().add(ingredientCommand2);
		
		Recipe recipe = convert.convert(recipeCommand);
		
		assertNotNull(recipe);
		assertNotNull(recipe.getCategories());
		assertNotNull(recipe.getNotes());
		assertNotNull(recipe.getIngredients());
		assertEquals(ValueTest.id, recipe.getId());
		assertEquals(ValueTest.cookTime, recipe.getCookTime());
		assertEquals(ValueTest.description, recipe.getDescription());
		assertEquals(Difficulty.EASY, recipe.getDifficulty());
		assertEquals(ValueTest.prepTime, recipe.getPrepTime());
		assertEquals(ValueTest.servings, recipe.getServings());
		assertEquals(ValueTest.source, recipe.getSource());
		assertEquals(ValueTest.url, recipe.getUrl());
		assertEquals(ValueTest.id, recipe.getNotes().getId());
		assertEquals(2, recipe.getCategories().size());
		assertEquals(2, recipe.getIngredients().size());
	}

}
