package com.productApp.model;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
	//We use this method while retriving data from Database.
	public ProductDTO convertToDTO(Product product){
		ProductDTO productDTO = new ProductDTO();
		String productName = product.getProductName();
		String brand = product.getBrand();
		//Set the values into productDTO object.
		productDTO.setProductName(productName);
		productDTO.setBrand(brand);
		productDTO.setProductId(product.getProductId());
		productDTO.setPrice(product.getPrice());
		productDTO.setCategory(product.getCategory());
		return productDTO;
	}
	// Call this method while Setting the Data in DataBase.
	public Product convertToEntity(ProductDTO productDTO) {
		Product product = new Product();
		//call the setter methods of Product class
		product.setProductId(productDTO.getProductId());
		product.setBrand(productDTO.getBrand());
		product.setProductName(productDTO.getProductName());
		product.setPrice(productDTO.getPrice());
		product.setCategory(productDTO.getCategory());
		return product;
	}

}
