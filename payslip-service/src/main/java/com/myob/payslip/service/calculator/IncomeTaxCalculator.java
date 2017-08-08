package com.myob.payslip.service.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.myob.payslip.model.Payslip;
import com.myob.payslip.repository.TaxRate;
import com.myob.payslip.util.TaxRateCache;

@Component(Calculator.INCOME_TAX_CALCULATOR)
@Scope("prototype")
public class IncomeTaxCalculator implements Calculator {

	@Autowired
	private TaxRateCache taxRateCache;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void calculate(Payslip payslip) {
		logger.debug("calculating income tax");
		int annualSalary = payslip.getEmployee().getAnnualSalary();
		Comparator<TaxRate> c = Comparator.comparing(TaxRate::getAnumMin).reversed();
		TaxRate taxInfo = taxRateCache.getTaxRates().stream().filter(ti -> ti.getAnumMin() <= annualSalary).sorted(c).findFirst()
				.orElse(null);
		logger.debug("tax band for sallary {} is {}", payslip.getEmployee().getAnnualSalary(), taxInfo);
		int delta = annualSalary - taxInfo.getAnumMin();
		long annualTax = (long) (taxInfo.getBaseTax() + (taxInfo.getAdditionalTax() * delta));
		int incomeTax = new BigDecimal(annualTax).divide(new BigDecimal(12), RoundingMode.HALF_UP).intValue();
		payslip.setIncomeTax(incomeTax);
		logger.debug("income tax for annual sallary {} is {}", payslip.getEmployee().getAnnualSalary(), incomeTax);
	}


}
