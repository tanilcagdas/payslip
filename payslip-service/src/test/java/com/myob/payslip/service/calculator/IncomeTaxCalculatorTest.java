package com.myob.payslip.service.calculator;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.myob.payslip.PayslipServiceApplicationTests;
import com.myob.payslip.exception.DateValidationException;
import com.myob.payslip.model.Employee;
import com.myob.payslip.model.Payslip;

import static org.junit.Assert.*;

public class IncomeTaxCalculatorTest extends PayslipServiceApplicationTests{
	
	@Autowired
	@Qualifier(Calculator.INCOME_TAX_CALCULATOR)
	private Calculator incomeTaxCalculator;

	@Test
	public void testCalculateDavid() throws DateValidationException {
		Payslip payslip = Payslip.PayslipBuilder(Employee.EmployeeBuilder().annualSalary(60050).build(), 9, "01 March – 31 March").build();
		incomeTaxCalculator.calculate(payslip);
		assertTrue (payslip.getIncomeTax() == 922);
	}

	@Test
	public void testCalculateRyan() throws DateValidationException {
		Payslip payslip = Payslip.PayslipBuilder(Employee.EmployeeBuilder().annualSalary(120000).build(), 10, "01 March – 31 March").build();
		incomeTaxCalculator.calculate(payslip);
		assertTrue (payslip.getIncomeTax() == 2696);
	}

}
