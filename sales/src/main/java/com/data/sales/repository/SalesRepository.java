package com.data.sales.repository;

import org.springframework.stereotype.Repository;

import com.data.sales.entity.Sales;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Prabu Ponnusamy
 *
 */
@Repository
public interface SalesRepository extends CrudRepository<Sales, Integer>{
	
	public List<Sales> findByOriginAndDestination(String origin, String destination);
	
	public List<Sales> findByOriginAndDestinationAndProduct(String origin, String destination, String product);

}
