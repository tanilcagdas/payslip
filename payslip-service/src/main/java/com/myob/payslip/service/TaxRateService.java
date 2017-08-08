package com.myob.payslip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myob.payslip.repository.TaxRate;
import com.myob.payslip.repository.TaxRateRepository;
import com.myob.payslip.util.TaxRateCache;

@Service
public class TaxRateService {
	
	@Autowired
	private TaxRateRepository taxRateRepository;
	
	@Autowired
	private TaxRateCache taxRateCache;
	
	public List<TaxRate> findAll(){
		return taxRateRepository.findAll();
	}
	
	public TaxRate save(TaxRate taxRate){
		taxRate = taxRateRepository.save(taxRate);
		taxRateCache.setTaxRates(taxRateRepository.findAll());
		return taxRate;
	}
	
	public void delete(TaxRate taxRate){
		taxRateRepository.delete(taxRate);
		taxRateCache.setTaxRates(taxRateRepository.findAll());
	}

}
