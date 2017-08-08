package com.myob.payslip.service.calculator;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.myob.payslip.PayslipServiceApplicationTests;
import com.myob.payslip.exception.DateValidationException;
import com.myob.payslip.model.Employee;
import com.myob.payslip.model.Payslip;

public class SuperCalculatorTest extends PayslipServiceApplicationTests {
	
	@Autowired
	@Qualifier(Calculator.SUPER_CALCULATOR)
	private Calculator superCalculator;

	@Test
	public void testCalculateDavid() throws DateValidationException{
		Payslip payslip = Payslip.PayslipBuilder(Employee.EmployeeBuilder().annualSalary(60050).build(), 9, "01 March – 31 March").grossIncome(5004).incomeTax(922).build();
		superCalculator.calculate(payslip);
		assertTrue(payslip.getSuperannuation() == 450);
		
	}
	
	@Test
	public void testCalculateRyan() throws DateValidationException{
		Payslip payslip = Payslip.PayslipBuilder(Employee.EmployeeBuilder().annualSalary(120000).build(), 10, "01 March – 31 March").grossIncome(10000).incomeTax(2696).build();
		superCalculator.calculate(payslip);
		assertTrue(payslip.getSuperannuation() == 1000);
	}

}
