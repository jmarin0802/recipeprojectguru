package guru.javi.recipeprojectguru.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.javi.recipeprojectguru.commands.UnitOfMeasureCommand;
import guru.javi.recipeprojectguru.domain.UnitOfMeasure;

class UnitOfMeasureToUnitOfMeasureCommandTest {

	UnitOfMeasureToUnitOfMeasureCommand convert;
	
	@BeforeEach
	void setUp() throws Exception {
		convert = new UnitOfMeasureToUnitOfMeasureCommand();
	}
	
	void isNull() {
		UnitOfMeasure unitOfMeasure = null;
		convert.convert(unitOfMeasure);
		assertNull(unitOfMeasure);
	}

	@Test
	void testConvert() {
		UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
		unitOfMeasure.setId(ValueTest.id);
		unitOfMeasure.setUom(ValueTest.description);
		UnitOfMeasureCommand unitOfMeasureCommand = convert.convert(unitOfMeasure);
		
		assertNotNull(unitOfMeasureCommand);
		assertEquals(ValueTest.id, unitOfMeasureCommand.getId());
		assertEquals(ValueTest.description, unitOfMeasureCommand.getUom());
	}

}
