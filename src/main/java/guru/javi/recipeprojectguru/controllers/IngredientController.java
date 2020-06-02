package guru.javi.recipeprojectguru.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.javi.recipeprojectguru.commands.IngredientCommand;
import guru.javi.recipeprojectguru.services.IngredientService;
import guru.javi.recipeprojectguru.services.RecipeService;
import guru.javi.recipeprojectguru.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IngredientController {

	private RecipeService recipeService;
	private IngredientService ingredientService;
	private UnitOfMeasureService unitOfMeasureService;

	public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
		this.unitOfMeasureService = unitOfMeasureService;
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
	}
	
	@GetMapping
	@RequestMapping("/recipe/{id}/ingredients")
	public String IngredientsList(@PathVariable String id, Model model) {
		log.debug("Getting ingredient list of recipe id: "+id);
		model.addAttribute("recipe",recipeService.findByCommandId(Long.valueOf(id)));
		return "recipe/ingredients/list";
	}
	
	@GetMapping
	@RequestMapping("/recipe/{id}/ingredients/{ingredientId}/show")
	public String showIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
		model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
		return "/recipe/ingredients/show";
	}
	
	@GetMapping
	@RequestMapping("/recipe/{recipeId}/ingredients/{ingredientId}/update")
	public String updataRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
		model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
		model.addAttribute("unitOfMeasureList", unitOfMeasureService.listFindAll());
		return "/recipe/ingredients/ingredientform";
	}
	
	@PostMapping("/recipe/{recipeId}/ingredient")
	public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand) {
		IngredientCommand savedCommand = ingredientService.savedIngredientCommand(ingredientCommand);
		
		log.debug("saved recipe id: " + savedCommand.getRecipeId());
		log.debug("saved ingredient id: "+ savedCommand.getId());
		
		return "redirect:/recipe/"+savedCommand.getRecipeId()+"/ingredient/"+savedCommand.getId()+"/show";
	}
	
}
