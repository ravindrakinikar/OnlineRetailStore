package com.mo.assemnt.online_retail_store.dataaccess.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mo.assemnt.online_retail_store.dataaccess.entity.LineItem;

public interface LineItemRepository extends CrudRepository<LineItem, Long> {
	
	public List<LineItem> findByProduct_id(long prodId);

}
