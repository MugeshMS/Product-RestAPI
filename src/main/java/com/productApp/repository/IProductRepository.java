package com.productApp.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.productApp.model.Product;
import com.productApp.model.ProductDTO;
@Repository
public interface IProductRepository extends JpaRepository<Product,Integer> {
	//derivedQuerires
	//Should start like readBy,getBy,findBy
	List<Product>findByBrand(String brand);
	List<Product> findByBrandAndProductNameContaining(String brand,String productName);
	//custom query.-JPQL
	@Query("from Product p where p.category = ?1")
	List<Product>getByCat(String category);
	@Query("from Product p where p.brand=?1 and p.price < ?2")
	List<Product>getByBrandandPriceLess(String brand,double price);
	//native Query.-old sql query
	@Query(value = """
			select * from product where cost 
			<?1
			""",nativeQuery = true)
	List<Product>getByLesserPrice(double price);
	@Query(value = """
			select * from product where category =?1
			and product_name like '%?2%'
			""",nativeQuery = true)
	List<Product>getByCatNameContains(String category,String productName);
	//Named Query
	@Query(name="getProductByBrand")
	List<Product> getProductByBrand(String brand);
	@Query(name = "getByCatPrice")
	List<Product> getByCatPriceLess(String category,double price);
	
}
