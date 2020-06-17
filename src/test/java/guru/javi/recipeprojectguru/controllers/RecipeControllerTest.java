package guru.javi.recipeprojectguru.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.javi.recipeprojectguru.Repositories.RecipeRepository;
import guru.javi.recipeprojectguru.commands.RecipeCommand;
import guru.javi.recipeprojectguru.domain.Recipe;
import guru.javi.recipeprojectguru.exceptions.NotFoundException;
import guru.javi.recipeprojectguru.services.RecipeService;

/*
 *Created for jalemaov on 19-05-2020
 */
class RecipeControllerTest {

	@Mock
	RecipeService recipeService;
	
	@Mock
	RecipeRepository recipeRepository;
	
	RecipeController recipeController;
	MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeController = new RecipeController(recipeService);
		mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
	}

	@Test
	void testGetRecipe() throws Exception{
		Recipe recipe = new Recipe();
		recipe.setId(1L);
				
		when(recipeService.findById(anyLong())).thenReturn(recipe);
		
		mockMvc.perform(get("/recipe/1/show/"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/show"));
	}
	
	@Test
	void testUpdateRecipe() throws Exception{
		RecipeCommand recipe = new RecipeCommand();
		recipe.setId(1L);
				
		when(recipeService.findByCommandId(anyLong())).thenReturn(recipe);
		
		mockMvc.perform(get("/recipe/1/update"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/recipeform"))
				.andExpect(model().attributeExists("recipe"));
	}
	
	@Test
	void TestNewRecipe() throws Exception {		
		mockMvc.perform(get("/recipe/new"))
		.andExpect(status().isOk())
		.andExpect(view().name("recipe/recipeform"))
		.andExpect(model().attributeExists("recipe"));
	}
	
	@Test
	void testSavedOrUpdated() throws Exception{
		RecipeCommand command = new RecipeCommand();
		command.setId(3L);
		
		when(recipeService.savedRecipe(any())).thenReturn(command);
		mockMvc.perform(post("/recipe")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", "")
				.param("description", "some string"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/recipe/3/show"));
	}

	@Test
	void deletebyid() throws Exception {
		mockMvc.perform(get("/recipe/1/delete"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/"));
		
		verify(recipeService, times(1)).deleteById(anyLong());
	}
	
    @Test
    public void testGetRecipeNotFound() throws Exception {

        when(recipeService.findById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404NotFound"));
    }
    
    @Test
    public void testGetRecipeNumberFormatException() throws Exception {

        //when(recipeService.findById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/recipe/asdf/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }

}
