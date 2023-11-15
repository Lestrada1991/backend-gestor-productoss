package com.company.inventory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.inventory.model.Ofertas;

public interface IOfertaDao extends JpaRepository<Ofertas,Long>{

}
