package guru.javi.recipeprojectguru.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.javi.recipeprojectguru.services.RecipeService;

@Controller
public class IndexController {
	
	RecipeService recipeService;
	
	public IndexController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@RequestMapping({"","/","index","index.html"})
	public String getIndex(Model model) {
		model.addAttribute("recipes", recipeService.getRecipes());
		return "index";
	}
}
