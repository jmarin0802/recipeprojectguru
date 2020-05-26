package guru.javi.recipeprojectguru.converters;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.javi.recipeprojectguru.commands.CategoryCommand;
import guru.javi.recipeprojectguru.domain.Category;

class CategoryCommandToCategoryTest {

	CategoryCommandToCategory convert;
	@BeforeEach
	void setUp() throws Exception {
		convert = new CategoryCommandToCategory();
	}
	
	@Test
	void isNull() {
		CategoryCommand categoryCommand = null;
		convert.convert(categoryCommand);
		assertNull(categoryCommand);
	}

	@Test
	void testConvert() {
		CategoryCommand categoryCommand = new CategoryCommand();
		categoryCommand.setId(ValueTest.id);
		categoryCommand.setDescription(ValueTest.description);
		Category category = convert.convert(categoryCommand);
		
		assertNotNull(category);
		assertEquals(ValueTest.id, category.getId());
		assertEquals(ValueTest.description, category.getDescription());
	}

}
