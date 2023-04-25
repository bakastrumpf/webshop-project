package com.iktpreobuka.project.services;

//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.iktpreobuka.project.entities.CategoryEntity;
//import com.iktpreobuka.project.repositories.CategoryRepository;

public interface CategoryServiceImpl extends CategoryService {
	 
//	@Autowired
//	private OfferService offerService;
//	
//	@Autowired
//	private BillService billService;
//	
//	@Autowired 
//	private CategoryRepository categoryRepository;
//	
//	@Override
//	public CategoryEntity deleteCategory(Integer id) {
//		
//		// VP 
//		
//		CategoryEntity category = categoryRepository.findById(id).orElse(null);
//		
//		boolean checkOffers = offerService.hasCategoryNonExpirationOffers(id);
//		boolean checkBills = billService.areActiveBills(id);
//		
//		if(category == null || checkOffers || checkBills) {
//			return null;
//		}
//		
//		categoryRepository.delete(category);
//		
//		return category;
//		
//	}

}
