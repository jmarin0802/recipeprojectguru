package guru.javi.recipeprojectguru.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import guru.javi.recipeprojectguru.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
