package com.productApp.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productApp.exception.ProductNotFoundException;
import com.productApp.model.Product;
import com.productApp.model.ProductDTO;
import com.productApp.model.ProductMapper;
import com.productApp.repository.IProductRepository;

@Service
public class ProductService implements IProductService {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private IProductRepository productRepository;
	@Override
	public void addProduct(Product product) {
		productRepository.save(product);
	}

	@Override
	public void updateProduct(Product product) {
		//save will check the if the product id is exist or not if exist it update if not create
		productRepository.save(product);
		

	}

	@Override
	public void deleteProduct(int productId) {
		 productRepository.deleteById(productId);
	}

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
		
	}

	@Override
	public Product getById(int productId) {
		Optional <Product>productOpt = productRepository.findById(productId);
		if(productOpt.isPresent()) {
			return productOpt.get();
		}
		return null;
	}

	@Override
	public List<ProductDTO> getByBrand(String brand) {
		List<Product> products = productRepository.findByBrand(brand);
//		//convert it to DTO object
//		List<ProductDTO> DTO = new ArrayList<>();
//		// with this Loop each product object is converted into DTO object.
//		for(Product products:product) {
//			DTO.add(productMapper.convertToDTO(products));
//		}
//		if(DTO.isEmpty()) {
//			throw new ProductNotFoundException("No Product Available");
//		}
		//-----------Using Stream Api-------------
//		List<ProductDTO>DTO = products.stream()
//							  .map(product->productMapper.convertToDTO(product))
//							  .collect(Collectors.toList());
		List<ProductDTO>DTO=convertToDTO(products);
		if(DTO.isEmpty()) {
			throw new ProductNotFoundException("No Product Available");
		}
		return DTO;
	}

	@Override
	public List<ProductDTO> getByBrandNameContains(String brand, String productName) {
		List<Product> product = productRepository.findByBrandAndProductNameContaining(brand,productName);
		//convert it to DTO object
		List<ProductDTO> DTO = new ArrayList<>();
		// with this Loop each product object is converted into DTO object.
		for(Product products:product) {
			DTO.add(productMapper.convertToDTO(products));
		}
		if(DTO.isEmpty()) {
			throw new ProductNotFoundException("No Product Available");
		}
		return DTO;
	}

	@Override
	public List<ProductDTO> getByCat(String category) {
		List<Product> products = productRepository.getByCat(category);
		return convertToDTO(products);
	}

	@Override
	public List<ProductDTO> getByBrandPriceLess(String brand, double price) {
		
			List<ProductDTO>DTO = convertToDTO( productRepository.getByBrandandPriceLess(brand, price));
			if(DTO.isEmpty()) {
				System.out.println("Exception");
				throw new ProductNotFoundException("No product Available");
				
			}
			return DTO;
	}
	
	@Override
	public List<ProductDTO> getByLesserPrice(double price) {
		List<ProductDTO>DTO=convertToDTO(productRepository.getByLesserPrice(price));
		return DTO;
	}

	@Override
	public List<ProductDTO> getByCategoryName(String category, String productName) {
		List<ProductDTO>DTO=convertToDTO(productRepository.getByCatNameContains(category,"%"+productName+"%"));
		return DTO;
	}

	@Override
	public List<ProductDTO> getProductByBrand(String brand) {
		List<ProductDTO>DTO = convertToDTO(productRepository.getProductByBrand(brand));
		return DTO;
	}

	@Override
	public List<ProductDTO> getByCatPriceLess(String category,double price) {
		List<ProductDTO>DTO = convertToDTO(productRepository.getByCatPriceLess(category,price));
		return DTO;
	}
	private List<ProductDTO> convertToDTO(List<Product> products){
		List<ProductDTO> DTO = products.stream()
							   .map(product->productMapper.convertToDTO(product))
							   .collect(Collectors.toList());
		return DTO;
	}

}
