package com.company.inventory.services;

import org.springframework.http.ResponseEntity;

import com.company.inventory.model.Product2;
import com.company.inventory.respnose.ProductResponseRest2;

public interface IProductService2 {
	
	public ResponseEntity<ProductResponseRest2> save(Product2 product, Long categoryId);
	public ResponseEntity<ProductResponseRest2> searchById(Long id);
	public ResponseEntity<ProductResponseRest2> searchByName(String name);
	public ResponseEntity<ProductResponseRest2> deleteById(Long id);
	public ResponseEntity<ProductResponseRest2> search();
	public ResponseEntity<ProductResponseRest2> update(Product2 product, Long categoryId, Long id);

}
