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
import com.iktpreobuka.project.services.BillService;
import com.iktpreobuka.project.services.OfferService;

@RestController
@RequestMapping("/project/categories")
public class CategoryController {
	
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	// TODO 2.2 metoda getDB vraća listu svih kategorija
	private List<CategoryEntity> getDB() {
		List<CategoryEntity> categories = new ArrayList<CategoryEntity>();
			CategoryEntity c1 = new CategoryEntity(1, "music", "music description", null);
			CategoryEntity c2 = new CategoryEntity(2, "food", "food description", null);
			CategoryEntity c3 = new CategoryEntity(3, "entertainment","entertainment description", null);
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
	
	// VP
//	@PostMapping("/")
//	public CategoryEntity addCategory(@RequestBody CategoryEntity category) {
//		return categoryRepository.save(category);
//	}
	
	
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
	
	// VP
//	@PutMapping("{id}")
//	public CategoryEntity updateCategory(@PathVariable Integer id, @RequestBody CategoryEntity) {
//		CategoryEntity categoryDB = Validation.validateEntity(id, categoryRepository);
//		categoryDB.setCategoryName(Validation.setIfNotNull(categoryDB.getCategoryName(), category.getCategoryName()));
//		categoryDB.setCategoryDescription(Validation.setIfNotNull(categoryDB.getCategoryDescription(), category.getCategoryDescription()));
//		
//		return categoryRepository.save(categoryDB);
//	}
	

	
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
	
	// VP
//	@DeleteMapping("/{id}")
//	public CategoryEntity deleteCategory(@PathVariable Integer id) {
//		return categoryService.deleteCategory(id);
//	}
	
	// SJ
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public CategoryEntity removeCategory(@PathVariable Integer categoryId) {
		if (categoryRepository.existsById(categoryId)) {
			CategoryEntity category = categoryRepository.findById(categoryId).get();
			boolean checkOffer = OfferService.ifCategoryHasNonExpOffers(categoryId);
			boolean checkBill = BillService.areBillsActiveByCategory(categoryId);
			if (checkOffer || checkBill) {
				return null;
			}
			categoryRepository.delete(category);
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
	
	// VP
//	@GetMapping("/{id}")
//	pubic Optional<CategoryEntity> findCategoryById (@PathVariable Integer id) {
//		return categoryRepository.findById(id);
//	}
	
	
	
	// TODO 3.1 ne dozvoliti brisanje onih kategorija za koje postoje neistekle ponude i računi
	// • napisati metodu u servisu zaduženom za rad sa ponudama koja proverava da li postoje ponude za datu kategoriju 
	// (kategoriju koja želi da se obriše)
	// • napisati metodu u servisu zaduženom za rad sa računima koja proverava da li postoje aktivni računi za datu kategoriju
	// • pozvati prethodno kreirane metode u metodi za brisanje kategorije u okviru CategoryController-a
	

}
