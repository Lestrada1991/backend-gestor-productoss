package com.company.inventory.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.inventory.dao.ICategoryDao;
import com.company.inventory.dao.IProduct2Dao;
import com.company.inventory.model.Category;
import com.company.inventory.model.Product2;
import com.company.inventory.respnose.ProductResponseRest2;
import com.company.inventory.services.IProductService2;
import com.company.inventory.util.Util;

@Service
public class ProductServiceImpl2 implements IProductService2{

	private ICategoryDao categoryDao;
	private IProduct2Dao productDao;

	public ProductServiceImpl2(ICategoryDao categoryDao, IProduct2Dao productDao) {
		super();
		this.categoryDao = categoryDao;
		this.productDao = productDao;
	}

	@Override
	@Transactional
	public ResponseEntity<ProductResponseRest2> save(Product2 product, Long categoryId) {
		
		ProductResponseRest2 response = new ProductResponseRest2();
		List<Product2> list = new ArrayList<>();
		
		try {
			
			//search category to set in the product object
			Optional<Category> category = categoryDao.findById(categoryId);
			
			if( category.isPresent()) {
				product.setCategory(category.get());
			} else {
				response.setMetadata("respuesta nok", "-1", "Categoria no encontrada asociada al producto ");
				return new ResponseEntity<ProductResponseRest2>(response, HttpStatus.NOT_FOUND);
			}
			
			
			//save the product
			Product2 productSaved = productDao.save(product);
			
			if (productSaved != null) {
				list.add(productSaved);
				response.getProduct().setProducts(list);
				response.setMetadata("respuesta ok", "00", "Producto guardado");
			} else {
				response.setMetadata("respuesta nok", "-1", "Producto no guardado ");
				return new ResponseEntity<ProductResponseRest2>(response, HttpStatus.BAD_REQUEST);

			}
			
			
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("respuesta nok", "-1", "Error al guardar producto");
			return new ResponseEntity<ProductResponseRest2>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<ProductResponseRest2>(response, HttpStatus.OK);
		
		
	}
	
	@Override
	@Transactional (readOnly = true)
	public ResponseEntity<ProductResponseRest2> searchById(Long id) {
		
		ProductResponseRest2 response = new ProductResponseRest2();
		List<Product2> list = new ArrayList<>();
		
		try {
			
			//search producto by id
			Optional<Product2> product = productDao.findById(id);
			
			if( product.isPresent()) {
				
				//byte[] imageDescompressed = Util.decompressZLib(product.get().getUrl_imagen1());
				//product.get().setUrl_imagen1(imageDescompressed);
				list.add(product.get());
				response.getProduct().setProducts(list);
				response.setMetadata("Respuesta ok", "00", "Producto encontrado");
				
			} else {
				response.setMetadata("respuesta nok", "-1", "Producto no encontrada ");
				return new ResponseEntity<ProductResponseRest2>(response, HttpStatus.NOT_FOUND);
			}
			
			
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("respuesta nok", "-2", "Error al guardar producto");
			return new ResponseEntity<ProductResponseRest2>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<ProductResponseRest2>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional (readOnly = true)
	public ResponseEntity<ProductResponseRest2> searchByName(String name) {
		ProductResponseRest2 response = new ProductResponseRest2();
		List<Product2> list = new ArrayList<>();
		List<Product2> listAux = new ArrayList<>();
		
		try {
			
			//search producto by name
			listAux = productDao.findByNameContainingIgnoreCase(name);
			
			
			if( listAux.size() > 0) {
				
				listAux.stream().forEach( (p) -> {
					String url_tienda="";
					if(p.getCod_almacen()==1) {
						url_tienda="../../../../assets/exito.png";
					}else if(p.getCod_almacen()==6) {
						url_tienda="../../../../assets/olimpica.png";
					}else if(p.getCod_almacen()==7) {
						url_tienda="../../../../assets/falabella.png";
					}else if(p.getCod_almacen()==2) {
						url_tienda="../../../../assets/jumbo.png";
					}
					p.setUrl_imagen6(url_tienda);
					//byte[] imageDescompressed = Util.decompressZLib(p.getUrl_imagen1());
					//p.setUrl_imagen1(imageDescompressed);
					list.add(p);
				});
				
				
				response.getProduct().setProducts(list);
				response.setMetadata("Respuesta ok", "00", "Productos encontrados");
				
			} else {
				response.setMetadata("respuesta no ok", "-1", "Productos no encontrados ");
				return new ResponseEntity<ProductResponseRest2>(response, HttpStatus.NOT_FOUND);
			}
			
			
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("respuesta no ok", "-2", "Error al buscar producto por nombre");
			return new ResponseEntity<ProductResponseRest2>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<ProductResponseRest2>(response, HttpStatus.OK);

	}

	@Override
	@Transactional
	public ResponseEntity<ProductResponseRest2> deleteById(Long id) {
		ProductResponseRest2 response = new ProductResponseRest2();
		
		try {
			
			//delete producto by id
			productDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Producto eliminado");
			
			
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("respuesta nok", "-1", "Error al eliminar producto");
			return new ResponseEntity<ProductResponseRest2>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<ProductResponseRest2>(response, HttpStatus.OK);
	}

	@Override
	@Transactional (readOnly = true)
	public ResponseEntity<ProductResponseRest2> search() {
		ProductResponseRest2 response = new ProductResponseRest2();
		List<Product2> list = new ArrayList<>();
		List<Product2> listAux = new ArrayList<>();
		
		try {
			
			//search producto
			listAux = (List<Product2>) productDao.findAll();
			
			
			if( listAux.size() > 0) {
				
				listAux.stream().forEach( (p) -> {
					//byte[] imageDescompressed = Util.decompressZLib(p.getUrl_imagen1());
					//p.setUrl_imagen1(imageDescompressed);
					String url_tienda="";
					if(p.getCod_almacen()==1) {
						url_tienda="../../../../assets/exito.png";
					}else if(p.getCod_almacen()==6) {
						url_tienda="../../../../assets/olimpica.png";
					}else if(p.getCod_almacen()==7) {
						url_tienda="../../../../assets/falabella.png";
					}else if(p.getCod_almacen()==2) {
						url_tienda="../../../../assets/jumbo.png";
					}
					p.setUrl_imagen6(url_tienda);
					list.add(p);
				});
				
				
				response.getProduct().setProducts(list);
				response.setMetadata("Respuesta ok", "00", "Productos encontrados");
				
			} else {
				response.setMetadata("respuesta nok", "-1", "Productos no encontrados ");
				return new ResponseEntity<ProductResponseRest2>(response, HttpStatus.NOT_FOUND);
			}
			
			
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("respuesta nok", "-2", "Error al buscar productos");
			return new ResponseEntity<ProductResponseRest2>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<ProductResponseRest2>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ProductResponseRest2> update(Product2 product, Long categoryId, Long id) {
		ProductResponseRest2 response = new ProductResponseRest2();
		List<Product2> list = new ArrayList<>();
		
		try {
			
			//search category to set in the product object
			Optional<Category> category = categoryDao.findById(categoryId);
			
			if( category.isPresent()) {
				product.setCategory(category.get());
			} else {
				response.setMetadata("respuesta nok", "-1", "Categoria no encontrada asociada al producto ");
				return new ResponseEntity<ProductResponseRest2>(response, HttpStatus.NOT_FOUND);
			}
			
			
			//search Product to update
			Optional<Product2> productSearch = productDao.findById(id);
			
			if (productSearch.isPresent()) {
				
				//se actualizará el producto
				productSearch.get().setCod_almacen(product.getCod_almacen());
				productSearch.get().setCategory(product.getCategory());
				productSearch.get().setName(product.getName());
				productSearch.get().setPrec_producto(product.getPrec_producto());
				productSearch.get().setUrl_imagen1(product.getUrl_imagen1());
				
				//save the product in DB
				Product2 productToUpdate = productDao.save(productSearch.get());
				
				if (productToUpdate != null) {
					list.add(productToUpdate);
					response.getProduct().setProducts(list);
					response.setMetadata("respuesta ok", "00", "Producto actualizado");
				} else {
					response.setMetadata("respuesta nok", "-1", "Producto no actualizado ");
					return new ResponseEntity<ProductResponseRest2>(response, HttpStatus.BAD_REQUEST);

				}

			} else {
				response.setMetadata("respuesta nok", "-1", "Producto no actualizado ");
				return new ResponseEntity<ProductResponseRest2>(response, HttpStatus.NOT_FOUND);

			}
			
			
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("respuesta nok", "-1", "Error al actualizar producto");
			return new ResponseEntity<ProductResponseRest2>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<ProductResponseRest2>(response, HttpStatus.OK);
	}

}
