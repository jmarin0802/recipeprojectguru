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
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

	@Nullable
	@Override
	public Notes convert(NotesCommand source) {
		if (source == null) return null;
		
		Notes notes = new Notes();
		notes.setId(source.getId());
		notes.setNotes(source.getNotes());
		return notes;
	}

}
