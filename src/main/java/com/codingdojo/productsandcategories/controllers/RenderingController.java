package com.codingdojo.productsandcategories.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.productsandcategories.models.Category;
import com.codingdojo.productsandcategories.models.Product;
import com.codingdojo.productsandcategories.services.CategoryService;
import com.codingdojo.productsandcategories.services.ProductService;

@Controller
public class RenderingController {
	
	private final ProductService productService;
	private final CategoryService categoryService;
	
	public RenderingController(ProductService productService, CategoryService categoryService) {
		this.productService = productService;
		this.categoryService = categoryService;
	}
	
	@RequestMapping("/products/new")
	public String newProduct(@ModelAttribute("product") Product product) {
		return "/newProduct.jsp";
	}
	
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String create(@ModelAttribute("product") Product product) {
		productService.createProduct(product);
		return "redirect:/products/new";
	}
	
	@RequestMapping("/categories/new")
	public String newCategory(@ModelAttribute("category") Category category) {
		return "/newCategory.jsp";
	}
	
	@RequestMapping(value="/categories", method=RequestMethod.POST)
	public String create(@ModelAttribute("category") Category category) {
		categoryService.createCategory(category);
		return "redirect:/categories/new";
	}
	
	
	@RequestMapping("/products/{id}")
	public String showProduct(@PathVariable Long id, Model model) {
		Product p = productService.findProduct(id);
		List<Category> currentCategories = p.getCategories();
		List<Category> nonCurrentCategories = categoryService.getNonCurrentCategoriesByProduct(p);
		System.out.println(currentCategories);
		
		model.addAttribute("currentCategories", currentCategories);
		model.addAttribute("nonCurrentCategories", nonCurrentCategories);
		model.addAttribute("product", p);
		return "/product.jsp";
	}
	
	@PostMapping("/products/{id}/category")
	public String addCat(@PathVariable("id") Long id, @RequestParam(value="category_id") Long category_id) {
		Product p = productService.findProduct(id);
		Category c = categoryService.findCategory(category_id);
		productService.addCategoryToProduct(p, c);
		return "redirect:/products/{id}";
	}
	
	@RequestMapping("/categories/{id}")
	public String showCategory(@PathVariable Long id, Model model) {
		Category c = categoryService.findCategory(id);
		List<Product> currentProducts = c.getProducts();
		List<Product> nonCurrentProducts = productService.getNonCurrentProductsByCategory(c);
		
		model.addAttribute("nonCurrentProducts", nonCurrentProducts);
		model.addAttribute("currentProducts", currentProducts);
		model.addAttribute("category", c);
		return "/category.jsp";
	}
	
	@PostMapping("/categories/{id}/product")
	public String addProd(@PathVariable("id") Long id, @RequestParam(value="product_id") Long product_id) {
		Category c = categoryService.findCategory(id);
		Product p  = productService.findProduct(product_id);
		categoryService.addProductToCategory(p, c);
		return "redirect:/categories/{id}";
	}
	
}
