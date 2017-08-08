package com.myob.payslip.repository;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.myob.payslip.PayslipServiceApplicationTests;

public class TaxRateRepositoryTest extends PayslipServiceApplicationTests {
	
	@Autowired
	private TaxRateRepository taxRateRepository;


	@Test
	public void testSaveDelete() {
		TaxRate taxRate = taxRateRepository.save(new TaxRate(6,1,1.1));
		TaxRate checkTaxRate = taxRateRepository.findOne(taxRate.getId());
		assertTrue(checkTaxRate!=null);
		taxRateRepository.delete(checkTaxRate);
		checkTaxRate = taxRateRepository.findOne(checkTaxRate.getId());
		assertTrue(checkTaxRate==null);
	}


}
