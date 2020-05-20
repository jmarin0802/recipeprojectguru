package guru.javi.recipeprojectguru.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.javi.recipeprojectguru.services.RecipeService;

/*
 *Created for jalemaov on 19-05-2020
 */
@Controller
public class RecipeController {

	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@RequestMapping("/recipe/show/{id}")
	public String showById(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
		/*
		 * // model.addAttribute("categories",
		 * recipeService.findById(Long.valueOf(id)).getCategories()); //
		 * model.addAttribute("ingredients",
		 * recipeService.findById(Long.valueOf(id)).getIngredients()); //
		 * model.addAttribute("directions",
		 * recipeService.findById(Long.valueOf(id)).getDirections()); //
		 * model.addAttribute("notes",
		 * recipeService.findById(Long.valueOf(id)).getNotes());
		 */		
		return "recipe/show";
	}
}
