package com.myob.payslip.util;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myob.payslip.repository.TaxRate;
import com.myob.payslip.repository.TaxRateRepository;

@Component
public class TaxRateCache {
	
	@Autowired
	private TaxRateRepository taxRateRepository;
	
	private static List<TaxRate> taxRates;
	
	@PostConstruct
	public void init() {
		taxRates = taxRateRepository.findAll();
	}

	public List<TaxRate> getTaxRates() {
		return taxRates;
	}
	public void  setTaxRates(List<TaxRate> taxRates) {
		TaxRateCache.taxRates = taxRates;
	}

}
