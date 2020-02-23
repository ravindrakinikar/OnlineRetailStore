package com.mo.assemnt.online_retail_store.dataaccess.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mo.assemnt.online_retail_store.dataaccess.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

	public long count();

	public List<Product> findByBarCodeId(String barCodeId);

}