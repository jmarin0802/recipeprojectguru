package guru.javi.recipeprojectguru.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.javi.recipeprojectguru.services.RecipeService;
import guru.javi.recipeprojectguru.services.UnitOfMeasureService;

@Controller
public class UnitOfMeasureController {

	private final UnitOfMeasureService unitOfMeasureService;
	private final RecipeService recipeService;

	public UnitOfMeasureController(UnitOfMeasureService unitOfMeasureService, RecipeService recipeService) {
		this.unitOfMeasureService = unitOfMeasureService;
		this.recipeService = recipeService;
	}

	@GetMapping
	@RequestMapping("/recipe/{recipeid}/ingredients/{ingredientid}/unitOfMeasure/list")
	public String UnitOfMeasureList(@PathVariable String recipeid, @PathVariable String ingredientid, Model model) {
		model.addAttribute("recipe", recipeService.findById(Long.valueOf(recipeid)));
		model.addAttribute("unitOfMeasure", unitOfMeasureService.listFindAll());
		return "/recipe/ingredients/unitOfMeasure/list";
	}
}
