package com.myob.payslip.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myob.payslip.model.TaxRateWrapper;
import com.myob.payslip.repository.TaxRate;
import com.myob.payslip.service.TaxRateService;

@RestController
@RequestMapping(value = "/taxRate")
public class TaxRateController {
	
	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TaxRateService taxRateService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody TaxRateWrapper tw){
		try {
			TaxRate taxRate = taxRateService.save(tw.getTaxRate());
			return new ResponseEntity<>(taxRate, HttpStatus.OK);
	    }catch(Exception e){
	    	logger.error(e.getMessage(), e);
	        return new ResponseEntity<>("error occured while saving tax rate", HttpStatus.BAD_REQUEST);
	    }
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<?> delete(@RequestBody TaxRateWrapper tw){
		try {
			taxRateService.delete(tw.getTaxRate());
			return new ResponseEntity<>("Success", HttpStatus.OK);
	    }catch(Exception e){
	    	logger.error(e.getMessage(), e);
	        return new ResponseEntity<>("error occured while deleting tax rate", HttpStatus.BAD_REQUEST);
	    }
	}

	@RequestMapping(value = "/findAll")
	public ResponseEntity<?> findAll(){
		try {
			return new ResponseEntity<>(taxRateService.findAll(), HttpStatus.OK);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
	        return new ResponseEntity<>("error occured while listing tax rate", HttpStatus.BAD_REQUEST);
		}
	}

}
