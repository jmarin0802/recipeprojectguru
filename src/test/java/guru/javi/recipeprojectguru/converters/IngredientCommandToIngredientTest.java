package guru.javi.recipeprojectguru.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.javi.recipeprojectguru.commands.IngredientCommand;
import guru.javi.recipeprojectguru.domain.Ingredient;

class IngredientCommandToIngredientTest {

	IngredientCommandToIngredient convert;
	@BeforeEach
	void setUp() throws Exception {
		convert = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
	}
	
	@Test
	void isNull() {
		IngredientCommand ingredientCommand = null;
		convert.convert(ingredientCommand);
		assertNull(ingredientCommand);
	}

	@Test
	void testConvert() {
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(ValueTest.id);
		ingredientCommand.setDescription(ValueTest.description);
		ingredientCommand.setAmount(ValueTest.amount);
		
		Ingredient ingredient = convert.convert(ingredientCommand);
		assertNotNull(ingredient);
		assertEquals(ValueTest.id, ingredient.getId());
		assertEquals(ValueTest.description, ingredient.getDescription());
		assertEquals(ValueTest.amount, ingredient.getAmount());
	}

}
