package com.myob.payslip.service.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.myob.payslip.model.Payslip;

@Component(Calculator.GROSS_INCOME_CALCULATOR)
public class GrossIncomeCalculator implements Calculator {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// Employee annual salary is 60,050, super rate is 9%, how much will this
	// employee be paid for the
	// 60,050 / 12 = 5,004.16666667 (round down) = 5,004
	@Override
	public void calculate(Payslip payslip) {
		logger.debug("calculating gross Income");
		int grossIncome = new BigDecimal(payslip.getEmployee().getAnnualSalary()).divide(new BigDecimal(12),RoundingMode.HALF_UP).intValue();
		payslip.setGrossIncome(grossIncome);
		logger.debug("gross income for annual sallary {} is {}",payslip.getEmployee().getAnnualSalary(), grossIncome);

	}


}
