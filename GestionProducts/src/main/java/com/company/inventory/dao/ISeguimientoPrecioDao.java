package com.company.inventory.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.company.inventory.model.SeguimientoPrecio;
import com.company.inventory.model.User;

public interface ISeguimientoPrecioDao extends CrudRepository<SeguimientoPrecio, Long> {

	List<SeguimientoPrecio> findByUser(User user);
}
