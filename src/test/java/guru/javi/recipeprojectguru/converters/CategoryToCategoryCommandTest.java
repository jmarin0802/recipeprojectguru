package guru.javi.recipeprojectguru.converters;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.javi.recipeprojectguru.commands.CategoryCommand;
import guru.javi.recipeprojectguru.domain.Category;

class CategoryToCategoryCommandTest {

	CategoryToCategoryCommand convert;

	@BeforeEach
	void setUp() throws Exception {
		convert = new CategoryToCategoryCommand();
	}
	
	@Test
	void isNull() {
		Category category = null;
		convert.convert(category);
		assertNull(category);
	}


	@Test
	void testConvert() {
		Category category = new Category();
		category.setId(ValueTest.id);
		category.setDescription(ValueTest.description);
		CategoryCommand categoryCommand = convert.convert(category);
		
		assertNotNull(categoryCommand);
		assertEquals(ValueTest.id, categoryCommand.getId());
		assertEquals(ValueTest.description, categoryCommand.getDescription());
		
	}

}
