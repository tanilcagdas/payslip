package com.myob.payslip.factory;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.myob.payslip.service.calculator.Calculator;

@Service
public class CalculatorFactory {
	

	
	@Autowired
	@Qualifier(Calculator.GROSS_INCOME_CALCULATOR)
	private Calculator grossIncomeCalculator;
	
	@Autowired
	@Qualifier(Calculator.INCOME_TAX_CALCULATOR)
	private Calculator incomeTaxCalculator;
	
	@Autowired
	@Qualifier(Calculator.NET_INCOME_CALCULATOR)
	private Calculator netIncomeCalculator;
	
	@Autowired
	@Qualifier(Calculator.SUPER_CALCULATOR)
	private Calculator superCalculator;

	private List<Calculator> calculators;

	@PostConstruct
	private void registerCalculators() {
		calculators = new ArrayList<>();
		calculators.add(grossIncomeCalculator);
		calculators.add(incomeTaxCalculator);
		calculators.add(netIncomeCalculator);
		calculators.add(superCalculator);
	}

	public List<Calculator> getCalculators() {
		return calculators;
	}

	public void setCalculators(List<Calculator> calculators) {
		this.calculators = calculators;
	}
}
