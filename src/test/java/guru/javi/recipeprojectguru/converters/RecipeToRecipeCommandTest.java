package guru.javi.recipeprojectguru.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.javi.recipeprojectguru.commands.CategoryCommand;
import guru.javi.recipeprojectguru.commands.IngredientCommand;
import guru.javi.recipeprojectguru.commands.NotesCommand;
import guru.javi.recipeprojectguru.commands.RecipeCommand;
import guru.javi.recipeprojectguru.domain.Category;
import guru.javi.recipeprojectguru.domain.Ingredient;
import guru.javi.recipeprojectguru.domain.Notes;
import guru.javi.recipeprojectguru.domain.Recipe;
import guru.javi.recipeprojectguru.enump.Difficulty;

class RecipeToRecipeCommandTest {

	RecipeToRecipeCommand convert;
	
	@BeforeEach
	void setUp() throws Exception {
		convert = new RecipeToRecipeCommand(new NotesToNotesCommand(), new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()), new CategoryToCategoryCommand());
	}

	@Test
	void isNull() {
		Recipe recipe = null;
		convert.convert(recipe);
		assertNull(recipe);
	}
	
	@Test
	void testConvert() {
		Recipe recipe = new Recipe();
		
		recipe.setId(ValueTest.id);
		recipe.setCookTime(ValueTest.cookTime);
		recipe.setDescription(ValueTest.description);
		recipe.setDifficulty(Difficulty.EASY);
		recipe.setDirections(ValueTest.directions);
		recipe.setPrepTime(ValueTest.prepTime);
		recipe.setServings(ValueTest.servings);
		recipe.setSource(ValueTest.source);
		recipe.setUrl(ValueTest.url);
		
		Notes notes = new Notes();
		notes.setId(ValueTest.id);
		recipe.setNotes(notes);
		
		Category category1 = new Category();
		category1.setId(ValueTest.id);
		Category category2 = new Category();
		category2.setId(ValueTest.id2);
		recipe.getCategories().add(category1);
		recipe.getCategories().add(category2);
		
		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(ValueTest.id);
		Ingredient ingredient2 = new Ingredient();
		ingredient2.setId(ValueTest.id2);
		recipe.getIngredients().add(ingredient1);
		recipe.getIngredients().add(ingredient2);
		
		RecipeCommand recipeCommand = convert.convert(recipe);
		
		assertNotNull(recipeCommand);
		assertNotNull(recipeCommand.getCategories());
		assertNotNull(recipeCommand.getNotes());
		assertNotNull(recipeCommand.getIngredients());
		assertEquals(ValueTest.id, recipeCommand.getId());
		assertEquals(ValueTest.cookTime, recipeCommand.getCookTime());
		assertEquals(ValueTest.description, recipeCommand.getDescription());
		assertEquals(Difficulty.EASY, recipeCommand.getDifficulty());
		assertEquals(ValueTest.prepTime, recipeCommand.getPrepTime());
		assertEquals(ValueTest.servings, recipeCommand.getServings());
		assertEquals(ValueTest.source, recipeCommand.getSource());
		assertEquals(ValueTest.url, recipeCommand.getUrl());
		assertEquals(ValueTest.id, recipeCommand.getNotes().getId());
		assertEquals(2, recipeCommand.getCategories().size());
		assertEquals(2, recipeCommand.getIngredients().size());	}

}
