package com.iktpreobuka.project.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.project.entities.CategoryEntity;
import com.iktpreobuka.project.repositories.CategoryRepository;

@RestController
@RequestMapping("/project/categories")
public class CategoryController {
	
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	// TODO 2.2 metoda getDB vraća listu svih kategorija
	private List<CategoryEntity> getDB() {
		List<CategoryEntity> categories = new ArrayList<CategoryEntity>();
			CategoryEntity c1 = new CategoryEntity(1, "music", "music description");
			CategoryEntity c2 = new CategoryEntity(2, "food", "food description");
			CategoryEntity c3 = new CategoryEntity(3, "entertainment","entertainment description");
			categories.add(c1);
			categories.add(c2);
			categories.add(c3);	
		return categories;
		}
	
	
	
	// TODO 2.3: REST endpoint koji vraća listu svih kategorija
	// putanja /project/categories
	@RequestMapping(method = RequestMethod.GET, path = "/getAll")
		public List<CategoryEntity> getAll(){
			return (List<CategoryEntity>) categoryRepository.findAll();
		}

	
	
	// TODO 2.4: REST endpoint koji omogućava dodavanje nove kategorije
	// putanja: /project/categories
	// vraća dodatu kategoriju
	
	@RequestMapping(method = RequestMethod.POST, value = "/")
	public CategoryEntity createNewCategory(@RequestBody CategoryEntity newCategory) {
		List<CategoryEntity> categories = getDB();
		newCategory.setId(new Random().nextInt());
		newCategory.setCategoryName(newCategory.getCategoryName());
		newCategory.setCategoryDescription(newCategory.getCategoryDescription());
		categories.add(newCategory);
		
		return newCategory;
	}
	
	
	// TODO 2.3: omogućiti dodavanje kategorije i korisnika koji je kreirao ponudu
	// • izmeniti prethodnu putanju za dodavanje ponude
	// • putanja /project/offers/{categoryId}/seller/{sellerId}
	// • samo korisnik sa ulogom ROLE_SELLER ima pravo da bude postavljen kao onaj ko je kreirao/napravio ponudu 
	// (u suprotnom ne dozvoliti kreiranje ponude); 
	// Kao datum kreiranja ponude postaviti trenutni datum i ponuda ističe za 10 dana od dana kreiranja
	
	
	
	
	
	
	// TODO 2.5: REST endpoint koji omogućava izmenu kategorije
	// putanja: /project/categories/{id}
	// vraća NULL ako je prosleđen ID koji ne pripada nijednoj kategoriji
	// vraća podatke kategorije sa izmenjenim vrednostima
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public CategoryEntity modifyCategory(@RequestBody CategoryEntity modifiedCategory, @PathVariable Integer id) {
		for (CategoryEntity ce : getDB()) {
			if(ce.getId().equals(id)) {
				if(modifiedCategory.getCategoryName() != null) {
					ce.setCategoryName(modifiedCategory.getCategoryName());
				}
				if(modifiedCategory.getCategoryDescription() != null) {
					ce.setCategoryDescription(modifiedCategory.getCategoryDescription());
				}
				return modifiedCategory;
			}
		}
		return null;
	}
	

	
	// TODO 2.6: REST endpoint koji omogućava brisanje kategorije
	// putanja: /project/categories/{id}
	// vraća NULL ako je prosleđen ID koji ne pripada nijednoj kategoriji
	// vraća podatke o obrisanoj kategoriji
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public CategoryEntity deleteCategory(@PathVariable Integer id) {
		List<CategoryEntity> categories = getDB();
		Iterator<CategoryEntity> itcat = categories.iterator();
		while (itcat.hasNext()) {
			CategoryEntity catent = itcat.next();
			if(catent.getId().equals(id)) {
				itcat.remove();
				return catent;
			}
		}
		return null;
	}	
	
	
	
	// TODO 2.7: REST endpoint koji vraća kategoriju po vrednosti prosleđenog ID
	// putanja: /project/categories/{id}
	// vraća NULL ako je prosleđen ID koji ne pripada nijednoj kategoriji
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public CategoryEntity getById(@PathVariable Integer id) {
		for (CategoryEntity ce : getDB()) {
			if(ce.getId().equals(id))
				return ce;
		}
		return null;
	}
	
	

}
