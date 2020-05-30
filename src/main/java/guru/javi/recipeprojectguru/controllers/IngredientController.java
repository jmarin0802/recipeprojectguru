package guru.javi.recipeprojectguru.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.javi.recipeprojectguru.services.RecipeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IngredientController {

	private RecipeService recipeService;

	public IngredientController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@GetMapping
	@RequestMapping("/recipe/{id}/ingredients")
	public String IngredientsList(@PathVariable String id, Model model) {
		log.debug("Getting ingredient list of recipe id: "+id);
		model.addAttribute("recipe",recipeService.findByCommandId(Long.valueOf(id)));
		return "recipe/ingredients/list";
	}
	
}
