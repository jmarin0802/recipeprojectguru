package guru.javi.recipeprojectguru.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sun.istack.Nullable;

import guru.javi.recipeprojectguru.commands.CategoryCommand;
import guru.javi.recipeprojectguru.domain.Category;
import lombok.Synchronized;

/*
 *Created for jalemaov on 21-05-2020
 */
@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {
	
	@Nullable
	@Override
	public Category convert(CategoryCommand source) {
		if(source == null) return null;
		
		final Category category = new Category();
		category.setId(source.getId());
		category.setDescription(source.getDescription());
		return category;
	}

}
