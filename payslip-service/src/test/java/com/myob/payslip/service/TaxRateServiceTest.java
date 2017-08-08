package com.myob.payslip.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.myob.payslip.PayslipServiceApplicationTests;
import com.myob.payslip.repository.TaxRate;
import com.myob.payslip.util.TaxRateCache;

public class TaxRateServiceTest extends PayslipServiceApplicationTests {

	@Autowired
	private TaxRateService taxRateService;

	@Autowired
	private TaxRateCache taxRateCache;

	@Test
	public void testSaveDelete() {
		TaxRate taxRate = new TaxRate(1, 2, 1.1);
		int size = taxRateService.findAll().size();
		taxRateService.save(taxRate);
		assertTrue(taxRateService.findAll().size() == taxRateCache.getTaxRates().size());
		assertTrue(taxRateCache.getTaxRates().size() == size+1);
		taxRateService.delete(taxRate);
		assertTrue(taxRateService.findAll().size() == taxRateCache.getTaxRates().size());
		assertTrue(taxRateCache.getTaxRates().size() == size);
	}



}
