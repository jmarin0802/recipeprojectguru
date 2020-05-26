package guru.javi.recipeprojectguru.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.javi.recipeprojectguru.commands.IngredientCommand;
import guru.javi.recipeprojectguru.commands.NotesCommand;
import guru.javi.recipeprojectguru.domain.Notes;

class NotesToNotesCommandTest {
	
	NotesToNotesCommand convert;

	@BeforeEach
	void setUp() throws Exception {
		convert = new NotesToNotesCommand();
	}

	@Test
	void isNull() {
		Notes notes = null;
		convert.convert(notes);
		assertNull(notes);
	}
	
	@Test
	void testConvert() {
		Notes notes = new Notes();
		notes.setId(ValueTest.id);
		notes.setNotes(ValueTest.description);
		NotesCommand notesCommand = convert.convert(notes);
		
		assertNotNull(notesCommand);
		assertEquals(ValueTest.id, notesCommand.getId());
		assertEquals(ValueTest.description, notesCommand.getNotes());
	}

}
