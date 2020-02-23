package com.mo.assemnt.online_retail_store.controller;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mo.assemnt.online_retail_store.controller.bill.beans.BillUpdateInfo;
import com.mo.assemnt.online_retail_store.dataaccess.entity.Bill;
import com.mo.assemnt.online_retail_store.service.BillService;
import com.mo.assemnt.online_retail_store.util.BillStatus;


@RestController
public class BillingController {
	
	@Autowired
	private BillService billService;

	final Logger logger = LogManager.getLogger(getClass());

	@PostMapping("/bills")
	public ResponseEntity<Bill> createBill() {
		logger.info("Request recieved to create Bill = ");
		Bill bill = billService.createBill(new Bill(0.0, 0, BillStatus.IN_PROGRESS));
		logger.info("Created Bill with id = " + bill.getId());
				
		URI newUri = ServletUriComponentsBuilder.
				fromCurrentRequest().
				path("/{id}").
				buildAndExpand(bill.getId()).toUri();
		logger.info("Setting header url with newly created Bill= " + bill.getId());
		
		return ResponseEntity.created(newUri).build();		
	}

  
	@DeleteMapping("/bills/{id}")
	public ResponseEntity<String> deleteBill(@PathVariable Long id) {
		billService.deleteBill(id);
		return new ResponseEntity<>("{\"status\": \"success\"}", HttpStatus.OK);
	}
	  
	@GetMapping(value = "/bills")
	public ResponseEntity<Iterable<Bill>> getAllBills() {
		return new ResponseEntity<>(billService.getAllBills(), HttpStatus.OK);
	}
	  
	
	@GetMapping(value = "/bills/{id}")
	public ResponseEntity<Bill> getBillById(@PathVariable Long id) {
		Bill bill = billService.getBillById(id);
		if(bill.getBillStatus() == null)
			throw new CustomException("Bill not found for id " +id);
		return new ResponseEntity<>(bill, HttpStatus.OK);
	}
	  
		  
	@PutMapping("/bills/{id}")
	public ResponseEntity<Bill> updateBill(@RequestBody BillUpdateInfo billUpdateInfo, @PathVariable Long id) {
		Bill updated = billService.updateBill(billUpdateInfo, id);
		logger.info("Request recieved =  " + billUpdateInfo);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}
	 
	 

}
