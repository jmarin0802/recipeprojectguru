package guru.javi.recipeprojectguru.commands;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {
	private Long id;
	private String description;
	private Set<RecipeCommand> recipes = new HashSet<RecipeCommand>();
}
