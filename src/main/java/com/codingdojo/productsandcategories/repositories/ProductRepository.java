package com.codingdojo.productsandcategories.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.productsandcategories.models.Category;
import com.codingdojo.productsandcategories.models.Product;


public interface ProductRepository extends CrudRepository<Product, Long>{

	List<Product> findAll();
	
	List<Product> findByCategories(Category category);
	
	List<Product> findByCategoriesNotContains(Category category);
}
