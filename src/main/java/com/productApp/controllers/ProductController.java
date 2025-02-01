package com.productApp.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productApp.model.Product;
import com.productApp.model.ProductDTO;
import com.productApp.service.ProductService;

@RestController
@RequestMapping("/product-api")
public class ProductController {
	@Autowired
	private ProductService productService;
	// http://localhost:8080/product-api/products
	@PostMapping("/products")
	void addproduct(@ RequestBody Product product) {
		productService.addProduct(product);
	}
	// http://localhost:8080/product-api/products
	@PutMapping("/products")
	void updateProduct(@RequestBody Product product) {
		productService.updateProduct(product);
		
	}
	//http://localhost:8080/product-api/products/productId/---value---
	@DeleteMapping("/products")
	void deleteProduct(int productId) {
		productService.deleteProduct(productId);
	}
	// http://localhost:8080/product-api/products/productId/--value--
	@GetMapping("/products/productId/{id}")
	Product getById(@PathVariable("id") int id){
		return productService.getById(id);
	}
	//http://localhost:8080/product-api/products
	@GetMapping("/products")
	List<Product> getAll() {
	return	productService.getAll();
	}
	//http://localhost:8080/product-api/products/brand/---value---
	@GetMapping("/products/brand/{brand}")
	List<ProductDTO>getByBrand(@PathVariable("brand")String brand){
		return productService.getByBrand(brand);
	}
	// http://localhost:8080/product-api/products/brand/--value---/product-name/---value---
	@GetMapping("/products/brand/{brand}/product-name/{productname}")
	List<ProductDTO> getByBrandNameContains(@PathVariable("brand")String brand,@PathVariable("productname")String productname){
		return productService.getByBrandNameContains(brand,productname);
	}
	// https://localhost:8080/product-api/products/category?category=----value----
	@GetMapping("/products/category")
	List<ProductDTO>getByCategory(@RequestParam String category){
		return productService.getByCat(category);
	}
	// http://localhost:8080/product-api/products/brand/---value---/price/--value---
	@GetMapping("/products/brand/{brand}/price/{price}")
	List<ProductDTO>getByBrandPriceLess(@PathVariable("brand")String brand,@PathVariable("price")double price){
		return productService.getByBrandPriceLess(brand, price);
	}
	// http://localhost:8080/product-api/products/price/---value---
	@GetMapping("/products/price/{price}")
	List<ProductDTO>getByLesserPrice(@PathVariable("price")double price){
		return productService.getByLesserPrice(price);
	}
	// http://localhost:8080/product-api/products/product-brand/---value---
	@GetMapping("/products/product-brand/{brand}")
	List<ProductDTO>getProductByBrand(@PathVariable("brand")String brand){
		return productService.getProductByBrand(brand);
	}
	// http://localhost:8080/product-api/products/category/---value---/price/---value---
	@GetMapping("/products/category/{category}/price/{price}")
	List<ProductDTO>getByCatPriceLess(@PathVariable("category")String category,@PathVariable("price")double price){
		return productService.getByCatPriceLess(category, price);
	}
	
}
