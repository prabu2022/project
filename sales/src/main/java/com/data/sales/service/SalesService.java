package com.data.sales.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.sales.entity.Sales;
import com.data.sales.repository.SalesRepository;

/**
 * @author Prabu Ponnusamy
 *
 */
@Service
public class SalesService {
	
	@Autowired
	SalesRepository salesRepository;
	
	public List<Sales> getSalesByOriginDestination(String origin, String destination) {
		List<Sales> salesList = salesRepository.findByOriginAndDestination(origin, destination);
		return salesList;
	}
	
	public List<Sales> getSalesEntry(String origin, String destination, String product) {
		List<Sales> salesList = salesRepository.findByOriginAndDestinationAndProduct(origin, destination, product);
		return salesList;
	}
	
	public void createSales(List<Sales> sales) {
		salesRepository.saveAll(sales);
	}
		
	public void updateSales(List<Sales> sales) {
		System.out.println("inside update - SalesService");
		salesRepository.saveAll(sales);
	}
	
	public void deleteSales(List<Sales> sales) {
		salesRepository.deleteAll(sales);
	}

}
