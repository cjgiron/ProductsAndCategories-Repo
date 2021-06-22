package com.codingdojo.productsandcategories.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.productsandcategories.models.Category;
import com.codingdojo.productsandcategories.models.Product;
import com.codingdojo.productsandcategories.repositories.ProductRepository;

@Service
public class ProductService {
	
	private final ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<Product> allProducts() {
		return productRepository.findAll();
	}
	
	public Product createProduct(Product p) {
		return productRepository.save(p);
	}
	
	public Product addCategoryToProduct(Product p, Category c) {
		List<Category> categories = p.getCategories();
		categories.add(c);
		return productRepository.save(p);
	}
	
	
	public List<Product> getNonCurrentProductsByCategory(Category c) {
		return productRepository.findByCategoriesNotContains(c);
	}
	
	public List<Product> getProductsByCategory(Category category) {
		return productRepository.findByCategories(category);
	}
	
	public Product findProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            return null;
        }
    }
}
