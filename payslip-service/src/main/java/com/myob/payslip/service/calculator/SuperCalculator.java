package com.myob.payslip.service.calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.myob.payslip.model.Payslip;

@Component(Calculator.SUPER_CALCULATOR)
public class SuperCalculator implements Calculator {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void calculate(Payslip payslip) {
		logger.debug("calculating super");
		BigDecimal superannuation = new BigDecimal(payslip.getGrossIncome()).multiply(new BigDecimal(payslip.getSuperRatio()));
		payslip.setSuperannuation((superannuation.round(new MathContext(Integer.MAX_VALUE, RoundingMode.HALF_UP))).intValue());
		logger.debug("super for annual sallary {} is {}", payslip.getEmployee().getAnnualSalary(), payslip.getSuperannuation());

	}

}
