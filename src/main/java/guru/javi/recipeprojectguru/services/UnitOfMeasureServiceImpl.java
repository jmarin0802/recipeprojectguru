package guru.javi.recipeprojectguru.services;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import guru.javi.recipeprojectguru.Repositories.UnitOfMeasureRepository;
import guru.javi.recipeprojectguru.commands.UnitOfMeasureCommand;
import guru.javi.recipeprojectguru.converters.UnitOfMeasureToUnitOfMeasureCommand;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

	public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository,
			UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
	}

	@Override
	public Set<UnitOfMeasureCommand> listFindAll() {
		return StreamSupport.stream(unitOfMeasureRepository.findAll().spliterator(), false).map(unitOfMeasureToUnitOfMeasureCommand::convert).collect(Collectors.toSet());
	}

}
