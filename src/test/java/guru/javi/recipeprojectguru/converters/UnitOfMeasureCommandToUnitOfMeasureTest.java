package guru.javi.recipeprojectguru.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.javi.recipeprojectguru.commands.NotesCommand;
import guru.javi.recipeprojectguru.commands.UnitOfMeasureCommand;
import guru.javi.recipeprojectguru.domain.UnitOfMeasure;

class UnitOfMeasureCommandToUnitOfMeasureTest {

	UnitOfMeasureCommandToUnitOfMeasure convert;
	
	@BeforeEach
	void setUp() throws Exception {
		convert = new UnitOfMeasureCommandToUnitOfMeasure();
	}
	
	@Test
	void isNull() {
		UnitOfMeasureCommand unitOfMeasureCommand = null;
		convert.convert(unitOfMeasureCommand);
		assertNull(unitOfMeasureCommand);
	}

	@Test
	void testConvert() {
		UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
		unitOfMeasureCommand.setId(ValueTest.id);
		unitOfMeasureCommand.setUom(ValueTest.description);
		UnitOfMeasure unitOfMeasure = convert.convert(unitOfMeasureCommand);
		
		assertNotNull(unitOfMeasure);
		assertEquals(ValueTest.id, unitOfMeasure.getId());
		assertEquals(ValueTest.description, unitOfMeasure.getUom());
	}

}
