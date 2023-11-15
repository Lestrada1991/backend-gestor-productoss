package com.company.inventory.services;

import org.springframework.http.ResponseEntity;

import com.company.inventory.model.Ofertas;
import com.company.inventory.respnose.OfertasResponseRest;

public interface IOfertasService {

	public ResponseEntity<OfertasResponseRest> search();
	public ResponseEntity<OfertasResponseRest> searchById(Long id);
	public ResponseEntity<OfertasResponseRest> save(Ofertas ofertas,Long product_id);
	public ResponseEntity<OfertasResponseRest> update(Ofertas ofertas, Long id);
	public ResponseEntity<OfertasResponseRest> deleteById(Long id);
}
