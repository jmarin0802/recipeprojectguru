package guru.javi.recipeprojectguru.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import guru.javi.recipeprojectguru.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

	Optional<UnitOfMeasure> findByUom(String uom);
}
