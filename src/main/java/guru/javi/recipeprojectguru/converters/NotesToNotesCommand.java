package guru.javi.recipeprojectguru.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sun.istack.Nullable;

import guru.javi.recipeprojectguru.commands.NotesCommand;
import guru.javi.recipeprojectguru.domain.Notes;

/*
 *Created for jalemaov on 21-05-2020
 */
@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

	@Nullable
	@Override
	public NotesCommand convert(Notes source) {
		if (source == null) return null;
		
		NotesCommand notesCommand = new NotesCommand();
		notesCommand.setId(source.getId());
		notesCommand.setNotes(source.getNotes());
		
		return notesCommand;
	}

}
