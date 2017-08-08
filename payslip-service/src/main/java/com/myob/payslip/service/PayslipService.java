package com.myob.payslip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.myob.payslip.exception.DateValidationException;
import com.myob.payslip.factory.CalculatorFactory;
import com.myob.payslip.model.Employee;
import com.myob.payslip.model.Payslip;
import com.myob.payslip.service.calculator.Calculator;

@Service
@Scope("prototype")
public class PayslipService {

	@Autowired
	private PayPeriodParserService payPeriodParserService;

	@Autowired
	private CalculatorFactory calculatorFactory;

	public Payslip calculatePayslip(Employee employee, double superRatio, String paymentDateAsString)
			throws DateValidationException {
		Payslip payslip = Payslip.PayslipBuilder(employee, superRatio, paymentDateAsString).build();
		payPeriodParserService.parsePaymentDateAndCreateChilds(payslip);
		calculatorFactory.getCalculators().stream().forEach(calculator -> calculateWithIterraions(calculator, payslip));
		return payslip;
	}

	private void calculateWithIterraions(Calculator calculator, Payslip payslip) throws RuntimeException {
		try {
			calculator.calculate(payslip);
			if (payslip.hasNext()) {
				calculateWithIterraions(calculator, payslip.next());
			}
		} catch (DateValidationException e) {
			throw new RuntimeException(e);
		}
	}

	
}
