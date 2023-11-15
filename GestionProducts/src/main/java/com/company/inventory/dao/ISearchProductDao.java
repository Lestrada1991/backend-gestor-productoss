package com.company.inventory.dao;



import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.company.inventory.model.SearchProduct;
import com.company.inventory.model.User;

public interface ISearchProductDao extends CrudRepository<SearchProduct,Long> {

	List<SearchProduct> findByUser(User user);

}
