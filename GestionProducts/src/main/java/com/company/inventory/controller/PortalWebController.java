package com.company.inventory.controller;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.inventory.dao.ISearchProductDao;
import com.company.inventory.model.SearchProduct;
import com.company.inventory.model.SeguimientoPrecio;
import com.company.inventory.model.User;
import com.company.inventory.repository.UserRepository;
import com.company.inventory.respnose.ProductResponseRest;
import com.company.inventory.respnose.ProductResponseRest2;
import com.company.inventory.respnose.SeguimientoPrecioResponseRest;
import com.company.inventory.services.IProductService;
import com.company.inventory.services.IProductService2;
import com.company.inventory.services.ISeguimientoPrecioService;
//import com.company.inventory.services.TrigramService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/web")
@CrossOrigin(origins = { "*" })
@RequiredArgsConstructor
public class PortalWebController {

	@Autowired
	private IProductService productService;
	@Autowired
	private IProductService2 productService2;
	@Autowired
	private ISearchProductDao searchProduct;
	@Autowired
	private ISeguimientoPrecioService seguimientoprecio;
	@Autowired
	private UserRepository userService;
	

	@GetMapping("/products")
	public ResponseEntity<ProductResponseRest> search() {
		ResponseEntity<ProductResponseRest> response = productService.search();
		return response;
	}

	@GetMapping("/products/filter/{name}/{userId}")
	public ResponseEntity<ProductResponseRest> searchByName(@PathVariable String name, @PathVariable int userId) {
		SearchProduct busqueda = new SearchProduct();
		busqueda.setFecha(Date.from(Instant.now()));
		busqueda.setValueSearch(name);
		User user = new User();
		user = userService.findById(userId).orElse(null);
		busqueda.setUser(user);
		searchProduct.save(busqueda);
		ResponseEntity<ProductResponseRest> response = productService.searchByName(name);
		return response;
	}

	@GetMapping("/products2")
	public ResponseEntity<ProductResponseRest2> search2() {
		ResponseEntity<ProductResponseRest2> response = productService2.search();
		return response;
	}

	@GetMapping("/products2/filter/{name}")
	public ResponseEntity<ProductResponseRest2> searchByName2(@PathVariable String name) {
		ResponseEntity<ProductResponseRest2> response = productService2.searchByName(name);
		return response;
	}

	@PostMapping("/products/seguimientos/{price}/{productID}/{userID}")
	public ResponseEntity<SeguimientoPrecioResponseRest> save(@PathVariable String price, @PathVariable Long productID,
			@PathVariable int userID) throws IOException {
		User user = new User();
		user = userService.findById(userID).orElse(null);
		SeguimientoPrecio Seguimiento = new SeguimientoPrecio();
		Seguimiento.setFecha(Date.from(Instant.now()));
		Seguimiento.setValor(Long.parseLong(price));
		Seguimiento.setUser(user);

		ResponseEntity<SeguimientoPrecioResponseRest> response = seguimientoprecio.save(Seguimiento, productID);

		return response;

	}

	@GetMapping("/products/seguimientos")
	public ResponseEntity<SeguimientoPrecioResponseRest> searchSeguimiento() {
		System.out.println("LLEgo");
		ResponseEntity<SeguimientoPrecioResponseRest> response = seguimientoprecio.search();
		return response;
	}

	@GetMapping("/products/seguimientos/{userID}")
	public ResponseEntity<SeguimientoPrecioResponseRest> searchSeguimientoUser(@PathVariable int userID) {
		User user = new User();
		user = userService.findById(userID).orElse(null);
		ResponseEntity<SeguimientoPrecioResponseRest> response = seguimientoprecio.searchByUser(user);
		return response;
	}

	@DeleteMapping("/products/seguimientos/{id}")
	public ResponseEntity<SeguimientoPrecioResponseRest> delete(@PathVariable Long id) {

		ResponseEntity<SeguimientoPrecioResponseRest> response = seguimientoprecio.deleteById(id);
		return response;
	}
}
