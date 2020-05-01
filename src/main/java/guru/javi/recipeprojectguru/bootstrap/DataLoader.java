package guru.javi.recipeprojectguru.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.javi.recipeprojectguru.Repositories.CategoryRepository;
import guru.javi.recipeprojectguru.Repositories.RecipeRepository;
import guru.javi.recipeprojectguru.Repositories.UnitOfMeasureRepository;
import guru.javi.recipeprojectguru.domain.Category;
import guru.javi.recipeprojectguru.domain.Ingredient;
import guru.javi.recipeprojectguru.domain.Notes;
import guru.javi.recipeprojectguru.domain.Recipe;
import guru.javi.recipeprojectguru.domain.UnitOfMeasure;
import guru.javi.recipeprojectguru.enump.Difficulty;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent>{

	private final CategoryRepository categoryRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final RecipeRepository recipeRepository;
	
	public DataLoader(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.recipeRepository = recipeRepository;
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		recipeRepository.saveAll(executerecipe());		
	}

	private List<Recipe> executerecipe() {
		//Unit of Measure
		
		Optional<UnitOfMeasure> unitOptional = unitOfMeasureRepository.findByUom("Unit");
		if(!unitOptional.isPresent()) {
			throw new RuntimeException("Expected Unit of Measure not found");
		}
		UnitOfMeasure unitUOM = unitOptional.get();
		
		Optional<UnitOfMeasure> TeaspoonOptional = unitOfMeasureRepository.findByUom("Teaspoon");
		if(!TeaspoonOptional.isPresent()) {
			throw new RuntimeException("Expected Unit of Measure not found");
		}
		UnitOfMeasure teaspoonUOM = TeaspoonOptional.get();
		
		Optional<UnitOfMeasure> TablespoonOptional = unitOfMeasureRepository.findByUom("Tablespoon");
		if(!TablespoonOptional.isPresent()) {
			throw new RuntimeException("Expected Unit of Measure not found");
		}
		UnitOfMeasure tablespoonUOM = TablespoonOptional.get();

		//Categories
		
		Optional<Category> americanOptional = categoryRepository.findByDescription("American");
		if(!americanOptional.isPresent()) {
			throw new RuntimeException("Expected Category not found");
		}
		Category americanCategory = americanOptional.get();
		
		Optional<Category> mexicanOptional = categoryRepository.findByDescription("Mexican");
		if(!mexicanOptional.isPresent()) {
			throw new RuntimeException("Expected Category not found");
		}
		Category mexicanCategory = mexicanOptional.get();
		
		Optional<Category> fastFoodOptional = categoryRepository.findByDescription("Fast Food");
		if(!fastFoodOptional.isPresent()) {
			throw new RuntimeException("Expected Category not found");
		}

		Category fastFoodCategory = fastFoodOptional.get();
		
		//Ingredient of perfect guacamole
		
		String description = "The trick to making perfect guacamole is using ripe avocados that are just the right amount of ripeness. Not ripe enough and the avocado will be hard and tasteless. Too ripe and the taste will be off.";
		String directions = "1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd";
		
		Recipe perfectGuacamole = new Recipe(description, 10, 0, Difficulty.EASY, directions);
		
		Notes notes = new Notes();
		notes.setNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");
		perfectGuacamole.setNotes(notes);
		
		perfectGuacamole.addIngredients(new Ingredient("ripe avocados",new BigDecimal(2), unitUOM));
		perfectGuacamole.addIngredients(new Ingredient("salt, more to taste",new BigDecimal(1/4), teaspoonUOM));
		perfectGuacamole.addIngredients(new Ingredient("fresh lime juice or lemon juice",new BigDecimal(1), tablespoonUOM));
		
		perfectGuacamole.getCategories().add(americanCategory);
		perfectGuacamole.getCategories().add(mexicanCategory);		
		perfectGuacamole.getCategories().add(fastFoodCategory);
		
		List<Recipe> recipes= new ArrayList<>(1);
		
		recipes.add(perfectGuacamole);
		
		return recipes;
	}

}
