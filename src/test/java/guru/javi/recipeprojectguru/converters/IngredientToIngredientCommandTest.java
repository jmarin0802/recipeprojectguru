package guru.javi.recipeprojectguru.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.javi.recipeprojectguru.commands.IngredientCommand;
import guru.javi.recipeprojectguru.domain.Ingredient;

class IngredientToIngredientCommandTest {

	IngredientToIngredientCommand convert;
	
	@BeforeEach
	void setUp() throws Exception {
		convert = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
	}
	
	@Test
	void isNull() {
		Ingredient ingredient = null;
		convert.convert(ingredient);
		assertNull(ingredient);
	}

	@Test
	void testConvert() {
		Ingredient ingredient = new Ingredient();
		ingredient.setId(ValueTest.id);
		ingredient.setDescription(ValueTest.description);
		ingredient.setAmount(ValueTest.amount);
		IngredientCommand ingredientCommand = convert.convert(ingredient);
		
		assertNotNull(ingredientCommand);
		assertEquals(ValueTest.id, ingredientCommand.getId());
		assertEquals(ValueTest.description, ingredientCommand.getDescription());
		assertEquals(ValueTest.amount, ingredientCommand.getAmount());
	}

}
