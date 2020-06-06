package guru.javi.recipeprojectguru.services;

import org.springframework.web.multipart.MultipartFile;

/*
 * Created by @jalemaov on 03-06-2020
 */
public interface ImageService {

	void saveImageFile(Long recipeId, MultipartFile file);
}
