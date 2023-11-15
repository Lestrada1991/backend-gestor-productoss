package com.company.inventory.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.company.inventory.dao.IProductDao;
import com.company.inventory.dao.ISeguimientoPrecioDao;
import com.company.inventory.model.Category;
import com.company.inventory.model.Product;
import com.company.inventory.model.Product2;
import com.company.inventory.model.SeguimientoPrecio;
import com.company.inventory.model.User;
import com.company.inventory.respnose.ProductResponseRest2;
import com.company.inventory.respnose.SeguimientoPrecioResponseRest;
import com.company.inventory.services.ISeguimientoPrecioService;
import com.company.inventory.util.Util;

@Service
public class SeguimientoPrecioServiceImpl implements ISeguimientoPrecioService{

	@Autowired
	private ISeguimientoPrecioDao seguimientoPrecioDao;
	@Autowired
	private IProductDao producto;
	
	@Override
	public ResponseEntity<SeguimientoPrecioResponseRest> save(SeguimientoPrecio seguimientoPrecio, Long ProductId) {

		SeguimientoPrecioResponseRest response = new SeguimientoPrecioResponseRest();
		List<SeguimientoPrecio> list = new ArrayList<>();
		
		try {
			
			//search category to set in the product object
			Optional<Product> product = producto.findById(ProductId);
			
			if( product.isPresent()) {
				seguimientoPrecio.setProducto(product.get());
			} else {
				response.setMetadata("respuesta nok", "-1", "Categoria no encontrada asociada al producto ");
				return new ResponseEntity<SeguimientoPrecioResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			
			//save the product
			SeguimientoPrecio seguimientoSaved = seguimientoPrecioDao.save(seguimientoPrecio);
			
			if (seguimientoSaved != null) {
				list.add(seguimientoSaved);
				response.getSeguimientoPrecio().setSeguimientoPrecio(list);
				response.setMetadata("respuesta ok", "00", "seguimiento guardado");
			} else {
				response.setMetadata("respuesta nok", "-1", "seguimiento no guardado ");
				return new ResponseEntity<SeguimientoPrecioResponseRest>(response, HttpStatus.BAD_REQUEST);

			}
			
			
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("respuesta nok", "-1", "Error al guardar seguimiento");
			return new ResponseEntity<SeguimientoPrecioResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<SeguimientoPrecioResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<SeguimientoPrecioResponseRest> searchById(Long id) {
		SeguimientoPrecioResponseRest response = new SeguimientoPrecioResponseRest();
		List<SeguimientoPrecio> list = new ArrayList<>();
		
		try {
			
			//search producto by id
			Optional<SeguimientoPrecio> product = seguimientoPrecioDao.findById(id);
			
			if( product.isPresent()) {
				
				//byte[] imageDescompressed = Util.decompressZLib(product.get().getUrl_imagen1());
				//product.get().setUrl_imagen1(imageDescompressed);
				byte[] imageDescompressed = Util.decompressZLib(product.get().getProducto().getPicture());
				product.get().getProducto().setPicture(imageDescompressed);
				list.add(product.get());
				response.getSeguimientoPrecio().setSeguimientoPrecio(list);
				response.setMetadata("Respuesta ok", "00", "Seguimiento encontrado");
				
			} else {
				response.setMetadata("respuesta nok", "-1", "Seguimiento no encontrado ");
				return new ResponseEntity<SeguimientoPrecioResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("respuesta nok", "-2", "Error al buscar seguimiento");
			return new ResponseEntity<SeguimientoPrecioResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<SeguimientoPrecioResponseRest>(response, HttpStatus.OK);
		

	}

	@Override
	public ResponseEntity<SeguimientoPrecioResponseRest> deleteById(Long id) {
		SeguimientoPrecioResponseRest response = new SeguimientoPrecioResponseRest();
		
		try {
			
			//delete producto by id
			seguimientoPrecioDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Seguimiento eliminado");
			
			
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("respuesta nok", "-1", "Error al eliminar seguimiento");
			return new ResponseEntity<SeguimientoPrecioResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<SeguimientoPrecioResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SeguimientoPrecioResponseRest> search() {
		SeguimientoPrecioResponseRest response = new SeguimientoPrecioResponseRest();
		
		try {
			List<SeguimientoPrecio> list = new ArrayList<>();
			List<SeguimientoPrecio> seguimiento = (List<SeguimientoPrecio>) seguimientoPrecioDao.findAll();
			
			if (seguimiento != null) {
				seguimiento.stream().forEach( (p) -> {
					byte[] imageDescompressed = Util.decompressZLib(p.getProducto().getPicture());
					p.getProducto().setPicture(imageDescompressed);
					
					list.add(p);});
				
				response.getSeguimientoPrecio().setSeguimientoPrecio(list);
				response.setMetadata("respuesta ok", "00", "seguimiento guardado");
			} else {
				response.setMetadata("respuesta nok", "-1", "seguimiento no guardado ");
				return new ResponseEntity<SeguimientoPrecioResponseRest>(response, HttpStatus.BAD_REQUEST);

			}
			
			
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("respuesta nok", "-1", "Error al guardar seguimiento");
			return new ResponseEntity<SeguimientoPrecioResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<SeguimientoPrecioResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<SeguimientoPrecioResponseRest> update(SeguimientoPrecio seguimientoPrecio, Long productId,
			Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<SeguimientoPrecioResponseRest> searchByUser(User user) {
		SeguimientoPrecioResponseRest response = new SeguimientoPrecioResponseRest();
		
		try {
			List<SeguimientoPrecio> list = new ArrayList<>();
			List<SeguimientoPrecio> seguimiento = (List<SeguimientoPrecio>) seguimientoPrecioDao.findByUser(user);
			
			if (seguimiento != null) {
				
				seguimiento.stream().forEach( (p) -> {
					byte[] imageDescompressed = Util.decompressZLib(p.getProducto().getPicture());
					p.getProducto().setPicture(imageDescompressed);
					
					list.add(p);});
				
				response.getSeguimientoPrecio().setSeguimientoPrecio(list);
				response.setMetadata("respuesta ok", "00", "seguimiento guardado");
			} else {
				response.setMetadata("respuesta nok", "-1", "seguimiento no guardado ");
				return new ResponseEntity<SeguimientoPrecioResponseRest>(response, HttpStatus.BAD_REQUEST);

			}
			
			
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("respuesta nok", "-1", "Error al guardar seguimiento");
			return new ResponseEntity<SeguimientoPrecioResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<SeguimientoPrecioResponseRest>(response, HttpStatus.OK);
		
	}

}
