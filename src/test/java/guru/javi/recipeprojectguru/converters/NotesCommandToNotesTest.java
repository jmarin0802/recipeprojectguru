package guru.javi.recipeprojectguru.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.javi.recipeprojectguru.commands.IngredientCommand;
import guru.javi.recipeprojectguru.commands.NotesCommand;
import guru.javi.recipeprojectguru.domain.Notes;

class NotesCommandToNotesTest {

	NotesCommandToNotes convert;
	
	@BeforeEach
	void setUp() throws Exception {
		convert = new NotesCommandToNotes();
	}
	
	@Test
	void isNull() {
		NotesCommand notes = null;
		convert.convert(notes);
		assertNull(notes);
	}

	@Test
	void testConvert() {
		NotesCommand notesCommand = new NotesCommand();
		notesCommand.setId(ValueTest.id);
		notesCommand.setNotes(ValueTest.description);
		Notes notes = convert.convert(notesCommand);
		
		assertNotNull(notes);
		assertEquals(ValueTest.id, notes.getId());
		assertEquals(ValueTest.description, notes.getNotes());
	}

}
