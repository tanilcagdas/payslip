package com.myob.payslip.service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.myob.payslip.exception.DateValidationException;
import com.myob.payslip.model.PaymentDate;
import com.myob.payslip.model.Payslip;

@Service
public class PayPeriodParserService {

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	private static List<String> KEYS = Arrays.asList("-", "–");

	public void parsePaymentDateAndCreateChilds(Payslip payslip) throws DateValidationException {
		String paymentStr = payslip.getPaymentDateRequest();

		String splitterKey = null;
		for (String key : KEYS) {
			if (paymentStr.contains(key)) {
				splitterKey = key;
				break;
			}
		}
		if (splitterKey == null) {
			throw new DateValidationException("Couldnt find date splitter");
		}

		String[] splitArr = paymentStr.split(splitterKey);
		if(splitArr.length != 2){
			throw new DateValidationException("The should be 2 dates consist of start and end");
		}
		MonthDay startDate = validateStartDate(parseDate(splitArr[0].trim()));
		MonthDay endDate = validateEndDate(parseDate(splitArr[1].trim()));
		if (startDate.getMonth().getValue() > endDate.getMonth().getValue()) {
			throw new DateValidationException("End month cannot be smaller than start month");
		} else {
			payslip.setPaymentDate(createPaymentDate(startDate.getMonth().getValue()));
			if (!startDate.getMonth().equals(endDate.getMonth())) {
				createChildPayslip(payslip, endDate, startDate.getMonthValue());

			}
		}

	}

	private void createChildPayslip(Payslip payslip, MonthDay endDate, int monthIndex) {
		Payslip newPayslip;
		try {
			newPayslip = (Payslip) payslip.clone();
			monthIndex++;
			newPayslip.setPaymentDate(createPaymentDate(monthIndex));
			payslip.setNext(newPayslip);
			if (endDate.getMonthValue() > monthIndex) {
				createChildPayslip(newPayslip, endDate, monthIndex);
			}
		} catch (CloneNotSupportedException e) {
			logger.error(e.getMessage(),e);
		}

	}

	PaymentDate createPaymentDate(int monthInt) {
		Month month = Month.of(monthInt);
		LocalDate startDate = LocalDate.of(Year.now().getValue(), month, 1);
		LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());
		String monthAsStr = month.getDisplayName(TextStyle.FULL, Locale.US);
		String dateAsString = new StringBuilder("01 ").append(monthAsStr).append(" - ").append(endDate.getDayOfMonth())
				.append(" ").append(monthAsStr).toString();

		return new PaymentDate(startDate, endDate, dateAsString);
	}

	private MonthDay validateEndDate(MonthDay monthDay) throws DateValidationException {
		LocalDate date = LocalDate.of(Year.now().getValue(), monthDay.getMonth(), monthDay.getDayOfMonth());
		LocalDate lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());
		if (date.equals(lastDayOfMonth)) {
			return monthDay;
		} else {
			throw new DateValidationException("End date is not valid");
		}
	}

	private MonthDay validateStartDate(MonthDay monthDay) throws DateValidationException {
		LocalDate date = LocalDate.of(Year.now().getValue(), monthDay.getMonth(), monthDay.getDayOfMonth());
		LocalDate firstDayOfMonth = date.with(TemporalAdjusters.firstDayOfMonth());
		if (date.equals(firstDayOfMonth)) {
			return monthDay;
		} else {
			throw new DateValidationException("Start date is not valid");
		}
	}

	MonthDay parseDate(String dateStr) throws DateValidationException {
		int day = 0;
		Month month = null;
		String[] strSplit = dateStr.split(" ");
		assert (strSplit.length == 2);
		try {
			day = Integer.parseInt(strSplit[0].trim());
		} catch (NumberFormatException e) {
			throw new DateValidationException("Cannot parse the day");
		}
		try {
			month = Month.valueOf(strSplit[1].toUpperCase());
		} catch (NumberFormatException e) {
			throw new DateValidationException("Cannot parse the month");
		} catch(IllegalArgumentException e){
			throw new DateValidationException("Cannot parse the month : " +e.getMessage());
			
		}
		try {
			return MonthDay.of(month, day);
		} catch (DateTimeException e) {
			throw new DateValidationException("Cannot parse the month or day : " + e.getMessage());
		}
		
	}

}
