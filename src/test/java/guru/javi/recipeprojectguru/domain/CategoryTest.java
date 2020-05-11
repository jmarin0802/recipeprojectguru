package guru.javi.recipeprojectguru.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/*
 *Created for jalemaov on 09-05-2020
 */
public class CategoryTest {
	
	Category category;

	@Before
	public void setUp(){
		category=new Category();
	}

	@Test
	public void testGetId() throws Exception{
		Long idvalue = 4L;
		category.setId(idvalue);
		assertEquals(idvalue, category.getId()); 
	}

	@Test
	public void testGetDescription() throws Exception{
	}

	@Test
	public void testGetRecipes() throws Exception{
	}

}
