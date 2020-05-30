package guru.javi.recipeprojectguru.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import guru.javi.recipeprojectguru.commands.RecipeCommand;
import guru.javi.recipeprojectguru.services.RecipeService;
import lombok.extern.slf4j.Slf4j;

/*
 *Created for jalemaov on 19-05-2020
 */
@Slf4j
@Controller
public class RecipeController {

	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@GetMapping
	@RequestMapping("/recipe/{id}/show")
	public String showById(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));	
		return "recipe/show";
	}
	
	@GetMapping
	@RequestMapping("/recipe/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findByCommandId(Long.valueOf(id)));
		return "recipe/recipeform";
	}
	
	@GetMapping
	@RequestMapping("/recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCommand());
		return "recipe/recipeform";
	}
	
	@PostMapping("recipe")
	public String SavedOrUpdate(@ModelAttribute RecipeCommand recipeCommand) {
		RecipeCommand savedCommand = recipeService.savedRecipe(recipeCommand);
		return "redirect:/recipe/"+savedCommand.getId()+"/show";
	}
	
	@GetMapping
	@RequestMapping("/recipe/{id}/delete")
	public String deleteById(@PathVariable String id) {
		log.debug("Deleting id: "+id);
		recipeService.deleteById(Long.valueOf(id));
		return "redirect:/";
	}
}
