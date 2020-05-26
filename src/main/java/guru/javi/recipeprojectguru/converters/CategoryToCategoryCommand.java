package guru.javi.recipeprojectguru.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import guru.javi.recipeprojectguru.commands.CategoryCommand;
import guru.javi.recipeprojectguru.domain.Category;

/*
 *Created for jalemaov on 21-05-2020
 */
@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

	@Override
	public CategoryCommand convert(Category source) {
		if(source == null) return null;
		final CategoryCommand categoryCommand = new CategoryCommand();
		categoryCommand.setId(source.getId());
		categoryCommand.setDescription(source.getDescription());
		return categoryCommand;
	}

}