package guru.javi.recipeprojectguru.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.javi.recipeprojectguru.commands.IngredientCommand;
import guru.javi.recipeprojectguru.commands.RecipeCommand;
import guru.javi.recipeprojectguru.services.IngredientService;
import guru.javi.recipeprojectguru.services.RecipeService;

class IngredientControllerTest {

	@Mock
	RecipeService recipeService;
	@Mock
	IngredientService ingredientService;
	IngredientController IngredientController;
	MockMvc mockmvc;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		IngredientController = new IngredientController(recipeService, ingredientService, null);
		mockmvc = MockMvcBuilders.standaloneSetup(IngredientController).build();
	}

	@Test
	void testIngredientsList() throws Exception {
		RecipeCommand recipeCommand = new RecipeCommand();
		when(recipeService.findByCommandId(anyLong())).thenReturn(recipeCommand);

		mockmvc.perform(get("/recipe/1/ingredients")).andExpect(status().isOk())
				.andExpect(view().name("recipe/ingredients/list")).andExpect(model().attributeExists("recipe"));

		verify(recipeService, times(1)).findByCommandId(anyLong());
	}

	@Test
	public void testShowIngredient() throws Exception {
		// given
		IngredientCommand ingredientCommand = new IngredientCommand();

		// when
		when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

		// then
		mockmvc.perform(get("/recipe/1/ingredients/2/show"))
				.andExpect(status().isOk())
				.andExpect(view().name("/recipe/ingredients/show"))
				.andExpect(model().attributeExists("ingredient"));
	}

}
