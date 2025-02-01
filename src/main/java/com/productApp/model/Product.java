package com.productApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
	    name = "product",
	    uniqueConstraints = @UniqueConstraint(columnNames = {"productId", "brand", "category", "productName"})
	)
@NamedQuery(name = "getProductsByBrand",query = "from Product p where p.brand =?1")
@NamedQuery(name = "getByCatPrice",query ="from Product p where p.category =?1 and p.price<?2")
public class Product {
	private String productName;
	@Id
	@GeneratedValue
	private Integer productId;
	private String brand;
	private String category;
	@Column(name ="cost")
	private double price;

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Product(String productName,  String brand, String category, double price) {
		super();
		this.productName = productName;
		
		this.brand = brand;
		this.category = category;
		this.price = price;
	}
	public double getPrice() {
		return price;
	}
	@Override
	public String toString() {
		return "Product [productName=" + productName + ", productId=" + productId + ", brand=" + brand + ", category="
				+ category + ", price=" + price + "]";
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
