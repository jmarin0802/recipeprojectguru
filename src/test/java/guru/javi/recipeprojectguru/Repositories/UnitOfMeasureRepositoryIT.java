package guru.javi.recipeprojectguru.Repositories;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import guru.javi.recipeprojectguru.domain.UnitOfMeasure;

/*
 *Created for jalemaov on 11-05-2020
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindByUom() throws Exception{
		Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByUom("Teaspoon");
		
		assertEquals("Teaspoon", uomOptional.get().getUom()); 
	}
	
	@Test
	public void testFindByUomCup() throws Exception{
		Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByUom("Cup");
		
		assertEquals("Cup", uomOptional.get().getUom()); 
	}


}
