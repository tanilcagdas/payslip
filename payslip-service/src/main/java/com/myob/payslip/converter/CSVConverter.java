package com.myob.payslip.converter;

import org.springframework.stereotype.Component;

import com.myob.payslip.model.Payslip;

@Component
public class CSVConverter {
	
	private String SINGLE_SPACE = " ";

	private static final String CSV_HEADERS = "NAME\tPAY_PERIOD\tGROSS_INCOME\tINCOME_TAX\tNET_INCOME\tSUPER\n";

	private static final String SEPERATOR = "\t";
	
	public static final String NEXT_LINE = "\n";

	public String convert(Payslip payslip) {
		StringBuilder csvBuilder = new StringBuilder(CSV_HEADERS);
		fillCsv(csvBuilder, payslip);
		return csvBuilder.toString();
	}
	

	private void fillCsv(StringBuilder csvBuilder, Payslip payslip) {
		csvBuilder.append(payslip.getEmployee().getFirstName()).append(SINGLE_SPACE)
				.append(payslip.getEmployee().getLastName()).append(SEPERATOR)
				.append(payslip.getPaymentDate().getDateAsString()).append(SEPERATOR).append(payslip.getGrossIncome())
				.append(SEPERATOR).append(payslip.getIncomeTax()).append(SEPERATOR).append(payslip.getNetIncome())
				.append(SEPERATOR).append(payslip.getSuperannuation()).append(NEXT_LINE).toString();
		if (payslip.hasNext()) {
			fillCsv(csvBuilder, payslip.next());
		}

	}


}
