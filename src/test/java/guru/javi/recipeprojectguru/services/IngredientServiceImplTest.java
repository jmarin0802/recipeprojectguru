package guru.javi.recipeprojectguru.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.javi.recipeprojectguru.Repositories.RecipeRepository;
import guru.javi.recipeprojectguru.commands.IngredientCommand;
import guru.javi.recipeprojectguru.converters.IngredientToIngredientCommand;
import guru.javi.recipeprojectguru.domain.Ingredient;
import guru.javi.recipeprojectguru.domain.Recipe;

class IngredientServiceImplTest {

	@Mock
	RecipeRepository recipeRepository;
	@Mock
	IngredientToIngredientCommand ingredientToIngredientCommand;
	IngredientService ingredientService;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		ingredientService = new IngredientServiceImpl(recipeRepository,null, ingredientToIngredientCommand, null);
	}

	@Test
	void testFindByRecipeIdAndIngredientId()  throws Exception{
		//given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(1L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredients(ingredient1);
        recipe.addIngredients(ingredient2);
        recipe.addIngredients(ingredient3);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //then
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        //when
        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
	}

}
