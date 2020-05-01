package guru.javi.recipeprojectguru.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import guru.javi.recipeprojectguru.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	Optional<Category> findByDescription(String description);
	
}
