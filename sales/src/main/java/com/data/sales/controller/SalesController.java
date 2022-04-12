package com.data.sales.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.data.sales.entity.Sales;
import com.data.sales.service.SalesService;
import com.data.sales.util.SalesUtil;

/**
 * @author Prabu Ponnusamy
 *
 */
@RestController
public class SalesController {
	
	@Autowired
	SalesService salesService;
	
	private static String SAVE_SUCCESS_MSG = "Sales details are saved successfully";
	private static String SAVE_FAILURE_MSG = "Failed to save Sales details";
	private static String DELETE_SUCCESS_MSG = "Sales details are deleted successfully";
	private static String DELETE_FAILURE_MSG = "Failed to delete Sales details";
	
	@PostMapping(value="/getSalesByOriginDestination", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public List<Sales> getSalesByOriginDestination(@RequestParam(name = "file") MultipartFile file) throws Exception {
		List<Sales> salesList = SalesUtil.mapDetails(file);
		Sales sales = salesList.get(0);
		salesList = salesService.getSalesByOriginDestination(sales.getOrigin(), sales.getDestination());
		return salesList;		
	}
	
	@PostMapping(value="/getSales", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public List<Sales> getSalesDetails(@RequestParam(name = "file") MultipartFile file) throws Exception {
		List<Sales> salesList = SalesUtil.mapDetails(file);
		Sales sales = salesList.get(0);
		salesList = salesService.getSalesEntry(sales.getOrigin(), sales.getDestination(), sales.getProduct());
		return salesList;		
	}
	
	@PostMapping(value="/createSales", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public String createSalesDetails(@RequestParam(name = "file") MultipartFile file) {
		try {
			List<Sales> salesList = SalesUtil.mapDetails(file);
			salesService.createSales(salesList);
			return SAVE_SUCCESS_MSG;
		} catch (Exception e) {
			e.printStackTrace();
			return SAVE_FAILURE_MSG;
		}
	}
	
	@PutMapping(value="/updateSales", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public String updateSalesDetails(@RequestParam(name = "file") MultipartFile file) {
		System.out.println("inside update >>>> ");
		try {
			List<Sales> salesList = SalesUtil.mapDetails(file);
			System.out.println("Inside Controller - Gonna update data");
			salesService.updateSales(salesList);
			System.out.println("Inside Controller - After update data");
			return SAVE_SUCCESS_MSG;
		} catch (Exception e) {
			e.printStackTrace();
			return SAVE_FAILURE_MSG;
		}
	}
	
	@DeleteMapping(value="/deleteSales", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public String deleteSalesDetails(@RequestParam(name = "file") MultipartFile file) throws Exception {
		System.out.println("inside delete");
		try {
			List<Sales> salesList = SalesUtil.mapDetails(file);
			salesService.deleteSales(salesList);
			return DELETE_SUCCESS_MSG;
		} catch (Exception e) {
			e.printStackTrace();
			return DELETE_FAILURE_MSG;
		}
	}
	
	
}
