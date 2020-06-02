package guru.javi.recipeprojectguru.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import guru.javi.recipeprojectguru.Repositories.RecipeRepository;
import guru.javi.recipeprojectguru.Repositories.UnitOfMeasureRepository;
import guru.javi.recipeprojectguru.commands.IngredientCommand;
import guru.javi.recipeprojectguru.converters.IngredientCommandToIngredient;
import guru.javi.recipeprojectguru.converters.IngredientToIngredientCommand;
import guru.javi.recipeprojectguru.domain.Ingredient;
import guru.javi.recipeprojectguru.domain.Recipe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
	
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;
	
	public IngredientServiceImpl(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository,
			IngredientToIngredientCommand ingredientToIngredientCommand,
			IngredientCommandToIngredient indIngredientCommandToIngredient) {
		super();
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.ingredientCommandToIngredient = indIngredientCommandToIngredient;
	}

	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId){
		
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		if(!recipeOptional.isPresent()) {
			log.error("recipe id not found id: " +recipeId);
		}
		
		Recipe recipe = recipeOptional.get();
		
		Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId))
				 .map( ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();
		
		if(!ingredientCommandOptional.isPresent()) {
			log.error("Ingredient id not found: "+ ingredientId);
		}
		
		return ingredientCommandOptional.get();
	}

	@Override
	@Transactional
	public IngredientCommand savedIngredientCommand(IngredientCommand ingredientCommand) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientCommand.getRecipeId());
		if(!recipeOptional.isPresent()) {
			log.error("recipe not found id: " +ingredientCommand.getRecipeId());
			return new IngredientCommand();
		}else {
			Recipe recipe = recipeOptional.get();
			Optional<Ingredient> ingredientOptional = recipe.getIngredients()
					.stream()
					.filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
					.findFirst();
			if(ingredientOptional.isPresent()) {
				Ingredient ingredientFound = ingredientOptional.get();
				ingredientFound.setDescription(ingredientCommand.getDescription());
				ingredientFound.setAmount(ingredientCommand.getAmount());
				ingredientFound.setUnitOfMeasure(unitOfMeasureRepository.findById(ingredientCommand.getUnitOfMeasure().getId()).orElseThrow(() -> new RuntimeException("UOM not found")));
			}else {
				recipe.addIngredients(ingredientCommandToIngredient.convert(ingredientCommand));
			}
			Recipe savedRecipe = recipeRepository.save(recipe);
			return ingredientToIngredientCommand.convert(savedRecipe.getIngredients().stream()
					.filter(recipeIngredients -> recipeIngredients.getId().equals(ingredientCommand.getId()))
					.findFirst().get());
		}
	}
	
	
}
