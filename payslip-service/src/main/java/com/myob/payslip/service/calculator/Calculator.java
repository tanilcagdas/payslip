package com.myob.payslip.service.calculator;

import com.myob.payslip.exception.DateValidationException;
import com.myob.payslip.model.Payslip;

public interface Calculator {
	
	public static final String GROSS_INCOME_CALCULATOR = "grossIncomeCalculator";
	public static final String INCOME_TAX_CALCULATOR = "incomeTaxCalculator";
	public static final String NET_INCOME_CALCULATOR = "netIncomeCalculator";
	public static final String SUPER_CALCULATOR = "superCalculator";
	
	public void calculate(Payslip payslip) throws DateValidationException;

}
