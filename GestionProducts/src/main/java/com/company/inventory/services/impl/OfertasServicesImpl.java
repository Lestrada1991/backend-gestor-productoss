package com.company.inventory.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.company.inventory.dao.IOfertaDao;
import com.company.inventory.dao.IProduct2Dao;
import com.company.inventory.model.Category;
import com.company.inventory.model.Ofertas;
import com.company.inventory.model.Product;
import com.company.inventory.model.Product2;
import com.company.inventory.respnose.OfertasResponseRest;
import com.company.inventory.respnose.ProductResponseRest2;
import com.company.inventory.services.IOfertasService;

public class OfertasServicesImpl implements IOfertasService{
	@Autowired
	private IProduct2Dao productDao;
	@Autowired
	private IOfertaDao ofertaDao;
	
	public OfertasServicesImpl(IProduct2Dao productDao) {
		super();
		this.productDao=productDao;
	}
	
	
	@Override
	public ResponseEntity<OfertasResponseRest> search() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<OfertasResponseRest> searchById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<OfertasResponseRest> save(Ofertas ofertas, Long productId) {
		OfertasResponseRest response = new OfertasResponseRest();
		List<Ofertas> list = new ArrayList<>();
		
		try {
			
			//search category to set in the product object
			Optional<Product> product = Optional.empty();
			
			if( product.isPresent()) {
				ofertas.setProducto(product.get());
			} else {
				response.setMetadata("respuesta nok", "-1", "Producto no encontrada asociada al producto ");
				return new ResponseEntity<OfertasResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			
			//save the product
			Ofertas ofertaSaved = ofertaDao.save(ofertas);
			
			if (ofertaSaved != null) {
				list.add(ofertaSaved);
				response.getOfertas().setOfertas(list);
				response.setMetadata("respuesta ok", "00", "Oferta guardado");
			} else {
				response.setMetadata("respuesta nok", "-1", "Oferta no guardado ");
				return new ResponseEntity<OfertasResponseRest>(response, HttpStatus.BAD_REQUEST);

			}
			
			
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("respuesta nok", "-2", "Error al guardar oferta");
			return new ResponseEntity<OfertasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<OfertasResponseRest>(response, HttpStatus.OK);
		
		
	}

	@Override
	public ResponseEntity<OfertasResponseRest> update(Ofertas ofertas, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<OfertasResponseRest> deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
