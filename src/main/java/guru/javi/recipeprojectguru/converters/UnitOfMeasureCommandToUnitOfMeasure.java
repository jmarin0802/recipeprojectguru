package guru.javi.recipeprojectguru.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sun.istack.Nullable;

import guru.javi.recipeprojectguru.commands.UnitOfMeasureCommand;
import guru.javi.recipeprojectguru.domain.UnitOfMeasure;

/*
 *Created for jalemaov on 21-05-2020
 */
@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

	@Nullable
	@Override
	public UnitOfMeasure convert(UnitOfMeasureCommand source) {
		if (source == null) return null;
		UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
		unitOfMeasure.setId(source.getId());
		unitOfMeasure.setUom(source.getUom());
		return unitOfMeasure;
	}

}
