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


	public IngredientServiceImpl(RecipeRepository recipeRepository,
			UnitOfMeasureRepository unitOfMeasureRepository,
			IngredientToIngredientCommand ingredientToIngredientCommand,
			IngredientCommandToIngredient ingredientCommandToIngredient) {
		this.recipeRepository = recipeRepository;

		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
	}

	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		if (!recipeOptional.isPresent()) {
			log.error("recipe id not found id: " + recipeId);
		}

		Recipe recipe = recipeOptional.get();

		Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId))
				.map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

		if (!ingredientCommandOptional.isPresent()) {
			log.error("Ingredient id not found: " + ingredientId);
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
			
			Optional<Ingredient> ingredientOptional = recipe
					.getIngredients()
					.stream()
					.filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
					.findFirst();
			
			if(ingredientOptional.isPresent()) {
				Ingredient ingredientFound = ingredientOptional.get();
				ingredientFound.setDescription(ingredientCommand.getDescription());
				ingredientFound.setAmount(ingredientCommand.getAmount());
				ingredientFound.setUnitOfMeasure(unitOfMeasureRepository
						.findById(ingredientCommand.getUnitOfMeasure().getId())
						.orElseThrow(() -> new RuntimeException("UOM not found")));
				
			}else {
				Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
                ingredient.setRecipe(recipe);
				recipe.addIngredients(ingredient);
			}
			Recipe savedRecipe = recipeRepository.save(recipe);
			Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
	                    .filter(recipeIngredients -> recipeIngredients.getId().equals(ingredientCommand.getId()))
	                    .findFirst();

	        if(!savedIngredientOptional.isPresent()){
	                savedIngredientOptional = savedRecipe.getIngredients().stream()
	                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(ingredientCommand.getDescription()))
	                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(ingredientCommand.getAmount()))
	                        .filter(recipeIngredients -> recipeIngredients.getUnitOfMeasure().getId().equals(ingredientCommand.getUnitOfMeasure().getId()))
	                        .findFirst();
	        }
	        //log.debug("I'm savedIngredientCommand ingredientserviceimpl - recipeid: "+ recipe.getId()+" ingredientid:"+);
	        return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
		}
		
	}

	@Override
	public void deleteById(Long recipeId, Long ingredientId) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		if(!recipeOptional.isPresent()) {
			log.error("Not found recipeid: "+recipeId);
		}else {
			Recipe recipe = recipeOptional.get();
			Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(ingredientId)).findFirst();
			if(ingredientOptional.isPresent()) {
				Ingredient ingredienteDelete = ingredientOptional.get();
				ingredienteDelete.setRecipe(null);
				recipe.getIngredients().remove(ingredientOptional.get());
				recipeRepository.save(recipe);
			}
		}
	}
}