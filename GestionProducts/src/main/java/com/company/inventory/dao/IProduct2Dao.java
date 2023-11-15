package com.company.inventory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.company.inventory.model.Product2;

public interface IProduct2Dao extends CrudRepository<Product2, Long>{

	
	
	
	List<Product2> findByNameContainingIgnoreCase(String name);

}
