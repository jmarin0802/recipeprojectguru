package guru.javi.recipeprojectguru.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.javi.recipeprojectguru.Repositories.RecipeRepository;
import guru.javi.recipeprojectguru.Repositories.UnitOfMeasureRepository;
import guru.javi.recipeprojectguru.commands.IngredientCommand;
import guru.javi.recipeprojectguru.converters.IngredientCommandToIngredient;
import guru.javi.recipeprojectguru.converters.IngredientToIngredientCommand;
import guru.javi.recipeprojectguru.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.javi.recipeprojectguru.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.javi.recipeprojectguru.domain.Ingredient;
import guru.javi.recipeprojectguru.domain.Recipe;

public class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientService ingredientService;
    
    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }
	
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(recipeRepository, unitOfMeasureRepository, ingredientToIngredientCommand, ingredientCommandToIngredient);
    }

	@Test
	public void testFindByRecipeIdAndIngredientId()  throws Exception{
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
