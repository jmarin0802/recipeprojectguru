package guru.javi.recipeprojectguru.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import guru.javi.recipeprojectguru.Repositories.RecipeRepository;
import guru.javi.recipeprojectguru.domain.Recipe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> getRecipes() {
		log.debug("I'm in the service");
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}

}
