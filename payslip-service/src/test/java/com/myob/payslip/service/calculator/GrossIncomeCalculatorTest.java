package com.myob.payslip.service.calculator;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.myob.payslip.PayslipServiceApplicationTests;
import com.myob.payslip.exception.DateValidationException;
import com.myob.payslip.model.Employee;
import com.myob.payslip.model.Payslip;

public class GrossIncomeCalculatorTest extends PayslipServiceApplicationTests {

	@Autowired
	@Qualifier(Calculator.GROSS_INCOME_CALCULATOR)
	private Calculator grossIncomeCalculator;

	@Test
	public void testCalculate() throws DateValidationException {
		grossIncomeCalculator.calculate(Payslip.PayslipBuilder(new Employee(), 9, "01 March – 31 March").build());
	}

	@Test
	public void testCalculateDavid() throws DateValidationException {
		Payslip payslip = Payslip.PayslipBuilder(Employee.EmployeeBuilder().annualSalary(60050).build(), 9, "01 March – 31 March").build();
		grossIncomeCalculator.calculate(payslip);
		assertTrue(payslip.getGrossIncome() == 5004);
	}

	@Test
	public void testCalculateRyan() throws DateValidationException {
		Payslip payslip = Payslip.PayslipBuilder(Employee.EmployeeBuilder().annualSalary(120000).build(), 10, "01 March – 31 March").build();
		grossIncomeCalculator.calculate(payslip);
		assertTrue(payslip.getGrossIncome() == 10000);
	}

}
