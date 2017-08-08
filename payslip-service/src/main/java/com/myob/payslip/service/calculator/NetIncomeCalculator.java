package com.myob.payslip.service.calculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.myob.payslip.model.Payslip;

@Component(Calculator.NET_INCOME_CALCULATOR)
public class NetIncomeCalculator implements Calculator {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
//	net income = 5,004 - 922 = 4,082

	@Override
	public void calculate(Payslip payslip) {
		logger.debug("calculating net income");
		payslip.setNetIncome(payslip.getGrossIncome() - payslip.getIncomeTax());
		logger.debug("net income for annual sallary {} is {}",payslip.getEmployee().getAnnualSalary(), payslip.getNetIncome());

	}

}
