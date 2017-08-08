package com.myob.payslip.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.myob.payslip.PayslipServiceApplicationTests;
import com.myob.payslip.exception.DateValidationException;
import com.myob.payslip.model.Employee;
import com.myob.payslip.model.Payslip;

public class PayslipServiceTest extends PayslipServiceApplicationTests{
	
	@Autowired
	private PayslipService payslipService;

	@Test
	public void testCalculate60050Payslip() throws DateValidationException {
		
		Employee employee = Employee.EmployeeBuilder().annualSalary(60050).build();
		Payslip payslip = payslipService.calculatePayslip(employee, 9, "01 March – 31 March");
		assertTrue(payslip.getGrossIncome() == 5004);
		assertTrue(payslip.getIncomeTax() == 922);
		assertTrue(payslip.getNetIncome() == 4082);
		assertTrue(payslip.getSuperannuation() == 450);
	}
	

	@Test
	public void testCalculateRyan() throws DateValidationException {
		
		Employee employee = Employee.EmployeeBuilder().annualSalary(120000).build();
		Payslip payslip = payslipService.calculatePayslip(employee, 10, "01 March – 31 March");
		assertTrue(payslip.getGrossIncome() == 10000);
		assertTrue(payslip.getIncomeTax() == 2696);
		assertTrue(payslip.getNetIncome() == 7304);
		assertTrue(payslip.getSuperannuation() == 1000);
	}

	@Test
	public void testCalculate17500Payslip() throws DateValidationException{
		
		Employee employee = Employee.EmployeeBuilder().annualSalary(17500).build();
		Payslip payslip = payslipService.calculatePayslip(employee, 9, "01 March – 31 March");
		assertTrue(payslip.getGrossIncome() == 1458);
		assertTrue(payslip.getIncomeTax() == 0);
		assertTrue(payslip.getNetIncome() == 1458);
		assertTrue(payslip.getSuperannuation() == 131);
	}
	
	@Test
	public void testCalculate100000Payslip() throws DateValidationException{
		
		Employee employee = Employee.EmployeeBuilder().annualSalary(100000).build();
		Payslip payslip = payslipService.calculatePayslip(employee, 9, "01 March – 31 March");
		assertTrue(payslip.getGrossIncome() == 8333);
		assertTrue(payslip.getIncomeTax() == 2079);
		assertTrue(payslip.getNetIncome() == 6254);
		assertTrue(payslip.getSuperannuation() == 749);
	}

	@Test
	public void testCalculate200000Payslip() throws DateValidationException{
		
		Employee employee = Employee.EmployeeBuilder().annualSalary(200000).build();
		Payslip payslip = payslipService.calculatePayslip(employee, 9, "01 March – 31 March");
		assertTrue(payslip.getGrossIncome() == 16667);
		assertTrue(payslip.getIncomeTax() == 5296);
		assertTrue(payslip.getNetIncome() == 11371);
		assertTrue(payslip.getSuperannuation() == 1500);
	}
	
	@Test
	public void testCalculate1000000Payslip() throws DateValidationException{
		
		Employee employee = Employee.EmployeeBuilder().annualSalary(1000000).build();
		Payslip payslip = payslipService.calculatePayslip(employee, 9, "01 March – 31 March");
		assertTrue(payslip.getGrossIncome() == 83333);
		assertTrue(payslip.getIncomeTax() == 35296);
		assertTrue(payslip.getNetIncome() == 48037);
		assertTrue(payslip.getSuperannuation() == 7499);
	}

}
