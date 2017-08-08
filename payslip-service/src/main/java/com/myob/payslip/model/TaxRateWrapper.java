package com.myob.payslip.model;

import com.myob.payslip.repository.TaxRate;

public class TaxRateWrapper {
	
	private TaxRate taxRate;

	public TaxRate getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(TaxRate taxRate) {
		this.taxRate = taxRate;
	}

}
