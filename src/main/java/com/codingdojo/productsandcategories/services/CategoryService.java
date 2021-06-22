package com.codingdojo.productsandcategories.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.productsandcategories.models.Category;
import com.codingdojo.productsandcategories.models.Product;
import com.codingdojo.productsandcategories.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public List<Category> allCategories() {
		return categoryRepository.findAll();
	}
	
	public Category createCategory(Category c) {
		return categoryRepository.save(c);
	}
	
	public List<Category> getCategoriesByProduct(Product product) {
		
		return product.getCategories();
//		return categoryRepository.findByProducts(product);
	}
	
	public Category addProductToCategory(Product p, Category c) {
		List<Product> products = c.getProducts();
		products.add(p);
		return categoryRepository.save(c);
	}
	
	
	
	public List<Category> getNonCurrentCategoriesByProduct(Product product) {
		return categoryRepository.findByProductsNotContains(product);
	}
	
	public Category findCategory(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()) {
            return optionalCategory.get();
        } else {
            return null;
        }
    }
}
