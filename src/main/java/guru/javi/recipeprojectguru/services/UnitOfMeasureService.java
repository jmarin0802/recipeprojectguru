package guru.javi.recipeprojectguru.services;

import java.util.Set;

import guru.javi.recipeprojectguru.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {

	Set<UnitOfMeasureCommand> listFindAll();
}
