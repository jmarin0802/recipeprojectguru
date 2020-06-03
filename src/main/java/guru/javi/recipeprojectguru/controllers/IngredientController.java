package guru.javi.recipeprojectguru.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import guru.javi.recipeprojectguru.commands.IngredientCommand;
import guru.javi.recipeprojectguru.commands.RecipeCommand;
import guru.javi.recipeprojectguru.commands.UnitOfMeasureCommand;
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
	
	@GetMapping("/recipe/{id}/ingredients")
	public String IngredientsList(@PathVariable String id, Model model) {
		log.debug("Getting ingredient list of recipe id: "+id);
		model.addAttribute("recipe",recipeService.findByCommandId(Long.valueOf(id)));
		return "recipe/ingredients/list";
	}
	
	@GetMapping("/recipe/{id}/ingredients/{ingredientId}/show")
	public String showIngredient(@PathVariable String id, @PathVariable String ingredientId, Model model) {
		model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(id), Long.valueOf(ingredientId)));
		return "/recipe/ingredients/show";
	}
	
	@GetMapping("/recipe/{recipeId}/ingredients/{ingredientId}/update")
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

		return "redirect:/recipe/"+savedCommand.getRecipeId()+"/ingredients/"+savedCommand.getId()+"/show";
	}
	
    @GetMapping("recipe/{recipeId}/ingredients/new")
    public String newRecipe(@PathVariable String recipeId, Model model){

    	log.debug("I'm ingredientcontroller newRecipe - recipeid:"+recipeId);
        //make sure we have a good id value
        //RecipeCommand recipeCommand = recipeService.findByCommandId(Long.valueOf(recipeId));
        //todo raise exception if null

        //need to return back parent id for hidden form property
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        

        //init uom
        ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());
        model.addAttribute("ingredient", ingredientCommand);
        model.addAttribute("unitOfMeasureList",  unitOfMeasureService.listFindAll());

        return "/recipe/ingredients/ingredientform";
    }
    
	@GetMapping("/recipe/{recipeId}/ingredients/{ingredientId}/delete")
	public String deleteById(@PathVariable String recipeId, @PathVariable String ingredientId) {
		log.debug("Deleting ingredientId: "+ingredientId);
		ingredientService.deleteById(Long.valueOf(recipeId),Long.valueOf(ingredientId));
		return "redirect:/recipe/"+recipeId+"/ingredients";
	}
	
}
