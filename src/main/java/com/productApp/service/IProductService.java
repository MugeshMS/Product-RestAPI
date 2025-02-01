package com.productApp.service;

import java.util.List;


import com.productApp.exception.ProductNotFoundException;
import com.productApp.model.Product;
import com.productApp.model.ProductDTO;

public interface IProductService {
	void addProduct(Product product);
	void updateProduct(Product product);
	void deleteProduct(int productId);
	List<Product>getAll();
	Product getById(int productId);
	
	
	//derived queries
	List<ProductDTO> getByBrand(String brand) throws ProductNotFoundException;
	List<ProductDTO> getByBrandNameContains(String brand,String productName)throws ProductNotFoundException;
	//custom query
	List<ProductDTO> getByCat(String category)throws ProductNotFoundException;
	List<ProductDTO> getByBrandPriceLess(String brand,double price)throws ProductNotFoundException;
	//native query
	List<ProductDTO> getByLesserPrice(double price)throws ProductNotFoundException;
	List<ProductDTO> getByCategoryName(String category,String productName)throws ProductNotFoundException;
	//named query
	List<ProductDTO> getProductByBrand(String brand)throws ProductNotFoundException;
	List<ProductDTO> getByCatPriceLess(String category,double price)throws ProductNotFoundException;
	

}
