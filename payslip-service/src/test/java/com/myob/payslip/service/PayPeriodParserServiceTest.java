package com.myob.payslip.service;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.myob.payslip.PayslipServiceApplicationTests;
import com.myob.payslip.exception.DateValidationException;
import com.myob.payslip.model.Employee;
import com.myob.payslip.model.PaymentDate;
import com.myob.payslip.model.Payslip;

public class PayPeriodParserServiceTest extends PayslipServiceApplicationTests {
	
	@Autowired
	private PayPeriodParserService payPeriodParserService;

	@Test
	public void testCalculate() {
	}

	@Test
	public void testParsePaymentDate() throws DateValidationException{
		Payslip payslip = Payslip.PayslipBuilder(Employee.EmployeeBuilder().build(), 9, "01 March - 31 March").build();
		payPeriodParserService.parsePaymentDateAndCreateChilds(payslip);
	}
	
	@Test(expected = DateValidationException.class)
	public void testParsePaymentDate01March31January() throws DateValidationException{
		Payslip payslip = Payslip.PayslipBuilder(Employee.EmployeeBuilder().build(), 9, "01 March - 31 January").build();
		payPeriodParserService.parsePaymentDateAndCreateChilds(payslip);
	}
	
	@Test
	public void testParsePaymentDate3Motnhs() throws DateValidationException{
		Payslip payslip = Payslip.PayslipBuilder(Employee.EmployeeBuilder().build(), 9, "01 March - 31 May").build();
		payPeriodParserService.parsePaymentDateAndCreateChilds(payslip);
		assertTrue(payslip.hasNext());
		assertTrue(payslip.next().hasNext());
		assertTrue(!payslip.next().next().hasNext());
		assertTrue(payslip.getPaymentDate().getDateAsString().equals("01 March - 31 March"));
		assertTrue(payslip.next().getPaymentDate().getDateAsString().equals("01 April - 30 April"));
		assertTrue(payslip.next().next().getPaymentDate().getDateAsString().equals("01 May - 31 May"));
	}
	
	@Test
	public void testParseDate() throws DateValidationException {
		MonthDay md = payPeriodParserService.parseDate("01 March");
		assertTrue(md.equals(MonthDay.of(Month.MARCH, 1)));
	}

	@Test(expected = DateValidationException.class)
	public void testParseDate01Marc() throws DateValidationException {
		payPeriodParserService.parseDate("01 Marc");
	}
	
	@Test(expected = DateValidationException.class)
	public void testParseDate31February() throws DateValidationException {
		payPeriodParserService.parseDate("31 February");
	}


	@Test
	public void testcreatePaymentDate() throws DateValidationException {
		PaymentDate paymentDate = payPeriodParserService.createPaymentDate(1);
		assertTrue(paymentDate.getStartDate().equals(LocalDate.of(Year.now().getValue(), Month.JANUARY, 1)));
		assertTrue(paymentDate.getEndDate().equals(LocalDate.of(Year.now().getValue(), Month.JANUARY, 31)));
	}

}
