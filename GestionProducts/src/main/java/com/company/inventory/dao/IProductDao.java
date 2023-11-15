package com.company.inventory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.company.inventory.model.Product;

public interface IProductDao extends JpaRepository<Product, Long>{

	@Query("select p from Product p where p.name like %?1%")
	List<Product> findByNameLike(String name);
	
	
	List<Product> findByNameContainingIgnoreCase(String name);
	
	@Query(value="select schbocli.td_producto_list(?1)",nativeQuery=true)
	//@Query(value="SELECT * FROM product p  \n"
		//	+ "WHERE ?1 % ANY(STRING_TO_ARRAY(p.name,' '))",nativeQuery=true)
	int callFunctionSimilaridad(String name);
	@Query(value="select id,name,price,picture,category_id,account from "
			+ "product_simil where inc= ?1 order by simil desc",nativeQuery=true)
	List<Product> findSimilProduct(int simil);
	
	
    
	
}
