package com.company.inventory.services;

import org.springframework.http.ResponseEntity;

import com.company.inventory.model.SeguimientoPrecio;
import com.company.inventory.model.User;
import com.company.inventory.respnose.SeguimientoPrecioResponseRest;

public interface ISeguimientoPrecioService {
	
	public ResponseEntity<SeguimientoPrecioResponseRest> save(SeguimientoPrecio seguimientoPrecio, Long ProductId);
	public ResponseEntity<SeguimientoPrecioResponseRest> searchById(Long id);
	public ResponseEntity<SeguimientoPrecioResponseRest> deleteById(Long id);
	public ResponseEntity<SeguimientoPrecioResponseRest> search();
	public ResponseEntity<SeguimientoPrecioResponseRest> update(SeguimientoPrecio seguimientoPrecio, Long productId, Long id);
	public ResponseEntity<SeguimientoPrecioResponseRest> searchByUser(User user);
	
}
