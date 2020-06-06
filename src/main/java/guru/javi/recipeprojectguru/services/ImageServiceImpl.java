package guru.javi.recipeprojectguru.services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import guru.javi.recipeprojectguru.Repositories.RecipeRepository;
import guru.javi.recipeprojectguru.domain.Recipe;
import lombok.extern.slf4j.Slf4j;

/*
 * Created by @jalemaov on 03-06-2020
 */
@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

	private final RecipeRepository recipeRepository;
		
	public ImageServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	@Transactional
	public void saveImageFile(Long recipeId, MultipartFile file) {
		try {
			Recipe recipe = recipeRepository.findById(recipeId).get();
			Byte[] byteObjects = new Byte[file.getBytes().length];
			int i=0;
			for(byte b : file.getBytes()) {
				byteObjects[i++]=b;
			}
			recipe.setImage(byteObjects);
			recipeRepository.save(recipe);
		} catch (IOException e) {
			log.error("Error ocurred", e);
			e.printStackTrace();
		}
	}
}
