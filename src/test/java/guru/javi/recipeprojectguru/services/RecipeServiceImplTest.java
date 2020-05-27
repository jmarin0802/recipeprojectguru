package guru.javi.recipeprojectguru.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.javi.recipeprojectguru.Repositories.RecipeRepository;
import guru.javi.recipeprojectguru.commands.CategoryCommand;
import guru.javi.recipeprojectguru.commands.IngredientCommand;
import guru.javi.recipeprojectguru.commands.NotesCommand;
import guru.javi.recipeprojectguru.commands.RecipeCommand;
import guru.javi.recipeprojectguru.converters.RecipeCommandToRecipe;
import guru.javi.recipeprojectguru.converters.RecipeToRecipeCommand;
import guru.javi.recipeprojectguru.converters.ValueTest;
import guru.javi.recipeprojectguru.domain.Recipe;
import guru.javi.recipeprojectguru.enump.Difficulty;

class RecipeServiceImplTest {

	@Mock
	RecipeRepository recipeRepository;
	@Mock
	RecipeCommandToRecipe recipeCommandToRecipe;
	@Mock
	RecipeToRecipeCommand recipeToRecipeCommand;
	RecipeServiceImpl recipeServiceImpl;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeServiceImpl = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
	}

	@Test
	public void testGetRecipes() {
		Recipe recipe = new Recipe();
		HashSet recipeData = new HashSet();
		recipeData.add(recipe);
		
		when(recipeRepository.findAll()).thenReturn(recipeData);
		Set<Recipe> recipes = recipeServiceImpl.getRecipes();
		
		assertEquals(recipes.size(), 1);
		verify(recipeRepository, times(3)).findAll();
	}	

@Test
	public void findById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
		
        Recipe recipeReturned = recipeServiceImpl.findById(1L);

        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
	}

	@Test
	void savedRecipe() {
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
		
		RecipeCommand detachedRecipeCommand =  recipeServiceImpl.savedRecipe(recipeCommand);
		
		assertNotNull(detachedRecipeCommand);
		assertNotNull(detachedRecipeCommand.getCategories());
		assertNotNull(detachedRecipeCommand.getNotes());
		assertNotNull(detachedRecipeCommand.getIngredients());
		assertEquals(ValueTest.id, detachedRecipeCommand.getId());
		assertEquals(ValueTest.cookTime, detachedRecipeCommand.getCookTime());
		assertEquals(ValueTest.description, detachedRecipeCommand.getDescription());
		assertEquals(Difficulty.EASY, detachedRecipeCommand.getDifficulty());
		assertEquals(ValueTest.prepTime, detachedRecipeCommand.getPrepTime());
		assertEquals(ValueTest.servings, detachedRecipeCommand.getServings());
		assertEquals(ValueTest.source, detachedRecipeCommand.getSource());
		assertEquals(ValueTest.url, detachedRecipeCommand.getUrl());
		assertEquals(ValueTest.id, detachedRecipeCommand.getNotes().getId());
		assertEquals(2, detachedRecipeCommand.getCategories().size());
		assertEquals(2, detachedRecipeCommand.getIngredients().size());
	}

}
